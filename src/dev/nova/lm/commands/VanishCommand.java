package dev.nova.lm.commands;

import dev.nova.lm.methods.Punishments;
import dev.nova.lm.methods.Vanishing;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {
    public String vanish  ="vanish";
    private Vanishing vanishing = new Vanishing();
    private Punishments punishments = new Punishments();
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;

        if(command.getName().equalsIgnoreCase(vanish)){

            if(player.hasPermission("lm.vanish")){
                if(strings.length == 0){
                    vanishing.vanish(player,null);
                }else{
                    Player target = Bukkit.getPlayerExact(strings[0]);
                    vanishing.vanish(player,target);
                }

            }else{player.sendMessage("You don't have permission to this!");}
        }
        return true;
    }
}
