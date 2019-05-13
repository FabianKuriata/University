class Tuple[A, B](var fst: A, var snd: B){
  override def toString = "("+ fst + "," + snd +")"
}

val par = new Tuple(4,"5")

par

class Pracownik(private val nazwisko: String) {
  var zwolniony: Boolean = false
  
  private var iloscPracownikow = Pracownik.zwieksz
  
  def zwolnij = {
    zwolniony = true
    iloscPracownikow = Pracownik.zmiejsz
  }
  
  def zwrocIloscPrac = Pracownik.iloscPrac
  
  override def toString = nazwisko + " " + zwolniony
}
object Pracownik {
  private var iloscPracownikow = 0
  def zwieksz = {
    iloscPracownikow += 1
    iloscPracownikow
  }
  def zmiejsz = {
    iloscPracownikow -= 1
    iloscPracownikow
  }
  def iloscPrac = iloscPracownikow
}

val p1 = new Pracownik("Jan")
val p2 = new Pracownik("Jansd")
val p3 = new Pracownik("wada")
//p1.zwolnij
p1.zwrocIloscPrac
p1

val krotka = (2, "3da")