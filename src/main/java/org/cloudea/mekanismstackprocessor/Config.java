package org.cloudea.mekanismstackprocessor;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final int MIN_MULTIPLIER = 1;
    private static final int MAX_MULTIPLIER = 64;

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

        basicTierMultiplier = defineMultiplier("basicTierMultiplier", 8, "Basic Factory - items per operation");
        advancedTierMultiplier = defineMultiplier("advancedTierMultiplier", 16, "Advanced Factory - items per operation");
        eliteTierMultiplier = defineMultiplier("eliteTierMultiplier", 32, "Elite Factory - items per operation");
        ultimateTierMultiplier = defineMultiplier("ultimateTierMultiplier", 64, "Ultimate Factory - items per operation (max stack!)");

        BUILDER.pop();

        BUILDER.comment("Regular Machine Settings",
                "For non-factory machines like Crusher, Enrichment Chamber, etc.").push("regular_machines");

        baseMachineMultiplier = defineMultiplier("baseMachineMultiplier", 4, "Items per operation for regular machines");

        BUILDER.pop();

        BUILDER.comment("Chemical Factory Tier Multipliers",
                "For factories that process gases/fluids:",
                "- Mekanism: Injecting, Infusing, Purifying factories",
                "- MoreMachine mekaf: Dissolving, Liquefying, Oxidizing, etc.",
                "Higher tier = more chemicals processed at once.").push("chemical_factory_tiers");

        chemBasicTierMultiplier = defineMultiplier("chemBasicTierMultiplier", 1, "Basic Chemical Factory - operations per tick multiplier");
        chemAdvancedTierMultiplier = defineMultiplier("chemAdvancedTierMultiplier", 4, "Advanced Chemical Factory - operations per tick multiplier");
        chemEliteTierMultiplier = defineMultiplier("chemEliteTierMultiplier", 8, "Elite Chemical Factory - operations per tick multiplier");
        chemUltimateTierMultiplier = defineMultiplier("chemUltimateTierMultiplier", 16, "Ultimate Chemical Factory - operations per tick multiplier");

        BUILDER.pop();

        BUILDER.comment("Evolved Mekanism Factory Tier Multipliers",
                "For the extended factory tiers added by Evolved Mekanism.",
                "These apply to all factories (Alloying, Crushing, Enriching, etc.) when using EM tiers.").push("evolved_mekanism_factory_tiers");

        emOverclockedTierMultiplier = defineMultiplier("emOverclockedTierMultiplier", 64, "Overclocked tier - items per operation");
        emQuantumTierMultiplier = defineMultiplier("emQuantumTierMultiplier", 64, "Quantum tier - items per operation");
        emDenseTierMultiplier = defineMultiplier("emDenseTierMultiplier", 64, "Dense tier - items per operation");
        emMultiversalTierMultiplier = defineMultiplier("emMultiversalTierMultiplier", 64, "Multiversal tier - items per operation");
        emCreativeTierMultiplier = defineMultiplier("emCreativeTierMultiplier", 64, "Creative tier - items per operation");

        BUILDER.pop();

        SPEC = BUILDER.build();
    }

    private static ModConfigSpec.IntValue defineMultiplier(String key, int defaultValue, String comment) {
        return BUILDER.comment(comment).defineInRange(key, defaultValue, MIN_MULTIPLIER, MAX_MULTIPLIER);
    }
}
