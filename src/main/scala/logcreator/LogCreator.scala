package logcreator

trait LogCreator

object Main extends App {  
  override def main (args: Array[String]) = {    
    val parsedArgs = args.map(_.toInt)
    
    val seed = if (parsedArgs.length > 0) parsedArgs(0) else 50
    val duration = if (parsedArgs.length > 1) parsedArgs(1) else 10000
    val total = if (parsedArgs.length > 2) parsedArgs(2) else 1000000
    
    val logCreator:LogCreator = 
      if (parsedArgs.length > 2) 
        LogCreatorIncremental(seed, duration, total)
      else 
        LogCreatorRandom(seed, duration) 
        
  }
}