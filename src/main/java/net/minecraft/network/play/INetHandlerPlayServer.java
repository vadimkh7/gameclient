package net.minecraft.network.play;

import net.minecraft.network.INetHandler;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.network.play.client.CPacketClickWindow;
import net.minecraft.network.play.client.CPacketClientSettings;
import net.minecraft.network.play.client.CPacketClientStatus;
import net.minecraft.network.play.client.CPacketCloseWindow;
import net.minecraft.network.play.client.CPacketConfirmTeleport;
import net.minecraft.network.play.client.CPacketConfirmTransaction;
import net.minecraft.network.play.client.CPacketCreativeInventoryAction;
import net.minecraft.network.play.client.CPacketCustomPayload;
import net.minecraft.network.play.client.CPacketEnchantItem;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketInput;
import net.minecraft.network.play.client.CPacketKeepAlive;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerAbilities;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.network.play.client.CPacketRecipeInfo;
import net.minecraft.network.play.client.CPacketRecipePlacement;
import net.minecraft.network.play.client.CPacketResourcePackStatus;
import net.minecraft.network.play.client.CPacketSeenAdvancements;
import net.minecraft.network.play.client.CPacketSpectate;
import net.minecraft.network.play.client.CPacketSteerBoat;
import net.minecraft.network.play.client.CPacketTabComplete;
import net.minecraft.network.play.client.CPacketUpdateSign;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.network.play.client.CPacketVehicleMove;

public interface INetHandlerPlayServer extends INetHandler {
   void handleAnimation(CPacketAnimation packetIn);

   void processChatMessage(CPacketChatMessage packetIn);

   void processTabComplete(CPacketTabComplete packetIn);

   void processClientStatus(CPacketClientStatus packetIn);

   void processClientSettings(CPacketClientSettings packetIn);

   void processConfirmTransaction(CPacketConfirmTransaction packetIn);

   void processEnchantItem(CPacketEnchantItem packetIn);

   void processClickWindow(CPacketClickWindow packetIn);

   void func_191985_a(CPacketRecipePlacement p_191985_1_);

   void processCloseWindow(CPacketCloseWindow packetIn);

   void processCustomPayload(CPacketCustomPayload packetIn);

   void processUseEntity(CPacketUseEntity packetIn);

   void processKeepAlive(CPacketKeepAlive packetIn);

   void processPlayer(CPacketPlayer packetIn);

   void processPlayerAbilities(CPacketPlayerAbilities packetIn);

   void processPlayerDigging(CPacketPlayerDigging packetIn);

   void processEntityAction(CPacketEntityAction packetIn);

   void processInput(CPacketInput packetIn);

   void processHeldItemChange(CPacketHeldItemChange packetIn);

   void processCreativeInventoryAction(CPacketCreativeInventoryAction packetIn);

   void processUpdateSign(CPacketUpdateSign packetIn);

   void processRightClickBlock(CPacketPlayerTryUseItemOnBlock packetIn);

   void processPlayerBlockPlacement(CPacketPlayerTryUseItem packetIn);

   void handleSpectate(CPacketSpectate packetIn);

   void handleResourcePackStatus(CPacketResourcePackStatus packetIn);

   void processSteerBoat(CPacketSteerBoat packetIn);

   void processVehicleMove(CPacketVehicleMove packetIn);

   void processConfirmTeleport(CPacketConfirmTeleport packetIn);

   void func_191984_a(CPacketRecipeInfo p_191984_1_);

   void func_194027_a(CPacketSeenAdvancements p_194027_1_);
}
