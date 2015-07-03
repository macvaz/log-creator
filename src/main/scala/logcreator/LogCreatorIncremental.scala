package logcreator

import org.slf4j.Logger
import org.slf4j.LoggerFactory;

import scala.concurrent._
import ExecutionContext.Implicits.global

import java.util.Calendar

object LogCreatorIncremental {
    def apply(n: Integer, duration: Integer, total: Integer) = {
       new LogCreatorIncremental(n, duration, total)
    }
  }
  
  class LogCreatorIncremental(val numMessages: Integer, val duration: Integer, val total:Integer) extends LogCreator {
    val logger = LoggerFactory.getLogger(classOf[LogCreatorIncremental])
    var counter = 1
  
    def writer(logOp:String => Unit) = {
      var n = numMessages
      while (n > 0) {
        logOp(counter.toString)
        counter += 1
        n -= 1
      }
    }
    
    while (counter <= total) {
      val infos = Future {
        writer(logger.info)
      }
      
      val timeAtCompletion = for {
        _ <- infos
      } yield Calendar.getInstance.getTime
      
      timeAtCompletion.foreach { println(_) }
      
      Thread.sleep(duration.toLong)
    }
  }