import java.awt.Dimension;
import javax.swing.JFrame;

public class PaintFrame extends JFrame
{
	public static JFrame frame;
	public static int width = 1280;
	public static int height = 800;
	
	public static void main(String[] args) 
	{
		frame = new JFrame("Paint");
		frame.setPreferredSize(new Dimension(width, height));
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		PaintPanel panel = new PaintPanel();
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}
}
