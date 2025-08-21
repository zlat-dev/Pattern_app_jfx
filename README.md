Pattern_app_jfx
@zlatko
20250820

## Getting Started

L'idée est de créer un squelette d'une application graphique en JavaFX qui dispose des fonctionalités de bases pour ensuite le réutiliser à volonté comme point de départ d'autres programmes. Il devra disposer d'un lanceur, d'une IHM et d'une capacité de lecture écriture JSON.
But pédagogique personnel pour apprendre JFX.

## Folder Structure

L'espace de travail contient :

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

## Dependency Management

- `/lib` : la version JFX correspondant au SDK utilisé
- `/bin/ressources` : des fichiers compléments (fonts, icons ...)

## Tasks

ORGANISATION
---  une fenètre principale
---  une feuille de style en css
---  layout conteneur et sous blocs (hors menu, tool, status) / 
---  une barre de status en bas avec :
--- --- un message timé
--- --- une barre de progression
--- --- un label status notification
--- --- un label status user
--- --- un label status timestamp du fichier de données
--- --- un label status du dernier item log
---  fichier conf pour les messages préenregistré (configparser)
---  fichier ini pour le programme (configparser)
---  une boite à outils (icones 32)
---  un menu (icones 32)
--- --- fichier / édition / A propos
--- --- les actions associées au menu et toolbar
---  une icone application (icones 512)
---  dans le layout mid-W
--- --- un Treeview pour les données json
--- --- un TreeView pour les dir
--- --- splitter pour séparer et ajuster
---  un journal d'évènement appli (file et stdout)
--- --- log file par jour
--- --- créer un journal de log et archiver les actions
--- --- nettoyer le msg info
---  fenetre modale création entité
---  fenetre modale création cible pour une entité
---  fenetre modale boite à propos
--- --- affiche le README file dans A propos

ACTION
---  message bienvenue
---  parser conf
---  logger
---  affiche dir
---  boite dialogue fermeture
---  fenetre modale création entité (via designer6)
---  fenetre modale création cible (via designer6)
---  fenetre modale A propos (via designer6)

TODO
- todo revoir box cible pour ajouter une cible à choisir parmi
- todo enregistrer entité dans json

RESTE
- des fichiers json pour les données
- une fenètre noms des agents en arborescence et objectifs et ssobjectifs
- une fenètre avec leurs données (taches, parent, fils)
- une fenètre graphique avec des stats
- une fenètre graphique avec la feuille de route
- une fenetre de log pour profil appli et profil log
- nettoyer les notifications
- associer les user avec profil et logger
- associer les tips avec messages.conf et msglogger
- affiche json data
- Créer une entité
- Créer des cibles pour cette entité
- Créer des agents rattachés à l'entité et connecter à une cible
- Créer des objectifs pour une cible et connecter à un agent
- créer un organisme avec agents et objectif final visé
- affecter des taches aux agents et les positionner dans la feuille de route
- segmenter les taches par groupe en ligne
- cadencer les taches sur une ligne de temps
- générer des stats de réalisation de l'objectif final
- générer les stats de réalisation des agents
- exporter les graphes en png
- exporter la feuille de route en png
- extraire la fiche d'un agent et ses stats
- extraire la fiche de l'objectif principal et les réalisations
- remplacer les layout de la page principale par des splitters

STRUCTURE DES DONNÉES
- json entité : id, nom, code
- json cible de la feuille de route : id, id entité, nom, code
- json agent : id, nom, poste, code nom
- json tache : id, id cible rattachée, id agent arttaché, nom, description, actions constituantes, critère de réussite
