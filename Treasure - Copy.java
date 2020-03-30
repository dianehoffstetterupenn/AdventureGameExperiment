
public class Treasure {
    
    /**
     * Contains objects for player to collect
     * 
     */
    
    /***Instance variables for Treasure Class***/
    private String bitcoin; 
    private String jewel;
    private String sword;
    private String magicIDE;
    
    public Treasure(String bitcoin, String jewel, String sword, String magicIDE) {
        this.bitcoin = bitcoin;
        this.jewel = jewel;
        this.sword = sword;
        this.magicIDE = magicIDE;
    }

    
    /***Getters and Setters***/
    
    public String getBitcoin() {
        return bitcoin;
    }

    public void setBitcoin(String bitcoin) {
        this.bitcoin = bitcoin;
    }

    public String getJewel() {
        return jewel;
    }

    public void setJewel(String jewel) {
        this.jewel = jewel;
    }

    public String getSword() {
        return sword;
    }

    public void setSword(String sword) {
        this.sword = sword;
    }

    public String getMagicIDE() {
        return magicIDE;
    }

    public void setMagicIDE(String magicIDE) {
        this.magicIDE = magicIDE;
    }
    
   
    
    
}
