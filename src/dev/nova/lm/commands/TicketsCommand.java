package dev.nova.lm.commands;

import dev.nova.lm.methods.Tickets;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class TicketsCommand implements CommandExecutor {
    public String tickeCmd ="tickets";
       private Tickets tickets =  new Tickets();

    public static String combineArgs(int fromArg, String[] args){
        int inArg = 0;
        String combined = "";
        /*while (!(args.length < inArg)){
            Bukkit.getServer().getConsoleSender().sendMessage(args[inArg]);
            String comL = combined;
            combined = comL+args[inArg]+" ";
            inArg++;
        }*/
        for(String arg : args){

            if(inArg >= fromArg){
                combined = combined+arg+" ";
            }
            inArg++;
        }
        return combined;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
            Player player = (Player) commandSender;
          if(command.getName().equalsIgnoreCase("tickets")){
              if(args.length != 0){
                if(args[0].equalsIgnoreCase("create")){

                     if(args.length == 2){
                         tickets.createTicket(player,combineArgs(1,args));
                         return true;
                     }else{
                         tickets.createTicket(player,null);
                         return true;
                     }
                 }
                  if(args[0].equalsIgnoreCase("delete")){
                    tickets.deleteTicket(player);
                    return true;
                  }
                  if(player.hasPermission("lm.tickets")){
                    String string = args[0];

                    Player player1 = Bukkit.getPlayer(args[1]);
                    if(string.equalsIgnoreCase("reject")){
                        if(args.length == 2){
                            tickets.rejectTicket(player1,player,null);
                            return true;
                        }else if(args.length >= 3){
                            tickets.rejectTicket(player1,player,combineArgs(2,args));
                            return true;
                        }
                        String string1 = args[2];
                        tickets.rejectTicket(player1,player,string1);
                    }
                      if(string.equalsIgnoreCase("close")){
                          tickets.closeTicket(player1,player);
                      }
                      if(string.equalsIgnoreCase("accept")){
                          tickets.acceptTicket(player1,player);
                      }
                  }



            }else{player.sendMessage("§cIncorrect arguments");}
        }
        return true;
    }
}
