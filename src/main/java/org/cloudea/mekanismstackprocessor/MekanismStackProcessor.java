package org.cloudea.mekanismstackprocessor;

import com.mojang.logging.LogUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import org.slf4j.Logger;

@Mod(MekanismStackProcessor.MODID)
public class MekanismStackProcessor {
    public static final String MODID = "mekanismstackprocessor";
    public static final Logger LOGGER = LogUtils.getLogger();

    public MekanismStackProcessor(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        if (Config.enableUpdateCheck.get()) {
            UpdateChecker.check();
        }

        NeoForge.EVENT_BUS.register(this);

        LOGGER.info("Mekanism Stack Processor initialized");
    }

    @SubscribeEvent
    public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (UpdateChecker.updateAvailable) {
            event.getEntity().sendSystemMessage(
                    Component.literal("[MekanismStackProcessor] ")
                            .withStyle(ChatFormatting.GOLD)
                            .append(Component.literal("New version available: " + UpdateChecker.latestVersion
                                    + " (current: " + UpdateChecker.currentVersion + ")")
                                    .withStyle(ChatFormatting.YELLOW))
            );
        }
    }
}
