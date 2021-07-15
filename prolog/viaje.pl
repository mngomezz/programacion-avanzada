% PRACTICA PROLOG: EJERCICIO 4
/*  EXPLICACION DEL FUNCIONAMIENTO DEL PROGRAMA 
    Se implementaron las funciones:
    - viaje(C,S,H,P) que calcula el precio de viajar a la
    ciudad 'C' durante 'S' semanas con estancia en 'H'.
    La variable 'P' es de salida, esta indica los pesos.
    - viaje_economico(C,S,H,P,Pmax) indica los viajes que
    no superen un precio maximo (Pmax).
    */
transporte(roma,20).
transporte(londres,30).
transporte(tunez,10).
alojamiento(roma,hotel,50).
alojamiento(roma,hostel,30).
alojamiento(roma,camping,10).
alojamiento(londres,hotel,60).
alojamiento(londres,hostel,40).
alojamiento(londres,camping,20).
alojamiento(tunez,hotel,40).
alojamiento(tunez,hostel,20).
alojamiento(tunez,camping,5).
multiplicar(P,X,Y):-P is X*Y.
sumar(S,X,Y):-S is X+Y.
menor(X,Y):-X<Y.

%En resumen, el viaje() averigua el precio de alojamiento, lo multiplica por las semanas, y luego suma el precio del transporte.
viaje(C,S,H,P):-transporte(C,PrT),alojamiento(C,H,PrA),multiplicar(CE,S,PrA),sumar(P,CE,PrT).
viaje_economico(C,S,H,P,Pmax):-viaje(C,S,H,P),menor(P,Pmax).

% Explicacion en clase de una proyeccion y una seleccion
proyeccion(C,H):-alojamiento(C,H,_).
seleccion(C,H,P):-alojamiento(C,H,P).