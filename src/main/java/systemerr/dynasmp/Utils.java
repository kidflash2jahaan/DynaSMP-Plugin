package systemerr.dynasmp;

import org.bukkit.Bukkit;
import org.bukkit.Material;

public class Utils {
    public static int toSeconds(int ticks) {
        return ticks / 20;
    }
    
    public static int toTicks(int seconds) {
        return seconds * 20;
    }
    
    public static void fillSpawn(int radius, Material block, int y1, int y2) {
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                for (int y = y1; y <= y2; y++) {
                    Bukkit.getServer().getWorlds().getFirst().getBlockAt(x, y, z).setType(block);
                }
            }
        }
    }
}
