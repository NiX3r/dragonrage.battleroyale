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
            
            int x = 0, y = -1, z = 0;
            
            while(y == -1) {
                x = ThreadLocalRandom.current().nextInt(map.GetMinX() +50, map.GetMaxX() -50);
                z = ThreadLocalRandom.current().nextInt(map.GetMinZ() +50, map.GetMaxZ() -50);
                y = GetY(map.GetWorld(), x, z);
            }
            
            Bukkit.getPlayer(pi.GetNick()).teleport(new Location(Bukkit.getWorld(map.GetWorld()), x, y, z));
            
        }
        
    }
    
    private int GetY(String world, int x, int z) {
        
        for(int i = 255; i > -1; i--) {
            
            Location l = new Location(Bukkit.getWorld(world), x, i, z);
            
            if(l.getBlock().getTypeId() != 0) {
                
                if(l.getBlock().getTypeId() == 8 || l.getBlock().getTypeId() == 9) {
                    return -1;
                }
                else {
                    return i;
                }
                
            }
            
        }
        
        return -1;
        
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
        Bukkit.getPlayer(last).setHealth(20);
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
