package net.minecraft.world.chunk;

import javax.annotation.Nullable;

public interface IChunkProvider {
   @Nullable
   Chunk getLoadedChunk(int x, int z);

   Chunk provideChunk(int x, int z);

   boolean unloadQueuedChunks();

   String makeString();

   boolean func_191062_e(int p_191062_1_, int p_191062_2_);
}
