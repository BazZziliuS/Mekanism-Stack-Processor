package org.cloudea.mekanismstackprocessor;

import mekanism.common.tier.FactoryTier;
import mekanism.common.tile.factory.TileEntityFactory;

/**
 * Утилитный класс для расчёта множителей обработки.
 */
public class StackProcessorUtil {

    /**
     * Получает operationsPerTick из фабрики.
     */
    public static int getOperationsPerTick(TileEntityFactory<?> factory) {
        return factory.getOperationsPerTick();
    }

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

    /**
     * Возвращает множитель для химических фабрик (газы/жидкости) из MoreMachine mekaf.
     */
    public static int getChemicalFactoryMultiplier(FactoryTier tier) {
        if (!Config.enableOptimization.get()) {
            return 1;
        }
        return switch (tier) {
            case BASIC -> Config.chemBasicTierMultiplier.get();
            case ADVANCED -> Config.chemAdvancedTierMultiplier.get();
            case ELITE -> Config.chemEliteTierMultiplier.get();
            case ULTIMATE -> Config.chemUltimateTierMultiplier.get();
        };
    }
}
