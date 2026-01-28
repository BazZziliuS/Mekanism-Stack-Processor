package org.cloudea.mekanismstackprocessor;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;

@Mod(MekanismStackProcessor.MODID)
public class MekanismStackProcessor {
    public static final String MODID = "mekanismstackprocessor";
    public static final Logger LOGGER = LogUtils.getLogger();

    public MekanismStackProcessor(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        LOGGER.info("Mekanism Stack Processor initialized");
    }
}
