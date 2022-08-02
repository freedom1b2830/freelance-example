package freedom1b2830.freelance.ex4;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

//восстановление жизней за опыт
public class Plugin extends JavaPlugin implements CommandExecutor {
    @Override
    public void onEnable() {
        this.getCommand("healexp").setExecutor(this);
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
            int exp = player.getTotalExperience();
            int healNeed = HEALTHOK - health;
            if (healNeed > exp) {
                player.setHealth(player.getHealth() + exp);
                player.setTotalExperience(0);
                player.sendMessage("" + health, "" + healNeed, "" + exp);
            } else if (healNeed == exp) {
                player.setHealth(HEALTHOK);
                player.setTotalExperience(0);
                player.sendMessage("healthOk");
            } else {
                int expAfter = exp - healNeed;
                player.setTotalExperience(expAfter);
                player.setHealth(HEALTHOK);
                player.sendMessage("ELSE " + health, "" + healNeed, "" + exp);
            }
            player.sendHealthUpdate();
        }
        return false;
    }
}
