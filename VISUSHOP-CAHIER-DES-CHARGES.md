# VISUSHOP — Cahier des charges et étude de faisabilité

**Version :** 0.1 — document de travail à valider  
**Date :** 12 novembre 2026  
**Projet :** éditeur d’images Android, gratuit et open source  
**Statut :** recherche documentaire initiale ; aucune compilation ni aucun test sur appareil n’ont encore été réalisés.

## 1. Vision et objectifs

VISUSHOP doit être une application Android de création et de retouche d’images, gratuite, partageable sous forme d’APK, dont l’interface et les méthodes de travail sont familières aux utilisateurs de Photoshop sur ordinateur. Le point de départ privilégié est le port Android de PhotoCraft transmis par le commanditaire, avec conservation de ses capacités utiles et amélioration progressive.

### Principes
- Application gratuite, sans filigrane imposé ni abonnement pour les fonctions locales retenues.
- Code source ouvert, sous licence à déterminer après audit des composants.
- Édition de base sans connexion ni compte ; toute connexion optionnelle doit être expliquée et contrôlable.
- Traitement local et respect des fichiers personnels.
- Fonctionnement sur une gamme aussi large que possible d’appareils Android, notamment Tecno ; aucune promesse de compatibilité universelle ou de performance identique.
- Interface professionnelle proche des repères de Photoshop, tout en respectant les droits et marques de tiers.
- Première étape : établir une base fonctionnelle et vérifiée ; pas de promesse de parité totale avant tests.

## 2. Référence réelle : constat de recherche

Le lien fourni correspond à **MostafaXS1/artcraft-android**, un projet public distinct du dépôt principal PhotoCraft. Son README indique qu’il construit des applications ArtCraft pour Android à partir de leurs sources PC et ajoute des adaptations Android. PhotoCraft est explicitement une cible du constructeur. [S1]

La documentation d’architecture décrit un adaptateur spécifique pour PhotoCraft, un lanceur Rust basé sur GameActivity, des dépendances corrigées, des passerelles Android et une chaîne de compilation cargo-ndk + Gradle. La cible documentée est **arm64-v8a**, pas l’ensemble des architectures Android. [S2]

La release du constructeur v0.5.0, publiée le 5 novembre 2026, signale notamment des améliorations du clavier virtuel et des correctifs de saisie. Il s’agit de la version du constructeur, à distinguer de la version de PhotoCraft intégrée à un APK donné. [S3]

**Limites de notre observation :** les descriptions ci-dessus sont celles des auteurs du projet. Nous n’avons pas installé leur APK, vérifié son interface visuellement, testé chaque fonctionnalité, ni confirmé la version installée sur le téléphone du commanditaire. Les capacités réelles et l’ergonomie doivent être vérifiées en conditions réelles.

## 3. Périmètre et stratégie de départ

### Option privilégiée : dérivé du port existant
1. Fixer une version précise du constructeur Android et une version précise du dépôt PhotoCraft.
2. Compiler PhotoCraft Android **sans modification fonctionnelle** et documenter le résultat.
3. Installer et tester sur un appareil réel, notamment le Tecno du commanditaire.
4. Créer le dérivé VISUSHOP : nom d’application, identifiant de paquet propre, attribution, informations légales et configuration de compilation.
5. Vérifier la conservation du fonctionnement de la référence et corriger les défauts Android prioritaires.
6. Ajouter progressivement les améliorations convenues.

Cette stratégie est une hypothèse à valider après audit des licences, de la maintenabilité et des conditions de compilation. Elle évite de reconstruire inutilement l’interface et le moteur si la base existante convient. Changer uniquement le nom de l’APK ne suffira pas à créer un produit fiable et maintenable.

### Alternatives si le port bloque
- Réutiliser certaines bibliothèques PhotoCraft et développer un nouvel environnement Android.
- Construire un moteur ou une interface distincts à partir d’autres composants libres.

Ces alternatives sont plus risquées pour la fidélité de l’interface et le calendrier ; les comparer seulement après le prototype de compilation.

## 4. Public et cas d’usage

- Graphistes travaillant sur téléphone ou tablette Android.
- Utilisateurs de Photoshop souhaitant retrouver des commandes et panneaux familiers.
- Créateurs de visuels, affiches, miniatures et images pour réseaux sociaux.
- Utilisateurs disposant d’une connexion limitée : création et édition locales prioritaires.

**Parcours principal :** ouvrir ou créer un document → ajouter des éléments et des calques → utiliser les outils et les réglages → enregistrer le projet modifiable → exporter une image → partager le résultat.

## 5. Exigences fonctionnelles

### 5.1 Référence et priorité
- **P0 :** indispensable à la première version testable.
- **P1 :** nécessaire à une première version publique solide.
- **P2 :** ultérieur ou selon faisabilité.
- Une fonction indiquée ci-dessous constitue une exigence VISUSHOP, non une affirmation que le port actuel l’implémente parfaitement.

### 5.2 Documents et fichiers
| ID | Exigence | Priorité | Vérification |
|---|---|---|---|
| F01 | Créer un document : dimensions, fond et résolution si disponibles. | P0 | Créer, enregistrer et rouvrir un document. |
| F02 | Ouvrir une image locale PNG et JPEG via le sélecteur système Android. | P0 | Ouvrir depuis stockage interne et un fournisseur de documents. |
| F03 | Exporter en PNG et JPEG avec options pertinentes, sans filigrane VISUSHOP. | P0 | Vérifier dimensions, transparence PNG et image exportée. |
| F04 | Enregistrer et rouvrir un projet avec ses calques modifiables dans un format supporté. | P0 | Fermer, relancer et comparer calques et rendu. |
| F05 | Ouvrir et enregistrer des PSD avec calques pris en charge. | P1 | Jeux de PSD réels ; documenter les éléments conservés, modifiés ou non pris en charge. |
| F06 | Avertir avant toute perte possible d’information ou aplatissement. | P1 | Tester un PSD contenant des éléments non pris en charge. |
| F07 | Enregistrer sous un nouveau nom, partager et recevoir des fichiers via Android. | P1 | Tester avec gestionnaire de fichiers et application de partage. |
| F08 | Récupérer le travail après interruption, si possible. | P1 | Forcer une interruption et vérifier la récupération. |

L’export PSD n’est pas synonyme de compatibilité parfaite. La conservation des polices, masques, effets, objets dynamiques et métadonnées doit être mesurée. Ne jamais remplacer silencieusement l’original si la fidélité n’est pas garantie.

### 5.3 Édition et outils
| ID | Exigence | Priorité |
|---|---|---|
| F09 | Panneau des calques : créer, sélectionner, masquer, réordonner, dupliquer et supprimer. | P0 |
| F10 | Modifier l’opacité et transformer un calque : déplacement, taille, rotation et retournement. | P0 |
| F11 | Pinceau, gomme, pipette, outil de déplacement et recadrage. | P0 |
| F12 | Sélections rectangulaire et elliptique, puis lasso selon disponibilité. | P0/P1 |
| F13 | Texte modifiable : saisie, police disponible, taille, couleur et position. | P0 |
| F14 | Historique avec annuler/rétablir et retour cohérent après opérations. | P0 |
| F15 | Réglages courants : luminosité, contraste, saturation et teinte. | P1 |
| F16 | Masques, modes de fusion et réglages non destructifs. | P1, selon tests de la base |
| F17 | Tampon, correcteur, courbes et filtres avancés. | P2 |
| F18 | Outils IA, sélection intelligente et remplissage génératif. | P2 ; faisabilité, coût et confidentialité à définir |

Pour chaque outil conservé depuis PhotoCraft, établir une fiche : emplacement, comportement attendu, entrées tactiles, raccourcis, état réel, bugs et tests. L’inventaire exhaustif ne pourra être établi qu’après observation et essais.

### 5.4 Interface et ergonomie
- Conserver la disposition professionnelle de la référence autant que faisable : menus, barre d’outils, toile centrale, panneaux et réglages.
- Mode paysage prioritaire si nécessaire ; portrait et tablettes à évaluer après essais.
- Zoom, déplacement de la toile, saisie au clavier logiciel, clavier physique et souris selon les capacités de l’appareil.
- Contrôles suffisamment utilisables au doigt, sans masquer les fonctions de bureau ; les adaptations tactiles ne doivent pas casser les usages souris/clavier.
- Textes et messages compréhensibles ; français à prévoir, couverture de traduction à mesurer. Autres langues à décider.
- Identifier clairement VISUSHOP ; ne pas utiliser les logos ou ressources Adobe sans droit approprié.

## 6. Compatibilité Android

### Cibles provisoires
- Architecture de référence : **arm64-v8a**, documentée par le constructeur. Prise en charge 32 bits ARM et autres architectures : étude distincte, non garantie.
- Version Android minimale : **à déterminer après lecture des configurations générées et tests réels**, et non arbitrairement.
- `targetSdk` : conforme aux exigences applicables à la date de diffusion, en particulier si Google Play est envisagé ; il ne détermine pas à lui seul la version minimale.
- Mémoire, stockage, GPU et taille maximale des images : à établir par mesures ; avertir si une opération dépasse les capacités de l’appareil.
- Tester Android avec pages mémoire 16 Ko et les bibliothèques natives lorsque pertinent.

### Matrice initiale proposée
1. Tecno du commanditaire : modèle, version Android, RAM et architecture à relever.
2. Téléphone ARM64 à ressources modestes et écran réduit.
3. Téléphone ARM64 milieu de gamme.
4. Tablette Android ; clavier et souris si disponibles.
5. Différentes versions Android dans la plage retenue.

Une matrice réelle, renseignée après tests, indiquera appareil, OS, RAM, version VISUSHOP, résultat et problèmes connus. « Tous les Android » reste un objectif de couverture, pas une garantie testable.

## 7. Architecture technique préconisée

### 7.1 Base existante
- **Moteur et interface PhotoCraft :** Rust et composants du dépôt amont, version fixée à un commit ou tag.
- **Adaptation Android :** constructeur ArtCraft Android, adaptateur PhotoCraft, runtime et correctifs associés.
- **Lancement/intégration :** GameActivity et passerelles natives décrites par le projet ; système de fichiers Android SAF, clavier IME, presse-papiers et événements Android.
- **Compilation :** Git, Rust/Cargo, Python, JDK, Android SDK/NDK, cargo-ndk et Gradle. Versions exactes à consigner après construction reproductible.
- **Livrable :** APK debug pour essais, puis APK release signé avec une clé de publication séparée.

Il n’est pas nécessaire de reconstruire l’interface en Kotlin/Compose pour un premier dérivé : le port existant conserve déjà l’interface du programme Rust. Un module Kotlin ou Java peut être ajouté si une intégration Android précise le justifie après audit.

### 7.2 Points d’attention
- Figer les versions et documenter tous les correctifs Android : une mise à jour amont peut casser le port.
- Vérifier la gestion du cycle de vie : suspension, retour, rotation, perte/recréation de surface graphique.
- Tester les performances CPU/GPU, la mémoire et les éventuelles solutions de repli sur chaque famille de matériel ; ne pas affirmer qu’un repli existe avant vérification.
- Vérifier le fonctionnement SAF sur documents locaux et fournisseurs externes ; gérer les erreurs de lecture/écriture et éviter les sauvegardes partielles silencieuses.
- Séparer les modifications VISUSHOP de l’amont dans la mesure du possible pour faciliter les mises à jour et contributions.

## 8. Données, sécurité et confidentialité

- Pas de transfert d’images requis pour les opérations locales ; auditer les requêtes réseau existantes, notamment la recherche de mises à jour.
- Éviter les permissions Android inutiles ; documenter toute permission et sa raison.
- N’enregistrer aucun document sans action explicite ou mécanisme de récupération clairement annoncé.
- Ne jamais inclure la clé privée de signature dans le dépôt ou une distribution publique.
- Tester les fichiers corrompus ou très volumineux sans planter ni perdre les autres travaux.
- Fournir un mécanisme clair pour signaler les bogues, sans transmettre automatiquement les images ou données personnelles.

## 9. Licences, attribution et identité

- Le fichier LICENSE du constructeur Android est une licence MIT comportant une notice de copyright à conserver avec les portions concernées. [S4]
- Le dépôt PhotoCraft indique une double licence MIT ou Apache-2.0 au choix. [S5]
- Auditer chaque composant inclus : dépendances Rust, Java, bibliothèques natives, correctifs vendored, polices, icônes et autres ressources. Établir une nomenclature et conserver les notices requises selon les licences retenues.
- Vérifier les conditions applicables aux noms et logos PhotoCraft/ArtCraft ; remplacer les éléments de marque non autorisés et ne pas suggérer une affiliation.
- N’utiliser ni logo, ni code, ni ressources graphiques d’Adobe sans autorisation. Une ressemblance d’interface peut présenter des risques juridiques selon les éléments et les pays : revue appropriée avant diffusion.
- Choisir et publier la licence du code propre à VISUSHOP après cet audit ; gratuit ne signifie pas automatiquement open source, et une licence libre peut imposer des obligations d’attribution.

## 10. Tests et critères d’acceptation

### Tests de référence
Avant modification, noter la version exacte du constructeur et de PhotoCraft, et exécuter :
1. Lancer l’application et créer un document 1920 × 1080.
2. Importer une image PNG, ajouter un calque, dessiner et ajouter un texte accentué.
3. Annuler/rétablir, enregistrer, fermer et rouvrir ; vérifier calques et contenu.
4. Exporter en PNG/JPEG et vérifier le fichier dans une autre application.
5. Ouvrir et enregistrer une sélection de PSD de complexité croissante, en comparant le rendu et la structure.
6. Tester tactile, clavier logiciel, rotation, interruption, récupération et retour au système.
7. Relever les erreurs d’accès aux fichiers, plantages, temps observés et consommation mémoire si mesurable.

### Acceptation première version de test
- APK debug construit à partir de versions identifiées ; lancement sur au moins un appareil réel.
- Les parcours de base documentés réussissent sur cet appareil ; défauts et limitations sont listés.
- Aucun écrasement silencieux des originaux dans les scénarios testés.
- Attribution et licences identifiées dans le livrable.

### Acceptation version publique
- Matrice d’appareils validée et minimale explicitement annoncée.
- Scénarios P0 validés, défauts bloquants corrigés ; compatibilité PSD annoncée par cas testés.
- APK release signé, contrôle d’intégrité publié, procédure d’installation et notes de version disponibles.
- Vérification de sécurité, dépendances, licences et informations de confidentialité.

## 11. Distribution et compilation

Le constructeur documente `bash build.sh -photocraft`, ainsi que des options de nom et d’identifiant Android. Cette commande est une piste documentée, **pas un résultat déjà vérifié dans notre environnement**. [S1]

- Générer un APK debug pour les premiers tests puis une version release.
- Choisir un identifiant de paquet unique et stable sous le contrôle du projet ; vérifier sa disponibilité et ne pas utiliser celui du projet d’origine.
- Conserver la même clé de signature de publication pour les futures mises à jour ; sauvegarder cette clé de façon sécurisée. Une signature différente empêche généralement la mise à jour directe d’une application existante.
- Publier l’APK avec version, notes de changement, somme SHA-256 et consignes d’installation ; les utilisateurs devront autoriser l’installation depuis la source choisie selon leur version Android.
- Si Google Play est souhaité ultérieurement : préparer le format AAB et vérifier les exigences courantes de Google Play et de vérification des développeurs au moment de la publication.

## 12. Plan de travail et jalons

| Étape | Résultat attendu | Décision de passage |
|---|---|---|
| 0. Cadrage | Ce document validé, appareil identifié, version de référence choisie. | Accord sur le périmètre. |
| 1. Audit | Inventaire des sources, dépendances, licences, chaîne de build et fonctionnalités. | Feu vert juridique/technique préliminaire. |
| 2. Reproduction | Compilation non modifiée et test du port d’origine sur appareil. | Résultat reproductible et limites connues. |
| 3. VISUSHOP alpha | Identité propre, source maintenable, APK debug et parcours de base testés. | Tests P0 sur appareil de référence. |
| 4. Stabilisation | Corrections, compatibilité Android, sauvegarde et PSD testés. | Matrice et défauts acceptables. |
| 5. Publication | APK signé, documentation, code source et notices. | Validation finale du commanditaire. |
| 6. Améliorations | Fonctions distinctives priorisées selon les retours. | Nouvelle feuille de route. |

Aucun délai ni budget sérieux ne peut être garanti avant l’audit et une compilation réussie. La parité complète avec Photoshop est un objectif à long terme, pas un critère réaliste pour la première livraison.

## 13. Risques et réponses

| Risque | Effet | Réponse |
|---|---|---|
| Incompatibilité amont/port | Compilation impossible après mise à jour | Figer commits et conserver patches, tester en continu. |
| Appareil à faible RAM | Fermeture ou lenteur | Mesurer, limiter le travail, avertir, tester plusieurs formats. |
| PSD complexe | Perte ou modification d’information | Tester par fonctionnalité, avertir, préserver l’original. |
| Ergonomie PC sur petit écran | Boutons trop petits | Mesurer sur téléphone, proposer adaptations sans supprimer les outils. |
| Signature perdue | Mises à jour bloquées | Clé de publication sauvegardée et protégée. |
| Licences et marques | Redistribution bloquée ou litige | Audit exhaustif, attribution, identité propre, revue juridique si nécessaire. |
| Réseau ou mises à jour hérités | Atteinte aux attentes de confidentialité | Auditer, désactiver ou rendre optionnel, documenter. |

## 14. Décisions à prendre avec le commanditaire

1. Version de PhotoCraft réellement installée et captures de l’écran, si l’on souhaite comparer à son installation précise.
2. Modèle Tecno, version Android, RAM et espace disponible.
3. Maintien de l’interface PC telle quelle en priorité, ou adaptations tactiles dès la première version ?
4. Première langue : français seul, français + anglais, ou reprise des langues existantes ?
5. Distribution initiale : APK direct uniquement, ou Google Play envisagé ?
6. Quel niveau de fidélité PSD est indispensable à la première version publique ?
7. Appareils additionnels disponibles pour les tests.

## 15. Sources consultées

- [S1] ArtCraft Android, README et instructions de compilation : https://github.com/MostafaXS1/artcraft-android
- [S2] Architecture du constructeur Android : https://github.com/MostafaXS1/artcraft-android/blob/main/architecture.md
- [S3] Versions et notes de publication : https://github.com/MostafaXS1/artcraft-android/releases
- [S4] Licence MIT du constructeur : https://github.com/MostafaXS1/artcraft-android/blob/main/LICENSE
- [S5] PhotoCraft, dépôt et licence déclarée : https://github.com/storytold/photocraft
- [S6] Corpus de test PhotoCraft : https://github.com/storytold/photocraft-corpus

**Note :** ces sources décrivent des projets en évolution. Avant de développer ou de distribuer, fixer les versions exactes et revérifier les informations, licences et conditions applicables.
