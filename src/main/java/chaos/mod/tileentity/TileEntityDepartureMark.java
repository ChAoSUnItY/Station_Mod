package chaos.mod.tileentity;

import chaos.mod.init.BlockInit;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityDepartureMark extends TileEntityBase {
	private boolean parentRegistered;

	private int parentX;

	private int parentY;

	private int parentZ;

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.parentX = compound.getInteger("parentX");
		this.parentY = compound.getInteger("parentY");
		this.parentZ = compound.getInteger("parentZ");
		this.parentRegistered = compound.getBoolean("parReg");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setInteger("parentX", this.parentX);
		compound.setInteger("parentY", this.parentY);
		compound.setInteger("parentZ", this.parentZ);
		compound.setBoolean("parReg", this.parentRegistered);
		return super.writeToNBT(compound);
	}

	public int getParentX() {
		return this.parentX;
	}

	public int getParentY() {
		return this.parentY;
	}

	public int getParentZ() {
		return this.parentZ;
	}

	public boolean isRegistered() {
		return this.parentRegistered;
	}

	public boolean setParent(int x, int y, int z) {
		if (world.getBlockState(pos).getBlock() == BlockInit.DEPARTURE_MARK) {
			this.parentX = x;
			this.parentY = y;
			this.parentZ = z;
			this.parentRegistered = true;
			return true;
		}
		return false;
	}
}
