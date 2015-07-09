package logcreator

trait LogCreator {
  val messages = List.fill(95)("Useless message for dashbord") ++ 
    List(
      """msg="Merchant: PreProAdyen successfully logged in using Adyen notifications", "op":"ADYEN_AUTH_HANDLER"""",  
      """msg="KPI: authorized_adyen_notification", "op":"ADYEN_AUTH_HANDLER"""" ) 
      
}

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
