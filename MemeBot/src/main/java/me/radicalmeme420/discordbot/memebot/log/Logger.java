package me.radicalmeme420.discordbot.memebot.log;

import me.radicalmeme420.discordbot.memebot.util.Ref;
import net.dv8tion.jda.core.entities.User;

public class Logger {

    private static Logger logger;
    
    public static Logger getLogger() {
        if(logger == null) {
            logger = new Logger();
        }
        return logger;
    }
    
    
    private Logger() {
        
    }
    
    public void log(LogType type, String message) {
        // if(type == LogType.COMMAND) { throw new IllegalArgumentException("Use logger.logCommand for logging commands!"); }
        if((type.equals(LogType.DEBUG) && Ref.DEBUG) || !type.equals(LogType.DEBUG)) {
            System.out.println(type.getPrefix() + message);
        }
        
    }
    
    public void logCommand(String[] command, User user) {
        String str = user.getName() + ": ";
        
        for(int i = 0; i < command.length; i++) {
            if(i!=0) {
                str += "<" + command[i] + "> ";
            } else {
                str += Ref.PREFIX + command[0];
            }
        }
        
        log(LogType.COMMAND, str);
    }
    
    public enum LogType {
        COMMAND("[Command]: "),
        INFO("[INFO]: "),
        WARNING("[WARNING]: "),
        DEBUG("[DEBUG]: ");
        
        private String prefix;
        
        LogType(String prefix){
        this.prefix = prefix;    
        }
        
        public String getPrefix() {
            return this.prefix;
        }
    }
}
