package net.justwoofwolf.chromesmp.mixin;

import net.justwoofwolf.chromesmp.component.ModComponents;
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

@Mixin(Item.class)
public class ItemMixin {
    @Inject(at = @At("HEAD"), method = "appendTooltip")
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type, CallbackInfo ci) {
        if (stack.contains(ModComponents.TREE_FELLER_COMPONENT)) {
            tooltip.add(Text.literal("Tree Feller").setStyle(Style.EMPTY.withColor(Formatting.DARK_PURPLE).withItalic(true)));
        }
    }

    @Inject(at = @At("HEAD"), method = "hasGlint", cancellable = true)
    public void hasGlint(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.contains(ModComponents.TREE_FELLER_COMPONENT)) {
            cir.setReturnValue(true);
        }
    }
}
