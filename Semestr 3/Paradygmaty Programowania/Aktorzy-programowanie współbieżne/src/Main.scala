import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.ActorRef

case class StartGame(duration: Int, msg:String, other: ActorRef)
case class HitBall(duriation: Int, msg: String)

class Player extends Actor {
  def receive = {
    case StartGame(duration, msg, other) => 
      print(self.path.name + ": ")
      println(msg)
      other ! HitBall(duration-1, msg)
    case HitBall(duration, msg) => 
      if(duration > 0){
        //println(msg)
        msg match {
          case "ping" =>
            print(self.path.name + ": ")
            println("pong")
            sender ! HitBall(duration-1, "pong")
          case "pong" => 
            print(self.path.name + ": ")
            println("ping")
            sender ! HitBall(duration-1, "ping")
        }
      }
      else {
        println("Game Over")
        context.system.shutdown()
      }
    case _ => 
      println("Nie obslugiwany komunikat")
      //context.system.shutdown()
  }
}

object Main extends App {
    
    val system = ActorSystem("MySystem")
    val player1 = system.actorOf(Props[Player],"Kazimierz")
    val player2 = system.actorOf(Props[Player],"Janusz")
    val duration = 5;
    val startMessage = "ping"
    player1 ! StartGame(duration, startMessage, player2)
}
