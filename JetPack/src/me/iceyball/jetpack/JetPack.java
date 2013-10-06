package me.iceyball.jetpack;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class JetPack extends JavaPlugin implements Listener{
	
		public final Logger logger = Logger.getLogger("Minecraft");
		public static JetPack plugin;
		public final FlyListener flyListener = new FlyListener(this);
		public final RefillListener refillListener = new RefillListener(this);
		
	public void onDisable()
	  {
	    PluginDescriptionFile pdffile = getDescription();
	    this.logger.info(pdffile.getName() + " has been disabled.");
	  }

	public void onEnable() {

		new File("plugins/PluginName").mkdir();

		File configFile = new File("plugins/PluginName/config.yml");
		
		getConfig().options().copyDefaults(true);
		saveConfig();

		if(!configFile.exists()){

		    try { configFile.createNewFile(); } catch(Exception e){ System.out.println("Error when creating config file."); }

		}
		ShapedRecipe jetpack = new ShapedRecipe(setName(new ItemStack(Material.IRON_CHESTPLATE, 1), ChatColor.DARK_RED + "JetPack"));
		jetpack.shape("123", "456", "789");
		jetpack.setIngredient('1', Material.getMaterial(getConfig().getString("Recipe.Top Left")));
		jetpack.setIngredient('2', Material.getMaterial(getConfig().getString("Recipe.Top Center")));
		jetpack.setIngredient('3', Material.getMaterial(getConfig().getString("Recipe.Top Right")));
		jetpack.setIngredient('4', Material.getMaterial(getConfig().getString("Recipe.Middle Left")));
		jetpack.setIngredient('5', Material.getMaterial(getConfig().getString("Recipe.Middle Center")));
		jetpack.setIngredient('6', Material.getMaterial(getConfig().getString("Recipe.Middle Right")));
		jetpack.setIngredient('7', Material.getMaterial(getConfig().getString("Recipe.Bottom Left")));
		jetpack.setIngredient('8', Material.getMaterial(getConfig().getString("Recipe.Bottom Center")));
		jetpack.setIngredient('9', Material.getMaterial(getConfig().getString("Recipe.Bottom Right")));
	    PluginDescriptionFile pdffile = getDescription();
	    this.logger.info(pdffile.getName() + " Version " + pdffile.getVersion() + " has been enabled.");
	    PluginManager pm = getServer().getPluginManager();
	    pm.registerEvents(this, this);
	    pm.registerEvents(flyListener, this);
	    pm.registerEvents(refillListener, this);
	    getServer().addRecipe(jetpack);
	  }
	public ItemStack setName(ItemStack item, String name)
	{
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 10, true);
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.YELLOW + "Left Click to reload.");
		lore.add(ChatColor.YELLOW + "Jump and shift to fly.");
		lore.add(ChatColor.YELLOW + "Must be equiped.");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
}


