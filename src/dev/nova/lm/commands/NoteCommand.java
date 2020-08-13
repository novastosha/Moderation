package dev.nova.lm.commands;

import dev.nova.lm.LegendaryModeration;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;

public class NoteCommand implements CommandExecutor {
    public String p = "setnote";
    private Plugin plugin = LegendaryModeration.getPlugin(LegendaryModeration.class);
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        InventoryClickEvent event;
        Player player = (Player) commandSender;
        if(command.getName().equalsIgnoreCase(p)){
            if(player.hasPermission("lm.note")){
               if(strings.length == 0){
                   player.sendMessage("§cPlease select a player!");

               }else{
                   Player player1 = Bukkit.getPlayer(strings[0]);
                   plugin.getConfig().set("Notes."+player.getUniqueId().toString()+".Player."+player1.getUniqueId().toString(), strings[1]);
                   player.sendMessage(LegendaryModeration.prefix+"Set: §3"+strings[1]+" §8as a note for: "+strings[0]);
                   plugin.saveConfig();
               }
            }
        }
        return true;
    }
}
