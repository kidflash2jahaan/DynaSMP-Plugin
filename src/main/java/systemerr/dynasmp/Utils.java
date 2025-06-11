package systemerr.dynasmp;

public class Utils {
    public static int toSeconds(int ticks) {
        return ticks / 20;
    }
    
    public static int toTicks(int seconds) {
        return seconds * 20;
    }
}
