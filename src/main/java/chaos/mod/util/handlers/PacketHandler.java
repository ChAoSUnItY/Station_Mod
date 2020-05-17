package chaos.mod.util.handlers;

import chaos.mod.util.network.PacketMarkData;
import chaos.mod.util.network.PacketVendorWorker;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {
	public static SimpleNetworkWrapper INSTANCE;
	
	public static int ID = 0;
	
	public static int nextID() {
		return ID++;
	}
	
	public static void registerMessages(String channelName) {
		INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(channelName);
		
		INSTANCE.registerMessage(PacketVendorWorker.Handler.class, PacketVendorWorker.class, nextID(), Side.SERVER);
		
		INSTANCE.registerMessage(PacketMarkData.Handler.class, PacketMarkData.class, nextID(), Side.SERVER);
	}
}
