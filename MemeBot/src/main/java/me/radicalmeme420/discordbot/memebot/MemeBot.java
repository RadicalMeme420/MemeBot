package me.radicalmeme420.discordbot.memebot;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MemeBot extends ListenerAdapter {
    
    public static JDA api;
    
    public static void main(String[] args) {
        
        try {
            api = new JDABuilder(Ref.TOKEN).build();
            api.getPresence().setGame(Game.playing("with myself"));
            api.addEventListener(new MessageListener());
            api.awaitReady();
            System.out.println("Finished Building JDA!");
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }
        
    }

}
