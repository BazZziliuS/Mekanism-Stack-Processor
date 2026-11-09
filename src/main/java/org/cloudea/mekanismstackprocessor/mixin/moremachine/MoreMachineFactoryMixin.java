package org.cloudea.mekanismstackprocessor.mixin.moremachine;

import com.jerry.mekmm.common.tile.factory.TileEntityMoreMachineFactory;
import com.jerry.mekmm.common.tile.factory.TileEntityMoreMachineItemStackToItemStackFactory;
import com.jerry.mekmm.common.tile.factory.TileEntityPlantingFactory;
import com.jerry.mekmm.common.tile.factory.TileEntityPressingFactory;
import com.jerry.mekmm.common.tile.factory.TileEntityRecyclingFactory;
import com.jerry.mekmm.common.tile.factory.TileEntityReplicatingFactory;
import com.jerry.mekmm.common.tile.factory.TileEntityStampingFactory;
import mekanism.api.recipes.cache.CachedRecipe;
import org.cloudea.mekanismstackprocessor.StackProcessorUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin for MoreMachine factories (Lathe, Rolling Mill, Planting, Pressing, Recycling, Replicating, Stamping).
 */
@Mixin(value = {
        TileEntityMoreMachineItemStackToItemStackFactory.class,
        TileEntityPlantingFactory.class,
        TileEntityPressingFactory.class,
        TileEntityRecyclingFactory.class,
        TileEntityReplicatingFactory.class,
        TileEntityStampingFactory.class
}, remap = false)
public abstract class MoreMachineFactoryMixin {

    @Inject(method = "createNewCachedRecipe", at = @At("RETURN"))
    private void addBatchProcessing(CallbackInfoReturnable<CachedRecipe<?>> cir) {
        TileEntityMoreMachineFactory<?> factory = (TileEntityMoreMachineFactory<?>) (Object) this;
        StackProcessorUtil.applyBatch(cir.getReturnValue(),
                StackProcessorUtil.getFactoryMultiplier(factory.tier), factory::getOperationsPerTick);
    }
}
