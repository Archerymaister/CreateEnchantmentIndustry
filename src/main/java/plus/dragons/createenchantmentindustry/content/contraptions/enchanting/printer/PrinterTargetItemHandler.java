package plus.dragons.createenchantmentindustry.content.contraptions.enchanting.printer;

import com.simibubi.create.AllItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;
import plus.dragons.createenchantmentindustry.foundation.config.CeiConfigs;

public class PrinterTargetItemHandler implements IItemHandler {
    PrinterBlockEntity be;

    public PrinterTargetItemHandler(PrinterBlockEntity be) {
        this.be = be;
    }

    @Override
    public int getSlots() {
        return 1;
    }

    @Override
    public @NotNull ItemStack getStackInSlot(int slot) {
        return be.getCopyTarget();
    }

    @Override
    public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
        if(!be.getCopyTarget().isEmpty()) return stack;
        else{
            if(!simulate){
                be.setCopyTarget(stack);
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
        var ret = be.getCopyTarget().copy();
        if(!simulate){
            be.setCopyTarget(ItemStack.EMPTY);
        }
        return ret;
    }

    @Override
    public int getSlotLimit(int slot) {
        return 1;
    }

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        return (stack.is(Items.ENCHANTED_BOOK) || stack.is(Items.WRITTEN_BOOK) || stack.is(Items.NAME_TAG) || stack.is(AllItems.SCHEDULE.get())) && stack.getCount() == 1;
    }
}
