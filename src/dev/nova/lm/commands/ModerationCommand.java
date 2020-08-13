package dev.nova.lm.commands;

import dev.nova.libs.time.TimeLib;
import dev.nova.lm.GUIs;
import dev.nova.lm.LegendaryModeration;
import dev.nova.lm.methods.Tracking;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ModerationCommand implements CommandExecutor {

    public String mod = "moderation";
    private GUIs legendaryModerationGUIs = new GUIs();
    private TimeLib timeLib = (TimeLib) Bukkit.getServer().getPluginManager().getPlugin("TimeLibrary");


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        if(command.getName().equalsIgnoreCase(mod)){

            if(player.hasPermission("lm.command")){
                if(strings.length == 0){
                    player.sendMessage(LegendaryModeration.prefix+"Opening player list...");
                    legendaryModerationGUIs.openListPlayersGUI(player);
                }else{
                    Player t = Bukkit.getPlayer(strings[0]);
                    player.sendMessage(LegendaryModeration.prefix+"Opening options for: §3"+t.getName());
                    legendaryModerationGUIs.openOptionsGUI(player,t);
                }
            }
        }
        return true;
    }
}
