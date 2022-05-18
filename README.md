# GwentDB
Projet POEC Java Clermont 2022, Mars 2022
---
Projet Angular correspondant: https://github.com/maoherve/GwentDB-Angular
---
## Description
Le projet a pour but de recenser l’ensemble des cartes du jeu “Gwent” venant de l’univers de “The Witcher”, spécifiquement celles de “The Witcher 3”. (199 au total)
Il s’agit d’un jeu de carte au tour par tour.
Permettre à l’utilisateur de naviguer sur la base de données des cartes de Gwent et de les sélectionner pour en afficher les détails.

## Utilisation
- L’utilisateur accède à la page d'accueil de l'application
- Sur cette page, il peut rechercher une carte en se servant d'une barre de recherche (selon les critères ci-dessous)
- L'utilisateur consulte la page de la carte
- Il peut ajouter cette carte à ses favoris
- Il peut également créer un "deck" de cartes à partir de celles disponibles sur le site.

## Recherche
### Critères

L'user peut taper partiellement ou entièrement le nom de la carte dans une barre de recherche, mais il peut aussi faire une recherche selon les critères suivants:

* Deck: le deck dont la carte fait partie. Il en existe 6: Monsters, Nilfgaardian Empire, Northern Realms, Scoia'tael, Skellige, Neutral
* Ability: les cartes qui disposent de cette abilité. Elles sont les suivantes: Berserker, Commander’s Horn, Decoy, Medic, Morale Boost, Mardroeme, Muster, Scorch, Spy, Tight Bond, Summon Avenger
* Row: La position où la carte peut être placée. Il y en a 4: Close Combat, Ranged, Agile (Close Combat or Ranged), Siege
* Type: Le type de la carte. Il y en a 4: Unit, Hero, Leader, Special

Ces options sont disponibles via liste déroulante pour chacune d'entre elles, au dessus de la barre de recherche.
Une fois la carte voulue sélectionnée, les détails de cette carte sont affichés:
* son nom,
* son illustration,
* sa puissance,
* sa description,
* sa localisation dans le jeu.

## Fonctions
### Favoris
Sur la page de chaque carte, un bouton "Favoris" est mis à disposition.
Chaque utilisateur peut cliquer dessus pour l'ajouter à ses favoris, et ainsi la retrouver directement dans l'onglet "Favoris" de l'application sans passer par la recherche.

### Decks
Chaque utilisateur a l'option de créer un deck dans l'onglet "Deck" de l'application. Après lui avoir donné un nom et l'avoir crée, il peut alors sélectionner les cartes de son choix (maximum de cartes et contraintes à définir) pour constituer son deck. Ainsi il est possible d'obtenir un lien unique pour chaque deck crée par chaque utilisateur, et de pouvoir partager ce lien.

## Roles

### Utilisateur
L’application ne nécessite pas d’authentification de la part de l’utilisateur. Il peut:
- rechercher les cartes selon les critères mentionnés ci dessus
- consulter les cartes
- ajouter une carte à ses favoris
- créer un ou plusieurs decks, et le constituer des cartes de son choix

### Administrateur
Un administrateur a un compte et doit s’authentifier. Il dispose des droits précédents, plus:
- créer, modifier et supprimer des entrées dans la liste des cartes.

### Super Administrateur
Un super administrateur dispose de tous les droits, il peut donc: 
- créer, modifier et supprimer les comptes des administrateurs.

