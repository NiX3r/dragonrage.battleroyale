package cz.nixdevelopment.br;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import cz.nixdevelopment.br.commands.Commands;
import cz.nixdevelopment.br.instances.DatabaseInstance;
import cz.nixdevelopment.br.instances.GameInstance;
import cz.nixdevelopment.br.instances.InGamePlayersInstance;
import cz.nixdevelopment.br.instances.MapInstance;
import cz.nixdevelopment.br.listeners.OnDamageListener;
import cz.nixdevelopment.br.listeners.OnDeathListener;
import cz.nixdevelopment.br.listeners.OnJoinListener;
import cz.nixdevelopment.br.listeners.OnLeaveListener;
import cz.nixdevelopment.br.utils.GameUtil;
import cz.nixdevelopment.br.utils.MySQL;
import cz.nixdevelopment.br.utils.PAPIManager;

public class BattleRoyale extends JavaPlugin{

    private DatabaseInstance DBS;
    private static Connection connection;
    private static InGamePlayersInstance gamePlayers;
    private static ArrayList<MapInstance> maps;
    private static MapInstance actualMap;
    private static GameInstance gameInfo;
    private static int minimumToStart, phasesCounter, timeBetweenPhase, secToNextPhase = 0;
    private static Boolean isGod = false;
    
    public static JavaPlugin inst;
    public static String Prefix;
    
    public void onEnable() {

        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PAPIManager().register();
        } else {
            this.getPluginLoader().disablePlugin(this);
        }
        
        getConfig().options().copyDefaults(true);
        saveConfig();
        
        DBS = new DatabaseInstance(getConfig().getString("SQL.Host"), getConfig().getInt("SQL.Port"), getConfig().getString("SQL.Database"), getConfig().getString("SQL.User"), getConfig().getString("SQL.Pass"));
        Prefix = getConfig().getString("Prefix").replaceAll("&", "§");
        minimumToStart = getConfig().getInt("PlayersToStart");
        phasesCounter = getConfig().getInt("Phases");
        timeBetweenPhase = getConfig().getInt("TimeBetweenPhases");
        gamePlayers = new InGamePlayersInstance();
        maps = GameUtil.LoadMaps(getConfig());
        gameInfo = new GameInstance();
        
        if(maps == null)
            this.getPluginLoader().disablePlugin(this);
        
        inst = this;
        setUpDatabase();
        
        this.getCommand("br").setExecutor(new Commands());
        
        Bukkit.getPluginManager().registerEvents(new OnDeathListener(), this);
        Bukkit.getPluginManager().registerEvents(new OnJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new OnLeaveListener(), this);
        Bukkit.getPluginManager().registerEvents(new OnDamageListener(), this);
        MySQL.CreateTable();
        
    }
    
    public void onDisable() {
        
        if(gameInfo.IsGameActive()) {
            Boolean set = false;
            for(Player p : Bukkit.getOnlinePlayers()) {
                
                if(p.getLocation().getWorld().getName().equals(actualMap.GetWorld())) {

                    if(!set) {
                        
                        Boolean isSended = false;
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set minecraft.command.worldborder");
                        while(!isSended) {
                            if(p.hasPermission("minecraft.command.worldborder"))
                                isSended = p.performCommand("worldborder set 700");
                        }
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission unset minecraft.command.worldborder");
                        set = true;
                    }
                    
                }
                
            }
        }
        
    }
    
    private void setUpDatabase() {
        
        try {
            
            synchronized(this) {
                
                if(getConnection() != null && !getConnection().isClosed())
                    return;
                
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(DBS.GetConnectionString(), DBS.GetUsername(), DBS.GetPassword());
                System.out.println("[BCoins] MySQL connection is successfully conected");
                
            }
            
        }
        catch(SQLException e) {
            System.out.println("Error: " + e.getMessage());
            this.getPluginLoader().disablePlugin(this);
        }
        catch(ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
            this.getPluginLoader().disablePlugin(this);
        }
        
    }
    
    public static int SetSecToNextPhase(int value) {
        secToNextPhase = value;
        return 0;
    }
    public static int GetSecToNextPhase() {
        return secToNextPhase;
    }
    public static void SetMapActual(MapInstance map) {
        actualMap = map;
    }
    public static Connection getConnection() {
        return connection;
    }
    public static InGamePlayersInstance GetGamePlayers() {
        return gamePlayers;
    }
    public static ArrayList<MapInstance> GetMap() {
        return maps;
    }
    public static MapInstance GetActualMap() {
        return actualMap;
    }
    public static GameInstance GameInfo() {
        return gameInfo;
    }
    public static int MinimumPlayersToStartGame() {
        return minimumToStart;
    }
    public static int PhaseCounter() {
        return phasesCounter;
    }
    public static int TimeBetweenPhase() {
        return timeBetweenPhase;
    }
    public static Boolean IsPlayersGod() {
        return isGod;
    }
    public static void SetPlayersGod(Boolean god) {
        isGod = god;
    }
    
}
