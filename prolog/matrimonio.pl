/* ENUNCIADO DEL EJERCICIO
    Se pide desarrollar la funcion busca(N,A,C,E) que indica que
    una persona N busca otra persona con una altura A, un cabello C,
    y una edad E que, ademas, comparta los mismos gustos (N,M,L,S). */
/*  EXPLICACION DEL FUNCIONAMIENTO DEL PROGRAMA
    Analizo notas de los primeros parciales (p1) y los segundos parciales (p2)
    Luego, en base a eso obtengo los alumnos (A) que promocionaron (promocion)
    y cual fue su nota (N). Tambien puedo saber quien queda en condicion de 
    cursada (cursada) y quien debe recursar la materia (recursa) */
hombre(pablo,media,negro,viejo).
mujer(maria,alta,rubio,joven).
gusta(pablo,pop,aventura,tenis).

productoCartesianoFisico(A,C,E):-
hombre(N1,A,C,E),
mujer(N2,A,C,E),
N1=\=N2.

/*
busca(N,A,C,E).

productoCartesianoGustos(N1,N2,M,L,S):-
gusta(N1,M,L,S),
gusta(N2,M,L,S).

max(S,W):-
venta(_,_,W,S),
\+productoCartesianoMaximo(S,W).

min(S,W):-
venta(_,_,W,S),
\+productoCartesianoMinimo(S,W).

busca(Nombre, Altura, Cabello, Edad):-
hombre(N,A,C,E),
mujer(N,A,C,E),
Diferencia is Maximo-Minimo.
*/