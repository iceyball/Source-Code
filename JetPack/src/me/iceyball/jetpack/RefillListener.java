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
	public static boolean hasArmor(ItemStack item, Material type)
	{
	    return (item == null ? false : item.getType() == type);
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        short dur4 = player.getItemInHand().getDurability();
        String name0 = "";
        if(player.getItemInHand() != null)
        {
        if(player.getItemInHand().hasItemMeta())
        {
        name0 = player.getItemInHand().getItemMeta().getDisplayName();
        }
        }
        if(event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_AIR){
        	if(hasArmor(player.getItemInHand(), Material.IRON_CHESTPLATE)){
            	if(name0.equals(ChatColor.DARK_RED + "JetPack"))
            	{
            		if(player.getInventory().contains(Material.getMaterial(plugin.getConfig().getString("Fuel"))))
            		{
            			if(player.getItemInHand().getDurability() != 0)
            			{
            		player.getInventory().removeItem(new ItemStack[] { new ItemStack(Material.getMaterial(plugin.getConfig().getString("Fuel")), 1) });
            		player.getItemInHand().setDurability((short) (dur4 - (plugin.getConfig().getInt("Power Gained Per Fuel"))));
            			}
            	}
            	}
        	}
}
	}
}