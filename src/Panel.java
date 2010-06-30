import java.awt.Color;

public class Panel 
{
	public int x;
	public int y;
	public int width; 
	public int headerHeight;
	public int height;
	public Color c;
	public String title = "";
	
	public Panel(int x, int y, int width, int height, int headerHeight, Color c)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.headerHeight = headerHeight;
		this.height = height;
		this.c = new Color(c.getRed(),c.getGreen(),c.getBlue(),100);
	}
	
	public Panel(int x, int y, int width, int height, int headerHeight, Color c, String title)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.headerHeight = headerHeight;
		this.height = height;
		this.c = new Color(c.getRed(),c.getGreen(),c.getBlue(),100);
		this.title = title;
	}
}
