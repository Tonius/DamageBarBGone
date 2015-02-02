package tonius.damagebarbgone;

import java.util.Arrays;

import com.google.common.eventbus.EventBus;

import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;

public class ModContainer extends DummyModContainer {

	public ModContainer() {
		super(new ModMetadata());
		ModMetadata md = getMetadata();
		md.autogenerated = true;
		md.authorList = Arrays.asList("tonius11");
		md.modId = md.name = md.description = "DamageBarBGone";
		md.version = "000";
	}

	@Override
	public boolean registerBus(EventBus bus, LoadController controller) {
		bus.register(this);
		return true;
	}

}
