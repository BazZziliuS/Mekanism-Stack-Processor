package org.cloudea.mekanismstackprocessor.mixin.moremachine.mekaf;

import com.jerry.mekaf.common.tile.factory.TileEntityCentrifugingFactory;
import com.jerry.mekaf.common.tile.factory.TileEntityCrystallizingFactory;
import com.jerry.mekaf.common.tile.factory.TileEntityDissolvingFactory;
import com.jerry.mekaf.common.tile.factory.TileEntityItemStackToChemicalStackFactory;
import com.jerry.mekaf.common.tile.factory.TileEntityLiquifyingFactory;
import com.jerry.mekaf.common.tile.factory.TileEntityPaintingFactory;
import com.jerry.mekaf.common.tile.factory.TileEntityPressurizedReactingFactory;
import com.jerry.mekaf.common.tile.factory.TileEntityWashingFactory;
import com.jerry.mekaf.common.tile.factory.base.TileEntityAdvancedFactoryBase;
import mekanism.api.recipes.cache.CachedRecipe;
import org.cloudea.mekanismstackprocessor.StackProcessorUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin for MoreMachine mekaf factories (Centrifuging, Crystallizing, Dissolving, Oxidizing,
 * Pigment Extracting, Liquifying, Painting, Pressurized Reacting, Washing).
 */
@Mixin(value = {
        TileEntityCentrifugingFactory.class,
        TileEntityCrystallizingFactory.class,
        TileEntityDissolvingFactory.class,
        TileEntityItemStackToChemicalStackFactory.class,
        TileEntityLiquifyingFactory.class,
        TileEntityPaintingFactory.class,
        TileEntityPressurizedReactingFactory.class,
        TileEntityWashingFactory.class
}, remap = false)
public abstract class AdvancedFactoryMixin {

    @Inject(method = "createNewCachedRecipe", at = @At("RETURN"))
    private void addBatchProcessing(CallbackInfoReturnable<CachedRecipe<?>> cir) {
        TileEntityAdvancedFactoryBase<?, ?> factory = (TileEntityAdvancedFactoryBase<?, ?>) (Object) this;
        StackProcessorUtil.applyBatch(cir.getReturnValue(),
                StackProcessorUtil.getChemicalFactoryMultiplier(factory.tier), factory::getOperationsPerTick);
    }
}
