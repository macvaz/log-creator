package logcreator

trait LogCreator {
  val messages = List.fill(95)(""""msg":"Useless message for dashbord"""") ++ 
    List(
      """"msg":"Merchant: PreProAdyen successfully logged in using Adyen notifications", "op":"ADYEN_AUTH_HANDLER"""",  
      """"msg":"KPI: authorized_adyen_notification", "op":"ADYEN_AUTH_HANDLER"""",
      """"msg":"Merchant: PreProAdyen successfully logged in using Adyen notification", "op":"ADYEN_AUTH_HANDLER"""",
      """"msg":"KPI: transaction_updated: 52e7cc2d9e29efe120c6ca61_1a7ec845-604b-48e7-a943-096f0107deba status: Authorised", "op":"ADYEN_BROKER_CHARGE"""",
      """"msg":"Validating notification for transaction id: 53f2fc487515b24afc457859_44541a41-4c87-46cc-90ee-e7cd7bbef1ab", "op":"PAYPAL_NOTIFICATION_HANDLER""""
    ) 
      
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
