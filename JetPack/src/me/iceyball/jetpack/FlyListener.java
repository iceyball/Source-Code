package me.iceyball.jetpack;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public class FlyListener implements Listener{
	
	private Plugin plugin;

    public FlyListener(Plugin plugin)
    {
        this.plugin = plugin;
    }
	  public static void launchPlayer(Player player)
	  {
		Vector dir = player.getLocation().getDirection();
		  if(player.getLocation().getPitch() < 0F)
		  {
		Vector vec2 = new Vector(dir.getX() * 0.8D, 0.8D, dir.getZ() * 0.8D);
		player.setVelocity(vec2);
		  }
		  if(player.getLocation().getPitch() >= 0F)
		  {
	    Vector vec = new Vector(dir.getX() * 0.8D,  dir.getY() * -0.25D, dir.getZ() * 0.8D);
	    player.setVelocity(vec);
		  }
	  }

	  public static boolean hasArmorType(ItemStack item, Material type)
	  {
	      return (item == null ? false : item.getType() == type);
	  }
	 
	 
	@EventHandler
	public void onFallDamage(EntityDamageEvent event){
        String name = ChatColor.DARK_RED + "JetPack";
        if(event.getEntity() instanceof Player && event.getCause() == DamageCause.FALL)
        {
        	Entity entity = event.getEntity();
    		Player player = (Player) entity;
            PlayerInventory inventory = player.getInventory();
        	
        	if(hasArmorType(inventory.getChestplate(), Material.IRON_CHESTPLATE)){
        		if(inventory.getChestplate().getItemMeta().getDisplayName().equals(name))
        		{
        			if(plugin.getConfig().getBoolean("Allow fall damage while wearing the jetpack")==(true))
        			{
        
             event.setCancelled(true);
        			}
    }
        		else
        		{
        		
        		}
        }
        }
        if(event.getEntity() instanceof Player)
        {
        	Entity entity1 = event.getEntity();
    		Player player1 = (Player) entity1;
        	PlayerInventory inventory1 = player1.getInventory();
        	short dur2 = inventory1.getChestplate().getDurability();
        if(dur2 == 239 && event.getEntity() instanceof Player ){

        	inventory1.getChestplate().setDurability((short) (dur2 - 1));
        }
        }
	}
	@EventHandler
	public void onMove(PlayerMoveEvent event){
    {
		Player player = event.getPlayer();
        World world = player.getWorld();
        final PlayerInventory inventory = player.getInventory();
        Location loc = player.getLocation();
        String name = "";
        if(inventory.getChestplate() != null)
        {
        if(inventory.getChestplate().hasItemMeta() && inventory.getChestplate().getItemMeta() != null)
        {
        name = inventory.getChestplate().getItemMeta().getDisplayName();
        }
        }
        if(hasArmorType(inventory.getChestplate(), Material.IRON_CHESTPLATE)){
        if(name.equals(ChatColor.DARK_RED + "JetPack") && name != null){
        	Block b;
        	b = player.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() - 1, loc.getBlockZ());
        	if (b == null) { return; }
        	if(event.getFrom().getY() != event.getTo().getY() && b.getType() == Material.AIR) {
                short dura = inventory.getChestplate().getDurability();
        		if(dura != 239)
        		{
        	        if(player.isSneaking())
        	        {
			launchPlayer(player);
			world.playEffect(player.getLocation(),Effect.GHAST_SHOOT,0);
			world.playEffect(player.getLocation(),Effect.SMOKE,0);
			player.setFallDistance(0);
			 
			Random random = new Random();
			int Chance = random.nextInt(100);
			if(Chance <= (plugin.getConfig().getInt("Fuel Capacity"))) {
				inventory.getChestplate().setDurability((short) (inventory.getChestplate().getDurability() + 1));
        		}
        	}
        }
	}
	}
}
	}
	}
}




