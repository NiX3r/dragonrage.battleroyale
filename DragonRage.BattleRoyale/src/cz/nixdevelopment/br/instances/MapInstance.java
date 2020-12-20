package cz.nixdevelopment.br.instances;

public class MapInstance {

    private int minX, maxX;
    private int minZ, maxZ;
    private int centerX, centerZ;
    private String world;
    private String name;
    private int votes;
    
    public MapInstance(String name, int minX, int maxX, int minZ, int maxZ, int centerX, int centerZ, String world) {
        this.name = name;
        this.minX = minX;
        this.maxX = maxX;
        this.minZ = minZ;
        this.maxZ = maxZ;
        this.centerX = centerX;
        this.centerZ = centerZ;
        this.world = world;
        this.votes = 0;
    }
    
    public int GetMinX() {
        return this.minX;
    }
    public int GetMaxX() {
        return this.maxX;
    }
    public int GetMinZ() {
        return this.minZ;
    }
    public int GetMaxZ() {
        return this.maxZ;
    }
    public int GetCenterX() {
        return this.centerX;
    }
    public int GetCenterZ() {
        return this.centerZ;
    }
    public int GetVotes() {
        return this.votes;
    }
    public String GetWorld() {
        return this.world;
    }
    public String GetName() {
        return this.name;
    }
    
    public void IncrementVotes() {
        this.votes++;
    }
    public void DecrementVotes() {
        this.votes--;
    }
    
}
