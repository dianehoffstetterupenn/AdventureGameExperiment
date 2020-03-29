import java.util.HashMap;

public class Treasures {
    
    /**
     * Contains objects the player collects for points
     * Interacts with Player, Score & Room Classes
     **/
    private HashMap<String, Integer> treasurePoints = new HashMap<String, Integer>(); 
    
    public Treasures() {
        
    }

    /***HashMap to assign values to treasures***/ 
    public void assignTreasureValues(){
        
        /***Local Variables***/
        String bitcoin = "Bitcoin";
        String jewel = "Jewel";
        String sword = "Sword";
        String magicIDE = "Magic IDE";
        Treasure t = new Treasure(bitcoin, jewel, sword, magicIDE);
             
        t.getBitcoin();
        t.getJewel();
        t.getSword();
        t.getMagicIDE();
        
        /***Assigns Treasure as Key - Integer is point Value***/
        treasurePoints.put(bitcoin, 2);
        treasurePoints.put(jewel, 3);
        treasurePoints.put(sword, 4);
        treasurePoints.put(magicIDE, 5);
               
    }
    
    public HashMap<String, Integer> getTreasureValues(){
        return treasurePoints;
    }
    
    public void setTreasureValues(HashMap<String, Integer> newTreasurePoints) {
        this.treasurePoints = newTreasurePoints;
    }
    
    public void putTreasuresInRoom() {
        
        
        
        
    }
    /***Checks if Treasure is in Room***/
    public boolean isTreasureInRoom(int id) { 
        
        return true;               
    }
    
    /***Adds treasure to Player***/ 
    public String addTreasure(String question) { 
        /***Local Variables***/
        String treasure = getTreasureValues().toString();
        if (question == "correct") {
            return treasure;
        }
        else
            return ("Sorry that is not correct.");
        
        
    }
    public static void main (String[] args) {
        Treasures test = new Treasures();
        test.addTreasure("correct");
        
    }
    
}
