package
{
	import flash.events.Event;
	
	public class DkolotovkinAndroidANEIntefraceEvent extends Event
	{
		public static const ON_AUTH_TOKEN_ACQUIRED:String = "ON_AUTH_TOKEN_ACQUIRED";
		
		public var data:Object;
		
		public function DkolotovkinAndroidANEIntefraceEvent(type:String, data:Object)
		{
			super(type, false, false);
			
			this.data = data;
		}
	}
}