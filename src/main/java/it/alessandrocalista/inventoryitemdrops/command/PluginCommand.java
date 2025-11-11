package it.alessandrocalista.inventoryitemdrops.command;

import it.alessandrocalista.inventoryitemdrops.InventoryItemDrops;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class PluginCommand implements CommandExecutor {
    private final InventoryItemDrops plugin;

    public PluginCommand(InventoryItemDrops plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(
            @NotNull CommandSender sender,
            @NotNull Command command,
            @NotNull String label,
            @NotNull String @NotNull [] args
    ) {
        if(!sender.hasPermission("inventoryitemdrops.reload")) {
            sender.sendRichMessage("<red>Non hai il permesso per eseguire questo comando.");
            return true;
        }

        plugin.reloadConfig();
        sender.sendRichMessage("<green>Configurazione ricaricata");
        return true;
    }
}
