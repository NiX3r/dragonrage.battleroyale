package cz.nixdevelopment.br.instances;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import cz.nixdevelopment.br.utils.MySQL;

public class InGamePlayersInstance {

    private ArrayList<PlayerInstance> players;
    
    public InGamePlayersInstance() {
        players = new ArrayList<PlayerInstance>();
    }
    
    // load player
    public void AddGamePlayer(Player player) {
        
        this.players.add(MySQL.GetData(player.getUniqueId()));
        
    }
    
    // unload player
    public void RemoveGamePlayer(Player player) {
        
        for(int i = 0; i < this.players.size(); i++) {
            
            if(players.get(i).GetUUID().equals(player.getUniqueId())) {
                MySQL.SetData(this.players.get(i));
                this.players.remove(i);
            }
            
        }
        
    }
    
    public int GetSize() {
        return players.size();
    }
    
    public void SpawnPlayers(MapInstance map) {
        
        for(PlayerInstance pi : this.players) {
            
            int x, y = -1, z;
            
            x = ThreadLocalRandom.current().nextInt(map.GetMinX(), map.GetMaxX());
            y = 255;
            z = ThreadLocalRandom.current().nextInt(map.GetMinZ(), map.GetMaxZ());
            
            
            Bukkit.getPlayer(pi.GetNick()).teleport(new Location(Bukkit.getWorld(map.GetWorld()), Double.valueOf(String.valueOf(x)), Double.valueOf(String.valueOf(y)), Double.valueOf(String.valueOf(z))));
            
        }
        
    }
    
    public void ResetInventory(String nick) {
        
        for(PlayerInstance pi : this.players) {
            
            if(pi.GetNick().equals(nick)) {
                
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "clear " + nick);
                
                Bukkit.getPlayer(nick).getInventory().setHelmet(null);
                Bukkit.getPlayer(nick).getInventory().setChestplate(null);
                Bukkit.getPlayer(nick).getInventory().setLeggings(null);
                Bukkit.getPlayer(nick).getInventory().setBoots(null);
                
            }
            
        }
        
    }
    
    public void TeleportLast() {
        
        String last = GetLastPlayer().GetNick();
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spawn " + last);
        ResetInventory(last);
        
    }
    
    public void IncreasePlays() {
        for(PlayerInstance pi : this.players) {
            if(pi.IsAlive())
                pi.IncreasePlays();
        }
    }
    
    public void RevivePlayers() {
        for(PlayerInstance pi : this.players) {
            pi.PlayerRevive();
        }
    }
    
    public PlayerInstance GetLastPlayer() {
        int index = 0;
        PlayerInstance last = null;
        for(PlayerInstance pi : this.players) {
            if(pi.IsAlive()) {
                index++;
                if(index > 1)
                    return null;
                last = pi;
            }
        }
        return last;
    }
    
    public PlayerInstance GetPlayer(UUID uuid) {
        
        for(PlayerInstance pi : this.players) {
            if(pi.GetUUID().equals(uuid))
                return pi;
        }
        
        return null;
        
    }
    
}
