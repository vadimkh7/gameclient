package net.minecraft.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockConcretePowder extends BlockFalling {
   public static final PropertyEnum<EnumDyeColor> field_192426_a = PropertyEnum.<EnumDyeColor>create("color", EnumDyeColor.class);

   public BlockConcretePowder() {
      super(Material.SAND);
      this.setDefaultState(this.blockState.getBaseState().withProperty(field_192426_a, EnumDyeColor.WHITE));
      this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
   }

   public void onEndFalling(World worldIn, BlockPos pos, IBlockState p_176502_3_, IBlockState p_176502_4_) {
      if (p_176502_4_.getMaterial().isLiquid()) {
         worldIn.setBlockState(pos, Blocks.field_192443_dR.getDefaultState().withProperty(BlockColored.COLOR, p_176502_3_.getValue(field_192426_a)), 3);
      }
   }

   protected boolean func_192425_e(World p_192425_1_, BlockPos p_192425_2_, IBlockState p_192425_3_) {
      boolean flag = false;

      for(EnumFacing enumfacing : EnumFacing.values()) {
         if (enumfacing != EnumFacing.DOWN) {
            BlockPos blockpos = p_192425_2_.offset(enumfacing);
            if (p_192425_1_.getBlockState(blockpos).getMaterial() == Material.WATER) {
               flag = true;
               break;
            }
         }
      }

      if (flag) {
         p_192425_1_.setBlockState(p_192425_2_, Blocks.field_192443_dR.getDefaultState().withProperty(BlockColored.COLOR, p_192425_3_.getValue(field_192426_a)), 3);
      }

      return flag;
   }

   public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos p_189540_5_) {
      if (!this.func_192425_e(worldIn, pos, state)) {
         super.neighborChanged(state, worldIn, pos, blockIn, p_189540_5_);
      }
   }

   public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
      if (!this.func_192425_e(worldIn, pos, state)) {
         super.onBlockAdded(worldIn, pos, state);
      }
   }

   public int damageDropped(IBlockState state) {
      return ((EnumDyeColor)state.getValue(field_192426_a)).getMetadata();
   }

   public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> tab) {
      for(EnumDyeColor enumdyecolor : EnumDyeColor.values()) {
         tab.add(new ItemStack(this, 1, enumdyecolor.getMetadata()));
      }
   }

   public MapColor getMapColor(IBlockState state, IBlockAccess p_180659_2_, BlockPos p_180659_3_) {
      return MapColor.func_193558_a((EnumDyeColor)state.getValue(field_192426_a));
   }

   public IBlockState getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(field_192426_a, EnumDyeColor.byMetadata(meta));
   }

   public int getMetaFromState(IBlockState state) {
      return ((EnumDyeColor)state.getValue(field_192426_a)).getMetadata();
   }

   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, new IProperty[]{field_192426_a});
   }
}
