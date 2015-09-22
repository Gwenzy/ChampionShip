package fr.gwenzy.cs;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ChampionShipListener implements Listener {

	@EventHandler
	public void onDisconnect(PlayerQuitEvent event)
	{
		if(Main.cs.isMatchOn())
		{
			event.getPlayer().setHealth(0.0);
		}
	}
	
	@EventHandler
	public void onPlayerDead(PlayerDeathEvent event)
	{
		if(Main.cs.isMatchOn())
		{
			switch(Main.cs.getState())
			{
			case 1:
				Main.cs.setPoule1Match1(Main.cs.getIdFromString(event.getEntity().getName()));
				break;
			case 2:
				Main.cs.setPoule1Match1(Main.cs.getIdFromString(event.getEntity().getName()));
				break;
			case 3:
				Main.cs.setPoule1Match1(Main.cs.getIdFromString(event.getEntity().getName()));
				break;
			case 4:
				Main.cs.setPoule1Match2(Main.cs.getIdFromString(event.getEntity().getName()));
				break;
			case 5:
				Main.cs.setPoule1Match2(Main.cs.getIdFromString(event.getEntity().getName()));
				break;
			case 6:
				Main.cs.setPoule1Match2(Main.cs.getIdFromString(event.getEntity().getName()));
				break;
			case 7:
				Main.cs.setPoule1Match3(Main.cs.getIdFromString(event.getEntity().getName()));
				break;
			case 8:
				Main.cs.setPoule1Match3(Main.cs.getIdFromString(event.getEntity().getName()));
				break;
			case 9:
				Main.cs.setPoule1Match3(Main.cs.getIdFromString(event.getEntity().getName()));
				break;
			case 10:
				Main.cs.setPoule1Match4(Main.cs.getIdFromString(event.getEntity().getName()));
				break;
			case 11:
				Main.cs.setPoule1Match4(Main.cs.getIdFromString(event.getEntity().getName()));
				break;
			case 12:
				Main.cs.setPoule1Match4(Main.cs.getIdFromString(event.getEntity().getName()));
				break;
			case 13:
				Main.cs.setPoule2Match1(Main.cs.getIdFromString(event.getEntity().getName()));
				break;
			case 14:
				Main.cs.setPoule2Match1(Main.cs.getIdFromString(event.getEntity().getName()));
				break;
			case 15:
				Main.cs.setPoule2Match1(Main.cs.getIdFromString(event.getEntity().getName()));
				break;
			case 16:
				Main.cs.setPoule2Match2(Main.cs.getIdFromString(event.getEntity().getName()));
				break;
			case 17:
				Main.cs.setPoule2Match2(Main.cs.getIdFromString(event.getEntity().getName()));
				break;
			case 18:
				Main.cs.setPoule2Match2(Main.cs.getIdFromString(event.getEntity().getName()));
				break;
			case 19:
				Main.cs.setPoule3Match1(Main.cs.getIdFromString(event.getEntity().getName()));
				break;
			case 20:
				Main.cs.setPoule3Match1(Main.cs.getIdFromString(event.getEntity().getName()));
				break;
			case 21:
				Main.cs.setPoule3Match1(Main.cs.getIdFromString(event.getEntity().getName()));
				break;
			}
			Main.cs.respawnPlayer(event.getEntity());
		}
	}
}
