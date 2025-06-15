package systemerr.dynasmp;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.util.Vector;

import java.util.function.Consumer;

import static org.bukkit.Bukkit.*;
import static org.bukkit.GameMode.SURVIVAL;
import static systemerr.dynasmp.Utils.toTicks;

public class Timer {
    public static int cooldown = 20;
    public static int amount = 5;

    public static int timer;
    public static BossBar bossbar = createBossBar("", BarColor.RED, BarStyle.SEGMENTED_20);

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

        if (timer == 5) {
            getOnlinePlayers().forEach((Consumer<Player>) player -> {
                if (player.getGameMode() == SURVIVAL) {
                    int height;
                    if (player.getWorld().getName().equals(getWorlds().get(1).getName())) {
                        height = 120;
                    } else if (player.getY() >= 318) {
                        height = -65;
                    } else {
                        height = 318;
                    }
                    for (int i = 0; i < amount; i++) {
                        TNTPrimed tnt = player.getWorld().spawn(new Location(player.getWorld(), Math.round(player.getX()), Math.round(player.getY()) + height, Math.round(player.getZ())), TNTPrimed.class);
                        tnt.setFuseTicks(toTicks(5));
                        tnt.setGlowing(true);
                        tnt.setVelocity(new Vector(0, -20, 0));
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
