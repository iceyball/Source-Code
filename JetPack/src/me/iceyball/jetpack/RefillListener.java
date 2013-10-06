package me.iceyball.jetpack;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;


public class RefillListener implements Listener{
	
	
	private Plugin plugin;

    public RefillListener(Plugin plugin)
    {
        this.plugin = plugin;
    }
	

	@EventHandler
	public void onPlayerInteract1(PlayerInteractEvent event){
        Player player = event.getPlayer();
        String name = ChatColor.DARK_RED + "JetPack";
        short dur = player.getItemInHand().getDurability();
        
        if(event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_AIR){
            	if(player.getItemInHand().getItemMeta().getDisplayName().equals(name))
            	{
            		if(player.getInventory().contains(Material.getMaterial(plugin.getConfig().getString("Fuel"))))
            		{
            			if(player.getItemInHand().getDurability() != 0)
            			{
            		player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.getMaterial(plugin.getConfig().getString("Fuel")), 1) });
            		player.getItemInHand().setDurability((short) (dur - (plugin.getConfig().getInt("Power Gained Per Fuel"))));
            			}
            	}
            	}
            	
            }
        }
    }
