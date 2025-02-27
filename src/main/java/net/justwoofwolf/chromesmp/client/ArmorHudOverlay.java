package net.justwoofwolf.chromesmp.client;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.justwoofwolf.chromesmp.ChromeSMP;
import net.justwoofwolf.chromesmp.ChromeSMPClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;

public class ArmorHudOverlay implements HudRenderCallback {
    // Texture Ids
    private static final Identifier ARMOR_HOTBAR_TEXTURE = Identifier.of(ChromeSMP.MOD_ID,
            "textures/hotbar.png");
    private static final Identifier ARMOR_HOTBAR_SELECTION_TEXTURE = Identifier.of(ChromeSMP.MOD_ID,
            "textures/hotbar_selection.png");

    @Override
    public void onHudRender(DrawContext context, RenderTickCounter renderTickCounter) {
        int x = 0;
        int y = 0;

        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null) {
            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();

            x = width / 2;
            y = height;
        }

        context.getMatrices().push();
        context.getMatrices().scale(1f, 1f, 1f);
        context.getMatrices().pop();

        // Render hotbar textures
        drawTexture(context, ARMOR_HOTBAR_TEXTURE, x + 9, y - 66,  82, 22);
        drawTexture(context, ARMOR_HOTBAR_SELECTION_TEXTURE, x + 8 + 20 * ChromeSMPClient.playerData.armorSlotId, y - 67, 24, 23);

        // Render armor items
        DefaultedList<ItemStack> armor = client.player.getInventory().armor;

        for (int i = 0; i < 4; ++i) {
            context.getMatrices().push();
            context.getMatrices().translate(0f, 0f, 0f);
            context.drawItem(armor.get(i), x + 72 - i * 20, y - 63);
            context.getMatrices().pop();
        }
    }

    private void drawTexture(DrawContext context, Identifier texture, int x, int y, int width, int height) {
        context.drawTexture(
                RenderLayer::getGuiTexturedOverlay,
                texture,
                x, y,
                0f, 0f,
                width, height,
                width, height
        );
    }
}
