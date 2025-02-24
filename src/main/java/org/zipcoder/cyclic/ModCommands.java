package org.zipcoder.cyclic;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ModCommands {

    /**
     * Parses and executes a command string using the given source.
     *
     * @param source The command source.
     * @param command The command to execute (without a leading '/').
     * @return The number of affected entities or a result code.
     */
    public static int executeParsedCommand(CommandSourceStack source, String command) {
        // Use the server's command dispatcher
        MinecraftServer server = source.getServer();
        var dispatcher = server.getCommands().getDispatcher();

        // Parse the command string (if a leading slash exists, remove it)
        if (command.startsWith("/")) {
            command = command.substring(1);
        }
        ParseResults<CommandSourceStack> parseResults = dispatcher.parse(command, source);
        try {
            // Execute the parsed command and return the result
            return dispatcher.execute(parseResults);
        } catch (CommandSyntaxException e) {
            source.sendFailure(Component.literal("Error executing command: " + e.getMessage()));
            return 0;
        }
    }

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        //A kill command that targets all entities within 10 blocks
        dispatcher.register(Commands.literal("kill")
                .requires(source -> source.hasPermission(2))  // Only players with permission level 2 or higher see this command
                .then(Commands.literal("near")
                        .executes(context -> {
                            executeParsedCommand(context.getSource(), "/kill @e[type=!player,distance=..10]");
                            return Command.SINGLE_SUCCESS;
                        })));
    }
}