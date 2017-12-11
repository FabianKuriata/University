//
//	a)'a * 'a * float -> 'a list * float
//	b)float list list * float list list -> bool
//	c)'a list * 'a list * 'b -> 'a list * ('a * 'a)
//

def fa val k(a,b,c) =
    if( c > 1) (List(a), c)
    else (List(b), c)
    
def fb (a:List[List[Float]],b:List[List[Float]]) =
	if (a == List(List(3.0,2.0),List(3.0)) && b == List(List(2.0,1.0),List(1.0))) false
	else true
	
	
def fc[A] (a:List[A], b:List[A], c:A) =
	if(a != Nil) (a,(a.head, a.head))
	else (a,(b.head, b.head))
	
fc(List(3.3,4.2), List(2.2), "Janusz")