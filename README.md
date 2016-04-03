# complexitealgo

Groupe Aziz ARIF, Guillaume BERTRAND, Khaled BOUGETTOUCHA, Alexandre GADEAU, Gatien SANCHO

Projet realise en JAVA

REMARQUES:

La methode avec les determinants ne considere pas deux points confondus de deux segments comme deux segments secants en un point.

Il est possible que les jars posent probleme, utiliser les jars du zip le cas echeant.

QUESTIONS/REMLARQUES:

<<Dans le jeu (i) les coordonnees des deux sommets de
chaque segment sont tires aleatoirement dans l’intervalle [0,n] (la région du plan dans
lequel se répartissent les n segments grandit avec le nombre n de segments).>>

Remarque (i): J'ai applique _la definition strict d'un intervalle_:
les segments sont repartis de maniere aleatoire avec X variant de [0, n] et Y dans [0, n] pour les 2 extremites des segments 

(i) Vous etes d'accord?

Dans le jeu (ii) les coordonnees du sommet gauche sont tirees aleatoirement dans l’intervalle [0,n], le
segment a une hauteur et largeur egale à 1, et sa pente est plus ou moins 1 de façon aléatoire.

j'ai applique le point gauche avec X dans l'intervalle [0, n] et avec Y dans [0, n]. Ceci dit on ne peut pas déterminer de manière aleatoire la pente si la hauteur=1 et largeur=1

J'ai remarque un comportement aleatoire qui semble corrige (On ne peut pas avoir de coeff directeur nul!)

(ii) verifiez aussi!

CF. schema_explicatif.jpg