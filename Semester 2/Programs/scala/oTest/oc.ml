let rec replicate( word, x) =
	if x < 0 then failwith "Ujemna wartosc"
	else if x = 0 then []
	else
		word::replicate(word, x-1);;

let polindrome(list)=
	list = List.rev list;; 
	

let root3 a =
  let rec root3Helper x = 
    if abs_float(x ** 3. -. a) <= 1e-15 *. abs_float(a) then x
    else root3Helper(x +. (a /. x ** 2. -. x) /. 3.)
  in root3Helper(if a <= 1. then a else a /. 3.);;


root3 5.



let rec replaceNth(list, index, element) = 
match (list, index) with
([], _) -> []	
  | (h :: t, 0) -> element :: t
  | (h :: t, _) -> h :: replaceNth(t, index-1, element);;

replaceNth(['a';'b';'c'],2,'z')