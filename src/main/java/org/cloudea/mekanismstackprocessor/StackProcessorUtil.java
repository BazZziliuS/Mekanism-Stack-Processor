package org.cloudea.mekanismstackprocessor;

import mekanism.common.tier.FactoryTier;
import mekanism.common.tile.factory.TileEntityFactory;

/**
 * Utility class for processing multiplier calculations.
 */
public class StackProcessorUtil {

    /**
     * Gets operationsPerTick from the factory.
     */
    public static int getOperationsPerTick(TileEntityFactory<?> factory) {
        return factory.getOperationsPerTick();
    }

    /**
     * Returns the multiplier for the specified factory tier.
     */
    public static int getFactoryMultiplier(FactoryTier tier) {
        if (!Config.enableOptimization.get()) {
            return 1;
        }

        return switch (tier.name()) {
            case "BASIC" -> Config.basicTierMultiplier.get();
            case "ADVANCED" -> Config.advancedTierMultiplier.get();
            case "ELITE" -> Config.eliteTierMultiplier.get();
            case "ULTIMATE" -> Config.ultimateTierMultiplier.get();

            case "OVERCLOCKED" -> Config.overclockedTierMultiplier.get();
            case "QUANTUM" -> Config.quantumTierMultiplier.get();
            case "DENSE" -> Config.denseTierMultiplier.get();
            case "MULTIVERSAL" -> Config.multiversalTierMultiplier.get();
            case "CREATIVE" -> Config.creativeTierMultiplier.get();

            default -> 1;
        };
    }

    /**
     * Returns the multiplier for regular machines.
     */
    public static int getMachineMultiplier() {
        if (!Config.enableOptimization.get()) {
            return 1;
        }
        return Config.baseMachineMultiplier.get();
    }

    /**
     * Returns the multiplier for chemical factories.
     */
    public static int getChemicalFactoryMultiplier(FactoryTier tier) {
        if (!Config.enableOptimization.get()) {
            return 1;
        }

        return switch (tier.name()) {
            case "BASIC" -> Config.chemBasicTierMultiplier.get();
            case "ADVANCED" -> Config.chemAdvancedTierMultiplier.get();
            case "ELITE" -> Config.chemEliteTierMultiplier.get();
            case "ULTIMATE" -> Config.chemUltimateTierMultiplier.get();

            case "OVERCLOCKED" -> Config.chemOverclockedTierMultiplier.get();
            case "QUANTUM" -> Config.chemQuantumTierMultiplier.get();
            case "DENSE" -> Config.chemDenseTierMultiplier.get();
            case "MULTIVERSAL" -> Config.chemMultiversalTierMultiplier.get();
            case "CREATIVE" -> Config.chemCreativeTierMultiplier.get();

            default -> 1;
        };
    }
}