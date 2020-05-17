package chaos.mod.tileentity;

import java.util.ArrayList;
import java.util.List;

import chaos.mod.util.handlers.PacketHandler;
import chaos.mod.util.network.PacketMarkData;
import chaos.mod.util.utils.UtilMarkData;
import chaos.mod.util.utils.UtilMinecraftDate;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class TileEntityMarkMachine extends TileEntityBase {
	public List<UtilMarkData> markDataList = new ArrayList<UtilMarkData>();

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.markDataList = new ArrayList<UtilMarkData>();
		this.markDataList.clear();
		NBTTagList tags = (NBTTagList) compound.getTag("marks");
		for (int i = 0; i < tags.tagCount(); i++) {
			NBTTagCompound compound2 = tags.getCompoundTagAt(i);
			UtilMarkData mkd = new UtilMarkData();
			mkd.readFromNBTTag(compound2);
			this.markDataList.add(mkd);
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		NBTTagList tags = new NBTTagList();
		for (int i = 0; i < this.markDataList.size(); i++) {
			NBTTagCompound compound2 = new NBTTagCompound();
			((UtilMarkData) this.markDataList.get(i)).writeToNBTTag(compound2);
			tags.appendTag((NBTBase) compound2);
		}
		compound.setTag("marks", (NBTBase) tags);
		return super.writeToNBT(compound);
	}

	public void setMarkDataList(List<UtilMarkData> list) {
		this.markDataList.clear();
		for (int i = 0; i < list.size(); i++)
			this.markDataList.add(list.get(i));
		if (!this.world.isRemote)
			PacketHandler.INSTANCE.sendToAll(new PacketMarkData(this.markDataList, pos.getX(), pos.getY(), pos.getZ()));
	}

	public List<UtilMarkData> getMarkDataList() {
		return this.markDataList;
	}

	public UtilMarkData[] getStringIndex() {
		UtilMinecraftDate md = new UtilMinecraftDate(this.world.getWorldTime());
		UtilMarkData[] result = new UtilMarkData[2];
		int resultIndex = 0;
		for (int i = 0; i < this.markDataList.size(); i++) {
			UtilMarkData mkd = this.markDataList.get(i);
			if (mkd.hours >= md.getHours())
				if (mkd.hours > md.getHours() || (mkd.minutes >= md.getMinutes() && mkd.hours >= md.getHours())) {
					result[resultIndex] = mkd;
					resultIndex++;
				}
			if (resultIndex >= 2)
				break;
		}
		return result;
	}
}
