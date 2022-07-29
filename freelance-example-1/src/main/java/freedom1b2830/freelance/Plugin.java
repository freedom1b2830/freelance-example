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
	public static void main(String[] args) {
	}

	@Override
	public void onDisable() {
		getLogger().info("onDisable has been invoked!");
	}

	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);
	}

	@EventHandler
	public void villagerDamage(EntityDamageEvent event) {
		@NotNull
		EntityType tpy = event.getEntityType();
		if (tpy.getEntityClass() == Villager.class) {
			System.out.println("Plugin.villagerDamage()");
		}

	}

}
