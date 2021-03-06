package ImageHandling;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*import jp.ac.tsukuba.cs.conclave.earthquake.data.DataPoint;
import jp.ac.tsukuba.cs.conclave.earthquake.data.GeoLine;
import jp.ac.tsukuba.cs.conclave.earthquake.data.GeoPoint;
import jp.ac.tsukuba.cs.conclave.earthquake.faultmodel.FaultModel;*/

/**
 * This object handles the drawing of a map, and all its related info.
 * It does not retain any drawing info, so to do any significant redrawing, you 
 * have to draw all the primitives again.
 * 
 * @author caranha
 *
 */
public class MapImage {

	static BufferedImage map;
	
	// Latitude and longitude offsets;
	static double startlon;
	static double startlat;
    static double endlon;
    static double endlat;

	// Size of the map in pixels;
    static int width;
    static int height;
	
	
	/**
	 * 
	 * Creates an image that holds a map. 
	 * The start and end latitudes and longitudes should not be changed - zooming in and out happens 
	 * at GUI level, not at map drawing level.
	 * 
	 * @param w (Screen Size) width of the map
	 * @param h (Screen Size) height of the map
	 * @param startlon longitude at the SW corner of the map
	 * @param startlat latitude at the SW corner of the map
	 * @param endlon longitude at the NE corner of the map
	 * @param endlat latitude at the NE corner of the map
	 */
	public MapImage(int w, int h, double startlon, double startlat, double endlon, double endlat)
	{
		this.startlon = startlon;
		this.startlat = startlat;
		this.endlon = endlon;
		this.endlat = endlat;
		
		this.width = w;
		this.height = h;
		
		map = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
		this.clear();
	}

	public int getHeight()
	{
		return height;
	}
	public int getWidth()
	{
		return width;
	}
	
	
	/**
	 * Returns a BufferedImage for drawing;
	 * @return
	 */
	public BufferedImage getImage()
	{
		return map;
	}
		
	/**
	 * Clear the map for redrawing
	 */
	public void clear()
	{
		Graphics g = map.createGraphics();
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		map.flush();
	}
	
	/**
	 * Saves the image held at this moment to a filename;
	 * TODO: This is probably not the responsability of this class.
	 * @param filename
	 */
	public void saveToFile(String filename)
	{
		File f = new File(filename);
	    
		try {
			ImageIO.write(map, "png", f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Calculates the image position for a given longitude;
	 * @param lon
	 * @return
	 */
	private static int calculateScreenPosLongitude(double lon)
	{
		double ret = ((lon-startlon)/(endlon - startlon))*width;		
		return (int)Math.floor(ret);
	}
	
	/**
	 * Calculates the image position for a given latitude;
	 * This position is already inverted on the Y axis;
	 * @param lat
	 * @return
	 */
	private static int calculateScreenPosLatitude(double lat)
	{
		double ret = ((lat-startlat)/(endlat - startlat))*height;		
		return height - (int)Math.floor(ret);
	}
	
	public void drawRectangle(double slon, double slat, double elon, double elat, Color c)
	{
		int x0 = calculateScreenPosLongitude(slon);
		int y0 = calculateScreenPosLatitude(slat);
		int x1 = calculateScreenPosLongitude(elon)-x0;
		int y1 = y0-calculateScreenPosLatitude(elat); // Y goes from top down
		
		Graphics g = map.createGraphics();	
		g.setColor(c);
		g.fillRect(x0, y0, x1,y1);
		map.flush();
	}
	
	/*public void drawGeoLine(GeoLine l, Color c)
	{
		
		Iterator<GeoPoint> it = l.getIterator();
		GeoPoint prev, cur;
		Graphics g = map.createGraphics();	
		g.setColor(c);
		
		cur = it.next();
		while (it.hasNext())
		{
			int x0,y0,x1,y1;
			prev = cur;
			cur = it.next();
			x0 = calculateScreenPosLongitude(prev.lon);
			y0 = calculateScreenPosLatitude(prev.lat);
			x1 = calculateScreenPosLongitude(cur.lon);
			y1 = calculateScreenPosLatitude(cur.lat);
			g.drawLine(x0,y0,x1,y1);
		}
		
			
		map.flush();
	}*/
	
	/*public void drawFaultModelPlane(FaultModel fm, Color c1, Color c2)
	{
		Graphics g = map.createGraphics();	
		g.setColor(c1);
		
		
		double plane[][] = fm.getPlane();
		int j = 3;
		for (int i = 0; i < 4; i++)
		{
			if (i == 1)
				g.setColor(c2);
			else
				g.setColor(c1);
			int x0,y0,x1,y1;
			x0 = calculateScreenPosLongitude(plane[j][0]);
			y0 = calculateScreenPosLatitude(plane[j][1]);
			x1 = calculateScreenPosLongitude(plane[i][0]);
			y1 = calculateScreenPosLatitude(plane[i][1]);
						
			g.drawLine(x0,y0,x1,y1);
			j = i;
		}
		
		map.flush();		
	}*/
	
	/**
	 * Draw an event as a circle in the map
	// * @param p Event.
	 * @param c Color to draw
	 * @param s Size to draw (will be rounded down)
	 */
	/*public void drawEvent(DataPoint p, Color c, float s)
	{
		drawEvent(p.longitude, p.latitude, c, s);
	}	*/
	public static void drawEvent(double lon, double lat, Color c, float s)
	{
		Graphics g = map.createGraphics();	
		g.setColor(c);
		int x,y,r;
		
		x = calculateScreenPosLongitude(lon);
		y = calculateScreenPosLatitude(lat);
		r = (int) Math.floor(s);
		
		g.drawOval(x, y, r,r);
		map.flush();
	}
	
	
	
	/**
	 * Creates a MapImage with the correct latitude and longitude for a japanese map
	 * @return
	 */
	static public MapImage JapaneseMapFactory()
	{
		return new MapImage(800,800,125,24,150,47);
	}
}
