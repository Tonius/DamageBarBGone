package tonius.damagebarbgone;

import java.util.Map;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

public class LoadingPlugin implements IFMLLoadingPlugin {

	@Override
	public String getModContainerClass() {
		return ModContainer.class.getName();
	}

	@Override
	public String[] getASMTransformerClass() {
		return new String[] { ASMTransformer.class.getName() };
	}

	@Override
	public String getAccessTransformerClass() {
		return null;
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {
	}

}
