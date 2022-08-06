package freedom1b2830.freelance.ex5;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

public class Plugin extends JavaPlugin {
    @Override
    public void onEnable() {
        @Nullable PluginCommand co = this.getCommand("vilinvedit");
        if (co == null) {
            getLogger().log(Level.SEVERE, "error on init command" + "vilinvedit");
            throw new IllegalStateException();
        }
        co.setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (sender instanceof Player player) {
            @NotNull List<Entity> near = player.getNearbyEntities(3, 3, 3);
            Optional<Entity> result = near.stream().filter(Villager.class::isInstance).findFirst();
            if (result.isPresent()) {
                Villager villager = (Villager) result.get();
                player.openInventory(villager.getInventory());
                return true;
            } else {
                player.sendMessage("no villager near 3 blocks");
                return false;
            }
        }
        return false;
    }
}
