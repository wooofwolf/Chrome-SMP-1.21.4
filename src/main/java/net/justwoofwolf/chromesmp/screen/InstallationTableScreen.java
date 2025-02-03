package net.justwoofwolf.chromesmp.screen;

import net.justwoofwolf.chromesmp.ChromeSMP;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class InstallationTableScreen extends HandledScreen<InstallationTableScreenHandler> {
    private static final Identifier TEXTURE = Identifier.of(ChromeSMP.MOD_ID, "textures/gui/installation_table_gui.png");
    private static final Identifier PROGRESS = Identifier.of(ChromeSMP.MOD_ID, "textures/gui/installation_progress_gui.png");

    public InstallationTableScreen(InstallationTableScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(RenderLayer::getGuiTextured, TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight, 256, 256);

        renderProgressArrow(context, x, y);
    }

    private void renderProgressArrow(DrawContext context, int x, int y) {
        if (handler.isCrafting()) {
            context.drawTexture(RenderLayer::getGuiTextured, PROGRESS, x+102, y+35, 0, 0, handler.getScaledProgress(), 16, 22, 16);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();
        // Center the title
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }
}
