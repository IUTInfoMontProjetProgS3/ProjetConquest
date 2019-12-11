**Date de rendu : 14 janvier 2020 à 18h00**  

### Consignes pour le fork
Les étapes à suivre par chaque équipe pour organiser correctement votre projet :
  1. Pour forker votre projet vous devez avoir consitué une équipe. __Un seul membre de l'équipe__ fera le fork (càd acceptera l'affectation du projet sur GitHub Classroom) : https://classroom.github.com/g/fBHHXmdD
    
      **Attention** : pas de fork à la main !
  
  2. Au moment de l'acceptation de l'invitation, ce membre de l'équipe choisira un nom d'équipe. Ce nom sera constitué des noms de famille de chaque membre accolés par ordre alphabétique. Par exemple, si les noms sont Dupont, Durand et Cornet, alors l'équipe s'appellera : `CornetDupontDurant`.
  3. Ensuite, une fois le projet forké avec classroom, l'étudiant invitera les autres membres de l'équipe un par un comme collaborateurs extérieurs (_Outside collaborator_) à ce dépôt. À vous de choisir si vous voulez qu'un d'entre vous (ou tous) ait les droits d'administration. À partir de ce moment vous pourrez travailler de façon collaborative sur votre dépôt.
    
### Consignes générales
* Pour que le coeur du projet soit fini (note >= 12), vous devez implémenter correctement l'ensemble de méthodes levant une exception avec l'instruction `throw new RuntimeException("Not Implemented")`.
* Sauf indication contraire, il est interdit de _modifier_ les méthodes dont le code vous est fourni (celles qui n'ont pas de `throw new RuntimeException("Not Implemented")`). Pensez au principe [OCP](https://en.wikipedia.org/wiki/Open%E2%80%93closed_principle).
* Vous pouvez _ajouter_ des fonctions utilitaires qui vous parraissent nécessaires ([OCP](https://en.wikipedia.org/wiki/Open%E2%80%93closed_principle) toujours). 
* Vous respecterez les bonnes pratiques et principes en programmation objet (vues en cours de [CPOA](https://github.com/IUTInfoMontp-M3105/Ressources) ou dans la littérature).  
* Le respect des conventions de nommage du langage Java est **impératif**. Pour garantir le respect de ces aspects esthétiques, les IDE vous fournissent des outils de nettoyage, veillez à bien les utiliser avant le moindre partage de code.
* Comment tester votre programme ? Voici quelques consignes :

    1. En écrivant vos propres tests unitaires.  
    2. En exécutant les tests unitaires qui vous ont été fournis dans le répertoire `src/test/java` :
        * Tous ces tests sont annotés `@Disabled` et donc pour l'instant ils sont ignorés.
        * Au fur et à mesure de l'avancement de votre projet vous activerez chaque test (en supprimant l'annotation `@Disabled`) et vérifierez que ce test passe.
        * **Attention** : n'essayez pas de passer tous les tests en même temps (principe [_BabySteps_](http://codingdojo.org/BabySteps/))
        * Ne faites pas de *add/commit* tant que des tests non-annotés `@Disabled` ne passent pas.
        * **Remarque** : soyez vigilants même si votre programme passe tous les tests car en règle générale un programme ne peut **jamais** être suffisamment testé...

* Votre base de code **doit être en permanence testé** et donc toujours être dans un état sain : le code compile et les tests qui ne sont pas `@Disabled` passent.
* Certaines précisions ou consignes pourront être ajoutées ultérieurement et vous en serez informés.

### Conseils concernant la gestion de version
* Chaque commit devrait être accompagné d'un message permettant de comprendre l'objet de la modification.
* Vos _commits_ doivent être les plus petits possibles. Un commit qui fait 10 modifications de code sans lien entre elles, devra être découpé en 10 _commits_.
* On vous conseille d'utiliser des _branches_ différentes lors du développement d'une fonctionnalité importante. Le nom de la branche doit être au maximum porteur de sens. Une fois que vous pensez que le code de la fonctionnalité est fini (tous les tests associés à celle-ci passent), vous fusionerez le code de sa branche avec la branche `master`.
