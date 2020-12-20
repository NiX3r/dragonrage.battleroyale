package cz.nixdevelopment.br.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import cz.nixdevelopment.br.BattleRoyale;
import cz.nixdevelopment.br.utils.GameUtil;
import cz.nixdevelopment.br.utils.Messages;
import cz.nixdevelopment.br.utils.MySQL;

public class OnJoinListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public static void OnJoinEvent(PlayerJoinEvent event) {
        
        MySQL.CheckPlayerExists(event.getPlayer().getUniqueId().toString(), event.getPlayer().getName());
        BattleRoyale.GetGamePlayers().AddGamePlayer(event.getPlayer());

        if(!BattleRoyale.GameInfo().IsGameActive() && !BattleRoyale.GameInfo().IsGameReady()) {
            BattleRoyale.GameInfo().SetTotal(BattleRoyale.GameInfo().GetTotal() + 1);
            Bukkit.broadcastMessage(Messages.PlayerJoin(event.getPlayer().getName(), BattleRoyale.GameInfo().GetTotal(), BattleRoyale.MinimumPlayersToStartGame()));
            if(BattleRoyale.GameInfo().GetTotal() == BattleRoyale.MinimumPlayersToStartGame()) {
                BattleRoyale.GameInfo().Ready();
                GameUtil.StartGame();
            }
        }
        
    }
    
}
