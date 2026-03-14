package com.exemple.candidat.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemple.candidat.dto.GlobalResultDTO;
import com.exemple.candidat.dto.ResultatDTO;
import com.exemple.candidat.model.Candidat;
import com.exemple.candidat.model.Matiere;
import com.exemple.candidat.model.Note;
import com.exemple.candidat.model.Parametre;
import com.exemple.candidat.repository.ParametreRepository;

@Service
public class CalculResultatService {

    @Autowired
    private NoteService noteService;

    @Autowired
    private ParametreRepository parametreRepository;

    public List<GlobalResultDTO> calculerTousLesResultats() {
        List<Note> allNotes = noteService.getAll();
        Map<Candidat, List<Note>> notesParCandidat = allNotes.stream()
                .collect(Collectors.groupingBy(Note::getCandidat));

        List<GlobalResultDTO> globals = new ArrayList<>();
        for (Map.Entry<Candidat, List<Note>> entry : notesParCandidat.entrySet()) {
            globals.add(calculerResultatCandidat(entry.getKey(), entry.getValue()));
        }
        return globals;
    }

    public GlobalResultDTO getResultatCandidat(Integer candidatId) {
        List<Note> notes = noteService.getByCandidatId(candidatId);
        if (notes.isEmpty())
            return null;
        return calculerResultatCandidat(notes.get(0).getCandidat(), notes);
    }

    private GlobalResultDTO calculerResultatCandidat(Candidat candidat, List<Note> notes) {
        GlobalResultDTO global = new GlobalResultDTO();
        global.setNomCandidat(candidat.getNom());
        global.setPrenomCandidat(candidat.getPrenom());
        global.setMatricule(candidat.getMatricule());

        Map<Matiere, List<Note>> notesParMatiere = notes.stream()
                .collect(Collectors.groupingBy(Note::getMatiere));

        List<ResultatDTO> details = new ArrayList<>();
        double totalNotes = 0.0;
        int matiereCount = 0;

        for (Map.Entry<Matiere, List<Note>> entry : notesParMatiere.entrySet()) {
            Matiere matiere = entry.getKey();
            List<Note> notesMatiere = entry.getValue();

            List<Parametre> params = parametreRepository.findByMatiereId(matiere.getId());
            if (params.isEmpty())
                continue;

            // ✅ Étape 1 : somme de toutes les différences entre chaque paire de notes
            double diff = calculerSommeDifferences(notesMatiere);

            // ✅ Étape 2 : choisir le paramètre qui correspond à cette différence
            Parametre selectedParam = choisirParametre(params, diff);
            if (selectedParam == null)
                continue;
            if (selectedParam.getResolution() == null || selectedParam.getOperateur() == null)
                continue;

            // ✅ Étape 3 : appliquer la résolution → min, max ou moyenne
            double noteFinale = appliquerResolution(notesMatiere, selectedParam.getResolution().getNom());

            // ✅ Étape 4 : reliquat et statut
            double reliquat = Math.round((noteFinale - 10.0) * 100.0) / 100.0;
            boolean admis = noteFinale >= 10.0;

            ResultatDTO dto = new ResultatDTO();
            dto.setNomCandidat(candidat.getNom());
            dto.setPrenomCandidat(candidat.getPrenom());
            dto.setMatricule(candidat.getMatricule());
            dto.setNomMatiere(matiere.getNom());
            dto.setDiff(Math.round(diff * 100.0) / 100.0);
            dto.setSeuil(selectedParam.getSeuil());
            dto.setOperateur(selectedParam.getOperateur().getOperateur());
            dto.setResolution(selectedParam.getResolution().getNom());
            dto.setNoteCalculee(Math.round(noteFinale * 100.0) / 100.0);
            dto.setReliquat(reliquat);
            dto.setStatus(admis ? "Admis" : "Ajourné");

            details.add(dto);
            totalNotes += noteFinale;
            matiereCount++;
        }

        double moyenneGenerale = (matiereCount > 0) ? (totalNotes / matiereCount) : 0.0;
        global.setDetails(details);
        global.setMoyenneGenerale(Math.round(moyenneGenerale * 100.0) / 100.0);
        global.setReliquatGlobal(Math.round((moyenneGenerale - 10.0) * 100.0) / 100.0);
        global.setStatusGlobal(moyenneGenerale >= 10.0 ? "ADMIS" : "AJOURNÉ");

        return global;
    }

    /**
     * Étape 1 : somme de toutes les différences entre chaque paire de notes.
     * Ex: [13, 12, 8] → |13-12| + |13-8| + |12-8| = 1 + 5 + 4 = 10
     */
    private double calculerSommeDifferences(List<Note> notes) {
        if (notes.size() < 2)
            return 0.0;
        double sum = 0.0;
        for (int i = 0; i < notes.size(); i++) {
            for (int j = i + 1; j < notes.size(); j++) {
                sum += Math.abs(notes.get(i).getNote() - notes.get(j).getNote());
            }
        }
        return sum;
    }

    /**
     * Étape 2 : choisir le paramètre dont la condition correspond à la diff
     * calculée.
     * Si plusieurs matchent, on prend celui dont le seuil est le plus proche de la
     * diff.
     * Si aucun ne matche, fallback sur le plus proche en distance.
     */
    private Parametre choisirParametre(List<Parametre> params, double diff) {
        Parametre meilleur = null;
        double distanceMin = Double.MAX_VALUE;

        for (Parametre p : params) {
            if (p.getOperateur() == null)
                continue;
            String op = p.getOperateur().getOperateur();
            if (verifierSeuil(diff, p.getSeuil(), op)) {
                double distance = Math.abs(diff - p.getSeuil());
                if (distance < distanceMin) {
                    distanceMin = distance;
                    meilleur = p;
                }
            }
        }

        // Fallback : aucun paramètre ne matche → prendre le seuil le plus proche
        if (meilleur == null) {
            for (Parametre p : params) {
                double distance = Math.abs(diff - p.getSeuil());
                if (distance < distanceMin) {
                    distanceMin = distance;
                    meilleur = p;
                }
            }
        }

        return meilleur;
    }

    /**
     * Étape 3 : appliquer la résolution sur les notes des correcteurs.
     * "Petit" → note la plus basse (min)
     * "Grand" → note la plus haute (max)
     * "Moyenne" → moyenne de toutes les notes
     */
    private double appliquerResolution(List<Note> notes, String nomResolution) {
        if (notes.isEmpty())
            return 0.0;
        return switch (nomResolution.trim().toLowerCase()) {
            case "petit" -> notes.stream().mapToDouble(Note::getNote).min().orElse(0.0);
            case "grand" -> notes.stream().mapToDouble(Note::getNote).max().orElse(0.0);
            case "moyenne" -> notes.stream().mapToDouble(Note::getNote).average().orElse(0.0);
            default -> notes.stream().mapToDouble(Note::getNote).average().orElse(0.0);
        };
    }

    /**
     * Vérifie si la diff calculée respecte la condition du paramètre.
     * Math.abs pour == afin d'éviter les erreurs de précision float.
     */
    private boolean verifierSeuil(double val, double seuil, String operateur) {
        return switch (operateur) {
            case ">" -> val > seuil;
            case "<" -> val < seuil;
            case "<=" -> val <= seuil;
            case ">=" -> val >= seuil;
            case "==" -> Math.abs(val - seuil) < 1e-9;
            default -> false;
        };
    }
}
