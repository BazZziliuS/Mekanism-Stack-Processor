package org.cloudea.mekanismstackprocessor.mixin;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

/**
 * Mixin plugin that conditionally loads MoreMachine mixins
 * only when the corresponding mod classes are present.
 */
public class MoreMachineMixinPlugin implements IMixinConfigPlugin {

    private static final String MEKMM_CLASS = "com.jerry.mekmm.common.tile.factory.TileEntityMoreMachineFactory";
    private static final String MEKAF_CLASS = "com.jerry.mekaf.common.tile.factory.TileEntityAdvancedFactoryBase";

    private static Boolean mekmmPresent = null;
    private static Boolean mekafPresent = null;

    @Override
    public void onLoad(String mixinPackage) {
        mekmmPresent = isClassPresent(MEKMM_CLASS);
        mekafPresent = isClassPresent(MEKAF_CLASS);
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        // mekaf mixins require mekaf classes
        if (mixinClassName.contains(".mekaf.")) {
            return mekafPresent != null && mekafPresent;
        }
        // mekmm mixins require mekmm classes
        return mekmmPresent != null && mekmmPresent;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    private static boolean isClassPresent(String className) {
        try {
            Class.forName(className, false, MoreMachineMixinPlugin.class.getClassLoader());
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
