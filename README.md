Voici un exemple de fichier README pour votre projet Android Studio :

# Projet Android - Validation NFC

Ce projet Android permet de valider un code et de scanner des tags NFC. Il utilise Kotlin pour le développement backend et XML pour la définition des interfaces utilisateur.

## Fonctionnalités

1. **Validation de code** : Saisie d'un code et validation via un bouton.
2. **Scan NFC** : Lecture des données NFC avec une interface utilisateur simple.

## Structure du projet

### Fichiers principaux

- **MainActivity.kt** : Gestion de l'activité principale, y compris la validation du code.
- **NfcScanActivity.kt** : Gestion de l'activité de scan NFC.
- **ApiService.kt** : Service pour les appels API.
- **JwtResponse.kt** : Modèle de réponse pour le JWT.
- **ValidationResponse.kt** : Modèle de réponse pour la validation.

### Layouts XML

- **activity_main.xml** : Interface utilisateur pour l'activité principale avec un champ de saisie de code et un bouton de validation.
- **activity_nfc_scan.xml** : Interface utilisateur pour l'activité de scan NFC avec une TextView pour afficher les données NFC.
- **rounded_border.xml** : Définition des bordures arrondies pour la mise en forme des éléments UI.

## Configuration requise

- Android Studio 4.1 ou plus récent
- SDK Android 21 ou supérieur

## Instructions d'installation

1. Clonez le dépôt.
   ```bash
   git clone <URL_DU_DEPOT>
   ```
2. Ouvrez le projet avec Android Studio.
3. Synchronisez les dépendances du projet.
4. Exécutez l'application sur un émulateur ou un appareil Android physique.

## Utilisation

1. Lancez l'application.
2. Pour valider un code, entrez le code dans le champ prévu et appuyez sur le bouton "Validate".
3. Pour scanner un tag NFC, accédez à l'écran de scan NFC et approchez un tag NFC compatible de l'appareil.

## Contributions

Les contributions sont les bienvenues ! Veuillez soumettre une pull request pour toute amélioration ou correction.

## Licence

Ce projet est sous licence MIT. Voir le fichier `LICENSE` pour plus de détails.

---

N'hésitez pas à adapter ce README selon les spécificités de votre projet et vos besoins.
