package dtd.acternity;

public class OriginDestinationDistanceDTO {

	public class Elements{
		private Element[] elements;
	}
	public class Element{
		private ElementChild distance;
		private ElementChild duration;
		private String status;
	}
	
	public class ElementChild{
		private String text;
		private int value;
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
		
		
		
	}
	
	
}
