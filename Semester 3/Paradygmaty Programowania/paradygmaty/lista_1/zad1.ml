let trojkat (a, b, c) =
	if ((a <= 0.)||(b <= 0.)||(c <= 0.)) then failwith "Podaj liczbe dodatnia!" 
	else if abs_float(b-.c) < a && a < b+.c then failwith "Podane odcinki nie tworza trojkata!"
	else 
		let p = (a+.b+.c)/.2. in p*.(p-.a)*.(p-.b)*.(p-.c);;

	