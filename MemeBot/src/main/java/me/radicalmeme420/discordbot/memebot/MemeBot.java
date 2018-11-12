package me.radicalmeme420.discordbot.memebot;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import me.radicalmeme420.discordbot.memebot.log.*;
import me.radicalmeme420.discordbot.memebot.log.Logger.LogType;

public class MemeBot extends ListenerAdapter {
    
    public static JDA api;
    
    public static void main(String[] args) {
        
        try {
            api = new JDABuilder(Ref.TOKEN).build();
            api.getPresence().setGame(Game.playing("with myself"));
            api.addEventListener(new MessageListener());
            Logger.getLogger().log(LogType.INFO, "Loading JDA...");
            long time = System.currentTimeMillis();
            api.awaitReady();
            time = System.currentTimeMillis()-time;
            Logger.getLogger().log(LogType.INFO, "Loaded JDA! (" + (time/1000 + "." + time%1000) + "s)");
        } catch (LoginException | InterruptedException e) {
            Logger.getLogger().log(LogType.WARNING, e.getMessage());
        }
        
    }

}
