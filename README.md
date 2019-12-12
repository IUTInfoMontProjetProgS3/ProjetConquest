# ![](ressources/logo.jpeg) Projet de programmation en Semestre 3

### IUT Montpellier-Sète – Département Informatique

* **Enseignants:** [Hoang La](mailto:xuan-hoang.la@umontpellier.fr), [Clément Agret](mailto:agret@lirmm.fr)
* Le [forum Piazza](https://piazza.com/class/jzs4o7je7zm1a0) de ce cours pour poser vos questions

# Projet - _CONQUEST_

**Date de rendu : Le 14 janvier 2020 à 18h00**

**Avant de commencer à coder quoique ce soit, consultez les [Consignes](Consignes.md).**

## Présentation
Le but de ce projet est de produire _Conquest_, une implémentation en _Java_ du jeu _Microscope_ qui est à la base un des puzzles du jeu _[The 7th Guest](https://en.wikipedia.org/wiki/The_7th_Guest)_.
En plus de l'implémentation du jeu lui-même, on considèrera l'implémentation de deux intelligences artificielles : un algorithme naïf et l'algorithme minmax.

### Le jeu Conquest
__Conquest__ est un jeu de plateau à 2 joueurs. Chaque joueur possède un type de pion (`X` ou `O`).
Le plateau est de taille impaire n. Chaque joueur commence avec 2 pions qui se trouvent dans les coins du plateau: 
- Le joueur `X` reçoit les pions en haut à gauche (0,0) et en bas à droite.
- Le joueur `O` reçoit les pions en haut à droite (0,n-1) et en bas à gauche.

Le jeu se joue tour par tour. À chaque tour, un joueur a le choix entre :
- Déplacer un pion à distance 1 (diagonales autorisées, par exemple (0,0) &rightarrow; (1,1) est ok) : dans ce cas le pion se duplique pour remplir la case d'arrivée et la case de départ.
- Déplacer un pion à distance 2 (par exemple (0,0) &rightarrow; (2,2) et (0,0) &rightarrow; (2,1) sont ok) : dans ce cas le pion ne se duplique pas (la case de départ est maintenant vide et la case d'arrivée remplie). 
- S'il ne peut déplacer aucun pion, il passe son tour.

Dans tous les cas, la case d'arrivée doit être initialement vide. Une fois qu'un pion *p* y est déplacé, tous les pions se trouvant dans les cases à distance 1 de cette case d'arrivée prennent la couleur de *p*.
Quand un joueur n'a plus de pions ou quand le plateau est rempli, la partie est finie et celui qui possède le plus de pions gagne la partie.

Pour mieux visualiser les règles du jeu, vous pouvez regarder la vidéo suivante du puzzle _[Microscope](https://www.youtube.com/watch?v=CVbYO6BV6BI)_.

Nous vous fournissons un squelette de code à compléter dans lequel les méthodes seront spécifiées, ainsi que des [tests unitaires](https://fr.wikipedia.org/wiki/Test_unitaire).
Pour avoir un aperçu global du projet, voici des informations générales sur la structure du jeu et des classes fournies. 
En cas de détails non précisés dans les paragraphes ci-dessous, référez vous aux spécifications, ainsi qu'aux tests.

### Déroulement d'un partie
La partie commence par demander à l'utilisateur de choisir un mode de jeu : 
`PvP (0) ou PvE (1) ? ou Battle of the Artificial Intelligence (2) ?`
- PvP : joueur humain contre joueur humain.
- PvE : joueur humain contre une intelligence artificielle.
- EvE : une intelligence artificielle contre une autre intelligence artificielle.

Dans chaque mode, l'utilisateur a la liberté de choisir les options suivantes :
- En mode PvP, les noms des joueurs sont demandés.
- En mode PvE, on demande au joueur humain son nom, s'il veut jouer en premier ou en deuxième et le niveau de l'intelligence artificielle qui varie entre 0 et 4 (cf. [Intelligence Artificielle](#intelligence-artificielle)).
- En mode EvE, l'utilisateur choisit les niveaux des deux intelligences artificielles.

Ensuite, le(s) joueur(s) peuvent choisir de jouer en mode `hardcore` ou non. En mode `hardcore`, il n'est pas possible d'annuler un coup joué.
À chaque tour d'un joueur humain, on demande au joueur correspondant de rentrer les coordonnées de départ (ligne puis colonne) et les coordonnées d'arrivée.

### Les classes importantes

#### `Pawn`
La classe `Pawn` modélise un pion. 
Un pion possède comme seul attribut le joueur auquel il appartient (`Player player`).

#### `Move`
La classe `Move` modélise un coup. 
Un coup est identifié par les coordonnées de départ (`int row1` et `int column1`) et les coordonnées d'arrivées (`int row2` et `int column2`).

#### `Player`
La classe `Player` modélise un joueur. Un joueur est identifié par sa couleur (`int color`) qui est 1 (pour `X`) ou 2 (pour `O`). 
Il possède aussi comme attributs son nom (`String name`), la partie qu'il est en train de jouer (`Game game`) et la stratégie qu'il utilise (`Strategy strategy`).

Dans `Player`, la méthode la plus importante est la méthode `Move play()` qui utilise la stratégie du joueur pour retourner un coup valide.

#### `Board` 
La classe `Board` modélise le plateau de jeu. 
Il a comme attribut un tableau de pions (`Pawn[][] field`).
Une case du tableau est soit vide (`null`), soit rempli par un pion (`Pawn`).

Voici quelques méthodes importantes de la classe `Board` (dont certaines sont à implémenter) :
- `void initField(Player player1, Player player2)` qui initialise le plateau en plaçant les pions de départ.
- `boolean isValid(Move move, Player player)` qui vérifie si un coup est valide.
- `void movePawn(Move move)` qui étant donné un coup valide, déplace le pion correspondant en appliquant les règles du jeu.


#### `Game`
Un jeu est constitué d'un plateau (`Board board`) et de deux joueurs (`Player[] players`).
 
Voici quelques méthodes importantes de la classe `Game` (dont certaines sont à implémenter) :
- `void run(int hardcore)` qui fait tourner une partie.
- `void initGame()` qui initialise la partie.
- `boolean isFinished()` qui vérifie si la partie est terminée.
- `Player getWinner()` qui retourne le joueur qui a gagné la partie quand elle est terminée.
- `Player confirmOrUndoMove(Player player)` qui étant donné un joueur, demande au joueur s'il veut annuler un coup tant qu'il le désire et tant que l'on n'est pas revenu au début du jeu, et retourne le joueur dont il est le tour de jouer.

#### L'interface `Strategy`
L'interface `Strategy` modélise la stratégie d'un joueur.
La seule méthode de `Strategy` est `Move getMove(Board board, Player player)` qui, étant donné un plateau `board` et le joueur `player` qui doit jouer, retourne un coup `Move` valide.
Une classe qui implémente cette interface est `Human`, cette classe implémente donc la méthode `getMove` qui, dans le cas de l'utilisateur humain, demande au joueur d'entrer les coordonnées du coup qu'il veut jouer tant que le coup n'est pas valide.


### Annulation d'un coup
Pour annuler un coup pendant une partie `non hardcore`, vous pouvez utiliser le [Design Pattern Memento](https://dzone.com/articles/memento-pattern-1).

### Intelligence Artificielle
Lors que l'utilisateur choisit de jouer en mode PvE ou en mode EvE, il va jouer contre une intelligence artificielle (IA). 
Concrètement, une intelligence artificielle correspondra à une classe qui va implémenter l'interface `Strategy`.

Dans ce projet, vous devez implémenter une IA `Naive` qui correspondra au niveau 0 (cf. [Déroulement d'une partie](#droulement-dun-partie)) et qui devra satisfaire les conditions suivantes :
- `Naive` n'aura pas d'attribut.
- `Naive` doit retourner un coup valide.

Vous pouvez (cf barème indicatif plus bas) aussi implémenter une IA `Minmax` qui correspondra aux niveaux 1,2,3 et 4 (cf. [Déroulement d'une partie](#droulement-dun-partie)) et qui utilisera l'algorithme [MinMax](https://fr.wikipedia.org/wiki/Algorithme_minimax).

- L'algorithme MinMax calcule les coups des joueurs à l'avance pour trouver le meilleur coup.
Le niveau 1 (resp. 2,3 ou 4) correspondra au fait que `Minmax` va calculer 1 (resp. 2,3 ou 4) coup(s) à l'avance.
Concrètement, ce niveau correspond à la profondeur de l'arbre de jeu que `Minmax` va explorer.
- Pour savoir quel coup est le meilleur, il faut pouvoir évaluer un état de jeu donné. 
L'idée naturelle pour ce jeu est d'avoir plus de pions de que adversaire. 
La fonction d'évaluation (de la valeur du jeu au niveau des feuilles de l'arbre) que nous imposons ici est de retourner le nombre de pions de l'intelligence artificielle auquel on soustrait le nombre de pions de l'adversaire.

Par exemple, pour un board `b` dans la configuration suivante : 
```
__0_1_2
0|X _ O
1|_ _ _
2|O _ X
```
Avec la profondeur 1, la méthode `getMove(b,player1)` doit retourner le coup `(0,0)->(1,1)`.
En effet, dans l'arbre du MinMax, les 5 fils de la racine `b` sont les plateaux suivants.
```
__0_1_2
0|X X X
1|_ _ _
2|O _ X
```
Valeur : 3.
```
__0_1_2
0|X _ O
1|X _ _
2|X _ X
```
Valeur : 3.
```
__0_1_2
0|X _ X
1|_ X _
2|X _ X
```
Valeur : 5.
```
__0_1_2
0|_ _ X
1|_ _ X
2|O _ X
```
Valeur : 2.
```
__0_1_2
0|_ _ O
1|_ _ _
2|X X X
```
Valeur : 2.
  
L'algorithme MinMax choisit donc le coup amenant à un board de valeur 5.
Une fois que les IAs sont implémentées, il faut compléter le code de `Strategy defineStrategies(int AILevel)` dans `AppConquest` qui retourne la stratégie correspondante au niveau de l'IA.
Il est impératif que les IA soient nommées `Naive` et `Minmax` et qu'elles respectent les conditions ci-dessus si vous voulez qu'elles passent nos tests unitaires privés ! 


### Interface utilisateur
L'interface utilisateur que vous aurez à gérer sera entièrement en ligne de commandes. 
Les informations du jeu seront affichées à l'écran en utilisant la sortie standard et les choix des joueurs se feront par lecture sur l'entrée standard (clavier). 
Le jeu commence par l'exécution de `main` dans la classe `AppConquest`.
Une fois la partie lancée, toutes les interactions avec l'utilisateur se feront donc dans le terminal. 
Dans une partie à plusieurs joueurs, un même processus demande successivement aux joueurs de jouer leur tour dans le même terminal.

### Rendu attendu
Vous travaillerez par groupes de 3. L'intégralité du code source du projet doit résider dans le dépôt GitHub associé à votre équipe de projet **sur la branche master**. Veillez à vous répartir le travail, et à ce que chaque membre du groupe effectue des commits pour laisser une trace de son activité. On vous encourage à travailler sur des *branches* différentes pour chaque fonctionnalité/personne de l'équipe et fusionner les branhces avec master au fur et à mesure.

 Pour les modalités de rendu, voir les [Consignes](Consignes.md).

### Évaluation
Nous évaluerons votre projet dans l'état où il sera sur le dépôt GitHub de votre équipe au moment de la deadline.

Barème indicatif : si votre projet 
- ne compile pas : 0
- passe tous les tests "publiques" (ceux que vous avez) : 12
- passe tous les tests "publiques" et "privés" (mais sans l'algorithme MinMax) : 15
- passe tous les tests "publiques" et "privés" avec l'algorithme minmax : 20

### Prise en compte de la note

La note comptera en partie dans le module Algorithmique Avancée (M3103) et le module Conception et Programmation Objet Avancées (M3105), de la façon suivante.

Algorithmique avancée (M3103) :
- 25% : contrôles de TD
- 25% : projet Conquest
- 50% : partiel

Conc. et Prog. Objet Avancées (M3105) :
- 20% : projet Conquest
- 80% : partiel

