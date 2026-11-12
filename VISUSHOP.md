# VISUSHOP — projet Android expérimental

VISUSHOP vise à proposer un éditeur graphique gratuit et open source sur Android, basé initialement sur PhotoCraft et son port Android. Le but à long terme est d'obtenir des fonctionnalités et une expérience familières aux utilisateurs de Photoshop, sans affiliation avec Adobe.

## État du projet

**Démarrage / expérimental.** Ce dépôt contient actuellement la base du constructeur ArtCraft Android et une configuration de construction de développement pour PhotoCraft. Il ne s'agit pas encore d'une version VISUSHOP vérifiée ni d'un APK prêt à distribuer. Les fonctions, la compatibilité PSD et les appareils pris en charge doivent être testés.

- [Cahier des charges initial](VISUSHOP-CAHIER-DES-CHARGES.md)
- [Audit et résultat de la première tentative](VISUSHOP-AUDIT.md)
- [Documentation du constructeur d'origine](README.md)

## Essai de compilation

Prérequis : Git, Rust/Cargo, Python 3, JDK, Android SDK/NDK et autres dépendances du constructeur ; consulter le README d'origine et vérifier les outils téléchargés. Sur une machine configurée :

```sh
bash build-visushop-debug.sh
```

Ce script demande un APK de développement baptisé VISUSHOP avec un identifiant d'application propre ; il utilise par défaut la sélection de version amont du constructeur. Pour des compilations reproductibles, choisir une version précise compatible au moyen des options du constructeur, puis enregistrer les commits et outils utilisés. Ce script n'a pas encore été testé jusqu'à production d'un APK.

L'architecture documentée par le constructeur cible actuellement arm64-v8a. Ne pas utiliser une clé de développement pour distribuer une version publique. Garder les clés de signature et les jetons hors du dépôt.

## Origine et licences

La base Android provient de [MostafaXS1/artcraft-android](https://github.com/MostafaXS1/artcraft-android), sous licence MIT ; sa notice figure dans `LICENSE`. Le code PhotoCraft est téléchargé séparément par le constructeur depuis [storytold/photocraft](https://github.com/storytold/photocraft), qui indique MIT ou Apache-2.0. Les licences, notices, marques, polices et dépendances devront être vérifiées avant redistribution. Les droits sur les marques des projets d'origine restent ceux de leurs titulaires. VISUSHOP n'est pas affilié à Adobe.

**Note de dépôt :** les workflows GitHub Actions du constructeur amont ne sont pas inclus dans cette première importation ; ils nécessitent une autorisation spécifique et un audit avant activation.

## Accueil communautaire (en cours de validation)

À chaque lancement de l'activité, un écran présente WhatsApp et Facebook ; X et GitHub sont désactivés en attendant leurs liens. La participation est facultative : « Continuer vers l’éditeur » donne accès à l'application. Les boutons sociaux ouvrent les liens externes, sans collecte ni vérification d'adhésion. Le retour depuis un lien ne doit pas être interprété comme un nouveau lancement.

L'interface et les liens ont été ajoutés au code, mais n'ont pas encore été compilés ni testés sur Android. L'override du nom et du package pour l'adaptateur PhotoCraft a fait l'objet d'un test de génération de manifeste avec un faux espace source.

### Icône et état de compilation

L'icône choisie est enregistrée dans `assets/visushop-icone.png` et transmise par le script de développement au générateur d'icônes Android. Une première tentative de compilation a utilisé PhotoCraft `v0.20.2` : les métadonnées Android générées indiquent Android 9 (API 28) minimum et ARM 64 bits. Ce sont les limites de cette configuration, pas une garantie de compatibilité. La compilation et les tests sur appareil restent à valider ; la clé de développement locale ne doit pas servir aux versions publiques.

Pour tenter une construction sur GitHub Actions, une proposition de workflow manuel figure dans `docs/visushop-build-workflow.yml`. Elle ne s'exécute pas tant que le fichier n'est pas placé dans `.github/workflows/` sur GitHub. Le workflow et son résultat doivent être vérifiés avant toute diffusion.
