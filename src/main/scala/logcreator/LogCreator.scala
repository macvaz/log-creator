package logcreator

trait LogCreator {
  val messages = List.fill(95)("msg=Useless message for dashbord") ++ 
    List(
      "op=ADYEN_AUTH_HANDLER|user=NA|corr=20b4a06b-626a-4917-a399-ab85e0a31a78|msg=Merchant: PreProAdyen successfully logged in using Adyen notification", 
      "op=ADYEN_AUTH_HANDLER|user=NA|corr=20b4a06b-626a-4917-a399-ab85e0a31a78|msg=KPI: authorized_adyen_notification",
      "op=ADYEN_AUTH_HANDLER|user=NA|corr=48a2fe4f-dc6d-4e57-a270-2bc5f9e201e8|msg=Merchant: PreProAdyen successfully logged in using Adyen notification",
      "op=ADYEN_BROKER_CHARGE|user=559b9a5f6228c93e8ae2479e|corr=af8568b2-8998-4880-b9ac-671795736fb1|msg=KPI: transaction_updated: 52e7cc2d9e29efe120c6ca61_1a7ec845-604b-48e7-a943-096f0107deba status: Authorised",
      "op=PAYPAL_NOTIFICATION_HANDLER|user=NA|corr=6ebfefd4-1fcc-4111-a24a-3230e1cf893b|msg=Validating notification for transaction id: 53f2fc487515b24afc457859_44541a41-4c87-46cc-90ee-e7cd7bbef1ab" ) 
      
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