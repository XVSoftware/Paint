import java.util.ArrayList;

public class Menu 
{
	private ArrayList<MenuEntry> entries = new ArrayList<MenuEntry>();
	public MenuEntry selectedMenuEntry = null;
	public MenuEntry openMenuEntry = null;
	
	public Menu()
	{
		MenuEntry file = new MenuEntry("File");
		MenuEntry edit = new MenuEntry("Edit");
		MenuEntry help = new MenuEntry("Help");
		
		entries.add(file);
		entries.add(edit);
		entries.add(help);
		
		edit.addMenuEntry("7");
		edit.addMenuEntry("8");
		edit.addMenuEntry("9");
		
		help.addMenuEntry("10");
		help.addMenuEntry("11");
		help.addMenuEntry("12");
		help.addMenuEntry("13");
		help.addMenuEntry("About");
		
		file.addMenuEntry("New");
		file.addMenuEntry("2");
		file.addMenuEntry("3");
		file.addMenuEntry("4");
		file.addMenuEntry("5");
		file.addMenuEntry("6");
		file.addMenuEntry("Exit");
		
		for(int i=0;i<entries.size();i++)
		{
			entries.get(i).x = i*75;
			
			for(int j=0;j<entries.get(i).getEntries().size();j++)
				entries.get(i).getEntries().get(j).x=i*75;
		}
	}
	
	public ArrayList<MenuEntry> getEntries()
	{
		return entries;
	}
}