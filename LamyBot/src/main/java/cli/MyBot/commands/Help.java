package cli.MyBot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;


public class Help extends Command {
    public Help(){
        super.name = "helpme";
    }

    @Override
    protected void execute(CommandEvent event){
        EmbedBuilder helper = new EmbedBuilder();
        helper.setTitle("✎ Svadbot");
        helper.setDescription("This bot is a project for Software Engineering");
        helper.addField("Creator:", "Christopher Li", false);
        helper.addField("Commands:", "!ping \n!ban \n!kick \n!unban", true);
        helper.setColor(0xC53920);

        event.getChannel().sendTyping().queue();
        event.getChannel().sendMessage(helper.build()).queue();
        helper.clear();
    }
}
