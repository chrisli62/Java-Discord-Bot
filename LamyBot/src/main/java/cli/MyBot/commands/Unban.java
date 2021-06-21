package cli.MyBot.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Unban extends Command {

    public Unban(){
        super.name = "unban";
    }

    @Override
    protected void execute(CommandEvent event) {

        final List<String> args = Collections.singletonList(event.getArgs());

        if(!event.getMember().hasPermission(Permission.BAN_MEMBERS)) {
            event.reply("You don't have permission to unban others");
            return;
        }

        if(!event.getSelfMember().hasPermission(Permission.BAN_MEMBERS)) {
            event.reply("You don't have permission to unban others");
            return;
        }

        if(args.isEmpty()) {
            return;
        }

        String argsJoin = String.join(" ", args);

        // Create a list of banned users as two users can have the same username so use a filter
        event.getGuild().retrieveBanList().queue((bans) -> {
            List<User> goodUser = bans.stream().filter((ban) -> isCorrectUser(ban, argsJoin))
                    .map(Guild.Ban::getUser).collect(Collectors.toList());

            if(goodUser.isEmpty()) {
                event.reply("User is not banned");
                return;
            }
            User target = goodUser.get(0);

            String bannedUser = String.format("%#s", target);

            event.getGuild().unban(target);
            event.reply("User has been unbanned");
        });
    }

    private boolean isCorrectUser(Guild.Ban ban, String arg) {
        User bannedUser = ban.getUser();
        // Returns banned user's name or id
        return bannedUser.getName().equalsIgnoreCase(arg) || bannedUser.getId().equals(arg) ||
                String.format("%#s", bannedUser).equalsIgnoreCase(arg);
    }
}
