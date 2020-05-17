package chaos.mod.util.handlers;

import net.minecraft.util.IStringSerializable;

public class EnumHandler {
	public static enum TicketTypes implements IStringSerializable {
		UNUSED("unused", 0), USED("used", 1), IC("ic_card", 2);

		private String name;
		private int meta;

		private TicketTypes(String name, int meta) {
			this.name = name;
			this.meta = meta;
		}

		@Override
		public String getName() {
			return this.name;
		}

		public int getMeta() {
			return this.meta;
		}

		@Override
		public String toString() {
			return getName();
		}
	}
	
	public static enum ClockTypes implements IStringSerializable {
		DIGITAL("digital", 0), ANALOG("analog", 1);

		private String name;
		private int meta;

		private ClockTypes(String name, int meta) {
			this.name = name;
			this.meta = meta;
		}

		@Override
		public String getName() {
			return this.name;
		}

		public int getMeta() {
			return this.meta;
		}

		@Override
		public String toString() {
			return getName();
		}
	}
	
	public static enum PillarType implements IStringSerializable {
		WHITE("white", 0), BLACK("black", 1);

		private static final PillarType[] META_LOOKUP = new PillarType[values().length];
		private String name;
		private int meta;

		private PillarType(String name, int meta) {
			this.name = name;
			this.meta = meta;
		}

		@Override
		public String getName() {
			return this.name;
		}

		public int getMeta() {
			return this.meta;
		}

		@Override
		public String toString() {
			return getName();
		}

		public static PillarType byMetadata(int meta) {
			return META_LOOKUP[meta];
		}

		static {
			for (PillarType pillarType : values()) {
				META_LOOKUP[pillarType.getMeta()] = pillarType;
			}
		}
	}
}
