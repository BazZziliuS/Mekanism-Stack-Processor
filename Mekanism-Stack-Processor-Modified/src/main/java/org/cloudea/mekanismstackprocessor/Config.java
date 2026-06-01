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
    public static final ModConfigSpec.IntValue overclockedTierMultiplier;
    public static final ModConfigSpec.IntValue quantumTierMultiplier;
    public static final ModConfigSpec.IntValue denseTierMultiplier;
    public static final ModConfigSpec.IntValue multiversalTierMultiplier;
    public static final ModConfigSpec.IntValue creativeTierMultiplier;

    // Regular machines multiplier
    public static final ModConfigSpec.IntValue baseMachineMultiplier;

    // Chemical Factory tier multipliers
    public static final ModConfigSpec.IntValue chemBasicTierMultiplier;
    public static final ModConfigSpec.IntValue chemAdvancedTierMultiplier;
    public static final ModConfigSpec.IntValue chemEliteTierMultiplier;
    public static final ModConfigSpec.IntValue chemUltimateTierMultiplier;
    public static final ModConfigSpec.IntValue chemOverclockedTierMultiplier;
    public static final ModConfigSpec.IntValue chemQuantumTierMultiplier;
    public static final ModConfigSpec.IntValue chemDenseTierMultiplier;
    public static final ModConfigSpec.IntValue chemMultiversalTierMultiplier;
    public static final ModConfigSpec.IntValue chemCreativeTierMultiplier;

    static {
        BUILDER.comment("Mekanism Stack Processor Configuration").push("general");

        enableOptimization = BUILDER
                .comment("Enable batch processing optimization for Mekanism machines")
                .define("enableOptimization", true);

        enableUpdateCheck = BUILDER
                .comment("Enable automatic update checking on startup")
                .define("enableUpdateCheck", true);

        BUILDER.pop();

        BUILDER.comment(
                "Factory Tier Multipliers",
                "How many items each factory tier processes per operation.",
                "Higher tier = more items processed at once."
        ).push("factory_tiers");

        basicTierMultiplier = BUILDER
                .comment("Basic Factory - items per operation")
                .defineInRange("basicTierMultiplier", 1, 1, 64);

        advancedTierMultiplier = BUILDER
                .comment("Advanced Factory - items per operation")
                .defineInRange("advancedTierMultiplier", 2, 1, 64);

        eliteTierMultiplier = BUILDER
                .comment("Elite Factory - items per operation")
                .defineInRange("eliteTierMultiplier", 3, 1, 64);

        ultimateTierMultiplier = BUILDER
                .comment("Ultimate Factory - items per operation")
                .defineInRange("ultimateTierMultiplier", 4, 1, 64);

        overclockedTierMultiplier = BUILDER
                .comment("Overclocked Factory - items per operation")
                .defineInRange("overclockedTierMultiplier", 8, 1, 64);

        quantumTierMultiplier = BUILDER
                .comment("Quantum Factory - items per operation")
                .defineInRange("quantumTierMultiplier", 16, 1, 64);

        denseTierMultiplier = BUILDER
                .comment("Dense Factory - items per operation")
                .defineInRange("denseTierMultiplier", 32, 1, 64);

        multiversalTierMultiplier = BUILDER
                .comment("Multiversal Factory - items per operation")
                .defineInRange("multiversalTierMultiplier", 64, 1, 64);

        creativeTierMultiplier = BUILDER
                .comment("Creative Factory - items per operation")
                .defineInRange("creativeTierMultiplier", 64, 1, 64);

        BUILDER.pop();

        BUILDER.comment(
                "Regular Machine Settings",
                "For non-factory machines like Crusher, Enrichment Chamber, etc."
        ).push("regular_machines");

        baseMachineMultiplier = BUILDER
                .comment("Items per operation for regular machines")
                .defineInRange("baseMachineMultiplier", 1, 1, 64);

        BUILDER.pop();

        BUILDER.comment(
                "Chemical Factory Tier Multipliers",
                "For factories that process gases/fluids:",
                "- Mekanism: Injecting, Infusing, Purifying factories",
                "- MoreMachine mekaf: Dissolving, Liquefying, Oxidizing, etc.",
                "Higher tier = more chemicals processed at once."
        ).push("chemical_factory_tiers");

        chemBasicTierMultiplier = BUILDER
                .comment("Basic Chemical Factory - operations per tick multiplier")
                .defineInRange("chemBasicTierMultiplier", 1, 1, 64);

        chemAdvancedTierMultiplier = BUILDER
                .comment("Advanced Chemical Factory - operations per tick multiplier")
                .defineInRange("chemAdvancedTierMultiplier", 2, 1, 64);

        chemEliteTierMultiplier = BUILDER
                .comment("Elite Chemical Factory - operations per tick multiplier")
                .defineInRange("chemEliteTierMultiplier", 3, 1, 64);

        chemUltimateTierMultiplier = BUILDER
                .comment("Ultimate Chemical Factory - operations per tick multiplier")
                .defineInRange("chemUltimateTierMultiplier", 4, 1, 64);

        chemOverclockedTierMultiplier = BUILDER
                .comment("Overclocked Chemical Factory - operations per tick multiplier")
                .defineInRange("chemOverclockedTierMultiplier", 8, 1, 64);

        chemQuantumTierMultiplier = BUILDER
                .comment("Quantum Chemical Factory - operations per tick multiplier")
                .defineInRange("chemQuantumTierMultiplier", 16, 1, 64);

        chemDenseTierMultiplier = BUILDER
                .comment("Dense Chemical Factory - operations per tick multiplier")
                .defineInRange("chemDenseTierMultiplier", 32, 1, 64);

        chemMultiversalTierMultiplier = BUILDER
                .comment("Multiversal Chemical Factory - operations per tick multiplier")
                .defineInRange("chemMultiversalTierMultiplier", 64, 1, 64);

        chemCreativeTierMultiplier = BUILDER
                .comment("Creative Chemical Factory - operations per tick multiplier")
                .defineInRange("chemCreativeTierMultiplier", 64, 1, 64);

        BUILDER.pop();

        SPEC = BUILDER.build();
    }
}