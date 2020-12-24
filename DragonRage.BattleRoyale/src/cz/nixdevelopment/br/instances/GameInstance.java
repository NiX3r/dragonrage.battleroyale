package cz.nixdevelopment.br.instances;

import org.bukkit.Bukkit;

import cz.nixdevelopment.br.utils.Messages;

public class GameInstance {

    private int total, left;
    private String map;
    private Boolean isActive;
    private Boolean isReady;
    
    public GameInstance() {
        this.isActive = false;
        this.isReady = false;
        this.map = "none";
        this.total = 0;
        this.left = 0;
    }
    
    public void SetTotal(int total) {
        this.total = this.left = total;
    }
    public void SetMap(String map) {
        this.map = map;
    }
    
    public int GetTotal() {
        return this.total;
    }
    public int GetLeft() {
        return this.left;
    }
    public String GetMap() {
        return this.map;
    }
    public Boolean IsGameActive() {
        return this.isActive;
    }
    public Boolean IsGameReady() {
        return this.isReady;
    }
    
    public void StartGame() {
        this.isActive = true;
    }
    public void EndGame() {
        this.isActive = false;
    }
    public void Ready() {
        this.isReady = true;
    }
    public void NotReady() {
        this.isReady = false;
    }
    
    public Boolean DecrementLeft() {
        left--;
        if(left == 1)
            return true;
        else
            return false;
    }
}
