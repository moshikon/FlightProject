package demo.jxmapviewerdemo;

public class coords {
	private double lon;
	private double lat;
	private long timestamp;
	
	
	public coords(double lon, double lat, long timestamp) {
		this.lon = lon;
		this.lat = lat;
		this.timestamp= timestamp;
	}


	public double getLon() {
		return lon;
	}


	public void setLon(int lon) {
		this.lon = lon;
	}


	public double getLat() {
		return lat;
	}


	public void setLat(int lat) {
		this.lat = lat;
	}


	public long getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	

	
}
