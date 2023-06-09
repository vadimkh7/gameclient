package net.minecraft.world.storage;

import net.minecraft.nbt.NBTTagCompound;

public abstract class WorldSavedData {
   public final String mapName;
   private boolean dirty;

   public WorldSavedData(String name) {
      this.mapName = name;
   }

   public abstract void readFromNBT(NBTTagCompound nbt);

   public abstract NBTTagCompound writeToNBT(NBTTagCompound compound);

   public void markDirty() {
      this.setDirty(true);
   }

   public void setDirty(boolean isDirty) {
      this.dirty = isDirty;
   }

   public boolean isDirty() {
      return this.dirty;
   }
}
