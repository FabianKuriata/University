let poleTrojkata(a, b, c)=
	if(a < 0. || b < 0. || c < 0.) then raise(Failure "Ujemne parametry") 
	else 
		if abs_float(b -. c) < a && a < b +. c  then sqrt(5. *. (5. -. a) *. (5. -. b)*.(5. -. c)) 
		else raise (Failure "Bledne parametry");;

poleTrojkata(2.,3.,4.)