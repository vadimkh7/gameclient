package net.minecraft.command;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.GameType;
import net.minecraft.world.WorldSettings;

public class CommandGameMode extends CommandBase {
   public String getCommandName() {
      return "gamemode";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getCommandUsage(ICommandSender sender) {
      return "commands.gamemode.usage";
   }

   public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
      if (args.length <= 0) {
         throw new WrongUsageException("commands.gamemode.usage", new Object[0]);
      } else {
         GameType gametype = this.getGameModeFromCommand(sender, args[0]);
         EntityPlayer entityplayer = args.length >= 2 ? getPlayer(server, sender, args[1]) : getCommandSenderAsPlayer(sender);
         entityplayer.setGameType(gametype);
         ITextComponent itextcomponent = new TextComponentTranslation("gameMode." + gametype.getName(), new Object[0]);
         if (sender.getEntityWorld().getGameRules().getBoolean("sendCommandFeedback")) {
            entityplayer.addChatMessage(new TextComponentTranslation("gameMode.changed", new Object[]{itextcomponent}));
         }

         if (entityplayer == sender) {
            notifyCommandListener(sender, this, 1, "commands.gamemode.success.self", new Object[]{itextcomponent});
         } else {
            notifyCommandListener(sender, this, 1, "commands.gamemode.success.other", new Object[]{entityplayer.getName(), itextcomponent});
         }
      }
   }

   protected GameType getGameModeFromCommand(ICommandSender sender, String gameModeString) throws CommandException, NumberInvalidException {
      GameType gametype = GameType.parseGameTypeWithDefault(gameModeString, GameType.NOT_SET);
      return gametype == GameType.NOT_SET ? WorldSettings.getGameTypeById(parseInt(gameModeString, 0, GameType.values().length - 2)) : gametype;
   }

   public List<String> getTabCompletionOptions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos pos) {
      if (args.length == 1) {
         return getListOfStringsMatchingLastWord(args, new String[]{"survival", "creative", "adventure", "spectator"});
      } else {
         return args.length == 2 ? getListOfStringsMatchingLastWord(args, server.getAllUsernames()) : Collections.emptyList();
      }
   }

   public boolean isUsernameIndex(String[] args, int index) {
      return index == 1;
   }
}
