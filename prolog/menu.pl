% PRACTICA PROLOG: EJERCICIO 1,2
/*  EXPLICACION DEL FUNCIONAMIENTO DEL PROGRAMA 
    - la funcion 'principal' retorna una comida 'C' que puede ser pescado o carne
    - la funcion 'menu' retorna el menu, esto es una entrada 'E', un plato 
    principal 'C', un postre 'P' y una bebida 'B'.
    - la funcion 'menuConConsome' extiende la funcionalidad de menu solo que como
    limitacion, solo selecciona los menus que contengan consome como entrada. 
    - la funcion 'menuSinFlan' extiende la funcionalidad de menu solo que como
    limitacion, solo selecciona los menus que NO contengan flan como postre.
    */
entrada(paella).
entrada(gazpacho).
entrada(consome).

carne(filete_de_cerdo).
carne(pollo_asado).

pescado(trucha).
pescado(bacalao).

postre(flan).
postre(helado).
postre(pastel).

bebida(agua).
bebida(vino).
bebida(cerveza).

%union(X):-pescado(X);carne(X).
principal(C):-carne(C);pescado(C).
menu(E,C,P,B):-entrada(E),principal(C),postre(P),bebida(B).
menuConConsome(C,P,B):-principal(C),postre(P),bebida(B).
menuSinFlan(E,C,P,B):-entrada(E),principal(C),postre(P),P\=flan,bebida(B).