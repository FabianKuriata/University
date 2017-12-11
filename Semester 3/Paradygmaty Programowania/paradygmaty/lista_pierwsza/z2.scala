def trojkat (a : Float, b : Float, c : Float) = {
  if ((a<=0)||(b<=0)||(c<=0)) throw newException("Podaj liczbe dodatnia!") 
	else if (math.abs(b-c)<a && a<b+c)  throw newException( "Podane odcinki nie tworza trojkata!")
	else {
	  def p = (a+b+c)/2
	  p*(p-a)*(p-b)*(p-c)
	}
}

trojkat(3, 4, 5)