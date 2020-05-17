package chaos.mod.util.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.minecraft.nbt.NBTTagCompound;

public class UtilMarkData {
	public static final String REGEX = "^<([0-9A-F]*)>\\s*(.*)";

	public static final Pattern p = Pattern.compile("^<([0-9A-F]*)>\\s*(.*)");

	public int hours;

	public int minutes;

	public String dest;

	public String type;

	public int typeColor;

	public int destColor;

	public int timeColor;

	public UtilMarkData() {
	}

	public UtilMarkData(int h, int m, String d, String t) {
		this.hours = h;
		this.minutes = m;
		this.timeColor = 65280;
		Matcher mt = p.matcher(d);
		if (mt.find())
			if (mt.groupCount() == 2) {
				String colorCode = mt.group(1);
				this.dest = mt.group(2);
				this.destColor = Integer.parseInt(colorCode, 16);
			}
		if (this.dest == null) {
			this.dest = d;
			this.destColor = 65280;
		}
		mt = p.matcher(t);
		if (mt.find())
			if (mt.groupCount() == 2) {
				String colorCode = mt.group(1);
				this.type = mt.group(2);
				this.typeColor = Integer.parseInt(colorCode, 16);
			}
		if (this.type == null) {
			this.type = t;
			this.typeColor = this.timeColor = 65280;
		}
	}

	public String toString() {
		return getTimeString() + " " + this.type + " " + this.dest;
	}

	public String getTimeString() {
		return String.format("%1$02d:%2$02d",
				new Object[] { Integer.valueOf(this.hours), Integer.valueOf(this.minutes) });
	}

	public void writeToNBTTag(NBTTagCompound tag) {
		tag.setInteger("hours", this.hours);
		tag.setInteger("min", this.minutes);
		tag.setString("trainType", this.type);
		tag.setString("dest", this.dest);
		tag.setInteger("typeColor", this.typeColor);
		tag.setInteger("destColor", this.destColor);
		tag.setInteger("timeColor", this.timeColor);
	}

	public void readFromNBTTag(NBTTagCompound tag) {
		this.hours = tag.getInteger("hours");
		this.minutes = tag.getInteger("min");
		this.type = tag.getString("trainType");
		this.dest = tag.getString("dest");
		this.typeColor = tag.getInteger("typeColor");
		this.destColor = tag.getInteger("destColor");
		this.timeColor = tag.getInteger("timeColor");
	}
}
