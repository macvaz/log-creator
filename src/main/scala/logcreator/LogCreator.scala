package logcreator

import org.slf4j.Logger
import org.slf4j.LoggerFactory;

import scala.concurrent._
import ExecutionContext.Implicits.global

import scala.util.Random
import java.util.Calendar

object LogCreator extends App {  
  override def main (args: Array[String]) = {    
    val parsedArgs = args.map(_.toInt)
    
    val seed = if (parsedArgs.length > 0) parsedArgs(0) else 50
    val duration = if (parsedArgs.length > 1) parsedArgs(1) else 10000
    
    val logObject = LogCreator(seed, duration)
  }
  
  def apply(n: Integer, duration: Integer) = {
     new LogCreator(n, duration)
  }
}

class LogCreator(val seed: Integer, val duration: Integer) {
  val logger = LoggerFactory.getLogger(classOf[LogCreator])
  val messages = List("MSG1", "MSG2")

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