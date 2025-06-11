package systemerr.dynasmp;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.util.function.Consumer;

import static org.bukkit.Bukkit.getOnlinePlayers;
import static org.bukkit.Bukkit.getServer;
import static systemerr.dynasmp.Main.log;

public class Timer {
    public static int cooldown = 20;
    public static int height = 40;
    public static int amount = 5;
    
    public static int timer;
    public static BossBar bossbar = Bukkit.createBossBar("", BarColor.RED, BarStyle.SEGMENTED_20);

    public static void update() {
        bossbar.setTitle("TNT Explodes In: " + timer);
        bossbar.setProgress((double) timer / cooldown);
        bossbar.setVisible(true);
        
        if (timer == 4) {
            getOnlinePlayers().forEach((Consumer<Player>) player -> {
                if (player.getGameMode() == GameMode.SURVIVAL) {
                    for (int i = 0; i < amount; i++) {
                        getServer().dispatchCommand(getServer().getConsoleSender(), "setblock " + Math.round(player.getX()) + " " + (Math.round(player.getY()) + height + 1) + " " + Math.round(player.getZ()) + " minecraft:redstone_block");
                        getServer().dispatchCommand(getServer().getConsoleSender(), "setblock " + Math.round(player.getX()) + " " + (Math.round(player.getY()) + height) + " " + Math.round(player.getZ()) + " minecraft:tnt");
                        getServer().dispatchCommand(getServer().getConsoleSender(), "setblock " + Math.round(player.getX()) + " " + (Math.round(player.getY()) + height + 1) + " " + Math.round(player.getZ()) + " minecraft:air");
                    }
                }
            });
        }
        
        if (timer <= 0) {
            timer = cooldown;
        }

        timer--;
        log.info(String.valueOf(timer));
    }
}
