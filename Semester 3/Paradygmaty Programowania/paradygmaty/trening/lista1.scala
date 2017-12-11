
def poleTrojkata(a:Double, b:Double, c:Double):Double= 
  if(a < 0 || b < 0 || c < 0) throw new Exception("Ujemne parametry")
  else{
    val p = (a+b+c)/2
    if(math.abs(b-c) < a && a < b+c) math.sqrt(p*(p-a)*(p-b)*(p-c))
    else throw new Exception("Bledne parametry")
  }
  
poleTrojkata(-2,-3,4)

