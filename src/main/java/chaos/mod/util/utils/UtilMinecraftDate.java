package chaos.mod.util.utils;

import net.minecraft.world.World;

public class UtilMinecraftDate {
	public static final long ONE_DAY = 24000L;

	public static final long ONE_HOUR = 1000L;

	public static final double ONE_MINUTE = 16.666666666666668D;

	private long time;

	private int hours;

	private int minutes;

	public UtilMinecraftDate(long now) {
		this.time = now;
		init();
	}

	public UtilMinecraftDate(World now) {
		this.time = now.getWorldTime();
		init();
	}

	private void init() {
		long z = this.time % 24000L;
		this.hours = ((int) (z / 1000L) + 6) % 24;
		this.minutes = (int) ((z % 1000L) / 16.666666666666668D);
	}

	public String toString() {
		return String.format("%1$02d:%2$02d",
				new Object[] { Integer.valueOf(this.hours), Integer.valueOf(this.minutes) });
	}

	public int getHours() {
		return this.hours;
	}

	public int getMinutes() {
		return this.minutes;
	}
}
