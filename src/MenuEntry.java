import java.util.ArrayList;

public class MenuEntry 
{
	private String label = "";
	private boolean disabled;
	private boolean hasRightMenu;
	public boolean selected = false;
	public ArrayList<MenuEntry> entries = new ArrayList<MenuEntry>();
	public boolean hidden = true;
	public int x = 0;
	public int y;
	
	public MenuEntry(String label)
	{
		this.label = label;
		hasRightMenu = false;
	}
	
	public MenuEntry(String label, boolean right)
	{
		this.label = label;
		hasRightMenu = right;
	}
	
	public void addMenuEntry(String label)
	{
		MenuEntry newEntry = new MenuEntry(label);
		newEntry.y = entries.size()*20+15;
		entries.add(newEntry);
	}
	
	public void setVisible(boolean visible)
	{
		if(visible)
			for(int i=0;i<entries.size();i++)
				entries.get(i).hidden=false;
		else
			for(int i=0;i<entries.size();i++)
				entries.get(i).hidden=true;
	}
	
	public ArrayList<MenuEntry> getEntries()
	{
		return entries;
	}
	
	public String getLabel()
	{
		return label;
	}
	
	public void performAction()
	{
		System.exit(0);
	}
}
