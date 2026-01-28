package org.cloudea.mekanismstackprocessor;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;

    public static final ModConfigSpec.BooleanValue enableOptimization;

    // Factory tier multipliers
    public static final ModConfigSpec.IntValue basicTierMultiplier;
    public static final ModConfigSpec.IntValue advancedTierMultiplier;
    public static final ModConfigSpec.IntValue eliteTierMultiplier;
    public static final ModConfigSpec.IntValue ultimateTierMultiplier;

    // Regular machines multiplier
    public static final ModConfigSpec.IntValue baseMachineMultiplier;

    static {
        BUILDER.comment("Mekanism Stack Processor Configuration").push("general");

        enableOptimization = BUILDER
                .comment("Enable batch processing optimization for Mekanism machines")
                .define("enableOptimization", true);

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

        SPEC = BUILDER.build();
    }
}
