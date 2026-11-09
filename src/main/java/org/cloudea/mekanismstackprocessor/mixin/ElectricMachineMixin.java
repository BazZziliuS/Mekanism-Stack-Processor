package org.cloudea.mekanismstackprocessor.mixin;

import mekanism.api.recipes.cache.CachedRecipe;
import mekanism.common.tile.prefab.TileEntityElectricMachine;
import org.cloudea.mekanismstackprocessor.StackProcessorUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin для обычных электрических машин (Crusher, Enrichment Chamber, etc.).
 * Добавляет batch processing для обработки нескольких предметов за раз.
 */
@Mixin(value = TileEntityElectricMachine.class, remap = false)
public abstract class ElectricMachineMixin {

    @Inject(method = "createNewCachedRecipe", at = @At("RETURN"))
    private void addBatchProcessing(CallbackInfoReturnable<CachedRecipe<?>> cir) {
        StackProcessorUtil.applyMachineBatch(cir.getReturnValue());
    }
}
