package dev.nova.lm.commands;

import dev.nova.libs.time.TimeLib;
import dev.nova.lm.LegendaryModeration;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class BanCommand implements CommandExecutor {

    public String cmdTemp = "tempban";
    public String cmdPerm = "ban";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(command.getName().equalsIgnoreCase(cmdTemp)){
            if(commandSender.hasPermission("lm.ban")){
                //Player player, String reason, @Nullable Player banIssuer, boolean silent,String frl
                //player, "no" or "yes","long","reason..."
                if(args.length == 0){
                    commandSender.sendMessage(ChatColor.RED+"Missing arguments");
                }
                if(args.length == 1){
                    commandSender.sendMessage(ChatColor.RED+"Missing arguments");
                }
                if(args.length == 2){
                    commandSender.sendMessage(ChatColor.RED+"Missing arguments");
                }
                if(args.length == 3){
                    commandSender.sendMessage(ChatColor.RED+"Missing arguments");
                }
                if(args.length >= 4){
                    String time = convertToTime(args[2]);
                }
                return true;
            }else{
                commandSender.sendMessage(ChatColor.RED+"You don't have permission to do this!");
            }
        }

        return true;
    }

    private String convertToTime(String arg) {
        ArrayList<String> timeStamps = new ArrayList<>();
        for(String cha: arg.split("")){
            String lastChars = "";
            if(isNumeric(cha)){
                lastChars = lastChars+cha;
            }else{
                if(cha.equalsIgnoreCase("s")){
                    cha="seconds";
                }

                if(cha.equalsIgnoreCase("m")){
                    cha="minutes";
                }

                if(cha.equalsIgnoreCase("h")){
                    cha="hours";
                }

                if(cha.equalsIgnoreCase("d")){
                    cha="days";
                }

                if(cha.equalsIgnoreCase("y")){
                    cha="years";
                }

                lastChars = lastChars+" "+cha;
                timeStamps.add(lastChars);
                lastChars = "";
            }
        }
        return combineArgs(timeStamps);
    }

    private String combineArgs(ArrayList<String> args) {
        String combined = "";
        for (String arg : args) {
            combined = combined + arg + " ";
        }
        return combined;
    }
    private boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
