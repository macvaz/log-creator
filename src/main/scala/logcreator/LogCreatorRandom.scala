package logcreator

import org.slf4j.Logger
import org.slf4j.LoggerFactory;

import scala.concurrent._
import ExecutionContext.Implicits.global

import scala.util.Random
import java.util.Calendar

object LogCreatorRandom {
  def apply(n: Integer, duration: Integer) = {
     new LogCreatorRandom(n, duration)
  }
}

class LogCreatorRandom(val seed: Integer, val duration: Integer) extends LogCreator {
  val logger = LoggerFactory.getLogger(classOf[LogCreatorRandom])

  def writer(logOp:String => Unit) = {
    var n = Random.nextInt(seed)*10
    
    while (n > 0) {
      messages.foreach { msg => logOp(msg) }
      n-=1
    }
  }
  
  while (true) {
  
    val infos = Future {
      writer(logger.info)
    }
    
    val debugs = Future {
      writer(logger.debug)
    }
    
    val errors = Future {
      writer(logger.error)
    }
    
    val warns = Future {
      writer(logger.warn)
    }
    
    val timeAtCompletion = for {
      _ <- infos
      _ <- debugs
      _ <- errors
      - <- warns
    } yield Calendar.getInstance.getTime
    
    timeAtCompletion.foreach { println(_) }
    
    Thread.sleep(duration.toLong)
  }
}