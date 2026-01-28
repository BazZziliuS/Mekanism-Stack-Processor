package org.cloudea.mekanismstackprocessor;

import mekanism.common.tier.FactoryTier;

/**
 * Утилитный класс для расчёта множителей обработки.
 */
public class StackProcessorUtil {

    /**
     * Возвращает множитель для указанного уровня фабрики.
     */
    public static int getFactoryMultiplier(FactoryTier tier) {
        if (!Config.enableOptimization.get()) {
            return 1;
        }
        return switch (tier) {
            case BASIC -> Config.basicTierMultiplier.get();
            case ADVANCED -> Config.advancedTierMultiplier.get();
            case ELITE -> Config.eliteTierMultiplier.get();
            case ULTIMATE -> Config.ultimateTierMultiplier.get();
        };
    }

    /**
     * Возвращает множитель для обычных машин (не фабрик).
     */
    public static int getMachineMultiplier() {
        if (!Config.enableOptimization.get()) {
            return 1;
        }
        return Config.baseMachineMultiplier.get();
    }
}
