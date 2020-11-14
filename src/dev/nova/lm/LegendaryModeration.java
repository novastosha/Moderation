package dev.nova.lm;

import dev.nova.lm.commands.*;
import dev.nova.lm.methods.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class LegendaryModeration extends JavaPlugin {
    public static String prefix = "§8[§3LM§8] ";

    public static ArrayList<String> loreE = new ArrayList<>();

    private Vanishing vanishing;
    private SpecSaveLoc specSaveLoc;
    private GUIs legendaryModerationGUIs;
    private SaveLocationCommand saveLocationCommand;
    private NoteCommand noteCommand;
    private TicketsCommand ticketsCommand;
    private Tracking tracking;
    private Punishments punishments;
    private BanCommand banCommand;

    public static String combineArgs(int fromArg, String[] args){
        int inArg = fromArg;
        String combined = "";
        while (args[inArg] != null){
            combined = combined+args[inArg]+" ";
            inArg++;
        }
        return combined;
    }

    @Override
    public void onEnable() {
        tracking = new Tracking();
        ticketsCommand = new TicketsCommand();
        specSaveLoc = new SpecSaveLoc();
        saveLocationCommand = new SaveLocationCommand();
        vanishing = new Vanishing();
        legendaryModerationGUIs = new GUIs();
        noteCommand = new NoteCommand();
        punishments = new Punishments();
        Bukkit.getServer().getPluginManager().registerEvents(legendaryModerationGUIs,this);
        Bukkit.getServer().getPluginManager().registerEvents(specSaveLoc,this);
        Bukkit.getServer().getPluginManager().registerEvents(tracking,this);
        Bukkit.getServer().getPluginManager().registerEvents(new StaffChat(),this);
        Bukkit.getServer().getPluginManager().registerEvents(punishments,this);
        Bukkit.getServer().getPluginManager().registerEvents(new Tickets(),this);
        loreE.add(" ");
        loreE.add("§7Exit this menu");
        loreE.add("  ");
        tracking.toString();
        getCommand(new VanishCommand().vanish).setExecutor(new VanishCommand());
        getCommand(saveLocationCommand.slc).setExecutor(saveLocationCommand);
        getCommand(new TrackCommand().cmd).setExecutor(new TrackCommand());
        getCommand(ticketsCommand.tickeCmd).setExecutor(ticketsCommand);
        getCommand(noteCommand.p).setExecutor(noteCommand);
        getCommand(new ModerationCommand().mod).setExecutor(new ModerationCommand());
        getCommand(banCommand.cmdTemp).setExecutor(new BanCommand());
        getCommand(banCommand.cmdPerm).setExecutor(new BanCommand());

        getConfig().options().copyDefaults(true); // NOTE: You do not have to use "plugin." if the class extends the java plugin
        //Save the config whenever you manipulate it
        saveConfig();

    }


}
