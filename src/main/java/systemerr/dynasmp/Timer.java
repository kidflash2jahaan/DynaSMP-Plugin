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

public class Timer {
    public static int cooldown = 20;
    public static int amount = 5;
    public static int activation = 5;

    public static int timer;
    public static BossBar bossbar = Bukkit.createBossBar("", BarColor.RED, BarStyle.SEGMENTED_20);

    public static void update() {
        Utils.fillSpawn(3, Material.AIR, -63, 319);
        Utils.fillSpawn(2, Material.BEDROCK, 255, 255);
        Utils.fillSpawn(1, Material.WATER, -63, 255);
        Utils.fillSpawn(0, Material.BEDROCK, 255, 255);

        bossbar.setTitle("TNT Explodes In: " + timer);
        bossbar.setProgress((double) timer / cooldown);
        bossbar.setVisible(true);

        if (timer == activation) {
            Bukkit.getOnlinePlayers().forEach((Consumer<Player>) player -> {
                if (player.getGameMode() == GameMode.SURVIVAL) {
                    int height;
                    if (player.getY() == 255) {
                        height = -65;
                    } else {
                        height = 319;
                    }
                    for (int i = 0; i < amount; i++) {
                        TNTPrimed tnt = player.getWorld().spawn(new Location(player.getWorld(), Math.round(player.getX()), Math.round(player.getY()) + height, Math.round(player.getZ())), TNTPrimed.class);
                        tnt.setFuseTicks(Utils.toTicks(activation));
                        tnt.setGlowing(true);
                        tnt.setVelocity(new Vector(0, -20, 0));
                    }
                }
            });
        }

        if (timer <= 0) {
            timer = cooldown;
        }
    }
}
