package cz.nixdevelopment.br.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import cz.nixdevelopment.br.BattleRoyale;
import cz.nixdevelopment.br.instances.PlayerInstance;
import cz.nixdevelopment.br.schedulers.CountdownScheduler;
import cz.nixdevelopment.br.utils.GameUtil;
import cz.nixdevelopment.br.utils.Messages;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        
        // commands: /br [start | stats]
        if(args.length == 1) {
            
            if(args[0].equalsIgnoreCase("start")) {
                if(sender.hasPermission("br.admin")) {
                    if(BattleRoyale.GetGamePlayers().GetSize() > 1) {
                        GameUtil.StartGame();
                    }
                    else {
                        sender.sendMessage(Messages.ForceStartMoreThanOne());
                    }
                }
                else {
                    sender.sendMessage(Messages.NotPermission("br.admin"));
                }
            }
            
            else if(args[0].equalsIgnoreCase("stats")) {
                
                PlayerInstance pi = BattleRoyale.GetGamePlayers().GetPlayer(Bukkit.getPlayer(sender.getName()).getUniqueId());
                sender.sendMessage(Messages.Stats(sender.getName(), pi.GetKills(), pi.GetDeaths(), pi.GetWins(), pi.GetLoses(), pi.GetPlays()));
                
            }
            
            else {
                
                sender.sendMessage(Messages.UnknownCommand());
                
            }
            
        }
        else if(args.length == 2) {
            
            if(args[0].equalsIgnoreCase("stats")) {
                
                if(Bukkit.getPlayer(args[1]) != null && Bukkit.getPlayer(args[1]).isOnline()) {
                    
                    PlayerInstance pi = BattleRoyale.GetGamePlayers().GetPlayer(Bukkit.getPlayer(sender.getName()).getUniqueId());
                    sender.sendMessage(Messages.Stats(sender.getName(), pi.GetKills(), pi.GetDeaths(), pi.GetWins(), pi.GetLoses(), pi.GetPlays()));
                    
                }
                else {
                    sender.sendMessage(Messages.TargetOffline(args[1]));
                }
                
            }
            else {
                sender.sendMessage(Messages.UnknownCommand());
            }
            
        }
        else {
            sender.sendMessage(Messages.BattleRoyaleUsage(sender.hasPermission("br.admin")));
        }
        
        return false;
    }

}
