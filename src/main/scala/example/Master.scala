package example

import akka.routing.{ ActorRefRoutee, RoundRobinRoutingLogic, Router }
import akka.actor._
import org.slf4j.MDC

class Master extends Actor {
  var router = {
    val routees = Vector.fill(5) {
      val r = context.actorOf(Props[Worker].withDispatcher("my-dispatcher"))
      context watch r
      ActorRefRoutee(r)
    }
    Router(RoundRobinRoutingLogic(), routees)
  }

  var totalMessageCount = 0  

  def receive = {
    case s: String =>
      totalMessageCount += 1
      MDC.put("message", s)
      router.route(totalMessageCount, sender())
    case Terminated(a) =>
      router = router.removeRoutee(a)
      val r = context.actorOf(Props[Worker])
      context watch r
      router = router.addRoutee(r)
  }
}
