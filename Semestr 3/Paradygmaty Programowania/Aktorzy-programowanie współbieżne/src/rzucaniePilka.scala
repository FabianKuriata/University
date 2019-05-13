import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import scala.util.Random

case object Start
case class Ball(count: Int)


class Player(val num: Int, val players: Array[ActorRef]) extends Actor{
  val r = new Random()
  
  def receive = {
    case Start => 
      println(self.path.name)
      println("Nr zaczynajacego: " + num + " zaczyna gre")
      var id = r.nextInt(3)
      while(id == (num-1))
        id = r.nextInt(3)
      
      println(self.path.name +" rzuca do " + players(id).path.name)
      players(id) ! Ball(1)
      
    case Ball(count) =>
      if(count < 20) {
        var id = r.nextInt(3)
        while(id == (num-1))
          id = r.nextInt(3)
   
        println("\n" + self.path.name + " rzuca do " + players(id).path.name)
        println("Nr rzucajacego: " + num + " Rzut:" + count)
        
        players(id) ! Ball(count + 1) 
      }
      else
        context.system.shutdown()
      
    case _ => println("Cos poszlo nie tak")
    
  }
}

object Play extends App {
  val system = ActorSystem("System")
  val players = new Array[ActorRef](3)
  val player1: ActorRef = system.actorOf(Props(classOf[Player], 1, players),"Jan")
  val player2: ActorRef = system.actorOf(Props(classOf[Player], 2, players),"Maciek")
  val player3: ActorRef = system.actorOf(Props(classOf[Player], 3, players),"Ala")
  players(0) = player1
  players(1) = player2
  players(2) = player3
  
  player1 ! Start
}


