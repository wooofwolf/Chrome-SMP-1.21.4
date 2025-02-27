package net.justwoofwolf.chromesmp.blockentity.custom;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.justwoofwolf.chromesmp.blockentity.ImplementedInventory;
import net.justwoofwolf.chromesmp.blockentity.ModBlockEntities;
import net.justwoofwolf.chromesmp.recipe.ModRecipes;
import net.justwoofwolf.chromesmp.screen.InstallationTableData;
import net.justwoofwolf.chromesmp.screen.InstallationTableScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class InstallationTableBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    protected DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

    private static final int ITEM_SLOT = 0;
    private static final int INSTALLATION_SLOT = 1;
    private static final int OUTPUT_SLOT = 2;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;

    public InstallationTableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.INSTALLATION_TABLE_BLOCK_ENTITY, pos, state);

        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch(index) {
                    case 0 -> InstallationTableBlockEntity.this.progress;
                    case 1 -> InstallationTableBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch(index) {
                    case 0 -> InstallationTableBlockEntity.this.progress = value;
                    case 1 -> InstallationTableBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        // We provide *this* to the screenHandler as our class Implements Inventory
        // Only the Server has the Inventory at the start, this will be synced to the client in the ScreenHandler
        return new InstallationTableScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable(getCachedState().getBlock().getTranslationKey());
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        super.readNbt(nbt, registries);
        Inventories.readNbt(nbt, inventory, registries);
        progress = nbt.getInt("installation_table.progress");
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registries) {
        super.writeNbt(nbt, registries);
        Inventories.writeNbt(nbt, inventory, false, registries);
        nbt.putInt("installation_table.progress", progress);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient) return;

        ItemStack inputSlot = getStack(ITEM_SLOT);
        ItemStack installationSlot = getStack(INSTALLATION_SLOT);

        if (isOutputSlotEmptyOrReceivable()) {
            if (ModRecipes.hasResult(inputSlot, installationSlot) && canInsertAmountIntoOutputSlot() && canInsertItemIntoOutputSlot(inputSlot.getItem())) {
                this.increaseCraftProgress();
                markDirty(world, pos, state);

                if (hasCraftingFinished()) {
                    this.craftItem(ModRecipes.getResult(inputSlot, installationSlot), ModRecipes.getTag(inputSlot, installationSlot));
                    this.resetProgress();
                }
            } else {
                this.resetProgress();
            }
        } else {
            this.resetProgress();
            markDirty(world, pos, state);
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private void craftItem(ItemStack itemStack, ComponentType<Integer> tag) {
        ItemStack newItem = new ItemStack(itemStack.getItem(), getStack(OUTPUT_SLOT).getCount() + itemStack.getCount());
        newItem.set(tag, 1);

        this.removeStack(ITEM_SLOT, 1);
        this.removeStack(INSTALLATION_SLOT, 1);

        this.setStack(OUTPUT_SLOT, newItem);
    }

    private boolean hasCraftingFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftProgress() {
        progress++;
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.getStack(OUTPUT_SLOT).getItem() == item || this.getStack(OUTPUT_SLOT).isEmpty();
    }

    private boolean canInsertAmountIntoOutputSlot() {
        return this.getStack(OUTPUT_SLOT).getCount() + 1 <= getStack(OUTPUT_SLOT).getMaxCount();
    }

    private boolean isOutputSlotEmptyOrReceivable() {
        return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getCount() < this.getStack(OUTPUT_SLOT).getMaxCount();
    }

    @Override
    public Object getScreenOpeningData(ServerPlayerEntity serverPlayerEntity) {
        return new InstallationTableData(this.pos);
    }
}
