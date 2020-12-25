package cz.nixdevelopment.br.utils;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import cz.nixdevelopment.br.BattleRoyale;
import cz.nixdevelopment.br.instances.MapInstance;
import cz.nixdevelopment.br.instances.PlayerInstance;
import cz.nixdevelopment.br.schedulers.CountdownScheduler;
import cz.nixdevelopment.br.schedulers.DropGodScheduler;
import cz.nixdevelopment.br.schedulers.PhaseScheduler;


public class GameUtil {

    public static ArrayList<MapInstance> LoadMaps(FileConfiguration config){
        
        ArrayList<MapInstance> output = new ArrayList<MapInstance>();
        ConfigurationSection sec = config.getConfigurationSection("Maps");
        if (sec != null) {
            for (String key : sec.getKeys(false)) {
                
                output.add(new MapInstance(key, config.getInt("Maps." + key + ".MinX"), config.getInt("Maps." + key + ".MinZ"), config.getInt("Maps." + key + ".MinZ"), config.getInt("Maps." + key + ".MaxZ"), config.getInt("Maps." + key + ".CenterX"), config.getInt("Maps." + key + ".CenterZ"), config.getString("Maps." + key + ".World")));
                
            }
        }
        
        return output;
        
    }
    
    public static void StartGame() {
        Bukkit.broadcastMessage(Messages.StartCountdown());
        CountdownScheduler.CountDown();
    }
    
    public static void StopCountDown() {
        Bukkit.broadcastMessage(Messages.StopCountdown());
    }
    
    public static void ForceStartGame() {
        
        Bukkit.broadcastMessage(Messages.StartGame());
        BattleRoyale.GameInfo().SetTotal(Bukkit.getOnlinePlayers().size());
        BattleRoyale.GetGamePlayers().RevivePlayers();
        BattleRoyale.GetGamePlayers().IncreasePlays();
        BattleRoyale.SetMapActual(BattleRoyale.GetMap().get(0));
        BattleRoyale.SetPlayersGod(true);
        BattleRoyale.GetGamePlayers().SpawnPlayers(BattleRoyale.GetActualMap());
        PhaseScheduler.CountDown();
        DropGodScheduler.DropGod();
        BattleRoyale.GameInfo().StartGame();
        
    }
    
    public static void EndGame() {
        
        if(BattleRoyale.GetGamePlayers().GetLastPlayer() != null) {
            
            BattleRoyale.GetGamePlayers().GetLastPlayer().IncreaseWins();
            Boolean isSended = false;
            Player p = Bukkit.getPlayer(BattleRoyale.GetGamePlayers().GetLastPlayer().GetNick());
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set minecraft.command.worldborder");
            while(!isSended) {
                if(p.hasPermission("minecraft.command.worldborder"))
                    isSended = p.performCommand("worldborder set 700");
            }
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission unset minecraft.command.worldborder");

            
            Bukkit.broadcastMessage(Messages.WinnerWinnerChickenDinner(BattleRoyale.GetGamePlayers().GetLastPlayer().GetNick()));
            BattleRoyale.GetGamePlayers().TeleportLast();
            
            BattleRoyale.GameInfo().EndGame();
            BattleRoyale.SetMapActual(null);
            BattleRoyale.SetSecToNextPhase(0);
            
            if(BattleRoyale.GameInfo().IsGameReady())
                StartGame();
            
        }
        else {
            Bukkit.broadcastMessage("§4Neco se zhroutilo! Napiste adminum! Error: GameUtil#EndGame()");
        }
        
    }
    
    public static void ForceEndGame() {
    }
    
}
