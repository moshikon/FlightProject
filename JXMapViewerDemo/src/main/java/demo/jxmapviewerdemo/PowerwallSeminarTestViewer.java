package demo.jxmapviewerdemo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
	import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.mapviewer.GeoPosition;

/**
 *
 * In this class, you can paint on top of the map yourselves by overriding the
 * paintComponent-method of the JXMapViewer parent. Here, you would load your
 * data to and use it to paint stuff onto the map.
 *
 * @author buchmueller
 */
@SuppressWarnings("serial")
public class PowerwallSeminarTestViewer extends JXMapViewer {

	public static int[][] konstanz;
    public static int[][] konstanz1;
    public static int[][] konstanz2;
   
    public static int[][] reichenau1;
    public static  int[][] reichenau2;
    public static  int[][] reichenau3;
    public static int[][] reichenau4;
    public static int[][] reichenau5;
    public static  int[][] reichenau6;
    
    public int[][] stuttgart;

    public void fillWeek(String cityName, Graphics2D g){
    	colorCity(cityName, g, 0);
    }                    
    public void drawWeekend(String cityName, Graphics2D g){
    	colorCity(cityName, g, 1);
    }                        
    public void fillOrDraw(int[] x, int[] y, int length, Graphics2D g, int dOrF){
    	switch(dOrF){
    	case 0:
    		g.fillPolygon(x, y, length);
    		break;
    	case 1:
    		g.drawPolygon(x, y, length);
    		break;
    	}
    }
    
	public void colorKonstanz(Graphics2D g1, int fillOrDraw){
		fillOrDraw(konstanz[0], konstanz[1], konstanz[0].length, g1, fillOrDraw);
		fillOrDraw(konstanz1[0], konstanz1[1], konstanz1[0].length, g1, fillOrDraw);
		fillOrDraw(konstanz2[0], konstanz2[1], konstanz2[0].length, g1, fillOrDraw);
	}

	public void colorReichenau(Graphics2D g1, int fillOrDraw){
		fillOrDraw(reichenau1[0], reichenau1[1], reichenau1[0].length, g1, fillOrDraw);
		fillOrDraw(reichenau2[0], reichenau2[1], reichenau2[0].length, g1, fillOrDraw);
		fillOrDraw(reichenau3[0], reichenau3[1], reichenau3[0].length, g1, fillOrDraw);
		fillOrDraw(reichenau4[0], reichenau4[1], reichenau4[0].length, g1, fillOrDraw);
		fillOrDraw(reichenau5[0], reichenau5[1], reichenau5[0].length, g1, fillOrDraw);
		fillOrDraw(reichenau6[0], reichenau6[1], reichenau6[0].length, g1, fillOrDraw);
	}
	
	public void colorStuttgart(Graphics2D g1, int fillOrDraw){
		fillOrDraw(stuttgart[0], stuttgart[1], stuttgart[0].length, g1, fillOrDraw);
	}
	
	public void updateCities(){
        konstanz = geoToArray(CityCoords.konstanzX, CityCoords.konstanzY);
        konstanz1 = geoToArray(CityCoords.konstanz1X, CityCoords.konstanz1Y);
        konstanz2 = geoToArray(CityCoords.konstanz2X, CityCoords.konstanz2Y);
       
        reichenau1 = geoToArray(CityCoords.reichenau1X, CityCoords.reichenau1Y);
        reichenau2 = geoToArray(CityCoords.reichenau2X, CityCoords.reichenau2Y);
        reichenau3 = geoToArray(CityCoords.reichenau3X, CityCoords.reichenau3Y);
        reichenau4 = geoToArray(CityCoords.reichenau4X, CityCoords.reichenau4Y);
        reichenau5 = geoToArray(CityCoords.reichenau5X, CityCoords.reichenau5Y);
        reichenau6 = geoToArray(CityCoords.reichenau6X, CityCoords.reichenau6Y);
        
        stuttgart = geoToArray(CityCoords.stuttgart1X, CityCoords.stuttgart1Y);

	}
	
	public int[][] geoToArray(double[] tempx, double[] tempy){
		int arr[][] = new int[2][tempx.length]; 
		Point2D f;
		for(int i=0; i<tempx.length; i++){
			f = convertGeoPositionToPoint(new GeoPosition(tempx[i], tempy[i]));
			arr[0][i] = (int)f.getX();
			arr[1][i] = (int)f.getY();
		}
		return arr;
	}
	
	public int numOfFlights(String cityName,int start,int end,int timeOfWeek){
    	int[][] cityHours = Starter.cityData.get(cityName);
    	int toReturn = 0;
    	for(int i= start ; i<=end; i++){
    		toReturn = toReturn + cityHours[timeOfWeek][i];
    	}
    	return toReturn;
    }
    
    public void colorCity(String cityName, Graphics2D g, int timeOfWeek){
    	switch (cityName){
    	case "Niederhasli":
    		colorKonstanz(g, timeOfWeek);
    		break;
    	case "Reichenau":
    		colorReichenau(g, timeOfWeek);
    		break;
    	case "Frauenfeld":
    		colorStuttgart(g, timeOfWeek);
    		break;
    	/*Add all cities*/
    	}
    }
	
    
    /**
     * This method gets called every time the view changes (panning, zooming) or
     * the data changes (in which case you would have to call repaint() on this
     * object from outside.
     *
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
    	
    	updateCities();
    	
        //First, the map is painted. 
        super.paintComponent(g);
        //Now, you can paint your own stuff onto the map. This is how it works:

        //This is the object you use to paint
        Graphics2D g2 = (Graphics2D) g;
        
        //moshiko added
      //  Graphics2D g1 = (Graphics2D) g.create();
        Color pointcolor;
        String[] names = new String[2];
        names[0]="Niederhasli";
        names[1]="Frauenfeld";
  //      Graphics2D[] graphics = new Graphics2D[1];
  //      graphics[0]=(Graphics2D) g.create();
//        graphics[1]=(Graphics2D) g.create();
        
      //  g2.setColor(Color.blue);
      //  colorStuttgart(g2, 0);
        
//important loop
        for (int i = 0; i < 2; i++) {
        	if(CheckBox.days == 2 || CheckBox.days == 3){
        		pointcolor = ColorHelper.numberToColor(numOfFlights(names[i],CheckBox.start,CheckBox.end,0)); //0 is weekend
        		g2.setColor(pointcolor);
        		fillWeek(names[i],g2);
        	}
        	if(CheckBox.days == 1 || CheckBox.days == 3){
        		pointcolor = ColorHelper2.numberToColor(numOfFlights(names[i],CheckBox.start,CheckBox.end,1)); //1 is weekend
        		g2.setColor(pointcolor);
        		g2.setStroke(new BasicStroke(5));
        		drawWeekend(names[i],g2);
        	}
        }
        
        
        /*
        
        //You can use it to draw information on the whole window in general, regardless of the map:
        //Setting the drawing color
        g2.setColor(Color.blue);
        //setting the font 
     //   g2.setFont(new Font("DAVID", Font.BOLD, 50));
     //   g2.drawString("FLIGHT PROJECT", 10, 50);

        //Or you can draw things on a specific spot on the map. 
        //To do this, you have to take the geo coordinates which 
        //you want to draw upon and convert them to screen coordinates like this.
        //Let's assume you want to draw a point on top of the city of Zurich.
        
        Pre_processing.set_city();
        
        //Then, we convert them to screen coordinates using convertGeoPositionToPoint()
        //provided by the parent class. This also works the other way round: convertPointToGeoPosition() 
        //gives you the Geo coordinates of a screen point (useful for interaction)
        Point2D zurich = convertGeoPositionToPoint(new GeoPosition(Pre_processing.cities[0][0], Pre_processing.cities[0][1]));
        Point2D konstanz = convertGeoPositionToPoint(new GeoPosition(Pre_processing.cities[1][0], Pre_processing.cities[1][1]));
        Point2D Nurnberg = convertGeoPositionToPoint(new GeoPosition(Pre_processing.cities[2][0], Pre_processing.cities[2][1]));
        Point2D Donaueschingen = convertGeoPositionToPoint(new GeoPosition(Pre_processing.cities[3][0], Pre_processing.cities[3][1]));
        Point2D Zimmern = convertGeoPositionToPoint(new GeoPosition(Pre_processing.cities[4][0], Pre_processing.cities[4][1]));
        Point2D Pforzheim = convertGeoPositionToPoint(new GeoPosition(Pre_processing.cities[5][0], Pre_processing.cities[5][1]));
        Point2D Waldshut = convertGeoPositionToPoint(new GeoPosition(Pre_processing.cities[6][0], Pre_processing.cities[6][1]));
        Point2D Kempten = convertGeoPositionToPoint(new GeoPosition(Pre_processing.cities[7][0], Pre_processing.cities[7][1]));
        Point2D Sulz = convertGeoPositionToPoint(new GeoPosition(Pre_processing.cities[8][0], Pre_processing.cities[8][1]));
                


        //moshiko added
        Color pointcolor2 = ColorHelper.numberToColor(90);
        g1.setColor(pointcolor2);
                     
        //Now we draw the point on the map
        g1.fillOval((int)zurich.getX(), (int)zurich.getY(), 20, 20);
        g1.fillOval((int)konstanz.getX(), (int)konstanz.getY(), 20, 20);
        g1.fillOval((int)Nurnberg.getX(), (int)Nurnberg.getY(), 20, 20);
        g1.fillOval((int)Donaueschingen.getX(), (int)Donaueschingen.getY(), 20, 20);
        g2.fillOval((int)Zimmern.getX(), (int)Zimmern.getY(), 20, 20);
        g2.fillOval((int)Pforzheim.getX(), (int)Pforzheim.getY(), 20, 20);
        g2.fillOval((int)Waldshut.getX(), (int)Waldshut.getY(), 20, 20);
        g2.fillOval((int)Kempten.getX(), (int)Kempten.getY(), 20, 20);
        g2.fillOval((int)Sulz.getX(), (int)Sulz.getY(), 20, 20);
        
        int xPoly[] = {(int)zurich.getX(), (int)konstanz.getX(),(int)Nurnberg.getX()};
        int yPoly[] = {(int)zurich.getY(), (int)konstanz.getY(),(int)Nurnberg.getY()};

       // g2.fillPolygon(xPoly, yPoly, xPoly.length);
        g1.setStroke(new BasicStroke(30));
        g1.drawPolygon(xPoly, yPoly, xPoly.length);
        //For more complex visualizations, you have to combine the primitive drawing functions the g2 object gives you.
        
        */
    }
}


