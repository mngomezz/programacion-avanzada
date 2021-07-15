% PRACTICA PROLOG: EJERCICIO 3
/*  EXPLICACION DEL FUNCIONAMIENTO DEL PROGRAMA 
    Se implementaron las funciones hije,hijo,hija y las
    funciones hermane,hermano,hermana que indican las
    relaciones entre las personas de la base de datos.
    */
hombre(pedro).
hombre(manuel).
hombre(arturo).
mujer(maria).
padre(pedro,manuel).
padre(pedro,maria).

%hije(X,Y) expresa que X es hijo o hija de Y.
hije(X,Y):-padre(Y,X).

%hijo(X,Y) expresa que X es hijo de Y.
hijo(X,Y):-hombre(X),padre(Y,X).

%hija(X,Y) expresa que X es hija de Y.
hija(X,Y):-mujer(X),padre(Y,X).

%hermane(X,Y) expresa que X es hermane de Y.
hermane(X,Y):-padre(Z,X),padre(Z,Y),X\=Y.

%hermana(X,Y) expresa que X es hermana de Y.
hermana(X,Y):-padre(Z,X),padre(Z,Y),X\=Y,mujer(X).

%hermano(X,Y) expresa que X es hermano de Y.
hermano(X,Y):-padre(Z,X),padre(Z,Y),X\=Y,hombre(X).