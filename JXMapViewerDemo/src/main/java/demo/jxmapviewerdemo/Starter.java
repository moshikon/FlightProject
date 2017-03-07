/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.jxmapviewerdemo;

import java.util.TimeZone;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.event.MouseInputListener;
import org.jdesktop.swingx.OSMTileFactoryInfo;
import org.jdesktop.swingx.input.CenterMapListener;
import org.jdesktop.swingx.input.PanMouseInputListener;
import org.jdesktop.swingx.input.ZoomMouseWheelListenerCursor;
import org.jdesktop.swingx.mapviewer.DefaultTileFactory;
import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.jdesktop.swingx.mapviewer.TileFactoryInfo;

/**
 *
 * @author buchmueller
 */
public class Starter {
	public static HashMap<String, int[][]> cityData = null;
		
	public static String timeStampConv (long t){
		t = t * 1000;
    	Timestamp stamp = new Timestamp(t);
    	String res = stamp.toString();
    	return res;
	}
	
	public static String getDate(String stamp) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date date = sdf.parse(stamp);		
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		sdfDate.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
		String dateStr = sdfDate.format(date);
		return dateStr;
	}
	
	public static String getHour(String stamp) throws ParseException{ 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date date = sdf.parse(stamp);
    	SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm:ss a");
		sdfTime.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
		String timeStr = sdfTime.format(date);
		return timeStr; 
	}
	
	public static boolean distence (double x1, double x2, double y1, double y2){
		double res = (Math.sqrt( (x1 - x2)*(x1 - x2) ) + ( (y1 - y2)*(y1 - y2) ) );
		if (res < 0.1 )
			return true;
		else
			return false;
	}
	
	public static int getHourInt(long t) throws ParseException{
		String ts = timeStampConv(t);
		String hourF = getHour(ts);
		//System.out.println("hourF: "+hourF);
		String hourS = hourF.substring(0,2);
		int toReturn = Integer.parseInt(hourS);
		char ampm = hourF.charAt(9);
		if(ampm == 'p'){
			toReturn = (toReturn + 12)%24;
		}
		return toReturn;
		
	}
	public static int getDayOfWeek(long t) throws ParseException{
		String temp = timeStampConv(t);
		String xx = getDate(temp);
		
		int year = Integer.parseInt(xx.substring(0,4));
		int month = Integer.parseInt(xx.substring(5,7));;
		int day = Integer.parseInt(xx.substring(8,10));
		
		 Calendar calendar = Calendar.getInstance();
	     calendar.set(year,month, day);
	     int dayofweek=calendar.get(Calendar.DAY_OF_WEEK)+3;
	     if(dayofweek>7) dayofweek=dayofweek-7;
	    // System.out.println(dayofweek);
		
		return dayofweek;
	}
	 	 
    public static void main(String[] args) throws ParseException {
   	   	
    	try {
	         FileInputStream fileIn = new FileInputStream("cityData.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         cityData = (HashMap<String, int[][]>) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i) {
	         i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c) {
	         System.out.println("Class not found!");
	         c.printStackTrace();
	         return;
	      }
    	
    	//This is the class where you paint your own stuff - see it for details
       PowerwallSeminarTestViewer mapViewer = new PowerwallSeminarTestViewer();

        // Create a TileFactoryInfo for OpenStreetMap - so that the map style resembles OpenStreetmap data
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        mapViewer.setTileFactory(tileFactory);
        
        // Use 8 threads in parallel to load the tiles
        tileFactory.setThreadPoolSize(8);

        // Sets the initial viewport of the map - here, the coordinates of Zurich
        GeoPosition zrh = new GeoPosition(47.458333, 8.548056);
        mapViewer.setAddressLocation(zrh);
        
        // Sets the initial zoom of the map
        mapViewer.setZoom(9);

        //Sets interaction for the map
        MouseInputListener mia = new PanMouseInputListener(mapViewer);
        mapViewer.addMouseListener(mia);
        mapViewer.addMouseMotionListener(mia);
        mapViewer.addMouseListener(new CenterMapListener(mapViewer));
        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCursor(mapViewer));
        SelectionAdapter sa = new SelectionAdapter(mapViewer);
        SelectionPainter sp = new SelectionPainter(sa);
        mapViewer.addMouseListener(sa);
        mapViewer.addMouseMotionListener(sa);
        mapViewer.setOverlayPainter(sp);

        // Display the viewer in a JFrame
        JFrame frame = new JFrame("JXMapviewer2 Example 1");
        frame.getContentPane().add(mapViewer);
        frame.setSize(1680, 1050);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        //moshiko add
        CheckBox frame1 = new CheckBox();
        frame1.setSize(210, 160);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true); 
        }

}
