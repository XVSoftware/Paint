import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JPanel;

public class PaintPanel extends JPanel
{
	private boolean pressed = false;
	private int pressedX, pressedY;
	private int movedX, movedY;
	private ArrayList<Panel> panels = new ArrayList<Panel>();
	private int panelIndex = -1;
	private Menu menu = new Menu();
	private int windowWidth;
	private int windowHeight;
	private boolean inMenuBounds = false;
	private int canvasWidth = 500;
	private int canvasHeight = 500;	
	
	public PaintPanel()
	{
		panels.add(new Panel(PaintFrame.width-300-50,50,300,200,20, Color.red));
		panels.add(new Panel(PaintFrame.width-300-50,260,300,200,20, Color.green));
		panels.add(new Panel(PaintFrame.width-300-50,475,300,200,20, Color.blue, "Test"));
		panels.add(new Panel(20,100,100,500,20, Color.LIGHT_GRAY, "Toolbar"));
		panels.add(new Panel(200,200,300,300,30,Color.cyan,"Test panel"));
		
		Listener listen = new Listener();
		addMouseMotionListener(listen);
		addMouseListener(listen);
		this.setFocusable(true);
	}
	
	public void paint(Graphics g)
	{		
		PaintFrame.width = windowWidth = this.getWidth();
		PaintFrame.height = windowHeight = this.getHeight();
		
		g.setColor(new Color(80,80,80));
		g.fillRect(0,0,windowWidth,windowHeight);
		g.setColor(Color.white);	
		
		/*if((double)canvasWidth/windowWidth>=.8 || (double)canvasHeight/windowHeight>=.8)
		{
			System.out.println(canvasHeight);
			canvasHeight*=.85;
			canvasWidth*=.85;
		}
		else if((double)canvasWidth/windowWidth<=.4 || (double)canvasHeight/windowHeight<=.4) 
		{
			System.out.println(canvasHeight);
			canvasHeight*=10/8.5;
			canvasWidth*=10/8.5;
		}*/
		g.fillRect(windowWidth/2-canvasWidth/2,(windowHeight+20)/2-canvasHeight/2,canvasWidth,canvasHeight);
		
		if(panelIndex != -1)
		{
			panels.add(panels.get(panelIndex));
			panels.remove(panelIndex);
			panelIndex = panels.size()-1;
		}
		
		for(int i=0;i<panels.size();i++)
		{
			if(i==panelIndex)
				g.setColor(new Color(panels.get(i).c.getRed(),panels.get(i).c.getGreen(),panels.get(i).c.getBlue(),200));
			else
				g.setColor(panels.get(i).c);
			
			g.fillRoundRect(panels.get(i).x,panels.get(i).y,panels.get(i).width,panels.get(i).height,10,10);
			g.setColor(Color.black);
			g.drawString(panels.get(i).title,panels.get(i).x+panels.get(i).width/2-panels.get(i).title.length()*3+1,panels.get(i).y+14);
			g.setColor(Color.white);
			g.drawString(panels.get(i).title,panels.get(i).x+panels.get(i).width/2-panels.get(i).title.length()*3,panels.get(i).y+13);
			g.setColor(new Color(50,50,50));
			g.drawLine(panels.get(i).x, panels.get(i).y+panels.get(i).headerHeight, panels.get(i).x+panels.get(i).width-1, panels.get(i).y+panels.get(i).headerHeight);
			g.setColor(new Color(200,200,200));
			g.drawRoundRect(panels.get(i).x,panels.get(i).y,panels.get(i).width,panels.get(i).height,10,10);
		}
		
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0,0,this.getWidth(),20);
		for(int i=0;i<menu.getEntries().size();i++)
		{			
			for(int j=0;j<menu.getEntries().get(i).getEntries().size();j++)
			{
				if(menu.getEntries().get(i).getEntries().get(j).hidden == false)
				{
					if(menu.selectedMenuEntry==menu.getEntries().get(i).getEntries().get(j))
					{
						g.setColor(new Color(0,0,200));
						g.fillRect(i*75,(j+1)*20,75,20);
						g.setColor(Color.white);
					}
					else
					{
						g.setColor(Color.LIGHT_GRAY);
						g.fillRect(i*75,(j+1)*20,75,20);
						g.setColor(Color.black);
					}		
		
					g.drawString(menu.getEntries().get(i).getEntries().get(j).getLabel(), i*75+5, (j+1)*20+15);
				}
			}
			
			if(menu.selectedMenuEntry==menu.getEntries().get(i))
			{
				g.setColor(new Color(0,0,150));
				g.fillRect(i*75,0,75,20);
				g.setColor(Color.white);
			}
			else
			{
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(i*75,0,75,20);
				g.setColor(Color.black);
			}
			
			if(menu.openMenuEntry == menu.getEntries().get(i))
			{
				g.setColor(new Color(0,0,150));
				g.fillRect(i*75,0,75,20);
				g.setColor(Color.white);
			}

			g.drawString(menu.getEntries().get(i).getLabel(),i*75+5,15);
		}
	}
	
	private class Listener implements MouseMotionListener, MouseListener
	{
		@Override
		public void mouseDragged(MouseEvent e)
		{
			Panel currPanel = null;
			
			if(panelIndex != -1)
				currPanel = panels.get(panelIndex);
			
			if(currPanel != null)
			{
				if(pressed && (!inMenuBounds || menu.openMenuEntry == null))
				{
					movedX = e.getX();
					movedY = e.getY();
					
					panels.get(panelIndex).x += movedX - pressedX;
					panels.get(panelIndex).y += movedY - pressedY;
					
					if(panels.get(panelIndex).x<0)
						panels.get(panelIndex).x=0;				
					if(panels.get(panelIndex).x>windowWidth-panels.get(panelIndex).width)
						panels.get(panelIndex).x=windowWidth-panels.get(panelIndex).width;
					if(panels.get(panelIndex).y<20)
						panels.get(panelIndex).y=20;
					if(panels.get(panelIndex).y>windowHeight-panels.get(panelIndex).height)
						panels.get(panelIndex).y=windowHeight-panels.get(panelIndex).height;				
					
					repaint();
							
					pressedX = e.getX();
					pressedY = e.getY();
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent e)
		{					
			inMenuBounds = false;
			
			for(int i=0;i<menu.getEntries().size();i++)
			{
				MenuEntry currEntry = menu.getEntries().get(i);
				
				if(e.getX()>=currEntry.x && e.getX()<=currEntry.x+75 && e.getY()>=0 && e.getY()<=20)
				{
					inMenuBounds = true;
					
					if(currEntry.hidden)
					{
						menu.openMenuEntry = currEntry;
						currEntry.setVisible(true);
						currEntry.hidden = false;
						
						for(int j=0;j<menu.getEntries().size();j++)
							if(i!=j)
							{
								menu.getEntries().get(j).setVisible(false);
								menu.getEntries().get(j).hidden=true;
							}
					}
					else
					{
						menu.openMenuEntry = null;
						menu.selectedMenuEntry = null;
						currEntry.setVisible(false);
						currEntry.hidden=true;
					}
				}			
				
				for(int j=0;j<currEntry.getEntries().size();j++)
					if(!currEntry.getEntries().get(j).hidden && e.getX()>=currEntry.getEntries().get(j).x && e.getX()<=currEntry.getEntries().get(j).x+75 && e.getY()>=currEntry.getEntries().get(j).y && e.getY()<=currEntry.getEntries().get(j).y+20)
					{						
						currEntry.getEntries().get(j).performAction();
						inMenuBounds = true;		
					}
			}
			
			if(!inMenuBounds && menu.openMenuEntry != null)
			{
				menu.openMenuEntry.hidden = true;
				menu.openMenuEntry.setVisible(false);
				menu.openMenuEntry = null;
				menu.selectedMenuEntry = null;
			}
			
			if(!inMenuBounds || menu.openMenuEntry == null)
			{

				for(int i=0;i<panels.size();i++)
				{
					if(e.getX()>=panels.get(i).x && e.getX()<=panels.get(i).x+panels.get(i).width && e.getY()>=panels.get(i).y+panels.get(i).headerHeight && e.getY()<=panels.get(i).y+panels.get(i).height)
					{
						panelIndex=i;
						pressed=false;
						repaint();			
					}
					
					if(e.getX()>=panels.get(i).x && e.getX()<=panels.get(i).x+panels.get(i).width && e.getY()>=panels.get(i).y && e.getY()<=panels.get(i).y+panels.get(i).headerHeight)
					{
						panelIndex = i;
						pressed = true;
						
						repaint();						
					}
				}
								
				pressedX = e.getX();
				pressedY = e.getY();
			}
			repaint();
		}
				
		@Override
		public void mouseMoved(MouseEvent e)
		{
			menu.selectedMenuEntry = null;
			for(int i=0;i<menu.getEntries().size();i++)
			{
				MenuEntry currEntry = menu.getEntries().get(i);
				
				if(e.getX()>=currEntry.x && e.getX()<=currEntry.x+75 && e.getY()>=0 && e.getY()<=20)
				{
					menu.selectedMenuEntry = currEntry;
					
					for(int j=0;j<menu.getEntries().size();j++)
						if(i!=j)
						{
							menu.getEntries().get(j).setVisible(false);
							menu.getEntries().get(j).hidden=true;
							
							if(menu.openMenuEntry != null)
							{
								menu.selectedMenuEntry.hidden = false;
								menu.selectedMenuEntry.setVisible(true);
								menu.openMenuEntry = menu.selectedMenuEntry;
							}
						}
				}
				
				for(int j=0;j<currEntry.getEntries().size();j++)
					if(e.getX()>=currEntry.getEntries().get(j).x && e.getX()<=currEntry.getEntries().get(j).x+75 && e.getY()>=currEntry.getEntries().get(j).y && e.getY()<=currEntry.getEntries().get(j).y+20)
						menu.selectedMenuEntry = currEntry.getEntries().get(j);				
			}
			
			repaint();
		}
		
		@Override
		public void mouseClicked(MouseEvent arg0){}

		@Override
		public void mouseEntered(MouseEvent arg0){}

		@Override
		public void mouseExited(MouseEvent arg0){}

		@Override
		public void mouseReleased(MouseEvent arg0)
		{
			pressed = false;
		}		
	}
}
