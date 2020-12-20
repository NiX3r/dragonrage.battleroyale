package cz.nixdevelopment.br.instances;

import java.util.UUID;

public class PlayerInstance {

    private UUID uuid;
    private String nick;
    private int wins = 0, loses = 0, kills = 0, deaths = 0, plays = 0;
    private Boolean isAlive = false;
    
    public PlayerInstance(UUID uuid, String nick, int wins, int loses, int kills, int deaths, int plays) {
        this.uuid = uuid;
        this.nick = nick;
        this.wins = wins;
        this.loses = loses;
        this.kills = kills;
        this.deaths = deaths;
        this.plays = plays;
    }
    
    public void IncreaseWins() {
        this.wins++;
    }
    public void IncreaseLoses() {
        this.loses++;
    }
    public void IncreaseKills() {
        this.kills++;
    }
    public void IncreaseDeaths() {
        this.deaths++;
    }
    public void IncreasePlays() {
        this.plays++;
    }
    
    public UUID GetUUID() {
        return this.uuid;
    }
    public String GetNick() {
        return this.nick;
    }
    public int GetWins() {
        return this.wins;
    }
    public int GetLoses() {
        return this.loses;
    }
    public int GetKills() {
        return this.kills;
    }
    public int GetDeaths() {
        return this.deaths;
    }
    public int GetPlays() {
        return this.plays;
    }
    public Boolean IsAlive() {
        return this.isAlive;
    }
    
    public void PlayerRevive() {
        this.isAlive = true;
    }
    public void PlayerDied() {
        this.isAlive = false;
    }
    public void PlayerCameBackFromDeath() {
        this.isAlive = true;
    }
    
}
