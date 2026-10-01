package com.jackhesslein.mcreimagined.progression;

import com.jackhesslein.mcreimagined.MinecraftReimagined;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.event.RegisterCommandsEvent;


public class ProgressionCommand {
    // Called via NeoForge.EVENT_BUS.addListener in MinecraftReimagined
    public static void onCommandRegister(RegisterCommandsEvent event) {
        MinecraftReimagined.LOGGER.info("Registering /mcr command");
        register(event.getDispatcher());
    }

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
            Commands.literal("mcr")
                .requires(source -> source.hasPermission(2))
                .then(Commands.literal("tier")
                    .then(Commands.literal("get")
                        .executes(context -> getTier(context.getSource())))
                    .then(Commands.literal("set")
                        .then(Commands.argument("tier", IntegerArgumentType.integer(0))
                            .executes(context -> setTier(
                                context.getSource(), 
                                IntegerArgumentType.getInteger(context, "tier"))))))
        );
    }

    private static int getTier(CommandSourceStack source) {
        int tier = ProgressionData.get(source.getServer()).getTier();
        source.sendSuccess(() -> Component.literal("World Tier: " + tier), false);
        return 1;
    }

    private static int setTier(CommandSourceStack source, int newTier) {
        ProgressionData.get(source.getServer()).setTier(newTier);
        source.sendSuccess(() -> Component.literal("World Tier set to: " + newTier), true);
        return 1;
    }
}
