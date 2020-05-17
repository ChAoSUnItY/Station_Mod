package chaos.mod.init;

import java.util.ArrayList;
import java.util.List;

import chaos.mod.objects.item.ItemCase;
import chaos.mod.objects.item.ItemClock;
import chaos.mod.objects.item.ItemLanSetter;
import chaos.mod.objects.item.ItemTicket;
import chaos.mod.objects.item.ItemWrench;
import net.minecraft.item.Item;

public class ItemInit {
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	public static final Item UNUSED_TICKET = new ItemTicket("unused_ticket", 0);
	
	public static final Item USED_TICKET = new ItemTicket("used_ticket", 1);
	
	public static final Item IC_CARD = new ItemTicket("ic_card", 2);
	
	public static final Item CASE = new ItemCase("case");
	
	public static final Item WRENCH = new ItemWrench("torque_wrench");
	
	public static final Item LAN_SETTER = new ItemLanSetter("lan_setter");
	
	public static final Item DIGITAL_CLOCK = new ItemClock("digital_clock", 0);
	
	public static final Item ANALOG_CLOCK = new ItemClock("analog_clock", 1);
}
