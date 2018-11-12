package me.radicalmeme420.discordbot.memebot.listeners;

import me.radicalmeme420.discordbot.memebot.command.Command;
import me.radicalmeme420.discordbot.memebot.util.Ref;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.isFromType(ChannelType.TEXT)) {
            Message message = event.getMessage();
            if (message.getAuthor().getId().equals(Ref.CLIENT_ID)) {
                return;
            }
            if(message.getAuthor().getId().equals(Ref.Users.ANTONIO.getId()) && Ref.ANTONIO_MODE) {
                message.getChannel().sendMessage(Ref.ANTONIO_MESSAGE).queue();
            }
            if (checkPrefix(message.getContentRaw())) {
                Command.getCommand(message, event.getAuthor()).execute();
            }
        }
    }

    private boolean checkPrefix(String message) {
        return message.charAt(0) == Ref.PREFIX;
    }
}
