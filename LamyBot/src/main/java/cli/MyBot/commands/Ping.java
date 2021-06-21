package cli.MyBot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class Ping extends Command {

    public Ping() {
        super.name = "ping";
    }

    @Override
    protected void execute(CommandEvent event) {
        event.reply("Pong! \nMy ping is: " + event.getJDA().getGatewayPing());
    }
}
