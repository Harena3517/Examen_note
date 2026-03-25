# Guide Git pour le projet Correction Bacc

Ce projet doit être géré avec deux branches principales : `Script` et `Tout_code`.

## Stratégie de Branches

1.  **Branche `Script`** : Contient uniquement les scripts SQL (`temp_sql`).
2.  **Branche `Tout_code`** : Contient l'intégralité du projet Spring Boot ainsi que les scripts SQL.

## Workflow recommandé

### 1. Initialisation
```bash
git init
git checkout -b Tout_code
git add .
git commit -m "Initial commit: Spring Boot project structure"
```

### 2. Création de la branche Script
```bash
git checkout -b Script
# On ne garde que le dossier temp_sql si on veut être strict, 
# mais souvent on filtre ou on travaille juste sur les fichiers SQL.
```

### 3. Développement et Pull Request
Avant de fusionner (`Merge`), assurez-vous que le code fonctionne localement :
1.  Testez les CRUD.
2.  Vérifiez les calculs de notes.

Pour fusionner `Script` dans `Tout_code` (si des modifications SQL ont été faites) :
```bash
git checkout Tout_code
git merge Script
```

### 4. Déploiement vers Git
```bash
git remote add origin <URL_DU_REPO_DU_PROF>
git push -u origin Tout_code
git push -u origin Script
```

> [!IMPORTANT]
> Ne jamais faire de `Pull Request` ou `Merge` sans avoir testé le projet complet sur la branche `Tout_code`.
