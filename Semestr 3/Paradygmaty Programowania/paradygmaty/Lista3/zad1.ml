	
let euler ep = 
	if ep < 0. then failwith("Wprowadzono ujemna wartosc")
	else 
		let rec eulerHelper(sum1, sum2, ep, n) =
			if abs_float((sum1 -. log(n -. 1.)) -. (sum2 -. log(n))) < ep then (sum2 -. log(n))
			else eulerHelper(sum2, sum2 +. (1. /. (n +. 1.)) , ep, n +. 1.)
		in eulerHelper(1. , 1.5, ep, 2.)

euler 0.00001

euler (-0.001)	
	
