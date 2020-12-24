package cz.nixdevelopment.br.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import cz.nixdevelopment.br.BattleRoyale;
import cz.nixdevelopment.br.utils.GameUtil;
import cz.nixdevelopment.br.utils.Messages;

public class OnLeaveListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public static void OnLeaveEvent(PlayerQuitEvent event) {
        
        if(!BattleRoyale.GameInfo().IsGameActive()) {
            BattleRoyale.GameInfo().SetTotal(BattleRoyale.GameInfo().GetTotal() - 1);
            Bukkit.broadcastMessage(Messages.PlayerLeave(event.getPlayer().getName(), BattleRoyale.GameInfo().GetTotal(), BattleRoyale.MinimumPlayersToStartGame()));
            if(BattleRoyale.GameInfo().GetTotal() < BattleRoyale.MinimumPlayersToStartGame()) {
                BattleRoyale.GameInfo().NotReady();
                for(Player p : Bukkit.getOnlinePlayers()) {
                    p.setLevel(0);
                }
            }
        }
        else if(BattleRoyale.GetGamePlayers().GetPlayer(event.getPlayer().getUniqueId()).IsAlive()) {       
            
            BattleRoyale.GetGamePlayers().GetPlayer(event.getPlayer().getUniqueId()).IncreaseDeaths();
            BattleRoyale.GetGamePlayers().GetPlayer(event.getPlayer().getUniqueId()).IncreaseLoses();
            BattleRoyale.GetGamePlayers().GetPlayer(event.getPlayer().getUniqueId()).PlayerDied();
            BattleRoyale.GetGamePlayers().ResetInventory(event.getPlayer().getName());
            BattleRoyale.GameInfo().DecrementLeft();
            BattleRoyale.GameInfo().SetTotal(BattleRoyale.GameInfo().GetTotal() - 1);
            
            Bukkit.broadcastMessage(Messages.PlayerLeave(event.getPlayer().getName()));
            
            if(BattleRoyale.GameInfo().GetTotal() < BattleRoyale.MinimumPlayersToStartGame())
                BattleRoyale.GameInfo().NotReady();
            
            if(BattleRoyale.GameInfo().GetLeft() == 1) {
                
                GameUtil.EndGame();
                
            }
        }

        BattleRoyale.GetGamePlayers().RemoveGamePlayer(event.getPlayer());
        Bukkit.broadcastMessage("REMOVING PLAYER");
        
        
    }
    
}
