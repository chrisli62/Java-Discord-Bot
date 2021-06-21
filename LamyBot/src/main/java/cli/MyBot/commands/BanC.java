package cli.MyBot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;

import java.util.Collections;
import java.util.List;

public class BanC extends Command {

    public BanC(){
        super.name = "ban";
    }

    @Override
    protected void execute(CommandEvent event){

        final Message message = event.getMessage();
        final Member member = event.getMember();
        final List<String> args = Collections.singletonList(event.getArgs());

        final Member target = message.getMentionedMembers().get(0);
        final Member self = event.getSelfMember();

        // User cannot ban person if they do not have permission
        if(!member.canInteract(target) || !member.hasPermission(Permission.BAN_MEMBERS)) {
            event.reply("You don't have permission to ban others");
            return;
        }

        if(!self.canInteract(target) || !self.hasPermission(Permission.BAN_MEMBERS)) {
            event.reply("You don't have permission to ban others");
            return;
        }

        final String reason = String.join(" ", args.subList(1, args.size()));

        // Target user gets banned
        event.getGuild().ban(target, 5, reason).reason(reason).queue(
                (__) -> event.reply("Ban successful"),
                (error) -> event.reply("Failed to ban " + target)
        );

    }
}
