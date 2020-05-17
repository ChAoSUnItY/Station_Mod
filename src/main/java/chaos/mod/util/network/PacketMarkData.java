package chaos.mod.util.network;

import java.util.ArrayList;
import java.util.List;

import chaos.mod.tileentity.TileEntityMarkMachine;
import chaos.mod.util.utils.UtilMarkData;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketMarkData implements IMessage {
	private List<UtilMarkData> markList;

	public int posX;

	public int posY;

	public int posZ;

	public PacketMarkData() {
	}

	public PacketMarkData(List<UtilMarkData> list, int x, int y, int z) {
		this.markList = list;
		this.posX = x;
		this.posY = y;
		this.posZ = z;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		try {
			this.posX = buf.readInt();
			this.posY = buf.readInt();
			this.posZ = buf.readInt();
			int size = buf.readInt();
			this.markList = new ArrayList<UtilMarkData>(size);
			this.markList.clear();
			for (int i = 0; i < size; i++) {
				int hours = buf.readInt();
				int minutes = buf.readInt();
				int typeLength = buf.readInt();
				byte[] typeChars = new byte[typeLength];
				buf.readBytes(typeChars);
				int destLength = buf.readInt();
				byte[] destChars = new byte[destLength];
				buf.readBytes(destChars);
				UtilMarkData md = new UtilMarkData();
				md.hours = hours;
				md.minutes = minutes;
				md.dest = new String(destChars, "UTF-8");
				md.type = new String(typeChars, "UTF-8");
				md.destColor = buf.readInt();
				md.timeColor = buf.readInt();
				md.typeColor = buf.readInt();
				this.markList.add(md);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		try {
			buf.writeInt(this.posX);
			buf.writeInt(this.posY);
			buf.writeInt(this.posZ);
			buf.writeInt(this.markList.size());
			for (int i = 0; i < this.markList.size(); i++) {
				UtilMarkData mkd = this.markList.get(i);
				buf.writeInt(mkd.hours);
				buf.writeInt(mkd.minutes);
				byte[] typeBytes = mkd.type.getBytes("UTF-8");
				buf.writeInt(typeBytes.length);
				buf.writeBytes(typeBytes);
				byte[] destBytes = mkd.dest.getBytes("UTF-8");
				buf.writeInt(destBytes.length);
				buf.writeBytes(destBytes);
				buf.writeInt(mkd.destColor);
				buf.writeInt(mkd.timeColor);
				buf.writeInt(mkd.typeColor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<UtilMarkData> getLists() {
		return this.markList;
	}

	public static class Handler implements IMessageHandler<PacketMarkData, IMessage> {

		@Override
		public IMessage onMessage(PacketMarkData message, MessageContext ctx) {
			int x = message.posX;
			int y = message.posY;
			int z = message.posZ;
			World world = (ctx.getServerHandler()).player.world;
			TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
			if (te != null && te instanceof TileEntityMarkMachine)
				((TileEntityMarkMachine) te).setMarkDataList(message.getLists());
			return null;
		}
	}
}
