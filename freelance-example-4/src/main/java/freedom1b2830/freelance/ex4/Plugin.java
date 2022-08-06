package freedom1b2830.freelance.ex4;

import mc.alk.arena.util.ExpUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.logging.Level;

//восстановление жизней за опыт
public class Plugin extends JavaPlugin implements CommandExecutor {
    @Override
    public void onEnable() {
        @Nullable PluginCommand co = this.getCommand("healexp");
        if (co == null) {
            getLogger().log(Level.SEVERE, "error on init command" + "healexp");
            throw new IllegalStateException();
        }
        co.setExecutor(this);
    }

    public static final int HEALTHOK = 20;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (sender instanceof Player player) {
            int health = (int) player.getHealth();
            if (health >= HEALTHOK) {
                player.sendMessage("your health is OK");
                return false;
            }
            int exp = ExpUtil.getTotalExperience(player);
            if (exp == 0) {
                player.sendMessage("your have not experience");
                return false;
            }
            int healNeed = HEALTHOK - health;
            if (healNeed > exp) {
                player.setHealth(player.getHealth() + exp);
                ExpUtil.setTotalExperience(player, 0);
                player.sendMessage("" + health, "" + healNeed, "" + exp);
            } else if (healNeed == exp) {
                player.setHealth(HEALTHOK);
                ExpUtil.setTotalExperience(player, 0);
                player.sendMessage("healthOk");
            } else {
                int expAfter = exp - healNeed;
                ExpUtil.setTotalExperience(player, expAfter);
                player.setHealth(HEALTHOK);
                player.sendMessage("ELSE " + health, "" + healNeed, "" + exp);
            }
            player.sendHealthUpdate();
        }
        return false;
    }
}
