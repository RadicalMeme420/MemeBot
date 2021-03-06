package me.radicalmeme420.discordbot.memebot.command;

import me.radicalmeme420.discordbot.memebot.util.AntonioGenerator;
import me.radicalmeme420.discordbot.memebot.util.Ref;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import me.radicalmeme420.discordbot.memebot.log.*;
import me.radicalmeme420.discordbot.memebot.log.Logger.LogType;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.exceptions.InsufficientPermissionException;
import net.dv8tion.jda.core.requests.RestAction;

public abstract class Command {

	private Message message;
	private User author;

	public static Command getCommand(Message message, User author) {

		// TODO un-fuck this. it works but it's shit
		Command c = null;
		String cStr = message.getContentRaw().substring(1).toLowerCase().split(" ")[0];
		if (cStr.equals("test")) {
			c = new TestCommand(message, author);
		} else if (cStr.equals("antonio")) {
			Logger.getLogger().log(LogType.DEBUG, "Antonio command created");
			c = new AntonioCommand(message, author);
		} else if (cStr.equals("spam")) {
			c = new SpamCommand(message, author);
		} else if (cStr.equals("clean")) {
			c = new CleanCommand(message, author);
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

		@Override
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

		@Override
		public void execute() {

			// If user is not admin tell them no
			if (!Ref.isAdmin(super.author.getId())) {
				try {
					super.message.delete().queue();
				} catch (InsufficientPermissionException e) {
				}
				super.message.getChannel().sendMessage("No").queue();
				return;
			}

			// Get args
			String[] args = getArgs(super.message);

			// If the length is at least 2, i.e. -x y
			if (args.length >= 2) {
				// Delete user message
				try {
					super.message.delete().queue();
				} catch (InsufficientPermissionException e) {
				}

				String msg = "";
				// For each arg, add it to the message
				for (int i = 0; i < args.length; i++) {
					if (i != 0) {
						msg += " " + args[i];
					}
				}

				// For each channel, send the message
				for (TextChannel c : super.message.getGuild().getTextChannels()) {
					try {
						c.sendMessage(msg).queue();
					} catch (InsufficientPermissionException e) {
					}
				}

			}
		}

	}

	public static class AntonioCommand extends Command {

		public AntonioCommand(Message message, User author) {
			super(message, author);
		}

		@Override
		public void execute() {

			String[] args = getArgs(super.message);

			MessageBuilder m = new MessageBuilder();
			m.appendFormat(AntonioGenerator.getRandomAntonio(), super.author.getAsMention());

			if (args.length >= 2 && args[1].equals("-t")) {
				m.setTTS(true);
			}

			super.message.getChannel().sendMessage(m.build()).queue();
		}
	}

	public static class CleanCommand extends Command {

		public CleanCommand(Message message, User author) {
			super(message, author);
		}

		public List<Message> msgs;

		@Override
		public void execute() {

			Logger.getLogger().log(LogType.INFO, "Clearing messages...");

			long time = System.currentTimeMillis();
			for (TextChannel ch : super.message.getGuild().getTextChannels()) {

				try {
					msgs = ch.getHistory().retrievePast(100).complete();
				} catch (InsufficientPermissionException e) {
				}

				for (Message m : msgs) {
					if (m.getAuthor().getId().equals(Ref.CLIENT_ID)) {
						try {
							m.delete().queue();
						} catch (InsufficientPermissionException e) {
						}
					}
					if (m.getContentRaw() != null && !m.getContentRaw().equals("")) {
						if (m.getContentRaw().charAt(0) == Ref.PREFIX) {
							try {
								m.delete().queue();
							} catch (InsufficientPermissionException e) {
							}
						}
					}
				}
			}

			time = System.currentTimeMillis() - time;
			Logger.getLogger().log(LogType.INFO, "Took " + (time / 1000 + "." + time % 1000) + "s");

		}
	}

	public static class TimerCommand extends Command {

		public TimerCommand(Message message, User author) {
			super(message, author);
		}

		@Override
		public void execute() {

		}

	}

}
