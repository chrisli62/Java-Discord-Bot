package cli.MyBot;

import javax.security.auth.login.LoginException;

import cli.MyBot.commands.*;
import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import java.util.concurrent.TimeUnit;

public class Bot {

    private Bot() throws LoginException {

        // WARNING: Do NOT connect to a client account
        // Connecting to a client account breaks Discord ToS
        // resulting in a ban!

        // Insert your Discord bot token in the space "setToken()"
        // Do NOT share your token with anyone who is not authorized!
        // If your token is leaked, regenerate your token
        final JDA jda = new JDABuilder(AccountType.BOT)
                .setToken("NzA4OTI4NTIxNjkwNjc3MzA4.Xrehag.v6CvliNrgQlO0vNanLzU7A6Zcb8").build();

        CommandClientBuilder builder = new CommandClientBuilder();

        // Set the symbol to be used to initiate the bot
        // Suggested symbols (!, @, #, $, %, ~, ?)
        builder.setPrefix("!");

        // Input your Discord ID by putting in text into a text channel,
        // Activate Developer Mode by opening your Discord settings and click on Appearance.
        // Scroll down to find Developer Mode and click to enable it.
        // Go to the more tab on the right corner of your text block, and
        // select Copy ID
        builder.setOwnerId("708928719888187444");

        builder.setHelpWord("help");
        builder.setActivity(Activity.watching("you"));

        CommandClient client = builder.build();
        jda.addEventListener(client);
        // Command list
        client.addCommand(new Help());
        client.addCommand(new Ping());
        client.addCommand(new BanC());
        client.addCommand(new Unban());
        client.addCommand(new Kick());
    }

    public static void main(String[] args) throws LoginException {
        long enable = System.currentTimeMillis();
        new Bot();
        System.out.println("Bot enabled in: " + TimeUnit.MILLISECONDS.toSeconds((System.currentTimeMillis() -enable)) + "ms.");
    }
}
