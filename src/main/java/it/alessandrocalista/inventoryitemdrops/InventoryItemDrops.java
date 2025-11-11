package it.alessandrocalista.inventoryitemdrops;

import it.alessandrocalista.inventoryitemdrops.command.PluginCommand;
import it.alessandrocalista.inventoryitemdrops.listener.BreakingListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.java.JavaPlugin;

public final class InventoryItemDrops extends JavaPlugin {
    public static boolean smeltOres = false;
    public static boolean itemsToInventory = false;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new BreakingListener(), this);
        getCommand("reloadiid").setExecutor(new PluginCommand(this));
    }

    @Override
    public void reloadConfig() {
        super.reloadConfig();
        smeltOres = getConfig().getBoolean("smelt-ores", false);
        itemsToInventory = getConfig().getBoolean("items-to-inventory", false);
    }

    public static ItemStack getSmeltingResult(ItemStack input) {
        if (input == null || input.getType() == Material.AIR) return input;

        for (Recipe recipe : Bukkit.getServer().getRecipesFor(input)) {
            if (recipe instanceof FurnaceRecipe furnaceRecipe) {
                ItemStack clone = furnaceRecipe.getResult().clone();
                clone.setAmount(input.getAmount());
                return clone;
            }
        }

        return input;
    }
}
