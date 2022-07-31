package freedom1b2830.freelance.ex3;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class Plugin extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }


    @EventHandler
    public void entityKilled2(EntityDamageByEntityEvent event) {
        @NotNull Entity entity = event.getEntity();
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;
            double health = livingEntity.getHealth();
            double damage = event.getDamage();
            if (health <= 0D || damage - health <= 0D) {
                @NotNull Entity damager = event.getDamager();
                if (damager instanceof Player) {
                    @NotNull String playerName = ((Player) damager).getName();
                    getLogger().info(String.format("%s killed by %s ", livingEntity.getName(), playerName));
                }
            }
        }

    }
}
