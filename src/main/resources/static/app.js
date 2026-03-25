// app.js - EvalSys SPA Controller

document.addEventListener('DOMContentLoaded', () => {
    initTabs();
    initForms();
    loadAllData();
});

// State
let appData = {
    candidats: [],
    correcteurs: [],
    matieres: [],
    parametres: [],
    notes: [],
    operateurs: [],
    resolutions: []
};

// --- Initialization ---

function initTabs() {
    const tabBtns = document.querySelectorAll('.tab-btn');
    tabBtns.forEach(btn => {
        btn.addEventListener('click', () => {
            const target = btn.dataset.target;

            // UI Toggle
            document.querySelectorAll('.tab-btn').forEach(b => b.classList.remove('active'));
            document.querySelectorAll('.tab-content').forEach(c => c.classList.remove('active'));

            btn.classList.add('active');
            document.getElementById(target).classList.add('active');

            // Data Refresh
            refreshTab(target);
        });
    });
}

function initForms() {
    document.getElementById('data-form').addEventListener('submit', handleFormSubmit);
}

async function loadAllData() {
    try {
        const [cand, corr, mat, param, note, ops, res] = await Promise.all([
            fetch('/api/candidats').then(r => r.json()),
            fetch('/api/correcteurs').then(r => r.json()),
            fetch('/api/matieres').then(r => r.json()),
            fetch('/api/parametres').then(r => r.json()),
            fetch('/api/notes').then(r => r.json()),
            fetch('/api/operateurs').then(r => r.json()),
            fetch('/api/resolutions').then(r => r.json())
        ]);

        appData = { candidats: cand, correcteurs: corr, matieres: mat, parametres: param, notes: note, operateurs: ops, resolutions: res };

        // Initial Render
        refreshTab('candidats');
        populateSelects();
    } catch (err) {
        console.error("Error loading data:", err);
    }
}

// --- Data Refresh & Rendering ---

async function refreshTab(tab) {
    if (tab === 'resultats') {
        renderResultats();
        return;
    }

    try {
        const response = await fetch(`/api/${tab}`);
        const data = await response.json();
        appData[tab] = data;

        if (tab === 'candidats' || tab === 'correcteurs' || tab === 'matieres') {
            renderTable(tab, data);
        } else {
            renderCards(tab, data);
        }
    } catch (err) {
        console.error(`Error refreshing ${tab}:`, err);
    }
}

function renderTable(type, data) {
    const tbody = document.getElementById(`${type}-tbody`);
    tbody.innerHTML = '';

    data.forEach(item => {
        const tr = document.createElement('tr');

        let cols = `<td>#${item.id}</td>`;
        if (type === 'candidats') {
            cols += `<td>${item.nom}</td><td>${item.prenom}</td><td>${item.matricule}</td>`;
        } else if (type === 'correcteurs') {
            cols += `<td>${item.nom}</td><td>${item.prenom}</td>`;
        } else if (type === 'matieres') {
            cols += `<td>${item.nom}</td>`;
        }

        cols += `
            <td style="text-align: right;">
                <button class="btn-icon" onclick="editItem('${type}', ${item.id})"><i class="fas fa-edit"></i></button>
                <button class="btn-icon delete" onclick="deleteItem('${type}', ${item.id})"><i class="fas fa-trash"></i></button>
            </td>
        `;
        tr.innerHTML = cols;
        tbody.appendChild(tr);
    });
}

function renderCards(type, data) {
    const container = document.getElementById(`${type}-cards-container`);
    container.innerHTML = '';

    data.forEach(item => {
        const card = document.createElement('div');
        card.className = (type === 'notes' ? 'param-card' : 'glass-card param-card');

        let content = '';
        if (type === 'parametres') {
            content = `
                <div class="card-title">
                    <span>${item.matiere ? item.matiere.nom : 'Matière'}</span>
                    <div style="font-size: 0.8rem; color: var(--primary)">#${item.id}</div>
                </div>
                <div class="card-row"><span class="card-label">Règle:</span><span class="card-value">${item.operateur ? item.operateur.operateur : ''} ${item.seuil}</span></div>
                <div class="card-row"><span class="card-label">Résolution:</span><span class="card-value">${item.resolution ? item.resolution.nom : ''}</span></div>
            `;
        } else if (type === 'notes') {
            content = `
                <div class="card-title">
                    <span>${item.candidat ? item.candidat.nom : 'Candidat'}</span>
                    <div style="font-size: 0.8rem; color: var(--secondary)">#${item.id}</div>
                </div>
                <div class="card-row"><span class="card-label">Matière:</span><span class="card-value">${item.matiere ? item.matiere.nom : ''}</span></div>
                <div class="card-row"><span class="card-label">Correcteur:</span><span class="card-value">${item.correcteur ? item.correcteur.nom : ''}</span></div>
                <div class="card-row"><span class="card-label">Note:</span><span class="card-value" style="color: var(--primary); font-size: 1.2rem;">${item.note}</span></div>
            `;
        }

        content += `
            <div style="margin-top: 15px; display: flex; justify-content: flex-end; gap: 8px;">
                <button class="btn-icon" onclick="editItem('${type}', ${item.id})"><i class="fas fa-edit"></i></button>
                <button class="btn-icon delete" onclick="deleteItem('${type}', ${item.id})"><i class="fas fa-trash"></i></button>
            </div>
        `;
        card.innerHTML = content;
        container.appendChild(card);
    });
}

async function renderResultats() {
    const container = document.getElementById('resultats-cards-container');
    container.innerHTML = '<p style="color: var(--text-muted); padding: 20px;">Calcul des synthèses en cours...</p>';

    try {
        const res = await fetch('/api/resultats');
        const data = await res.json();
        container.innerHTML = '';

        data.forEach(item => {
            const card = document.createElement('div');
            card.className = 'result-card';
            card.innerHTML = `
                <div class="card-title">
                    <span style="color: white;">${item.nomCandidat} ${item.prenomCandidat}</span>
                    <div class="badge ${item.statusGlobal === 'ADMIS' ? 'badge-success' : 'badge-danger'}">${item.statusGlobal}</div>
                </div>
                <div class="card-row"><span class="card-label">Matricule:</span><span class="card-value">${item.matricule}</span></div>
                <div class="card-row"><span class="card-label">Reliquat Global:</span><span class="card-value">${item.reliquatGlobal.toFixed(2)}</span></div>
                <button class="btn-primary" style="width: 100%; margin-top: 15px; padding: 10px;" onclick="showNoteDetails(${JSON.stringify(item).replace(/"/g, '&quot;')})">Détails Expertise</button>
            `;
            container.appendChild(card);
        });
    } catch (err) {
        container.innerHTML = '<p style="color: var(--danger);">Erreur lors du chargement des résultats.</p>';
        console.error("Result error:", err);
    }
}

// --- CRUD Actions ---

function showAddForm(type) {
    resetForm();
    document.getElementById('form-type').value = type;
    document.getElementById('modal-title').innerText = `Ajouter ${type.slice(0, -1)}`;
    openModal(type);
}

function editItem(type, id) {
    resetForm();
    const item = appData[type].find(i => i.id === id);
    if (!item) return;

    document.getElementById('form-id').value = item.id;
    document.getElementById('form-type').value = type;
    document.getElementById('modal-title').innerText = `Modifier ${type.slice(0, -1)}`;

    if (type === 'candidats' || type === 'correcteurs') {
        document.getElementById('form-nom').value = item.nom;
        document.getElementById('form-prenom').value = item.prenom;
        if (type === 'candidats') document.getElementById('form-matricule').value = item.matricule;
    } else if (type === 'matieres') {
        document.getElementById('form-nom').value = item.nom;
    } else if (type === 'parametres') {
        document.getElementById('form-matiere').value = item.matiere.id;
        document.getElementById('form-operateur').value = item.operateur.id;
        document.getElementById('form-resolution').value = item.resolution.id;
        document.getElementById('form-seuil').value = item.seuil;
    } else if (type === 'notes') {
        document.getElementById('form-candidat').value = item.candidat.id;
        document.getElementById('form-matiere').value = item.matiere.id;
        document.getElementById('form-correcteur').value = item.correcteur.id;
        document.getElementById('form-note').value = item.note;
    }

    openModal(type);
}

async function deleteItem(type, id) {
    if (!confirm(`Confirmer la suppression ?`)) return;
    try {
        await fetch(`/api/${type}/${id}`, { method: 'DELETE' });
        refreshTab(type);
    } catch (err) {
        alert("Erreur lors de la suppression");
    }
}

async function handleFormSubmit(e) {
    e.preventDefault();
    const type = document.getElementById('form-type').value;
    const id = document.getElementById('form-id').value;

    let payload = {};
    if (id) payload.id = parseInt(id);

    if (type === 'candidats' || type === 'correcteurs') {
        payload.nom = document.getElementById('form-nom').value;
        payload.prenom = document.getElementById('form-prenom').value;
        if (type === 'candidats') payload.matricule = document.getElementById('form-matricule').value;
    } else if (type === 'matieres') {
        payload.nom = document.getElementById('form-nom').value;
    } else if (type === 'parametres') {
        payload.matiere = { id: parseInt(document.getElementById('form-matiere').value) };
        payload.operateur = { id: parseInt(document.getElementById('form-operateur').value) };
        payload.resolution = { id: parseInt(document.getElementById('form-resolution').value) };
        payload.seuil = parseFloat(document.getElementById('form-seuil').value);
    } else if (type === 'notes') {
        payload.candidat = { id: parseInt(document.getElementById('form-candidat').value) };
        payload.matiere = { id: parseInt(document.getElementById('form-matiere').value) };
        payload.correcteur = { id: parseInt(document.getElementById('form-correcteur').value) };
        payload.note = parseFloat(document.getElementById('form-note').value);
    }

    try {
        await fetch(`/api/${type}`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
        });
        closeModal();
        refreshTab(type);
    } catch (err) {
        alert("Erreur lors de l'enregistrement");
    }
}

// --- Modals & Helpers ---

function openModal(type) {
    const modal = document.getElementById('modal');
    modal.style.display = 'flex';

    // Hide all groups first
    ['nom', 'prenom', 'matricule', 'candidat', 'matiere', 'correcteur', 'operateur', 'resolution', 'seuil', 'note'].forEach(g => {
        document.getElementById(`${g}-group`).style.display = 'none';
        const input = document.getElementById(`form-${g}`);
        if (input) input.required = false;
    });

    // Show relevant groups
    if (type === 'candidats') {
        ['nom', 'prenom', 'matricule'].forEach(g => showGroup(g));
    } else if (type === 'correcteurs') {
        ['nom', 'prenom'].forEach(g => showGroup(g));
    } else if (type === 'matieres') {
        showGroup('nom');
    } else if (type === 'parametres') {
        ['matiere', 'operateur', 'resolution', 'seuil'].forEach(g => showGroup(g));
    } else if (type === 'notes') {
        ['candidat', 'matiere', 'correcteur', 'note'].forEach(g => showGroup(g));
    }
}

function showGroup(id) {
    document.getElementById(`${id}-group`).style.display = 'block';
    const input = document.getElementById(`form-${id}`);
    if (input) input.required = true;
}

window.closeModal = function () {
    document.getElementById('modal').style.display = 'none';
};

window.closeNotesModal = function () {
    document.getElementById('notes-modal').style.display = 'none';
};

function resetForm() {
    document.getElementById('data-form').reset();
    document.getElementById('form-id').value = '';
}

function populateSelects() {
    fillSelect('form-candidat', appData.candidats, c => `${c.nom} ${c.prenom}`);
    fillSelect('form-matiere', appData.matieres, m => m.nom);
    fillSelect('form-correcteur', appData.correcteurs, c => `${c.nom} ${c.prenom}`);
    fillSelect('form-operateur', appData.operateurs, o => o.operateur);
    fillSelect('form-resolution', appData.resolutions, r => r.nom);
}

function fillSelect(id, data, labelFn) {
    const s = document.getElementById(id);
    s.innerHTML = '';
    data.forEach(item => {
        const opt = document.createElement('option');
        opt.value = item.id;
        opt.innerText = labelFn(item);
        s.appendChild(opt);
    });
}

// Result Details Modal
window.showNoteDetails = function (item) {
    document.getElementById('notes-modal-title').innerText = `Expertise: ${item.nomCandidat}`;
    const tbody = document.getElementById('candidat-notes-detail-tbody');
    tbody.innerHTML = '';

    item.details.forEach(d => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${d.nomMatiere}</td>
            <td style="font-weight:700">${d.noteCalculee.toFixed(2)}</td>
            <td>${d.operateur} ${d.seuil}</td>
            <td><span class="badge ${d.status === 'Admis' ? 'badge-success' : 'badge-danger'}">${d.status}</span></td>
        `;
        tbody.appendChild(tr);
    });

    document.getElementById('notes-modal').style.display = 'flex';
};
