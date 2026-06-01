package org.cloudea.mekanismstackprocessor;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;

    public static final ModConfigSpec.BooleanValue enableOptimization;
    public static final ModConfigSpec.BooleanValue enableUpdateCheck;

    // Factory tier multipliers
    public static final ModConfigSpec.IntValue basicTierMultiplier;
    public static final ModConfigSpec.IntValue advancedTierMultiplier;
    public static final ModConfigSpec.IntValue eliteTierMultiplier;
    public static final ModConfigSpec.IntValue ultimateTierMultiplier;

    // Regular machines multiplier
    public static final ModConfigSpec.IntValue baseMachineMultiplier;

    // Chemical Factory tier multipliers (for gas/fluid factories from MoreMachine)
    public static final ModConfigSpec.IntValue chemBasicTierMultiplier;
    public static final ModConfigSpec.IntValue chemAdvancedTierMultiplier;
    public static final ModConfigSpec.IntValue chemEliteTierMultiplier;
    public static final ModConfigSpec.IntValue chemUltimateTierMultiplier;

    // Evolved Mekanism extended factory tier multipliers
    public static final ModConfigSpec.IntValue emOverclockedTierMultiplier;
    public static final ModConfigSpec.IntValue emQuantumTierMultiplier;
    public static final ModConfigSpec.IntValue emDenseTierMultiplier;
    public static final ModConfigSpec.IntValue emMultiversalTierMultiplier;
    public static final ModConfigSpec.IntValue emCreativeTierMultiplier;

    static {
        BUILDER.comment("Mekanism Stack Processor Configuration").push("general");

        enableOptimization = BUILDER
                .comment("Enable batch processing optimization for Mekanism machines")
                .define("enableOptimization", true);

        enableUpdateCheck = BUILDER
                .comment("Enable automatic update checking on startup")
                .define("enableUpdateCheck", true);

        BUILDER.pop();

        BUILDER.comment("Factory Tier Multipliers",
                "How many items each factory tier processes per operation.",
                "Higher tier = more items processed at once.").push("factory_tiers");

        basicTierMultiplier = BUILDER
                .comment("Basic Factory - items per operation")
                .defineInRange("basicTierMultiplier", 8, 1, 64);

        advancedTierMultiplier = BUILDER
                .comment("Advanced Factory - items per operation")
                .defineInRange("advancedTierMultiplier", 16, 1, 64);

        eliteTierMultiplier = BUILDER
                .comment("Elite Factory - items per operation")
                .defineInRange("eliteTierMultiplier", 32, 1, 64);

        ultimateTierMultiplier = BUILDER
                .comment("Ultimate Factory - items per operation (max stack!)")
                .defineInRange("ultimateTierMultiplier", 64, 1, 64);

        BUILDER.pop();

        BUILDER.comment("Regular Machine Settings",
                "For non-factory machines like Crusher, Enrichment Chamber, etc.").push("regular_machines");

        baseMachineMultiplier = BUILDER
                .comment("Items per operation for regular machines")
                .defineInRange("baseMachineMultiplier", 4, 1, 64);

        BUILDER.pop();

        BUILDER.comment("Chemical Factory Tier Multipliers",
                "For factories that process gases/fluids:",
                "- Mekanism: Injecting, Infusing, Purifying factories",
                "- MoreMachine mekaf: Dissolving, Liquefying, Oxidizing, etc.",
                "Higher tier = more chemicals processed at once.").push("chemical_factory_tiers");

        chemBasicTierMultiplier = BUILDER
                .comment("Basic Chemical Factory - operations per tick multiplier")
                .defineInRange("chemBasicTierMultiplier", 1, 1, 64);

        chemAdvancedTierMultiplier = BUILDER
                .comment("Advanced Chemical Factory - operations per tick multiplier")
                .defineInRange("chemAdvancedTierMultiplier", 4, 1, 64);

        chemEliteTierMultiplier = BUILDER
                .comment("Elite Chemical Factory - operations per tick multiplier")
                .defineInRange("chemEliteTierMultiplier", 8, 1, 64);

        chemUltimateTierMultiplier = BUILDER
                .comment("Ultimate Chemical Factory - operations per tick multiplier")
                .defineInRange("chemUltimateTierMultiplier", 16, 1, 64);

        BUILDER.pop();

        BUILDER.comment("Evolved Mekanism Factory Tier Multipliers",
                "For the extended factory tiers added by Evolved Mekanism.",
                "These apply to all factories (Alloying, Crushing, Enriching, etc.) when using EM tiers.").push("evolved_mekanism_factory_tiers");

        emOverclockedTierMultiplier = BUILDER
                .comment("Overclocked tier - items per operation")
                .defineInRange("emOverclockedTierMultiplier", 64, 1, 64);

        emQuantumTierMultiplier = BUILDER
                .comment("Quantum tier - items per operation")
                .defineInRange("emQuantumTierMultiplier", 64, 1, 64);

        emDenseTierMultiplier = BUILDER
                .comment("Dense tier - items per operation")
                .defineInRange("emDenseTierMultiplier", 64, 1, 64);

        emMultiversalTierMultiplier = BUILDER
                .comment("Multiversal tier - items per operation")
                .defineInRange("emMultiversalTierMultiplier", 64, 1, 64);

        emCreativeTierMultiplier = BUILDER
                .comment("Creative tier - items per operation")
                .defineInRange("emCreativeTierMultiplier", 64, 1, 64);

        BUILDER.pop();

        SPEC = BUILDER.build();
    }
}
