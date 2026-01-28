package org.cloudea.mekanismstackprocessor.mixin;

import mekanism.api.recipes.ItemStackToItemStackRecipe;
import mekanism.api.recipes.cache.CachedRecipe;
import mekanism.common.tile.factory.TileEntityFactory;
import mekanism.common.tile.factory.TileEntityItemStackToItemStackFactory;
import org.cloudea.mekanismstackprocessor.StackProcessorUtil;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin для фабрик ItemStack -> ItemStack (Crushing, Enriching, Smelting).
 * Добавляет batch processing - обработку нескольких предметов за раз.
 */
@Mixin(value = TileEntityItemStackToItemStackFactory.class, remap = false)
public abstract class ItemStackToItemStackFactoryMixin {

    @Inject(method = "createNewCachedRecipe", at = @At("RETURN"))
    private void addBatchProcessing(@NotNull ItemStackToItemStackRecipe recipe, int cacheIndex,
                                    CallbackInfoReturnable<CachedRecipe<ItemStackToItemStackRecipe>> cir) {
        CachedRecipe<ItemStackToItemStackRecipe> cached = cir.getReturnValue();
        if (cached != null) {
            // Получаем tier из родительского класса через приведение типов
            TileEntityFactory<?> factory = (TileEntityFactory<?>) (Object) this;
            int multiplier = StackProcessorUtil.getFactoryMultiplier(factory.tier);
            if (multiplier > 1) {
                cached.setBaselineMaxOperations(() -> multiplier);
            }
        }
    }
}
