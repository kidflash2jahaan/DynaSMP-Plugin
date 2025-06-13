package systemerr.dynasmp;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                getServer().getWorlds().getFirst().getBlockAt(x, 319, z).setType(Material.BEDROCK);
            }
        }
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                for (int y = -63; y <= 319; y++) {
                    getServer().getWorlds().getFirst().getBlockAt(x, y, z).setType(Material.WATER);
                }
            }
        }
        getServer().getWorlds().getFirst().getBlockAt(0, 319, 0).setType(Material.BEDROCK);
        
        bossbar.setTitle("TNT Explodes In: " + timer);
        bossbar.setProgress((double) timer / cooldown);
        bossbar.setVisible(true);
        
        if (timer == 4) {
            getOnlinePlayers().forEach((Consumer<Player>) player -> {
                if (player.getGameMode() == GameMode.SURVIVAL) {
                    for (int i = 0; i < amount; i++) {
                        player.getWorld().getBlockAt((int) Math.round(player.getX()), (int) (Math.round(player.getY()) + height + 1), (int) Math.round(player.getZ())).setType(Material.REDSTONE_BLOCK);
                        player.getWorld().getBlockAt((int) Math.round(player.getX()), (int) (Math.round(player.getY()) + height), (int) Math.round(player.getZ())).setType(Material.TNT);
                        player.getWorld().getBlockAt((int) Math.round(player.getX()), (int) (Math.round(player.getY()) + height + 1), (int) Math.round(player.getZ())).setType(Material.AIR);
                    }
                }
            });
        }
        
        if (timer <= 0) {
            timer = cooldown;
        }

        timer--;
//        log.info(String.valueOf(timer));
    }
}
