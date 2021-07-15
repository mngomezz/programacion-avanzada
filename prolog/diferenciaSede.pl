venta(20210110091500,julio,11,1).
venta(20210111091500,lucas,35,1).
venta(20210112091500,fede,22,2).
venta(20210113091500,julio,48,2).
venta(20210110093000,fede,33,2).
venta(20210111093000,lucas,74,1).
venta(20210112093000,hernan,97,3).
venta(20210113100000,lucas,72,1).
venta(20210111110000,hernan,65,3).
venta(20210111114500,julio,56,3).
venta(20210111120000,julio,54,2).
venta(20210111131223,julio,81,1).

productoCartesianoMaximo(X,W):-
venta(_,_,W,X),
venta(_,_,Z,X),
W<Z.

productoCartesianoMinimo(X,W):-
venta(_,_,W,X),
venta(_,_,Z,X),
W>Z.

max(S,W):-
venta(_,_,W,S),
\+productoCartesianoMaximo(S,W).

min(S,W):-
venta(_,_,W,S),
\+productoCartesianoMinimo(S,W).

diferenciaSede(Diferencia, Sede):-
max(Sede,Maximo),
min(Sede,Minimo),
Diferencia is Maximo-Minimo.
