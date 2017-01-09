package 
{
	import flash.events.EventDispatcher;
	import flash.events.StatusEvent;
	import flash.external.ExtensionContext;

	public class DkolotovkinAndroidANEIntefrace extends EventDispatcher
	{
		private var context:ExtensionContext;
		
		public function DkolotovkinAndroidANEIntefrace()
		{
			if(!context)
			{
				context = ExtensionContext.createExtensionContext("com.dkolotovkin.androidane", null);
				context.addEventListener(StatusEvent.STATUS, onStatus);
			}
		}
		
		private function onStatus(event:StatusEvent):void
		{
			if(event.code == DkolotovkinAndroidANEIntefraceEvent.ON_AUTH_TOKEN_ACQUIRED)
				dispatchEvent(new DkolotovkinAndroidANEIntefraceEvent(DkolotovkinAndroidANEIntefraceEvent.ON_AUTH_TOKEN_ACQUIRED, event.level));
		}
		
		public function getAccounts():String
		{
			return context.call("getAccounts") as String;
		}
		
		public function getAuthToken(accountName:String):void
		{
			context.call("getAuthToken", accountName);
		}
	}
}