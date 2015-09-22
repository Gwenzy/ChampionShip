package fr.gwenzy.cs.objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import fr.gwenzy.cs.Main;

public class ChampionShip{
	public Main plugin;
	private HashMap<Integer, String> players;
	private List<String> remplacents;
	private HashMap<Integer, Integer> p1m1;
	private HashMap<Integer, Integer> p1m2;
	private HashMap<Integer, Integer> p1m3;
	private HashMap<Integer, Integer> p1m4;
	private HashMap<Integer, Integer> p2m1;
	private HashMap<Integer, Integer> p2m2;
	private HashMap<Integer, Integer> p3m1;
	private HashMap<Integer, Integer> ovo;
	Set<Integer> actualPlayers;
	private Location spawn1;
	private Location spawn2;
	private Location spawn3;
	private Location spawn4;
	private Location spawnspectate;
	List<Integer> idDone;
	private boolean match;
	private String winner;
	int playersDead;
	
	/**
	 * State 0 = Init
	 * State 1 = Poule 1 Match 1 Manche 1/3
	 * State 2 = Poule 1 Match 1 Manche 2/3
	 * State 3 = Poule 1 Match 1 Manche 3/3
	 * 
	 * State 4 = Poule 1 Match 2 Manche 1/3
	 * State 5 = Poule 1 Match 2 Manche 2/3
	 * State 6 = Poule 1 Match 2 Manche 3/3
	 * 
	 * State 7 = Poule 1 Match 3 Manche 1/3
	 * State 8 = Poule 1 Match 3 Manche 2/3
	 * State 9 = Poule 1 Match 3 Manche 3/3
	 * 
	 * State 10 = Poule 1 Match 4 Manche 1/3
	 * State 11 = Poule 1 Match 4 Manche 2/3
	 * State 12 = Poule 1 Match 4 Manche 3/3
	 * 
	 * ----------------------------------------
	 * ----------------------------------------
	 * 
	 * State 13 = Poule 2 Match 1 Manche 1/3
	 * State 14 = Poule 2 Match 1 Manche 2/3
	 * State 15 = Poule 2 Match 1 Manche 3/3
	 * 
	 * State 16 = Poule 2 Match 2 Manche 1/3
	 * State 17 = Poule 2 Match 2 Manche 2/3
	 * State 18 = Poule 2 Match 2 Manche 3/3
	 * 
	 * ----------------------------------------
	 * ----------------------------------------
	 * 
	 * State 19 = Poule 3 Match 1 Manche 1/3
	 * State 20 = Poule 3 Match 1 Manche 2/3
	 * State 21 = Poule 3 Match 1 Manche 3/3
	 * 
	 * State 22 = 1VS1 Manche 1/3
	 * State 23 = 1VS1 Manche 2/3
	 * State 24 = 1VS1 Manche 3/3
	 * 
	 * State 25 = Win
	 */
	private int state;
	
	public ChampionShip(Main plugin)
	{
		idDone=new ArrayList<Integer>();
		actualPlayers= new TreeSet<Integer>();
		match=false;
		spawn1 = null;
		spawn2=null;
		spawn3=null;
		spawn4=null;
		spawnspectate=null;
		this.plugin=plugin;
		players = new HashMap<Integer, String>();
		remplacents = new ArrayList<String>();
		state = 1;
		p1m1=new HashMap<Integer, Integer>();
		p1m2=new HashMap<Integer, Integer>();
		p1m3=new HashMap<Integer, Integer>();
		p1m4=new HashMap<Integer, Integer>();
		p2m1=new HashMap<Integer, Integer>();
		p2m2=new HashMap<Integer, Integer>();
		p3m1=new HashMap<Integer, Integer>();
		ovo=new HashMap<Integer, Integer>();
		p1m1.put(0, 0);
		p1m1.put(1, 0);
		p1m1.put(2, 0);
		p1m1.put(3, 0);
		p1m2.put(4, 0);
		p1m2.put(5, 0);
		p1m2.put(6, 0);
		p1m2.put(7, 0);
		p1m3.put(8, 0);
		p1m3.put(9, 0);
		p1m3.put(10, 0);
		p1m3.put(11, 0);
		p1m4.put(12, 0);
		p1m4.put(13, 0);
		p1m4.put(14, 0);
		p1m4.put(15, 0);
		playersDead=0;
	}
	public boolean isSpawnSet()
	{
		if(spawn1==null||spawn2==null||spawn3==null||spawn4==null)
			return false;
		else
			return true;
		
	}
	public void setSpawn(Integer id, Location loc)
	{
		switch(id)
		{
		case 1:
			spawn1=loc;
			break;
		case 2:
			spawn2=loc;
			break;
		case 3:
			spawn3=loc;
			break;
		case 4:
			spawn4=loc;
			break;
		case 5:
			spawnspectate = loc;
			break;
		}
	}
	public HashMap<Integer, Integer> getPoule1Match1()
	{
		return this.sortByValues(p1m1);
	}
	public HashMap<Integer, Integer> getPoule1Match2()
	{
		return this.sortByValues(p1m2);
	}
	public HashMap<Integer, Integer> getPoule1Match3()
	{
		return this.sortByValues(p1m3);
	}
	public HashMap<Integer, Integer> getPoule1Match4()
	{
		return this.sortByValues(p1m4);
	}
	public HashMap<Integer, Integer> getPoule2Match1()
	{
		return this.sortByValues(p2m1);
	}
	public HashMap<Integer, Integer> getPoule2Match2()
	{
		return this.sortByValues(p2m2);
	}
	public HashMap<Integer, Integer> getPoule3Match1()
	{
		return this.sortByValues(p3m1);
	}
	public HashMap<Integer, Integer> getOneVersusOne()
	{
		return this.sortByValues(ovo);
	}
	public void setPoule1Match1(Integer id)
	{
		if(!idDone.contains(id)){
		switch(playersDead)
		{
		case 0:
			p1m1.put(id, p1m1.get(id)+0);
			Bukkit.broadcastMessage(Main.TAG+ChatColor.translateAlternateColorCodes('&', Main.config.getString("messages.dead4").replaceAll("%j%", this.getPlayerFromId(id))));
			idDone.add(id);
			break;
		case 1:
			p1m1.put(id, p1m1.get(id)+1);
			Bukkit.broadcastMessage(Main.TAG+ChatColor.translateAlternateColorCodes('&', Main.config.getString("messages.dead3").replaceAll("%j%", this.getPlayerFromId(id))));
			idDone.add(id);
			break;

		case 2:
			p1m1.put(id, p1m1.get(id)+2);
			Bukkit.broadcastMessage(Main.TAG+ChatColor.translateAlternateColorCodes('&', Main.config.getString("messages.dead2").replaceAll("%j%", this.getPlayerFromId(id))));
			idDone.add(id);
			Bukkit.broadcastMessage(idDone.toString());

			for(Integer id2 : p1m1.keySet())
			{
				Bukkit.broadcastMessage(id2.toString());
				if(!idDone.contains(id2))
				{
					p1m1.put(id2, p1m1.get(id2)+4);
					Bukkit.broadcastMessage(Main.TAG+ChatColor.translateAlternateColorCodes('&', Main.config.getString("messages.win").replaceAll("%j%", this.getPlayerFromId(id2))));
					this.match=false;
					this.nextState();
				}
			}
			idDone.clear();

			break;
		}
		playersDead++;}
	}

	public Integer getPlayersDead()
	{return this.playersDead;}
	public void setPoule1Match2(Integer id)
	{if(!idDone.contains(id)){
		switch(playersDead)
		{
		case 0:
			p1m2.put(id, p1m2.get(id)+0);
			Bukkit.broadcastMessage(Main.TAG+ChatColor.translateAlternateColorCodes('&', Main.config.getString("messages.dead4").replaceAll("%j%", this.getPlayerFromId(id))));
			idDone.add(id);
			break;
		case 1:
			p1m2.put(id, p1m2.get(id)+1);
			Bukkit.broadcastMessage(Main.TAG+ChatColor.translateAlternateColorCodes('&', Main.config.getString("messages.dead3").replaceAll("%j%", this.getPlayerFromId(id))));
			idDone.add(id);
			break;

		case 2:
			p1m2.put(id, p1m2.get(id)+2);
			Bukkit.broadcastMessage(Main.TAG+ChatColor.translateAlternateColorCodes('&', Main.config.getString("messages.dead2").replaceAll("%j%", this.getPlayerFromId(id))));
			idDone.add(id);
			for(Integer id2 : p1m2.keySet())
			{
				if(!idDone.contains(id2))
				{
					p1m2.put(id2, p1m2.get(id2)+4);
					Bukkit.broadcastMessage(Main.TAG+ChatColor.translateAlternateColorCodes('&', Main.config.getString("messages.win").replaceAll("%j%", this.getPlayerFromId(id2))));
					this.match=false;
					this.nextState();
				}
			}
			idDone.clear();

			break;
		}
		playersDead++;}
	}

	public void setPoule1Match3(Integer id)
	{if(!idDone.contains(id)){
		switch(playersDead)
		{
		case 0:
			p1m3.put(id, p1m3.get(id)+0);
			Bukkit.broadcastMessage(Main.TAG+ChatColor.translateAlternateColorCodes('&', Main.config.getString("messages.dead4").replaceAll("%j%", this.getPlayerFromId(id))));
			idDone.add(id);
			break;
		case 1:
			p1m3.put(id, p1m3.get(id)+1);
			Bukkit.broadcastMessage(Main.TAG+ChatColor.translateAlternateColorCodes('&', Main.config.getString("messages.dead3").replaceAll("%j%", this.getPlayerFromId(id))));
			idDone.add(id);
			break;

		case 2:
			p1m3.put(id, p1m3.get(id)+2);
			Bukkit.broadcastMessage(Main.TAG+ChatColor.translateAlternateColorCodes('&', Main.config.getString("messages.dead2").replaceAll("%j%", this.getPlayerFromId(id))));
			idDone.add(id);
			for(Integer id2 : p1m3.keySet())
			{
				if(!idDone.contains(id2))
				{
					p1m3.put(id2, p1m3.get(id2)+4);
					Bukkit.broadcastMessage(Main.TAG+ChatColor.translateAlternateColorCodes('&', Main.config.getString("messages.win").replaceAll("%j%", this.getPlayerFromId(id2))));
					this.match=false;
					this.nextState();

				}
			}
			idDone.clear();

			break;
		}
		playersDead++;}
	}

	public void setPoule1Match4(Integer id)
	{if(!idDone.contains(id)){
		switch(playersDead)
		{
		case 0:
			p1m4.put(id, p1m4.get(id)+0);
			Bukkit.broadcastMessage(Main.TAG+ChatColor.translateAlternateColorCodes('&', Main.config.getString("messages.dead4").replaceAll("%j%", this.getPlayerFromId(id))));
			idDone.add(id);
			break;
		case 1:
			p1m4.put(id, p1m4.get(id)+1);
			Bukkit.broadcastMessage(Main.TAG+ChatColor.translateAlternateColorCodes('&', Main.config.getString("messages.dead3").replaceAll("%j%", this.getPlayerFromId(id))));
			idDone.add(id);
			break;

		case 2:
			p1m4.put(id, p1m4.get(id)+2);
			Bukkit.broadcastMessage(Main.TAG+ChatColor.translateAlternateColorCodes('&', Main.config.getString("messages.dead2").replaceAll("%j%", this.getPlayerFromId(id))));
			idDone.add(id);
			for(Integer id2 : p1m4.keySet())
			{
				if(!idDone.contains(id2))
				{
					p1m4.put(id2, p1m4.get(id)+4);
					Bukkit.broadcastMessage(Main.TAG+ChatColor.translateAlternateColorCodes('&', Main.config.getString("messages.win").replaceAll("%j%", this.getPlayerFromId(id2))));
					this.match=false;
					this.nextState();

				}
			}
			idDone.clear();

			break;
		}
		playersDead++;}
	}
	public void setPoule2Match1(Integer id)
	{if(!idDone.contains(id)){
		switch(playersDead)
		{
		case 0:
			p2m1.put(id, p2m1.get(id)+0);
			Bukkit.broadcastMessage(Main.TAG+ChatColor.translateAlternateColorCodes('&', Main.config.getString("messages.dead4").replaceAll("%j%", this.getPlayerFromId(id))));
			idDone.add(id);
			break;
		case 1:
			p2m1.put(id, p2m1.get(id)+1);
			Bukkit.broadcastMessage(Main.TAG+ChatColor.translateAlternateColorCodes('&', Main.config.getString("messages.dead3").replaceAll("%j%", this.getPlayerFromId(id))));
			idDone.add(id);
			break;

		case 2:
			p2m1.put(id, p2m1.get(id)+2);
			Bukkit.broadcastMessage(Main.TAG+ChatColor.translateAlternateColorCodes('&', Main.config.getString("messages.dead2").replaceAll("%j%", this.getPlayerFromId(id))));
			idDone.add(id);
			for(Integer id2 : p2m1.keySet())
			{
				if(!idDone.contains(id2))
				{
					p2m1.put(id2, p2m1.get(id2)+4);
					Bukkit.broadcastMessage(Main.TAG+ChatColor.translateAlternateColorCodes('&', Main.config.getString("messages.win").replaceAll("%j%", this.getPlayerFromId(id2))));
					this.match=false;
					this.nextState();


				}
			}
			idDone.clear();
			break;
		}
		playersDead++;}
	}

	public void setPoule2Match2(Integer id)
	{if(!idDone.contains(id)){
		switch(playersDead)
		{
		case 0:
			p2m2.put(id, p2m2.get(id)+0);
			Bukkit.broadcastMessage(Main.TAG+ChatColor.translateAlternateColorCodes('&', Main.config.getString("messages.dead4").replaceAll("%j%", this.getPlayerFromId(id))));
			idDone.add(id);
			break;
		case 1:
			p2m2.put(id, p2m2.get(id)+1);
			Bukkit.broadcastMessage(Main.TAG+ChatColor.translateAlternateColorCodes('&', Main.config.getString("messages.dead3").replaceAll("%j%", this.getPlayerFromId(id))));
			idDone.add(id);
			break;

		case 2:
			p2m2.put(id, p2m2.get(id)+2);
			Bukkit.broadcastMessage(Main.TAG+ChatColor.translateAlternateColorCodes('&', Main.config.getString("messages.dead2").replaceAll("%j%", this.getPlayerFromId(id))));
			idDone.add(id);
			for(Integer id2 : p2m2.keySet())
			{
				if(!idDone.contains(id2))
				{
					p2m2.put(id2, p2m2.get(id2)+4);
					Bukkit.broadcastMessage(Main.TAG+ChatColor.translateAlternateColorCodes('&', Main.config.getString("messages.win").replaceAll("%j%", this.getPlayerFromId(id2))));
					this.match=false;
					this.nextState();

				}
			}
			idDone.clear();
			break;
		}
		playersDead++;}
	}
	
	public void setPoule3Match1(Integer id)
	{
		if(!idDone.contains(id)){
		switch(playersDead)
		{
		case 0:
			p3m1.put(id, p3m1.get(id)+0);
			Bukkit.broadcastMessage(Main.TAG+ChatColor.translateAlternateColorCodes('&', Main.config.getString("messages.dead4").replaceAll("%j%", this.getPlayerFromId(id))));
			idDone.add(id);
			break;
		case 1:
			p3m1.put(id, p3m1.get(id)+1);
			Bukkit.broadcastMessage(Main.TAG+ChatColor.translateAlternateColorCodes('&', Main.config.getString("messages.dead3").replaceAll("%j%", this.getPlayerFromId(id))));
			idDone.add(id);
			break;

		case 2:
			p3m1.put(id, p3m1.get(id)+2);
			Bukkit.broadcastMessage(Main.TAG+ChatColor.translateAlternateColorCodes('&', Main.config.getString("messages.dead2").replaceAll("%j%", this.getPlayerFromId(id))));
			idDone.add(id);
			for(Integer id2 : p3m1.keySet())
			{
				if(!idDone.contains(id2))
				{
					p3m1.put(id2, p3m1.get(id2)+4);
					Bukkit.broadcastMessage(Main.TAG+ChatColor.translateAlternateColorCodes('&', Main.config.getString("messages.win").replaceAll("%j%", this.getPlayerFromId(id2))));
					this.match=false;
					this.nextState();


				}
			}
			idDone.clear();
			break;
		}
		playersDead++;}
	}
	public void setOvO(Integer id)
	{
		
			ovo.put(id, ovo.get(id)+1);
			Bukkit.broadcastMessage(Main.TAG+ChatColor.translateAlternateColorCodes('&', Main.config.getString("messages.ovodead").replaceAll("%j%", this.getPlayerFromId(id))));
			idDone.add(id);
			for(Integer id2 : ovo.keySet())
			{
				if(!idDone.contains(id2))
				{
					p3m1.put(id2, p3m1.get(id2)+4);
					Bukkit.broadcastMessage(Main.TAG+ChatColor.translateAlternateColorCodes('&', Main.config.getString("messages.ovowin").replaceAll("%j%", this.getPlayerFromId(id2))));
					this.match=false;
					this.winner=this.getPlayerFromId(id2);
					this.nextState();


				}
			}
			idDone.clear();
	}
	public void respawnPlayer(Player p)
	{
		p.spigot().respawn();
		if(spawnspectate!=null)p.teleport(spawnspectate);
	}
	
	public Integer getPlace()
	{
		switch(playersDead)
		{
		case 0:
			return 4;
		case 1:
			return 3;
		case 2:
			return 2;
		case 3:
			return 1;
		default:
			return 0;
			
		}
	}
	public Integer getPoule()
	{
		switch(Main.cs.getState())
		{
		case 0:
			return 0;
		case 1:			
			return 1;

		case 2:	
			return 1;
		case 3:	
			return 1;
		case 4:		
			return 1;
		case 5:	
			return 1;
		case 6:	
			return 1;
		case 7:	
			return 1;
		case 8:	
			return 1;
		case 9:	
			return 1;
		case 10:	
			return 1;
		case 11:	
			return 1;
		case 12:	
			return 1;
		case 13:	
			return 2;
		case 14:	
			return 2;
		case 15:	
			return 2;
		case 16:	
			return 2;
		case 17:	
			return 2;
		case 18:	
			return 2;
		case 19:	
			return 3;
		case 20:	
			return 3;
		case 21:	
			return 3;
		
		}
		return 0;
	}


	public Integer getMatch()
	{
		switch(Main.cs.getState())
		{
		case 0:
			return 0;
		case 1:			
			return 1;

		case 2:	
			return 1;
		case 3:	
			return 1;
		case 4:		
			return 2;
		case 5:	
			return 2;
		case 6:	
			return 2;
		case 7:	
			return 3;
		case 8:	
			return 3;
		case 9:	
			return 3;
		case 10:	
			return 4;
		case 11:	
			return 4;
		case 12:	
			return 4;
		case 13:	
			return 1;
		case 14:	
			return 1;
		case 15:	
			return 1;
		case 16:	
			return 2;
		case 17:	
			return 2;
		case 18:	
			return 2;
		case 19:	
			return 1;
		case 20:	
			return 1;
		case 21:	
			return 1;
		
		}
		return 0;
	}
	public Integer getManche()
	{
		switch(Main.cs.getState())
		{
		case 0:
			return 0;
		case 1:			
			return 1;

		case 2:	
			return 2;
		case 3:	
			return 3;
		case 4:		
			return 1;
		case 5:	
			return 2;
		case 6:	
			return 3;
		case 7:	
			return 1;
		case 8:	
			return 2;
		case 9:	
			return 3;
		case 10:	
			return 1;
		case 11:	
			return 2;
		case 12:	
			return 3;
		case 13:	
			return 1;
		case 14:	
			return 2;
		case 15:	
			return 3;
		case 16:	
			return 1;
		case 17:	
			return 2;
		case 18:	
			return 3;
		case 19:	
			return 1;
		case 20:	
			return 2;
		case 21:	
			return 3;
		
		}
		return 0;
	}
	
	
		 @SuppressWarnings({ "rawtypes", "unchecked" })
		private HashMap sortByValues(HashMap map) { 
		       List list = new LinkedList(map.entrySet());
		       // Defined Custom Comparator here
		       Collections.sort(list, new Comparator() {
					public int compare(Object o2, Object o1) {
		               return ((Comparable) ((Map.Entry) (o1)).getValue())
		                  .compareTo(((Map.Entry) (o2)).getValue());
		            }
		       });

		       // Here I am copying the sorted list in HashMap
		       // using LinkedHashMap to preserve the insertion order
		       HashMap sortedHashMap = new LinkedHashMap();
		       for (Iterator it = list.iterator(); it.hasNext();) {
		              Map.Entry entry = (Map.Entry) it.next();
		              sortedHashMap.put(entry.getKey(), entry.getValue());
		       } 
		       return sortedHashMap;
		  }
	
	public String getPlayerFromId(Integer id){
		return players.get(id);
	}
	
	public Integer getIdFromString(String name){
		for(Integer ide : this.players.keySet())
		{
			if(name.equalsIgnoreCase(this.players.get(ide)))
				return ide;
		}
		return 0;
	}
	public int getStatus()
	{
		return this.state;
	}
	
	public void start()
	{
		List<String> rempl = new ArrayList<String>();
		HashMap<Integer, String> names = new HashMap<Integer, String>();
		
		for(String remp : this.remplacents)
		{
			Player p = Bukkit.getServer().getPlayer(remp);
			if(p!=null)
			{
				rempl.add(remp);
			}
		}
		switch(Main.cs.getState())
		{
		case 1:			
			actualPlayers = p1m1.keySet();
			break;
		case 2:	
			actualPlayers = p1m1.keySet();
			break;
		case 3:	
			actualPlayers = p1m1.keySet();
			break;
		case 4:		
			actualPlayers = p1m2.keySet();
			break;
		case 5:			
			actualPlayers = p1m2.keySet();
			break;
		case 6:			
			actualPlayers = p1m2.keySet();
			break;
		case 7:			
			actualPlayers = p1m3.keySet();
			break;
		case 8:			
			actualPlayers = p1m3.keySet();
			break;
		case 9:			
			actualPlayers = p1m3.keySet();
			break;
		case 10:			
			actualPlayers = p1m4.keySet();
			break;
		case 11:			
			actualPlayers = p1m4.keySet();
			break;
		case 12:			
			actualPlayers = p1m4.keySet();
			break;
		case 13:			
			actualPlayers = p2m1.keySet();
			break;
		case 14:			
			actualPlayers = p2m1.keySet();
			break;
		case 15:			
			actualPlayers = p2m1.keySet();
			break;
		case 16:			
			actualPlayers = p2m2.keySet();
			break;
		case 17:			
			actualPlayers = p2m2.keySet();
			break;
		case 18:			
			actualPlayers = p2m2.keySet();
			break;
		case 19:			
			actualPlayers = p3m1.keySet();
			break;
		case 20:			
			actualPlayers = p3m1.keySet();
			break;
		case 21:			
			actualPlayers = p3m1.keySet();
			break;
		
		}
		for(Integer id : actualPlayers)
		{
			Player p = Bukkit.getServer().getPlayer(this.getPlayerFromId(id));
			if(p==null)
			{
				names.put(id, rempl.get(0));
				rempl.remove(0);
				Bukkit.getServer().broadcastMessage(Main.TAG+"En raison de l'absence de "+this.getPlayerFromId(id)+", "+names.get(id)+" le remplacera pour cette partie.");
				
			}
			else
			{
				names.put(id, this.getPlayerFromId(id));
			}
		}
		if (actualPlayers.size()==4)
		{
			Bukkit.broadcastMessage(Main.TAG+ChatColor.translateAlternateColorCodes('&', Main.config.getString("messages.tp4").replaceAll("%j1%", names.get(0)).replaceAll("%j2%", names.get(1)).replaceAll("%j3%", names.get(2)).replaceAll("%j4%", names.get(3))));
			for(String p : names.values())
			{
				Bukkit.getServer().getPlayer(p).spigot().respawn();
			}
			int i = 0;
			for(String p : names.values())
			{
				Bukkit.broadcastMessage("Teleporting "+p);
				if(i==0){
					Bukkit.getServer().getPlayer(p).teleport(spawn1);
				}
				else if(i==1){
					Bukkit.getServer().getPlayer(p).teleport(spawn2);
				}
				else if(i==2){
					Bukkit.getServer().getPlayer(p).teleport(spawn3);				

				}
				else{
					Bukkit.getServer().getPlayer(p).teleport(spawn4);
				}
				i++;
			}
		}else{
			Bukkit.broadcastMessage(Main.TAG+ChatColor.translateAlternateColorCodes('&', Main.config.getString("messages.tp2").replaceAll("%j1%", names.get(0)).replaceAll("%j2%", names.get(1))));
			int i = 0;
			for(String p : names.values())
			{
				if(i==0)
					Bukkit.getServer().getPlayer(p).teleport(spawn1);
				else
					Bukkit.getServer().getPlayer(p).teleport(spawn2);
				i++;
			}
		}
		
		this.waitSeconds();
		this.match=true;
		this.playersDead=0;
	}
	public boolean isMatchOn()
	{
		return this.match;
	}
	public void waitSeconds(){
		Bukkit.getScheduler().scheduleAsyncDelayedTask(this.plugin, new Runnable()
		{

			@Override
			public void run() {
				Bukkit.broadcastMessage(Main.TAG+ChatColor.translateAlternateColorCodes('&', Main.config.getString("messages.timer3")));
				try {
		Thread.sleep(1000);
		} catch (InterruptedException e) {
				}
				Bukkit.broadcastMessage(Main.TAG+ChatColor.translateAlternateColorCodes('&', Main.config.getString("messages.timer2")));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				Bukkit.broadcastMessage(Main.TAG+ChatColor.translateAlternateColorCodes('&', Main.config.getString("messages.timer1")));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				Bukkit.broadcastMessage(Main.TAG+ChatColor.translateAlternateColorCodes('&', Main.config.getString("messages.start")));
				
			}
			
		});
		
	}
	public void init(Player initiator)
	{
		
		int i=0;
		initiator.sendMessage(Main.TAG+"Initialisation des listes de joueurs...");
		if(plugin.config.getStringList("players.main").size()==16)
		{
		String joueurs = ChatColor.GOLD+"Joueurs présents :";
		for(String p : plugin.config.getStringList("players.main"))
		{
			if(Bukkit.getServer().getPlayer(p)==null)
				{joueurs = joueurs + ChatColor.RED+" ";}else{ joueurs=joueurs+ChatColor.GREEN+" ";} 
			joueurs = joueurs + p+ChatColor.GOLD+",";
			this.players.put(i, p);
			i++;
		}
		initiator.sendMessage(Main.TAG+joueurs);
		initiator.sendMessage(Main.TAG+"Initialisation des listes de joueurs...");
		String remp = ChatColor.GOLD+"Remplaçants présents :";
		for(String p : plugin.config.getStringList("players.remplacement"))
		{
			if(Bukkit.getServer().getPlayer(p)==null)
			{remp = remp + ChatColor.RED+" ";}else{ remp=remp+ChatColor.GREEN+" ";} 
			remp = remp + p+ChatColor.GOLD+",";
		
			this.remplacents.add(p);
		}
		
		initiator.sendMessage(Main.TAG+remp);
		}
		else
			initiator.sendMessage(Main.TAG+ChatColor.RED+"Il n'y a pas 16 joueurs dans la configuration, merci de vérifier.");
	}
	public void nextState() {
		switch(this.state)
		{
		case 3:
			HashMap<Integer, Integer> p = this.sortByValues(p1m1);
			int i=0;
			for(int id : p.keySet())
			{
				if(i<2)
				{
					this.p2m1.put(id, 0);
					i++;
				}
			}
			break;
		case 6:
			p = this.sortByValues(p1m2);
			i=0;
			for(int id : p.keySet())
			{
				if(i<2)
				{
					this.p2m1.put(id, 0);
					i++;
				}
			}
			break;
		case 9:
			p = this.sortByValues(p1m3);
			i=0;
			for(int id : p.keySet())
			{
				if(i<2)
				{
					this.p2m2.put(id, 0);
					i++;
				}
			}
			break;
		case 12:
			p = this.sortByValues(p1m4);
			i=0;
			for(int id : p.keySet())
			{
				if(i<2)
				{
					this.p2m2.put(id, 0);
					i++;
				}
			}
			break;
		case 15:
			p = this.sortByValues(p2m1);
			i=0;
			for(int id : p.keySet())
			{
				if(i<2)
				{
					this.p3m1.put(id, 0);
					i++;
				}
			}
			break;
		case 18:
			p = this.sortByValues(p2m2);
			i=0;
			for(int id : p.keySet())
			{
				if(i<2)
				{
					this.p3m1.put(id, 0);
					i++;
				}
			}
			break;
		case 21:
			p = this.sortByValues(p3m1);
			i=0;
			for(int id : p.keySet())
			{
				if(i<2)
				{
					this.ovo.put(id, 0);
					i++;
				}
			}
			break;
		case 25:
			Bukkit.broadcastMessage(Main.TAG+ChatColor.translateAlternateColorCodes('&', Main.config.getString("messages.winall").replaceAll("%j%", this.winner)));
			break;
		}
		this.state++;
		
	}
	public int getState() {
		return this.state;
	}
	public void resetState() {
		this.state=0;
	}
	

}
