package net.justwoofwolf.chromesmp.event;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.justwoofwolf.chromesmp.ChromeSMP;
import net.justwoofwolf.chromesmp.component.ModComponents;
import net.minecraft.block.Block;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class EventHandler {
    public static void registerEventHandler() {
        ChromeSMP.LOGGER.info("Registering Event Handler for " + ChromeSMP.MOD_ID);

        PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, entity) -> {
            // Check if player is holding an axe with TreeFeller
            if (player.getMainHandStack().contains(ModComponents.TREE_FELLER_COMPONENT) && world.getBlockState(pos).isIn(BlockTags.OVERWORLD_NATURAL_LOGS)) {
                // Get list of logs nearby
                List<BlockPos> logPosList = new ArrayList<>();
                List<BlockPos> checkedPos = new ArrayList<>();
                CheckForNearbyBlocksWithTag(world, pos, logPosList, checkedPos, BlockTags.OVERWORLD_NATURAL_LOGS);

                // Break every log
                for (int i = 0; i < logPosList.size(); ++i) {
                    world.breakBlock(logPosList.get(i), true);
                }
            }

            return true;
        });
    }

    private static void CheckForNearbyBlocksWithTag(World w, BlockPos pos, List<BlockPos> logPosList, List<BlockPos> checkedPos, TagKey<Block> blockTag) {
        logPosList.add(pos);
        checkedPos.add(pos);

        for (int x = -1; x < 2; ++x) {
            for (int y = -1; y < 2; ++y) {
                for (int z = -1; z < 2; ++z) {
                    BlockPos newPos = pos.add(x, y, z);

                    if (checkedPos.contains(newPos)) continue;

                    if (w.getBlockState(newPos).isIn(blockTag)) {
                        CheckForNearbyBlocksWithTag(w, newPos, logPosList, checkedPos, blockTag);
                    }
                }
            }
        }
    }
}
