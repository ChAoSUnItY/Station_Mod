package chaos.mod.util.network;

import chaos.mod.init.ItemInit;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.items.CapabilityItemHandler;

public class PacketVendorWorker implements IMessage {

	private boolean messageValid;

	private BlockPos pos;
	private int type;

	public PacketVendorWorker() {
		this.messageValid = false;
	}

	public PacketVendorWorker(BlockPos pos, int type) {
		this.pos = pos;
		this.type = type;
		this.messageValid = true;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		try {
			this.pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
			this.type = buf.readInt();
		} catch (IndexOutOfBoundsException e) {
			return;
		}
		this.messageValid = true;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		if (!this.messageValid)
			return;
		buf.writeInt(pos.getX());
		buf.writeInt(pos.getY());
		buf.writeInt(pos.getZ());
		buf.writeInt(type);
	}

	public static class Handler implements IMessageHandler<PacketVendorWorker, IMessage> {

		@Override
		public IMessage onMessage(PacketVendorWorker message, MessageContext ctx) {
			if (!message.messageValid && ctx.side != Side.SERVER)
				return null;
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler)
					.addScheduledTask(() -> processMessage(message, ctx));
			return null;
		}

		void processMessage(PacketVendorWorker message, MessageContext ctx) {
			int type = message.type;
			BlockPos pos = message.pos;
			EntityPlayerMP player = ctx.getServerHandler().player;
			World world = player.getEntityWorld();
			switch (type) {
			case 0:
				world.spawnEntity(modifiedEntityItem(1, world, pos));
				break;
			case 1:
				world.spawnEntity(modifiedEntityItem(1, world, pos));
				world.spawnEntity(modifiedEntityItem(1, world, pos));
				break;
			case 2:
				ItemStack base = new ItemStack(ItemInit.CASE);
				ItemStack content = new ItemStack(ItemInit.UNUSED_TICKET, 1, 0);
				if (base.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)) {
					for (int i=0;i<11;i++) {
						base.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).insertItem(i, content, false);
					}
				}
				EntityItem item = new EntityItem(world, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5,
					base);
				world.spawnEntity(item);
				break;
			case 3:
				world.spawnEntity(modifiedEntityItem(1, world, pos));
				break;
			default:
				return;
			}
		}
		
		EntityItem modifiedEntityItem(int amount, World world, BlockPos pos) {
			EntityItem item = new EntityItem(world, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5,
					new ItemStack(ItemInit.UNUSED_TICKET));
			return item;
		}
	}
}