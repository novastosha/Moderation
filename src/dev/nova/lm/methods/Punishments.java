package dev.nova.lm.methods;

import dev.nova.libs.time.TimeLib;
import dev.nova.lm.LegendaryModeration;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nullable;

public class Punishments implements Listener {
    private TimeLib timeLib = (TimeLib) Bukkit.getServer().getPluginManager().getPlugin("TimeLibrary");

    private FileConfiguration configuration = LegendaryModeration.getPlugin(LegendaryModeration.class).getConfig();
    private Plugin plugin = LegendaryModeration.getPlugin(LegendaryModeration.class);
    public void permBan(Player player, String reason, @Nullable Player banIssuer, boolean silent){
        configuration.set("Bans."+player.getUniqueId().toString()+".isPerm",true);
        if(banIssuer != null) configuration.set("Bans."+player.getUniqueId().toString()+".Issuer",banIssuer.getUniqueId().toString());
        configuration.set("Bans."+player.getUniqueId().toString()+".Reason",reason);
        plugin.saveConfig();

        if(silent){
            for(Player toSend : Bukkit.getOnlinePlayers()){
                if(toSend.hasPermission("lm.getNotified")) {
                    if(player.isOnline()){
                        player.kickPlayer("§cYou are permanently banned from the server\n§7Reason: §f"+reason);
                    }
                    if (banIssuer != null) {
                        toSend.sendMessage("§7(Silent) "+"§b"+banIssuer.getName() + "§8 has banned: §b" + player.getName() + " §8reason: §b" + reason);
                    } else {
                        toSend.sendMessage("§7(Silent) "+"§bConsole" + "§8 has banned: §b" + player.getName() + " §8reason: §b" + reason);
                    }
                }
            }
        }else{
            for(Player toSend : Bukkit.getOnlinePlayers()){
                if(banIssuer != null) {
                    if(player.isOnline()){
                        player.kickPlayer("§cYou are permanently banned from the server\n§7Reason: §f"+reason+"\n\n§7Issuer:§f "+banIssuer.getName());
                    }
                    toSend.sendMessage("§b"+banIssuer.getName() + "§8 has banned: §b" + player.getName() + " §8reason: §b" + reason);
                }else {
                    if(player.isOnline()){
                        player.kickPlayer("§cYou are permanently banned from the server\n§7Reason: §f"+reason+"\n\n§7Issuer:§f Console");
                    }
                    toSend.sendMessage("§bConsole"+ "§8 has banned: §b" + player.getName() + " §8reason: §b" + reason);
                }
            }
        }
    }
    public void tempBan(Player player, String reason, @Nullable Player banIssuer, boolean silent,String frl){
        configuration.set("Bans."+player.getUniqueId().toString()+".isPerm",false);
        if(banIssuer != null) configuration.set("Bans."+player.getUniqueId().toString()+".Issuer",banIssuer.getUniqueId().toString());
        configuration.set("Bans."+player.getUniqueId().toString()+".Reason",reason);
        configuration.set("Bans."+player.getUniqueId().toString()+".banned-at",System.currentTimeMillis());
        configuration.set("Bans."+player.getUniqueId().toString()+".for",frl);
        plugin.saveConfig();

        if(silent){
            for(Player toSend : Bukkit.getOnlinePlayers()){
                if(toSend.hasPermission("lm.getNotified")) {
                    if(player.isOnline()){
                        player.kickPlayer("§cYou are permanently banned from the server\n§7Reason: §f"+reason);
                    }
                    if (banIssuer != null) {
                        toSend.sendMessage("§7(Silent) "+"§b"+banIssuer.getName() + "§8 has banned: §b" + player.getName() + " §8reason: §b" + reason);
                    } else {
                        toSend.sendMessage("§7(Silent) "+"§bConsole" + "§8 has banned: §b" + player.getName() + " §8reason: §b" + reason);
                    }
                }
            }
        }else{
            for(Player toSend : Bukkit.getOnlinePlayers()){
                if(banIssuer != null) {
                    if(player.isOnline()){
                        player.kickPlayer("§cYou are permanently banned from the server\n§7Reason: §f"+reason+"\n\n§7Issuer:§f "+banIssuer.getName());
                    }
                    toSend.sendMessage("§b"+banIssuer.getName() + "§8 has banned: §b" + player.getName() + " §8reason: §b" + reason);
                }else {
                    if(player.isOnline()){
                        player.kickPlayer("§cYou are permanently banned from the server\n§7Reason: §f"+reason+"\n\n§7Issuer:§f Console");
                    }
                    toSend.sendMessage("§bConsole"+ "§8 has banned: §b" + player.getName() + " §8reason: §b" + reason);
                }
            }
        }
    }
    public void kick(Player player, String reason,@Nullable Player kickIssuer, boolean silent){
        if(silent){
            for(Player toSend : Bukkit.getOnlinePlayers()){
                if(toSend.hasPermission("lm.getNotified")) {
                    if(player.isOnline()){
                        player.kickPlayer("§cYou are kicked from the server\n§7Reason: §f"+reason);
                    }
                    if (kickIssuer != null) {
                        toSend.sendMessage("§7(Silent) "+"§b"+kickIssuer.getName() + "§8 has kicked: §b" + player.getName() + " §8reason: §b" + reason);
                    } else {
                        toSend.sendMessage("§7(Silent) "+"§bConsole" + "§8 has kicked: §b" + player.getName() + " §8reason: §b" + reason);
                    }
                }
            }
        }else{
            for(Player toSend : Bukkit.getOnlinePlayers()){
                if(kickIssuer != null) {
                    if(player.isOnline()){
                        player.kickPlayer("§cYou are kicked from the server\n§7Reason: §f"+reason+"\n\n§7Issuer:§f "+kickIssuer.getName());
                    }
                    toSend.sendMessage("§b"+kickIssuer.getName() + "§8 has kicked: §b" + player.getName() + " §8reason: §b" + reason);
                }else {
                    if(player.isOnline()){
                        player.kickPlayer("§cYou are kicked from the server\n§7Reason: §f"+reason+"\n\n§7Issuer:§f Console");
                    }
                    toSend.sendMessage("§bConsole"+ "§8 has kicked: §b" + player.getName() + " §8reason: §b" + reason);
                }
            }
            }
    }

    @EventHandler
    public void onConnect(PlayerLoginEvent event) {
        Player player = event.getPlayer();

        if (configuration.getBoolean("Bans." + player.getUniqueId().toString() + ".isPerm")) {
            event.getPlayer().kickPlayer("§cYou are permanently banned from the server\n§7Reason: §f" + configuration.getString("Bans."+player.getUniqueId().toString()+".Reason"));
        }else{
            long hasBBF = timeLib.getTime(timeLib.differenceNowAnd((Long) configuration.get("Bans."+player.getUniqueId().toString()+".banned-at"))).getSeconds();
            long BF = timeLib.getTime(configuration.getString("Bans."+player.getUniqueId().toString()+".for")).getSeconds();
            if(hasBBF < BF) {
                event.getPlayer().kickPlayer("§cYou are temporary banned from the server\n§7Reason: §f" + configuration.getString("Bans." + player.getUniqueId().toString() + ".Reason") + "\n" + timeLib.differenceNowAnd((Long) configuration.get("Bans." + player.getUniqueId().toString() + ".banned-at")) + " / " + configuration.getString("Bans." + player.getUniqueId().toString() + ".for"));
            }
            }

    }


}
