def sqrList[A](list:List[Int]):List[Int] = 
if (list == Nil) Nil
else list.head * list.head :: sqrList(list.tail)

def polindrome[A](list:List[A])=
   list==list.reverse
   
polindrome(List(1,2,3))


val double = (x:Int) => x+x 
double(6)

def evenR(n: Int): Boolean =       
  if (n==0) true else oddR(n-1) 
def oddR(n: Int): Boolean =
  if (n==0) false else evenR(n-1) 
  
  
evenR(100000)

def f(n:Int) = {
    def ff(n:Int, f1:Int, f2:Int):Int=
      if(n == 0) f1
      else if(n==1) f2
      else ff(n-1, f2, f1+f2)
    ff(n,0,1)
  }
f(45)