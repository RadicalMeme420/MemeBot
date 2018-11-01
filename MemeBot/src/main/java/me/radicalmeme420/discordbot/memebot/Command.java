package me.radicalmeme420.discordbot.memebot;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;

public abstract class Command {
    
    private Message message;
    private User author;
    
    public static Command getCommand(Message message, User author) {
        
        Command c = null;
        String cStr = message.getContentRaw().substring(1).toLowerCase();
        if(cStr.equals("test")) {
            c = new TestCommand(message, author);
        }
       
       return c; 
    }
    
    private Command(Message message, User author) {
        this.message = message;
        this.author = author;
    }
    
    public abstract void execute();
    
    public static class TestCommand extends Command {
        
        public TestCommand(Message message, User author) {
            super(message, author);
        }
        
        public void execute() {
            super.message.getChannel().sendMessage(super.message.getContentRaw().substring(1)).queue();
        }
    }

}
