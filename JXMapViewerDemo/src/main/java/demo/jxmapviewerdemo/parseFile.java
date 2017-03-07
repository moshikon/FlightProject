package demo.jxmapviewerdemo;

import java.util.Calendar;
import java.util.Set;
import java.util.Map.Entry;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import java.io.FileNotFoundException;
import java.io.FileReader;



// TODO: Auto-generated Javadoc
/**
 * The Class parseFile. Recieves the json file and parses it. creates {@link MicroService}
 */
public class parseFile {
	
	/** The initial array. */
	private coords[] initialJavaArray;
	
	/**
	 * Instantiates a new parses the file.
	 *
	 * @param reader the reader
	 */
	@SuppressWarnings("unchecked")
	public parseFile(JsonReader reader){
		JsonElement jelement = new JsonParser().parse(reader);
		JsonObject  allThings = jelement.getAsJsonObject();
		
		JsonArray initialJsonArray = allThings.getAsJsonArray("trail");
		initialJavaArray = new coords[initialJsonArray.size()];
		double lon;
		double lat;
		String lon1;
		String lat1;
		String timestamp;
		JsonObject temp;
		
		//sets an array of coordinates for a certain flight
		for(int i=0; i<initialJavaArray.length; i++){
			temp = initialJsonArray.get(i).getAsJsonObject();
			Set<Entry<String, JsonElement>> keys =  temp.entrySet();
			
			timestamp = ((Entry<String, JsonElement>)(keys.toArray())[0]).getKey();
			lon1 = ((Entry<String, JsonElement>)(keys.toArray())[0]).getValue().getAsJsonArray().get(1).getAsString();
			lat1 = ((Entry<String, JsonElement>)(keys.toArray())[0]).getValue().getAsJsonArray().get(0).getAsString();
			lon = Float.valueOf(lon1);
			lat = Float.valueOf(lat1);
			
			initialJavaArray[i] = new coords(lon, lat, Long.parseLong(timestamp, 10));
			
		}
	}
		
	public coords[] getCoords() {
		return initialJavaArray;
	}
		
	public static void main(String[] args){
		JsonReader reader=null;
		
	//	log.log( Level.INFO, "Reading JSON file");
		try {
			reader = new JsonReader(new FileReader(args[0]));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		parseFile x = new parseFile(reader);
		System.out.println(x.getCoords()[2].getTimestamp());
		/*end of parser*/
		
		
		/*Day of the week*/
		String xx = "2016-12-05";
		int year = Integer.parseInt(xx.substring(0,4));
		int month = Integer.parseInt(xx.substring(5,7));;
		int day = Integer.parseInt(xx.substring(8,10));
		
		 Calendar calendar = Calendar.getInstance();
	     calendar.set(year,month, day);
	     int dayofweek=calendar.get(Calendar.DAY_OF_WEEK)+3;
	     if(dayofweek>7) dayofweek=dayofweek-7;
	     System.out.println(dayofweek);

	}
}
