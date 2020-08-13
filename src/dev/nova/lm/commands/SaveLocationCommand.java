package dev.nova.lm.commands;

import dev.nova.lm.LegendaryModeration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class SaveLocationCommand implements CommandExecutor {
    public String slc = "savelocation";
    private Plugin plugin = LegendaryModeration.getPlugin(LegendaryModeration.class);

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;

        if (command.getName().equalsIgnoreCase(slc)) {
            if (player.hasPermission("lm.saveLocation")) {
                if(plugin.getConfig().getBoolean("SpL."+player.getUniqueId().toString()+".enabled")){
                    player.sendMessage(LegendaryModeration.prefix+"Disabled Spectator save location!");
                    plugin.getConfig().set("SpL."+player.getUniqueId().toString()+".enabled",false);
                    plugin.saveConfig();
                    return true;
                }else{
                    player.sendMessage(LegendaryModeration.prefix+"Enabled Spectator save location!");
                    plugin.getConfig().set("SpL."+player.getUniqueId().toString()+".enabled",true);
                    plugin.saveConfig();
                    return true;
                }
            } else {
                player.sendMessage("§cYou don't have permission to do that!");
            }
            return true;

        }
        return true;
    }
}