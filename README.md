# Guide d'installation et d'intégration du projet

## 1. Installer PostgreSQL

### Lien de téléchargement PostgreSQL
[Télécharger PostgreSQL](https://www.postgresql.org/download/)

### Installation de PostgreSQL
- Pendant l’installation, configurez un mot de passe pour l’utilisateur `postgres`. Notez ce mot de passe, car il sera nécessaire pour se connecter.

### Accéder à PostgreSQL
- Installez un outil graphique comme **pgAdmin** (inclus dans l'installation) ou utilisez un terminal pour interagir avec PostgreSQL.

---

## 2. Créer une base de données

### Avec pgAdmin
1. Ouvrez pgAdmin.
2. Connectez-vous avec l’utilisateur `postgres` et le mot de passe que vous avez défini.
3. Faites un clic droit sur "Databases" > "Create" > "Database".
4. Donnez un nom à la base de données, par exemple : `bookstore_db`.

---

## 3. Exécution du backend

### Commande pour démarrer le serveur
Exécutez le backend avec la commande suivante :
```bash
./gradlew server:bootRun
```
Cela démarre le serveur backend.

---

## 4. Structure du frontend

### Structure des fichiers
```
pages/
  index.js       # Page d'accueil pour afficher les livres
  add-book.js    # Formulaire pour ajouter un livre
```

---

## 5. Intégration avec le backend

### Intégration dans `index.js`
```javascript
import axios from "axios";
import { useEffect, useState } from "react";

export default function Home() {
    const [books, setBooks] = useState([]);

    useEffect(() => {
        axios.get("http://localhost:8080/api/books")
            .then(response => setBooks(response.data))
            .catch(error => console.error(error));
    }, []);

    return (
        <div>
            <h1>Liste des livres</h1>
            <ul>
                {books.map(book => (
                    <li key={book.id}>
                        <h3>{book.title}</h3>
                        <p>Auteur : {book.author}</p>
                        <p>Catégorie : {book.category}</p>
                    </li>
                ))}
            </ul>
        </div>
    );
}
```

### Intégration dans `add-book.js`

```javascript
import { useState } from "react";
import axios from "axios";

export default function AddBook() {
    const [formData, setFormData] = useState({
        title: "",
        author: "",
        category: "",
        filePath: "",
        publicationDate: "",
    });

    const handleSubmit = (e) => {
        e.preventDefault();
        axios.post("http://localhost:8080/api/books", formData)
            .then(() => alert("Livre ajouté avec succès"))
            .catch(error => console.error(error));
    };

    return (
        <form onSubmit={handleSubmit}>
            <input type="text" placeholder="Titre" onChange={(e) => setFormData({ ...formData, title: e.target.value })} />
            <input type="text" placeholder="Auteur" onChange={(e) => setFormData({ ...formData, author: e.target.value })} />
            <input type="text" placeholder="Catégorie" onChange={(e) => setFormData({ ...formData, category: e.target.value })} />
            <input type="date" onChange={(e) => setFormData({ ...formData, publicationDate: e.target.value })} />
            <input type="text" placeholder="Chemin du fichier" onChange={(e) => setFormData({ ...formData, filePath: e.target.value })} />
            <button type="submit">Ajouter</button>
        </form>
    );
}
# espace-livre_backend
