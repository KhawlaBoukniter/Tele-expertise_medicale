<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajouter un patient</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px auto;
            max-width: 600px;
            background-color: #f9f9f9;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        h2 {
            text-align: center;
            color: #333;
        }
        label {
            font-weight: bold;
            display: block;
            margin-top: 10px;
        }
        input[type="text"],
        input[type="datetime-local"],
        textarea {
            width: 100%;
            padding: 8px;
            margin-top: 4px;
            border: 1px solid #ccc;
            border-radius: 6px;
            box-sizing: border-box;
        }
        input[type="checkbox"] {
            margin-top: 8px;
        }
        button {
            margin-top: 20px;
            width: 100%;
            background-color: #4CAF50;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<h2>Ajouter un patient</h2>

<form action="patient" method="post">
    <input type="hidden" name="action" value="add"/>

    <label for="cin">CIN :</label>
    <input type="text" name="cin" required><br>

    <label for="nom">Nom :</label>
    <input type="text" name="nom" required><br>

    <label for="prenom">Prénom :</label>
    <input type="text" name="prenom" required><br>

    <label for="antecedents">Antécédents :</label>
    <textarea name="antecedents"></textarea><br>

    <label for="allergies">Allergies :</label>
    <textarea name="allergies"></textarea><br>

    <label for="traitements">Traitements :</label>
    <textarea name="traitements"></textarea><br>

    <label for="coordonnees">Coordonnées :</label>
    <textarea name="coordonnees"></textarea><br>

    <label for="mutuelle">Mutuelle :</label>
    <input type="checkbox" name="mutuelle"><br>

    <label for="numero_securite_sociale">Numéro de sécurité sociale :</label>
    <input type="text" name="numero_securite_sociale"><br><br>

    <h3>Signes vitaux à l’admission</h3>

    <label for="tension_arterielle">Tension artérielle :</label>
    <input type="text" name="tension_arterielle" required><br>

    <label for="temperature_corporelle">Température corporelle (°C) :</label>
    <input type="number" step="0.1" name="temperature_corporelle" required><br>

    <label for="frequence_cardiaque">Fréquence cardiaque (bpm) :</label>
    <input type="number" name="frequence_cardiaque" required><br>

    <label for="frequence_respiratoire">Fréquence respiratoire (bpm) :</label>
    <input type="number" name="frequence_respiratoire" required><br>

    <label for="poids">Poids (Kg) :</label>
    <input type="number" name="poids"><br>

    <label for="taille">Taille (cm) :</label>
    <input type="number" name="taille"><br>

    <button type="submit">Enregistrer</button>
</form>

</body>
</html>
