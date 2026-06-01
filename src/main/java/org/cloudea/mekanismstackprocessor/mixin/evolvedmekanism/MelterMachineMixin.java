package org.cloudea.mekanismstackprocessor.mixin.evolvedmekanism;

import fr.iglee42.evolvedmekanism.tiles.machine.TileEntityMelter;
import mekanism.api.recipes.ItemStackToFluidRecipe;
import mekanism.api.recipes.cache.CachedRecipe;
import org.cloudea.mekanismstackprocessor.StackProcessorUtil;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = TileEntityMelter.class, remap = false)
public abstract class MelterMachineMixin {

    @Inject(method = "createNewCachedRecipe", at = @At("RETURN"))
    private void addBatchProcessing(@NotNull ItemStackToFluidRecipe recipe, int cacheIndex,
                                    CallbackInfoReturnable<CachedRecipe<ItemStackToFluidRecipe>> cir) {
        CachedRecipe<ItemStackToFluidRecipe> cached = cir.getReturnValue();
        if (cached != null) {
            int multiplier = StackProcessorUtil.getMachineMultiplier();
            if (multiplier > 1) {
                cached.setBaselineMaxOperations(() -> multiplier);
            }
        }
    }
}
