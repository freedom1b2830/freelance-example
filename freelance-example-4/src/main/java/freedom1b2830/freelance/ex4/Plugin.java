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

    public static final double HEALTHOK = 20D;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (sender instanceof Player player) {
            double health = player.getHealth();
            if (health >= HEALTHOK) {
                player.sendMessage("your health is OK");
                return false;
            }
            double exp = player.getExp();
            double healNeed = HEALTHOK - health;
            if (healNeed > exp) {
                player.setHealth(player.getHealth() + exp);
                player.setExp(0F);
                player.sendMessage("" + health, "" + healNeed, "" + exp);
            } else if (healNeed == exp) {
                player.setHealth(HEALTHOK);
                player.setExp(0F);
                player.sendMessage("healthOk");
            } else {
                double expAfter = exp - healNeed;
                player.setExp((float) expAfter);
                player.setHealth(HEALTHOK);
                player.sendMessage("ELSE " + health, "" + healNeed, "" + exp);
            }
        }
        return false;
    }
}
