package example

import akka.actor._
import akka.routing._
import akka.routing.FromConfig
import scala.util.Random
import org.slf4j.MDC

object Main {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("routerActorSystem")
    val master: ActorRef = system.actorOf(Props[Master], "master")
    val r = new Random()
    while(true) {
        val msg = "Hey it's " + System.currentTimeMillis 
        master ! msg
        Thread.sleep(r.nextInt(500))
    }
    // runs forever intentionally - use Ctrl-C to stop the process
  }
}
