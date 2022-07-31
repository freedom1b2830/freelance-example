package freedom1b2830.freelance.ex2;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CopyOnWriteArrayList;

//Фильтр чата
public class Plugin extends JavaPlugin implements Listener {

    private final CopyOnWriteArrayList<Warn> out = new CopyOnWriteArrayList<>();

    public CopyOnWriteArrayList<Warn> getOut() {
        return out;
    }

    @EventHandler
    public void chatFilter(AsyncChatEvent event) {
        @NotNull
        Player player = event.getPlayer();
        @NotNull
        Component messageComponent = event.message();
        @NotNull
        String message = PlainTextComponentSerializer.plainText().serialize(messageComponent);
        if (SpamFilter.filter(message)) {
            getOut().parallelStream().forEachOrdered(warn -> warn.warn(player, message));
        }
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        //demo
        if (out.isEmpty()) {
            Warn warn = (player, message) -> getLogger().warning(String.format("player %s warnMsg is [%s]", player.getName(), message));
            getOut().addIfAbsent(warn);
        }
    }
}
