package fr.gwenzy.cs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

public class ChampionShipCommand implements TabExecutor
{

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd,
			String CommandLabel, String[] args) {
		List <String> tab = new ArrayList<String>();
		if(args.length==1)
		{
			tab.add("init");
			return tab;
		}
		return null;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel,
			String[] args) {
		if(sender instanceof Player)
		{
			if(args.length>0)
			{
				Player p = (Player) sender;
				if(args[0].equalsIgnoreCase("init"))
				{
					Main.cs.init((Player) sender);
					Main.cs.resetState();
					Main.cs.nextState();
				}
				else if (args[0].equalsIgnoreCase("status"))
				{
					String state="";
					switch(Main.cs.getState())
					{
					case 0:
						state = "Initialisation";
						break;
					case 1:
						state = "Poule 1 Match 1 1/3 --> A démarrer";
						break;
					case 2:
						state = "Poule 1 Match 1 2/3 --> A démarrer";
						break;
					case 3:
						state = "Poule 1 Match 1 3/3 --> A démarrer";
						break;
					case 4:
						state = "Poule 1 Match 2 1/3 --> A démarrer";
						break;
					case 5:
						state = "Poule 1 Match 2 2/3 --> A démarrer";
						break;
					case 6:
						state = "Poule 1 Match 2 3/3 --> A démarrer";
						break;
					case 7:
						state = "Poule 1 Match 2 1/3 --> A démarrer";
						break;
					case 8:
						state = "Poule 1 Match 3 2/3 --> A démarrer";
						break;
					case 9:
						state = "Poule 1 Match 3 3/3 --> A démarrer";
						break;
					case 10:
						state = "Poule 1 Match 4 1/3 --> A démarrer";
						break;
					case 11:
						state = "Poule 1 Match 4 2/3 --> A démarrer";
						break;
					case 12:
						state = "Poule 1 Match 4 3/3 --> A démarrer";
						break;
					case 13:
						state = "Poule 2 Match 1 1/3 --> A démarrer";
						break;
					case 14:
						state = "Poule 2 Match 1 2/3 --> A démarrer";
						break;
					case 15:
						state = "Poule 2 Match 1 3/3 --> A démarrer";
						break;
					case 16:
						state = "Poule 2 Match 2 1/3 --> A démarrer";
						break;
					case 17:
						state = "Poule 2 Match 2 2/3 --> A démarrer";
						break;
					case 18:
						state = "Poule 2 Match 2 3/3 --> A démarrer";
						break;
					case 19:
						state = "Poule 3 Match 1 1/3 --> A démarrer";
						break;
					case 20:
						state = "Poule 3 Match 1 2/3 --> A démarrer";
						break;
					case 21:
						state = "Poule 3 Match 1 3/3 --> A démarrer";
						break;
					
					}
					HashMap<Integer, Integer> p1m1;
					HashMap<Integer, Integer> p1m2;
					HashMap<Integer, Integer> p1m3;
					HashMap<Integer, Integer> p1m4;
					HashMap<Integer, Integer> p2m1;
					HashMap<Integer, Integer> p2m2;
					HashMap<Integer, Integer> p3m1;
					p1m1=Main.cs.getPoule1Match1();
					p1m2=Main.cs.getPoule1Match2();
					p1m3=Main.cs.getPoule1Match3();
					p1m4=Main.cs.getPoule1Match4();
					p2m1=Main.cs.getPoule2Match1();
					p2m2=Main.cs.getPoule2Match2();
					p3m1=Main.cs.getPoule3Match1();
					p.sendMessage(p1m1.toString());
					p.sendMessage(ChatColor.GOLD+"----------Infos Championnat---------");
					p.sendMessage(ChatColor.GOLD+"Etat actuel du championnat : "+state);
					p.sendMessage(ChatColor.GOLD+"Poule 1 Match 1: "+Main.cs.getPlayerFromId(Integer.parseInt(p1m1.keySet().toArray()[0].toString()))+"("+p1m1.get(p1m1.keySet().toArray()[0])+"), "+Main.cs.getPlayerFromId(Integer.parseInt(p1m1.keySet().toArray()[1].toString()))+"("+p1m1.get(p1m1.keySet().toArray()[1])+"), "+Main.cs.getPlayerFromId(Integer.parseInt(p1m1.keySet().toArray()[2].toString()))+"("+p1m1.get(p1m1.keySet().toArray()[2])+"), "+Main.cs.getPlayerFromId(Integer.parseInt(p1m1.keySet().toArray()[3].toString()))+"("+p1m1.get(p1m1.keySet().toArray()[3])+"), ");
					p.sendMessage(ChatColor.GOLD+"Poule 1 Match 2: "+Main.cs.getPlayerFromId(Integer.parseInt(p1m2.keySet().toArray()[0].toString()))+"("+p1m2.get(p1m2.keySet().toArray()[0])+"), "+Main.cs.getPlayerFromId(Integer.parseInt(p1m2.keySet().toArray()[1].toString()))+"("+p1m2.get(p1m2.keySet().toArray()[1])+"), "+Main.cs.getPlayerFromId(Integer.parseInt(p1m2.keySet().toArray()[2].toString()))+"("+p1m2.get(p1m2.keySet().toArray()[2])+"), "+Main.cs.getPlayerFromId(Integer.parseInt(p1m2.keySet().toArray()[3].toString()))+"("+p1m2.get(p1m2.keySet().toArray()[3])+"), ");
					p.sendMessage(ChatColor.GOLD+"Poule 1 Match 3: "+Main.cs.getPlayerFromId(Integer.parseInt(p1m3.keySet().toArray()[0].toString()))+"("+p1m3.get(p1m3.keySet().toArray()[0])+"), "+Main.cs.getPlayerFromId(Integer.parseInt(p1m3.keySet().toArray()[1].toString()))+"("+p1m3.get(p1m3.keySet().toArray()[1])+"), "+Main.cs.getPlayerFromId(Integer.parseInt(p1m3.keySet().toArray()[2].toString()))+"("+p1m3.get(p1m3.keySet().toArray()[2])+"), "+Main.cs.getPlayerFromId(Integer.parseInt(p1m3.keySet().toArray()[3].toString()))+"("+p1m3.get(p1m3.keySet().toArray()[3])+"), ");
					p.sendMessage(ChatColor.GOLD+"Poule 1 Match 4: "+Main.cs.getPlayerFromId(Integer.parseInt(p1m4.keySet().toArray()[0].toString()))+"("+p1m4.get(p1m4.keySet().toArray()[0])+"), "+Main.cs.getPlayerFromId(Integer.parseInt(p1m4.keySet().toArray()[1].toString()))+"("+p1m4.get(p1m2.keySet().toArray()[1])+"), "+Main.cs.getPlayerFromId(Integer.parseInt(p1m4.keySet().toArray()[2].toString()))+"("+p1m4.get(p1m4.keySet().toArray()[2])+"), "+Main.cs.getPlayerFromId(Integer.parseInt(p1m4.keySet().toArray()[3].toString()))+"("+p1m4.get(p1m4.keySet().toArray()[3])+"), ");
					try{
					p.sendMessage(ChatColor.GOLD+"Poule 2 Match 1: "+Main.cs.getPlayerFromId(Integer.parseInt(p2m1.keySet().toArray()[0].toString()))+"("+p2m1.get(p2m1.keySet().toArray()[0])+"), "+Main.cs.getPlayerFromId(Integer.parseInt(p2m1.keySet().toArray()[1].toString()))+"("+p2m1.get(p2m1.keySet().toArray()[1])+"), "+Main.cs.getPlayerFromId(Integer.parseInt(p2m1.keySet().toArray()[2].toString()))+"("+p2m1.get(p2m1.keySet().toArray()[2])+"), "+Main.cs.getPlayerFromId(Integer.parseInt(p2m1.keySet().toArray()[3].toString()))+"("+p2m1.get(p2m1.keySet().toArray()[3])+"), ");
					}catch(Exception e)
					{
						p.sendMessage(ChatColor.GOLD+"Poule 2 Match 1: Impossible de trouver les joueurs");
					}
					try{
					p.sendMessage(ChatColor.GOLD+"Poule 2 Match 2: "+Main.cs.getPlayerFromId(Integer.parseInt(p2m2.keySet().toArray()[0].toString()))+"("+p2m2.get(p2m2.keySet().toArray()[0])+"), "+Main.cs.getPlayerFromId(Integer.parseInt(p2m2.keySet().toArray()[1].toString()))+"("+p2m2.get(p2m2.keySet().toArray()[1])+"), "+Main.cs.getPlayerFromId(Integer.parseInt(p2m2.keySet().toArray()[2].toString()))+"("+p2m2.get(p2m2.keySet().toArray()[2])+"), "+Main.cs.getPlayerFromId(Integer.parseInt(p2m2.keySet().toArray()[3].toString()))+"("+p2m2.get(p2m2.keySet().toArray()[3])+"), ");
					}
					catch(Exception e){
						p.sendMessage(ChatColor.GOLD+"Poule 2 Match 2: Impossible de trouver les joueurs");

					}
					try
					{
					p.sendMessage(ChatColor.GOLD+"Poule 3 1VS1: "+Main.cs.getPlayerFromId(Integer.parseInt(p3m1.keySet().toArray()[0].toString()))+"("+p3m1.get(p1m3.keySet().toArray()[0])+"), "+Main.cs.getPlayerFromId(Integer.parseInt(p3m1.keySet().toArray()[1].toString()))+"("+p3m1.get(p1m3.keySet().toArray()[1])+"), "+Main.cs.getPlayerFromId(Integer.parseInt(p3m1.keySet().toArray()[2].toString()))+"("+p3m1.get(p1m3.keySet().toArray()[2])+"), "+Main.cs.getPlayerFromId(Integer.parseInt(p3m1.keySet().toArray()[3].toString()))+"("+p1m3.get(p3m1.keySet().toArray()[3])+"), ");
					}catch(Exception e)
					{
						p.sendMessage(ChatColor.GOLD+"Poule 3 1VS1: Impossible de trouver les joueurs");

					}
					
					
					
					p.sendMessage(ChatColor.GOLD+"------------------------------------");
					
					
				}
				else if (args[0].equalsIgnoreCase("start")&&Main.cs.getState()!=0)
				{
					if(Main.cs.isSpawnSet())
					{
						Bukkit.broadcastMessage(Main.TAG+ChatColor.translateAlternateColorCodes('&', Main.config.getString("messages.announce").replaceAll("%poule%", Main.cs.getPoule().toString()).replaceAll("%match%", Main.cs.getMatch().toString()).replaceAll("%actual%", Main.cs.getManche().toString())));
						Main.cs.start();
					}
					else
						p.sendMessage(Main.TAG+ChatColor.RED+"Tous les spawns n'ont pas étés définis, merci de les initialiser avec la commande /cs spawn #");
				}
				else if(args[0].equalsIgnoreCase("spawn"))
				{
					if(args.length>1)
					try
					{
						int nbSpawn = Integer.parseInt(args[1]);
						if(nbSpawn>0&&nbSpawn<5)
						{
							Main.cs.setSpawn(nbSpawn, p.getLocation());
							p.sendMessage(Main.TAG+ChatColor.GREEN+"Spawn "+args[1] +" set !");
						}
					}
					catch(NumberFormatException e)
					{
						if(args[1].equalsIgnoreCase("spectate"))
						{
							Main.cs.setSpawn(5, p.getLocation());
							p.sendMessage(Main.TAG+ChatColor.GREEN+"Spawn "+args[1] +" set !");
						}
						else
						p.sendMessage(Main.TAG+ChatColor.RED+"Le second argument doit être un entier correct.");
					}
					else
						p.sendMessage("1");
				}
			}
		}
		return true;
	}
	
	
}