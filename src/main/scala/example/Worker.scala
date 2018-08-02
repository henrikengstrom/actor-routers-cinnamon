package example

import akka.actor._
import org.slf4j.MDC

class Worker extends Actor with ActorLogging {
  def receive = {
    case messageCount: Int => 
      log.info(s"MDC message: ${MDC.get("message")}, for message count: $messageCount")
  }
}