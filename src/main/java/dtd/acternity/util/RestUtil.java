package dtd.acternity.util;

import java.net.URI;
import java.net.URISyntaxException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import dtd.acternity.service.model.Location;

@Component
public class RestUtil {

	
	@Value("${google.api.key}")
	private String api_key ;

	public  int getDistanceByTwoPoints(Location from, Location to){
		String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins="+from.getLatitude()+","+from.getLongitude()
				+ "&destinations="+to.getLatitude()+","+to.getLongitude()+"&key="+api_key;
		int distanceValue = 0;
		RestTemplate rTemplate = new RestTemplate();
		try {
			ResponseEntity<String> response = rTemplate.getForEntity(new URI(url), String.class);
			if(response.getStatusCode().is2xxSuccessful()){
				String body = response.getBody();
				JSONObject obj = new JSONObject(body);
				JSONArray elements =  obj.getJSONArray("rows");
				JSONObject els = elements.getJSONObject(0);
				JSONArray elementsarr = els.getJSONArray("elements");
				JSONObject elementArray = elementsarr.getJSONObject(0);
				JSONObject distance = elementArray.getJSONObject("distance");
				JSONObject duration = elementArray.getJSONObject("duration");
				distanceValue = distance.getInt("value");
				int durationValue = duration.getInt("value");
			}
		} catch (RestClientException | URISyntaxException e) {
			e.printStackTrace();
		}
		
		
		return distanceValue;
	}
	
	public static void main(String[] args) {
		Location l1 = new Location("","",48.3550665200702 ,11.770747194412252);
		Location l2 = new Location("","",47.3766969,8.540349299999999);
		new RestUtil().getDistanceByTwoPoints(l1, l2);
	}
}
