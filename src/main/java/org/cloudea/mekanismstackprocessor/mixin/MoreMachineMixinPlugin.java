package org.cloudea.mekanismstackprocessor.mixin;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

/**
 * Mixin plugin that conditionally loads MoreMachine mixins
 * only when the mekmm mod is present.
 */
public class MoreMachineMixinPlugin implements IMixinConfigPlugin {

    private static final String MOREMACHINE_CLASS = "com.jerry.mekmm.common.tile.factory.TileEntityMoreMachineFactory";
    private static Boolean moreMachinePresent = null;

    @Override
    public void onLoad(String mixinPackage) {
        // Check if MoreMachine is present
        moreMachinePresent = isClassPresent(MOREMACHINE_CLASS);
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        // Only apply mixins if MoreMachine is present
        return moreMachinePresent != null && moreMachinePresent;
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
