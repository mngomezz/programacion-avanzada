/* ENUNCIADO DEL EJERCICIO
    Se desea obtener:
    1. El listado de los alumnos que promocionan la materia, indicando el nombre y la nota final (promedio de los dos parciales), para cada uno.
    2. El listado de los alumnos que obtendrán la cursada. 
    3. El listado de los alumnos que recursan la materia.
    4. A fin de entregar la medalla al mérito, encontrar de aquellos que promocionan, el o los alumnos con mayor nota final. 

    ### Versión 2: El listado se compone de al menos una nota para cada alumno. En caso de que algún alumno adeude uno de los parciales, su condición es ausente.
    No se toman en cuenta quienes no dieron ninguno de los  parciales. */

/*  EXPLICACION DEL FUNCIONAMIENTO DEL PROGRAMA
    Analizo notas de los primeros parciales (p1) y los segundos parciales (p2)
    Luego, en base a eso obtengo los alumnos (A) que promocionaron (promocion)
    y cual fue su nota (N). Tambien puedo saber quien queda en condicion de 
    cursada (cursada) y quien debe recursar la materia (recursa) */
p1(ana,7).
p1(juan,8).
p1(pedro,6).
p1(esteban,4).
p1(martin,10).

p2(ana,9).
p2(juan,8).
p2(pedro,3).
p2(esteban,5).
p2(martin,1).

promocion(A,N):-
p1(A,N1),N1>=7, % obtengo notas del primer parcial que superen el 7
p2(A,N2),N2>=7, % obtengo notas del segundo parcial que superen el 7
N is (N1+N2)/2. % obtengo nota final (promedio de parcial1 y parcial2).

cursada(A,N):-
p1(A,N1),N1>=4, % obtengo notas del primer parcial que superen el 4
p2(A,N2),N2>=4, % obtengo notas del segundo parcial que superen el 4
(N1<7;N2<7), % aplico filtro de promocionados (ambas notas mayores a 7)
N is (N1+N2)/2. % obtengo nota final (promedio de parcial1 y parcial2).

recursa(A,N):-
p1(A,N1), % obtengo notas del primer parcial
p2(A,N2), % obtengo notas del segundo parcial
(N1<4;N2<4), % aplico filtro de cursados (ambas notas mayores a 4)
N is (N1+N2)/2. % obtengo nota final (promedio de parcial1 y parcial2).

productoEntrePromociones(A1,N1,A2,N2):-promocion(A1,N1),promocion(A2,N2).

seleccionProducto(N1,N2):-productoEntrePromociones(_,N1,_,N2),N1<N2.

proyeccion(N):-seleccionProducto(N,_).

notaMaxima(N):-promocion(_,N),not(proyeccion(N)).

medallaMerito(A,N):-promocion(A,N),notaMaxima(N2),N=:=N2.