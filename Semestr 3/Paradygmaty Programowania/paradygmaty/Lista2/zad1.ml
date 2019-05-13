(*
	a)'a * 'a * float -> 'a list * float
	b)float list list * float list list -> bool
	c)'a list * 'a list * 'b -> 'a list * ('a * 'a)
*)

let fa (a, b, c) =
		if c > 1. then ([a],c)
		else ([b],c)
		
fa([1.,2.,3.],[2.,3.,4.],3.)	



let fb (a,b) =
	if a = [[3.;2.];[3.]] && b = [[2.;1.];[1.]] then false
	else true
	


let fc (a, b, c) =
	if(a != [] ) then (a,(List.hd a,List.hd a))
	else (a,(List.hd b,List.hd b))
	
	fc([3.;4.], [2.2], "Janusz")




