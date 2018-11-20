package me.radicalmeme420.discordbot.memebot.util;

public class AntonioGenerator {

	private static String[] Messages = { "Don't be a darkie %s", "Come on liberal", "No darkie",
			"You guys just need to not be stupid", "That was kinda un epic", "Stop being a libtard %s",
			"PLAYER COACH PLAYER COACH DYNAMIC BLADE DYNAMIC BLADE", "Okay but Andrew was pretty bad guys",
			"Let's play jackbox guys come on", "We just needed to stop being shit", "5head", "This is not okay gamers",
			"I'm still looking for another support player guys", "Stop being a cuckie %s",
			"https://cdn.discordapp.com/attachments/489596317103030292/507008205310787584/r0uJa8SjgAmTM9dMPBCtVFj5n5e9KMBnRkwfozlx4Gx8SIl3EuAI96IsIeodB3i71z68BJV4J2PMLEoPsyU9cm0lM9EiH4d5m5OK.png \nSans Undertale", 
			};

	public static String getRandomAntonio() {
		return Messages[(int) (System.currentTimeMillis() % Messages.length)];
	}

}
