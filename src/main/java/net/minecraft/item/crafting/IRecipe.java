package net.minecraft.item.crafting;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public interface IRecipe {
   boolean matches(InventoryCrafting inv, World worldIn);

   ItemStack getCraftingResult(InventoryCrafting inv);

   boolean func_194133_a(int p_194133_1_, int p_194133_2_);

   ItemStack getRecipeOutput();

   NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv);

   default NonNullList<Ingredient> func_192400_c() {
      return NonNullList.<Ingredient>func_191196_a();
   }

   default boolean func_192399_d() {
      return false;
   }

   default String func_193358_e() {
      return "";
   }
}
