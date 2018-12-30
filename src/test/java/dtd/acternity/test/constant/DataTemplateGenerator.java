package dtd.acternity.test.constant;

import java.net.URI;

import dtd.acternity.service.model.Location;

public class DataTemplateGenerator {

	public static Location getShipper(){
		Location l = new Location();
		l.setAddress("customer 1 test");
		l.setAdd_id(123451042L);
		l.setLatitude(48.3550665200702);
		l.setLongitude(11.770747194412252);
		return l;
	}
	
	public static Location getConsignee(){
		Location l = new Location();
		l.setAdd_id(123451432L);
		l.setAddress("customer 2 test2");
		l.setLatitude(47.3766969);
		l.setLongitude(8.540349299999999);
		return l;
	}

	public static String getItemDescription() {
		String item = "1354:0:asses |AVI|1|Each|1.0|1|1|1|1.0|| | | | | | ";
		return item;
	}

	public static String getCourier1AddId() {
		String courier1AddId = "12345858";
		return courier1AddId;
	}

	public static String getCourier2AddId() {
		String courier2AddId = "123451399";
		return courier2AddId;
	}
	
}
