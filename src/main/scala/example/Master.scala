package example

import akka.actor._
import akka.routing.FromConfig
import org.slf4j.MDC

class Master extends Actor {  
  var router = context.actorOf(FromConfig.props(Props[Worker]), "router1")

  var totalMessageCount = 0  

  def receive = {
    case s: String =>
      totalMessageCount += 1
      MDC.put("message", s)
      router ! totalMessageCount
  }
}
