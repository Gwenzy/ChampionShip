package fr.gwenzy.cs;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import fr.gwenzy.cs.objects.ChampionShip;

public class Main extends JavaPlugin
{
	public static String TAG = "";
	public static FileConfiguration config;
	public static ChampionShip cs;
	
	@Override
	public void onEnable()
	{
		this.saveDefaultConfig();
		config = this.getConfig();
		TAG = ChatColor.translateAlternateColorCodes('&',config.getString("messages.tag"));
		cs=new ChampionShip(this);
		Main.cs.resetState();
		getCommand("championship").setExecutor(new ChampionShipCommand());
		Bukkit.getServer().getPluginManager().registerEvents(new ChampionShipListener(), this);
	}
	
	@Override
	public void onDisable()
	{
		
	}
}