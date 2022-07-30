package freedom1b2830.freelance.ex2;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

//Фильтр чата
public class Plugin extends JavaPlugin implements Listener {
	@EventHandler
	public void chatFilter(AsyncChatEvent event) {
		@NotNull
		Player player = event.getPlayer();
		@NotNull
		Component message = event.message();
		@NotNull
		String aaa = PlainTextComponentSerializer.plainText().serialize(message);
		System.out.println("Plugin.chatFilter() " + aaa);

	}

	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);
	}
}
