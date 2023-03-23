package net.minecraft.inventory;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public interface IContainerListener {
   void updateCraftingInventory(Container containerToSend, NonNullList<ItemStack> itemsList);

   void sendSlotContents(Container containerToSend, int slotInd, ItemStack stack);

   void sendProgressBarUpdate(Container containerIn, int varToUpdate, int newValue);

   void sendAllWindowProperties(Container containerIn, IInventory inventory);
}
