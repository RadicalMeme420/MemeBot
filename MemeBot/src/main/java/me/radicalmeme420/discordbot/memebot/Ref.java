package me.radicalmeme420.discordbot.memebot;

public class Ref {

    public static final String TOKEN = "NTA3MjgwNTc2NjEzNzExODgy.DruZjA.rsQbEEk9Z1RCsbST-fBU2l21Ozo";
    public static final String CLIENT_ID = "507280576613711882";
    public static final char PREFIX = '-';
    
    public static final boolean ANTONIO_MODE = true;
    
    public enum Users {
        
        ANTONIO("212648839369326602");
        
        private String id;
        
        Users(String id){
        this.id = id;    
        }
        
        public String getId() {
            return id;
        }
        
    }
}
