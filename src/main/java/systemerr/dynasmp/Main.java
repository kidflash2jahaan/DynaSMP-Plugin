package systemerr.dynasmp;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public final class Main extends JavaPlugin implements Listener {
    public static final Logger log = LoggerFactory.getLogger(Main.class);

    @Override
    public void onEnable() {
        Objects.requireNonNull(this.getCommand("howtoplay")).setExecutor(new HowToPlay());
        getServer().dispatchCommand(getServer().getConsoleSender(), "gamerule spawnRadius 0");
        getServer().dispatchCommand(getServer().getConsoleSender(), "setworldspawn 0 255 0");
        getServer().getPluginManager().registerEvents(this, this);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, Timer::update, 0, Utils.toTicks(1));
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Timer.bossbar.addPlayer(event.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Timer.bossbar.removePlayer(event.getPlayer());
    }
}
