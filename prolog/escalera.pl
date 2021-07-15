/* 
# PROLOG: Escalera de a 1, 2 o 3 escalones
Una escalera puede subirse de a 1, 2 o 3 escalones.
Dada una escalera de n escalones, hallar la cantidad de combinaciones 
diferentes en que puede subirse.
Ejemplos:
formas(2) =  2 -> ['02', '012']
formas(3) =  4 -> ['0123', '023', '03', '013']
formas(4) =  7 -> ['01234', '0124', '0134', '014', '0234', '024', '034'] 
formas(6) = 24 -> ['0123456', '012346', '012356', '01236', '012456', '01246',
                   '01256', '013456', '01346', '01356', '0136', '01456', '0146',
                   '023456', '02346', '02356', '0236', '02456', '0246', '0256',
                   '03456', '0346', '0356', '036']
Nota: 0 es el piso. No es necesario proporcionar las combinaciones, sino 
sólamente la cantidad.
*/

formas(N):-