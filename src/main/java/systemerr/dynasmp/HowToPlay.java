package systemerr.dynasmp;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class HowToPlay implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        commandSender.sendMessage("Hi " + commandSender.getName() + "! Welcome to the DynaSMP, a public Minecraft server where players try to survive in a limited world while explosives rain down on them!");
        
        return true;
    }
}
