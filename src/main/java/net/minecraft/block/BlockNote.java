package net.minecraft.block;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityNote;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockNote extends BlockContainer {
   private static final List<SoundEvent> INSTRUMENTS = Lists.newArrayList(SoundEvents.BLOCK_NOTE_HARP, SoundEvents.BLOCK_NOTE_BASEDRUM, SoundEvents.BLOCK_NOTE_SNARE, SoundEvents.BLOCK_NOTE_HAT, SoundEvents.BLOCK_NOTE_BASS, SoundEvents.field_193809_ey, SoundEvents.field_193807_ew, SoundEvents.field_193810_ez, SoundEvents.field_193808_ex, SoundEvents.field_193785_eE);

   public BlockNote() {
      super(Material.WOOD);
      this.setCreativeTab(CreativeTabs.REDSTONE);
   }

   public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos p_189540_5_) {
      boolean flag = worldIn.isBlockPowered(pos);
      TileEntity tileentity = worldIn.getTileEntity(pos);
      if (tileentity instanceof TileEntityNote) {
         TileEntityNote tileentitynote = (TileEntityNote)tileentity;
         if (tileentitynote.previousRedstoneState != flag) {
            if (flag) {
               tileentitynote.triggerNote(worldIn, pos);
            }

            tileentitynote.previousRedstoneState = flag;
         }
      }
   }

   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing heldItem, float side, float hitX, float hitY) {
      if (worldIn.isRemote) {
         return true;
      } else {
         TileEntity tileentity = worldIn.getTileEntity(pos);
         if (tileentity instanceof TileEntityNote) {
            TileEntityNote tileentitynote = (TileEntityNote)tileentity;
            tileentitynote.changePitch();
            tileentitynote.triggerNote(worldIn, pos);
            playerIn.addStat(StatList.NOTEBLOCK_TUNED);
         }

         return true;
      }
   }

   public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
      if (!worldIn.isRemote) {
         TileEntity tileentity = worldIn.getTileEntity(pos);
         if (tileentity instanceof TileEntityNote) {
            ((TileEntityNote)tileentity).triggerNote(worldIn, pos);
            playerIn.addStat(StatList.NOTEBLOCK_PLAYED);
         }
      }
   }

   public TileEntity createNewTileEntity(World worldIn, int meta) {
      return new TileEntityNote();
   }

   private SoundEvent getInstrument(int p_185576_1_) {
      if (p_185576_1_ < 0 || p_185576_1_ >= INSTRUMENTS.size()) {
         p_185576_1_ = 0;
      }

      return INSTRUMENTS.get(p_185576_1_);
   }

   public boolean eventReceived(IBlockState state, World worldIn, BlockPos pos, int id, int param) {
      float f = (float)Math.pow(2.0D, (double)(param - 12) / 12.0D);
      worldIn.playSound((EntityPlayer)null, pos, this.getInstrument(id), SoundCategory.RECORDS, 3.0F, f);
      worldIn.spawnParticle(EnumParticleTypes.NOTE, (double)pos.getX() + 0.5D, (double)pos.getY() + 1.2D, (double)pos.getZ() + 0.5D, (double)param / 24.0D, 0.0D, 0.0D);
      return true;
   }

   public EnumBlockRenderType getRenderType(IBlockState state) {
      return EnumBlockRenderType.MODEL;
   }
}
