package cz.nixdevelopment.br.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import cz.nixdevelopment.br.BattleRoyale;
import cz.nixdevelopment.br.instances.PlayerInstance;

//CREATE TABLE IF NOT EXISTS BattleRoyale(UUID varchar(36) PRIMARY KEY, Nick varchar(16) NOT NULL, Kills int DEFAULT 0, Deaths int DEFAULT 0, Wins int DEFAULT 0, Loses int DEFAULT 0, Plays int DEFAULT 0);
public class MySQL {

    public static void CreateTable() {
        
        try {
            
            PreparedStatement statement = BattleRoyale.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS BattleRoyale(UUID varchar(36) PRIMARY KEY, Nick varchar(16) NOT NULL, Kills int DEFAULT 0, Deaths int DEFAULT 0, Wins int DEFAULT 0, Loses int DEFAULT 0, Plays int DEFAULT 0);");
            
            if(statement.execute()) {
                System.out.println("Table was created");
            }
            
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public static void CheckPlayerExists(String UUID, String nick) {
        
        try {
            PreparedStatement statement = BattleRoyale.getConnection()
                    .prepareStatement("SELECT * FROM BattleRoyale WHERE UUID=?");
            statement.setString(1, UUID);

            ResultSet results = statement.executeQuery();
            if (results.next()) {
                //BCoins.inst.getServer().broadcastMessage(ChatColor.YELLOW + "Player Found");
            }
            else {
                
                //BCoins.inst.getServer().broadcastMessage(ChatColor.GREEN + "Player Inserted");
                
                PreparedStatement insert = BattleRoyale.getConnection()
                        .prepareStatement("INSERT INTO BattleRoyale(UUID, Nick) VALUES (?,?)");
                insert.setString(1, UUID);
                insert.setString(2, nick);
                insert.executeUpdate();
                
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public static PlayerInstance GetData(UUID uuid) {
        
        try {
            PlayerInstance output;
            PreparedStatement statement = BattleRoyale.getConnection()
                    .prepareStatement("SELECT * FROM BattleRoyale WHERE UUID=?");
            statement.setString(1, uuid.toString());
            
            ResultSet results = statement.executeQuery();
            results.next();
            
            output = new PlayerInstance(uuid, results.getString("Nick"), results.getInt("Wins"), results.getInt("Loses"), results.getInt("Kills"), results.getInt("Deaths"), results.getInt("Plays"));
            
            return output;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
    }
    
    public static Boolean SetData(PlayerInstance player) {
        
        try {
            PreparedStatement statement = BattleRoyale.getConnection()
                    .prepareStatement("UPDATE BattleRoyale SET Kills=?, Deaths=?, Wins=?, Loses=?, Plays=? WHERE UUID=?");
            statement.setInt(1, player.GetKills());
            statement.setInt(2, player.GetDeaths());
            statement.setInt(3, player.GetWins());
            statement.setInt(4, player.GetLoses());
            statement.setInt(5, player.GetPlays());
            statement.setString(6, player.GetUUID().toString());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
    }
    
}
