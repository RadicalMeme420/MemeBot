package me.radicalmeme420.discordbot.memebot;

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
            if (checkPrefix(message.getContentRaw())) {
                Command.getCommand(message, event.getAuthor()).execute();
            }
        }
    }

    private boolean checkPrefix(String message) {
        return message.charAt(0) == Ref.PREFIX;
    }
}
