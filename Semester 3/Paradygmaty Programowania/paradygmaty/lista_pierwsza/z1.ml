let trojkat (a, b, c) =
	if ((a<=0.0)||(b<=0.0)||(c<=0.0)) then failwith "Podaj liczbe dodatnia!" 
	else if abs_float(b-.c)>=a && a>=b+.c then failwith "Podane odcinki nie tworza trojkata!"
	else 
		let p = (a+.b+.c)/.2.0 in p*.(p-.a)*.(p-.b)*.(p-.c);;

trojkat(1.0, 5.0, 4.0);;




(*Lista 1 1.*)
let rec szereg(n, x) =
	let rec silnia (t) =
		if t <= 1 then 1
		else t * silnia(t-1) in
	if n=1 then -1.*.x
	else ((-1.)**float_of_int(n) *. x**float_of_int(n))/.float_of_int(silnia(n)) +. szereg(n-1,x)
	
szereg(3,3.)

(*Lista 1 3.*)
let najmniejsza lista = 
	let rec minimum(lista,min) =
		if lista = [] then min
		else if List.hd lista < min then minimum(List.tl lista, List.hd lista)
		else minimum(List.tl lista, min)
	in minimum(lista, List.hd lista)
	
najmniejsza [3;2;5;19;1]


let divideList lista =
	let rec divide(lista, smallerAcc, largerAcc) = 
		if lista = [] then [smallerAcc; largerAcc]
		else if List.hd lista >= 0 then divide(List.tl lista, smallerAcc, List.hd lista::largerAcc)
		else divide(List.tl lista, List.hd lista::smallerAcc, largerAcc)
	in divide(lista, [], [])
	
divideList [-3;3;2;0;-10]