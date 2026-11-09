package org.cloudea.mekanismstackprocessor.mixin.evolvedmekanism;

import fr.iglee42.evolvedmekanism.tiles.factory.TileEntityAlloyingFactory;
import mekanism.api.recipes.cache.CachedRecipe;
import mekanism.common.tile.factory.TileEntityFactory;
import org.cloudea.mekanismstackprocessor.StackProcessorUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = TileEntityAlloyingFactory.class, remap = false)
public abstract class AlloyingFactoryMixin {

    @Inject(method = "createNewCachedRecipe", at = @At("RETURN"))
    private void addBatchProcessing(CallbackInfoReturnable<CachedRecipe<?>> cir) {
        TileEntityFactory<?> factory = (TileEntityFactory<?>) (Object) this;
        StackProcessorUtil.applyBatch(cir.getReturnValue(),
                StackProcessorUtil.getFactoryMultiplier(factory.tier), factory::getOperationsPerTick);
    }
}
