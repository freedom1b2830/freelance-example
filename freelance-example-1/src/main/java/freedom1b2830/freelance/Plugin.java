package freedom1b2830.freelance;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class Plugin extends JavaPlugin implements Listener {
	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);
	}

	@EventHandler
	public void villagerDamage(EntityDamageEvent event) {
		@NotNull
		EntityType tpy = event.getEntityType();
		if (tpy.getEntityClass() == Villager.class) {
			event.setCancelled(true);
		}
	}
}
