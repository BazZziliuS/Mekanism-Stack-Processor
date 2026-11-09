package org.cloudea.mekanismstackprocessor;

import mekanism.api.recipes.cache.CachedRecipe;
import mekanism.common.tier.FactoryTier;
import org.jetbrains.annotations.Nullable;

import java.util.function.IntSupplier;

/**
 * Утилитный класс для расчёта множителей обработки.
 */
public class StackProcessorUtil {

    /**
     * Умножает количество операций рецепта на множитель.
     *
     * @param cached            кэшированный рецепт, созданный машиной
     * @param multiplier        множитель обработки
     * @param operationsPerTick базовое количество операций в тик
     */
    public static void applyBatch(@Nullable CachedRecipe<?> cached, int multiplier, IntSupplier operationsPerTick) {
        if (cached != null && multiplier > 1) {
            cached.setBaselineMaxOperations(() -> operationsPerTick.getAsInt() * multiplier);
        }
    }

    /**
     * Применяет множитель обычных машин (не фабрик).
     */
    public static void applyMachineBatch(@Nullable CachedRecipe<?> cached) {
        applyBatch(cached, getMachineMultiplier(), () -> 1);
    }

    /**
     * Возвращает множитель для указанного уровня фабрики.
     * Поддерживает стандартные тайры Mekanism и расширенные тайры Evolved Mekanism.
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
            default -> getEMTierMultiplierByName(tier.name());
        };
    }

    private static int getEMTierMultiplierByName(String name) {
        return switch (name) {
            case "OVERCLOCKED" -> Config.emOverclockedTierMultiplier.get();
            case "QUANTUM" -> Config.emQuantumTierMultiplier.get();
            case "DENSE" -> Config.emDenseTierMultiplier.get();
            case "MULTIVERSAL" -> Config.emMultiversalTierMultiplier.get();
            case "CREATIVE" -> Config.emCreativeTierMultiplier.get();
            default -> 1;
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
     * Поддерживает стандартные тайры Mekanism и расширенные тайры Evolved Mekanism.
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
            default -> getEMTierMultiplierByName(tier.name());
        };
    }
}
