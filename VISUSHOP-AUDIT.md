# VISUSHOP — Démarrage et audit technique initial

Date : 12 novembre 2026

## Travail effectué

- Dépôt public cloné localement dans `visushop-android/` depuis https://github.com/MostafaXS1/artcraft-android.
- Version du constructeur relevée : commit `52308fbcb26d8d8625f35fc1f048813f8e1e12a8` (v0.5.0 selon la page de publication).
- Vérification syntaxique Bash de `build.sh`, `build-debug.sh`, `lib/build-common.sh` et `adapters/photocraft/prepare.sh` : réussie.
- Compilation syntaxique des fichiers Python de `lib/` : réussie.
- Aide du constructeur exécutée ; présence des options `-photocraft`, `-tag`, `-appname`, `-packagename` et `--noupdate` confirmée.
- Première tentative de construction avec `bash build-debug.sh -photocraft --noupdate` : arrêt immédiat, car Rust/Cargo n'est pas installé dans cet environnement. Aucun APK généré.

## Constats

- Le constructeur prépare un adaptateur spécifique PhotoCraft, incluant notamment des passerelles pour la sélection et l'enregistrement des fichiers Android.
- Le chemin de construction examiné compile pour `arm64-v8a` ; cela ne démontre pas le support des appareils 32 bits.
- Une logique de recherche de mises à jour est intégrée. Vérifier son comportement réseau et son paramétrage avant une diffusion VISUSHOP hors ligne.
- La version minimale Android est issue des métadonnées générées ; une valeur par défaut dans le générateur ne prouve pas la version effectivement utilisée par PhotoCraft. La vérifier après génération et tester les appareils.
- La documentation `adapters/README.md` mentionne cargo-apk2, tandis que `architecture.md` et le script actuel décrivent cargo-ndk et Gradle. La chaîne effective doit être confirmée lors d'une construction et la documentation ne doit pas être supposée homogène.
- Ces vérifications syntaxiques ne sont pas des tests fonctionnels et ne prouvent pas qu'un APK peut être compilé ou qu'il fonctionne.

## Prochaines actions techniques

1. Disposer de Rust/Cargo, Android SDK/NDK et d'un JDK compatible ; consigner les versions réelles. Vérifier les installations ou téléchargements automatiques du constructeur avant leur exécution.
2. Fixer explicitement un tag/commit PhotoCraft compatible avec le constructeur, conserver les versions et empreintes dans un manifeste de compilation.
3. Construire d'abord PhotoCraft non modifié en mode debug, puis examiner l'APK : identifiant, SDK minimal/cible, architectures, permissions, signature et contenus.
4. Tester sur une matrice variée d'appareils, ensuite seulement construire VISUSHOP avec un identifiant propre, un nom distinct et les notices obligatoires.
5. Mesurer la compatibilité PSD et les fonctionnalités, puis établir un tableau comparatif Photoshop/PhotoCraft/VISUSHOP par outil. Objectif de parité fonctionnelle progressive : aucune garantie de copie parfaite à ce stade.
6. Pour une publication, créer une clé de signature de release distincte ; ne jamais stocker de jeton GitHub ou de clé privée dans le projet.

## Sources

- Constructeur : https://github.com/MostafaXS1/artcraft-android
- Architecture : https://github.com/MostafaXS1/artcraft-android/blob/52308fbcb26d8d8625f35fc1f048813f8e1e12a8/architecture.md
- PhotoCraft : https://github.com/storytold/photocraft
