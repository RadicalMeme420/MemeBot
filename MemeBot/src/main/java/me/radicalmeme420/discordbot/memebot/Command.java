package me.radicalmeme420.discordbot.memebot;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;

public abstract class Command {
    
    private Message message;
    private User author;
    
    public static Command getCommand(Message message, User author) {
        
        Command c = null;
        String cStr = message.getContentRaw().substring(1).toLowerCase().split("\\+")[0];
        if(cStr.equals("test")) {
            c = new TestCommand(message, author);
        } else if(cStr.equals("antonio")) {
            c = new AntonioCommand(message, author);
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
            System.out.println(super.message.getContentRaw());
        }
    }
    
    public static class AntonioCommand extends Command {
        
        public AntonioCommand(Message message, User author) {
            super(message, author);
        }
        
        public void execute() {
            super.message.getChannel().sendMessage(Ref.Users.ANTONIO.getId()).queue();
        }
    }

}
