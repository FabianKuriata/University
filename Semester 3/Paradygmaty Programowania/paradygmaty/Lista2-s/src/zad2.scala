def firstAndLast[A](list:List[A]):List[A]={
  if(list == Nil) Nil
  else list.head :: list.reverse.head :: Nil;
}

firstAndLast(List(1,2,3,54,4))
