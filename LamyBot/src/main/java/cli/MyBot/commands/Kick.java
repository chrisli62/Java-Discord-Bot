package cli.MyBot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;

import java.util.Collections;
import java.util.List;

public class Kick extends Command {

    public Kick(){
        super.name = "kick";
    }

    @Override
    protected void execute(CommandEvent event){

        final Message message = event.getMessage();
        final Member member = event.getMember();
        final List<String> args = Collections.singletonList(event.getArgs());

        final Member target = message.getMentionedMembers().get(0);
        final Member self = event.getSelfMember();

        // User cannot kick person if they do not have permission
        if(!member.canInteract(target) || !member.hasPermission(Permission.KICK_MEMBERS)) {
            event.reply("You don't have permission to kick others");
            return;
        }

        if(!self.canInteract(target) || !self.hasPermission(Permission.KICK_MEMBERS)) {
            event.reply("You don't have permission to kick others");
            return;
        }


        final String reason = String.join(" ", args.subList(1, args.size()));

        // Target user gets kicked
        event.getGuild().kick(target, reason).reason(reason).queue(
                (__) -> event.reply("Kick successful"),
                (error) -> event.reply("Failed to kick " + target)
        );


    }
}

