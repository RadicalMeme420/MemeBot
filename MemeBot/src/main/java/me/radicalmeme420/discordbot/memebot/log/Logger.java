package me.radicalmeme420.discordbot.memebot.log;

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
        if(type == LogType.COMMAND) { throw new IllegalArgumentException("Use logger.logCommand for logging commands!"); }
        System.out.println(type.getPrefix() + message);
    }
    
    public void logCommand(String command, String[] args, User user) {
        String str = user.getName() + ": ";
        
        if(args != null) {
            for(String arg : args) {
                str += "<" + arg + "> ";
            }
        }
        
        log(LogType.COMMAND, str);
    }
    
    public enum LogType {
        COMMAND("[Command]: "),
        INFO("[INFO]: "),
        WARNING("[WARNING]: ");
        
        private String prefix;
        
        LogType(String prefix){
        this.prefix = prefix;    
        }
        
        public String getPrefix() {
            return this.prefix;
        }
    }
}
