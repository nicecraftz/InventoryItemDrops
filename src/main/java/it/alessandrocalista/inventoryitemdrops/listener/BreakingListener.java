package it.alessandrocalista.inventoryitemdrops.listener;

import com.destroystokyo.paper.MaterialTags;
import it.alessandrocalista.inventoryitemdrops.InventoryItemDrops;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.HashMap;

public class BreakingListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        if (!MaterialTags.ORES.isTagged(block.getType())) return;

        Collection<ItemStack> list = block.getDrops(player.getActiveItem());

        if (InventoryItemDrops.smeltOres) {
            list = list.stream().map(InventoryItemDrops::getSmeltingResult).toList();
        }

        if (InventoryItemDrops.itemsToInventory) {
            event.setDropItems(false);
            HashMap<Integer, ItemStack> unfittedItems = player.getInventory().addItem(list.toArray(new ItemStack[0]));
            if (unfittedItems.isEmpty()) return;
            unfittedItems.forEach((slot, item) -> player.getWorld().dropItemNaturally(player.getLocation(), item));
        }

    }

}
