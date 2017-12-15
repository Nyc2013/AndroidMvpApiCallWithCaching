# AndroidMvpApiCallWithCaching

Projet présentant une simple vue  affichant une liste d’éléments récupérés à partir de [jsonplaceholder.typicode.com/photos](http://jsonplaceholder.typicode.com/photos) avec une **gestion de cache** afin de proposer une utilisation **offline**.

Cependant ce projet a pour but de présenter une problématique simple à travers une stack de développement plus “professionnelle”, avec **Dagger 2**, **RxJava 2** et une **architecture MVP**. 

L’application a été designée pour que le cache soit effectué automatiquement lors d’une requête, c’est à  dire que le rafraîchissement des données n’est pas laissé à l’appréciation de l’utilisateur. Certains considéreront cela comme une mauvaise pratique en terme d’utilisation des ressources mais c’est ce que je souhaitais faire. Mettre à disposition un projet simple se concentrant avant tout sur ce qui est demandé (afficher une vue à partir de données provenant d’une API distante pouvant fonctionner offline) tout en utilisant la stack évoquée.

De la même manière, nous ne traitons pas la memory cache mais nous traitons la persistance de ces données à travers une solution simple que la plupart connaissent, SQLite. Encore une fois, ce qui nous intéresse ici était de voir, dans un projet classique, la mise en place de l’injection de dépendance avec **Dagger 2**, la réalisation de traitements avec **RxJava 2**, le tout dans une **architecture MVP**.

Une autre implémentations de cette problématique mettant en jeu un autre design pour le cache et d’autres solutions sera probablement mise en ligne prochainement.

Ps: Ah oui! Evidemment, le fait de présenter ce dépôt en français est volontaire car j’estime qu’il existe peu de ressources de ce type en français (même si j’adore la langue de Shakespeare **_of course!_**) 
