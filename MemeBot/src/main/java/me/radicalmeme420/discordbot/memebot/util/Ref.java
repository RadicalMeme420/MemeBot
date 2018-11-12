package me.radicalmeme420.discordbot.memebot.util;

public class Ref {

    public static final String TOKEN = "NTA3MjgwNTc2NjEzNzExODgy.DruZjA.rsQbEEk9Z1RCsbST-fBU2l21Ozo";
    public static final String CLIENT_ID = "507280576613711882";
    public static final String ANTONIO_MESSAGE = "Quiet Liberal";
    public static final char PREFIX = '-';
    
    public static final boolean DEBUG = true;
    
    public static final boolean ANTONIO_MODE = true;
    
    public static boolean isAdmin(String id) {
        for(Users u : Users.values()) {
            if(u.getId().equals(id)) {
                return u.isAdmin();
            }
        }
        return false;
    }
    
    public enum Users {
        
        ANTONIO("212648839369326602", false),
        SELF("173068605645258752", false);
        
        private String id;
        private boolean admin;
        
        Users(String id, boolean admin){
            this.id = id;
            this.admin = admin;
        }
        
        public String getId() {
            return id;
        }
        
        public boolean isAdmin() {
            return admin;
        }
        
    }
}
