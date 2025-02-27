package net.justwoofwolf.chromesmp.mixin;

import net.justwoofwolf.chromesmp.component.ModComponents;
import net.minecraft.component.ComponentType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Map;

@Mixin(Item.class)
public class ItemMixin {
    @Inject(at = @At("HEAD"), method = "appendTooltip")
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type, CallbackInfo ci) {
        for (ComponentType<Integer> component : ModComponents.MOD_COMPONENTS.keySet()) {
            if (stack.contains(component)) {
                tooltip.add(Text.translatable("component.chromesmp." + ModComponents.MOD_COMPONENTS.get(component))
                        .setStyle(Style.EMPTY.withColor(Formatting.DARK_PURPLE).withItalic(true)));
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "hasGlint", cancellable = true)
    public void hasGlint(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        for (ComponentType<Integer> component : ModComponents.MOD_COMPONENTS.keySet()) {
            if (stack.contains(component)) {
                cir.setReturnValue(true);
            }
        }
    }
}
