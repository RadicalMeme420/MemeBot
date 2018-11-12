package me.radicalmeme420.discordbot.memebot.command;

import me.radicalmeme420.discordbot.memebot.util.Ref;
import me.radicalmeme420.discordbot.memebot.log.*;
import me.radicalmeme420.discordbot.memebot.log.Logger.LogType;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

public abstract class Command {
    
    private Message message;
    private User author;
    
    public static Command getCommand(Message message, User author) {
        
        // TODO un-fuck this. it works but it's shit
        Command c = null;
        String cStr = message.getContentRaw().substring(1).toLowerCase().split(" ")[0];
        if(cStr.equals("test")) {
            c = new TestCommand(message, author);
        } else if(cStr.equals("antonio")) {
            Logger.getLogger().log(LogType.DEBUG, "Antonio command created");
            c = new AntonioCommand(message, author);
        } else if(cStr.equals("spam")) {
            c = new SpamCommand(message, author);
        }
       
       return c; 
    }
    
    private Command(Message message, User author) {
        this.message = message;
        this.author = author;
    }
    
    String[] getArgs(Message m) {
        return m.getContentRaw().substring(1).split(" ");
    }
    
    public abstract void execute();
    
    public static class TestCommand extends Command {
        
        public TestCommand(Message message, User author) {
            super(message, author);
        }
        
        public void execute() {
            super.message.getChannel().sendMessage(super.message.getContentRaw().substring(1)).queue();
            
            String[] split = super.message.getContentRaw().substring(1).split("\\+");
            
            Logger.getLogger().logCommand(split, super.author);
            Logger.getLogger().log(LogType.DEBUG, super.author.getId());
        }
    }
    
    
    public static class SpamCommand extends Command {
        
        public SpamCommand(Message message, User author) {
            super(message, author);
        }
        
        public void execute() {
            
            // If user is not admin tell them no
            if(!Ref.isAdmin(super.author.getId())) {
                super.message.delete().queue();
                super.message.getChannel().sendMessage("No").queue();
                return;
            }
            
            // Get args
            String[] args = getArgs(super.message);
            
            // If the length is at least 2, i.e. -x y
            if(args.length >= 2) {
                // Delete user message
                super.message.delete().queue();
                
                String msg = "";
                // For each arg, add it to the message
                for(int i = 0; i < args.length; i++) {
                    if(i != 0) {
                        msg += " " + args[i];
                    }
                }
                
                // For each channel, send the message
                for(TextChannel c : super.message.getGuild().getTextChannels()) {
                    c.sendMessage(msg).queue();
                }
                
            }
        }
        
    }
    
    public static class AntonioCommand extends Command {
        
        public AntonioCommand(Message message, User author) {
            super(message, author);
        }
        
        public void execute() {
            String[] args = getArgs(super.message);
            
            if(args.length >= 2) {
                
                super.message.delete().queue();

                if(args[1].equals("-a")) {
                    for(TextChannel c : super.message.getGuild().getTextChannels()) {
                        c.sendMessage("<@!" + Ref.Users.ANTONIO.getId() + ">").queue();
                    }
                }
                
            } else {
                super.message.getChannel().sendMessage("<@!" + Ref.Users.ANTONIO.getId() + ">").queue();
            }
            
        }
    }

}
