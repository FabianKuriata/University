def zwroc[A](list:List[A]):List[A]={
  if(list == Nil) Nil
  else list.head::zwroc(list.tail))
}

def fa[A](a:A, b:A, c:Float):(List[A])={
  Nil
}

def fb(list:List[List[Float]], list2:List[List[Float]]):Boolean ={
   1==2
}



def najmniejsza(lista:List[Int]):Int = {
  def minimum(lista: List[Int],min:Int):Int = 
    if (lista == Nil) min
		else if (lista.head < min) minimum(lista.tail, lista.head)
		else minimum(lista.tail, min)
	minimum(lista, lista.head) 
}
		
najmniejsza(List(2,3,45,1,5))	