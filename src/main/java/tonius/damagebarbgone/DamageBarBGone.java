package tonius.damagebarbgone;

import java.util.Arrays;
import java.util.Map;

import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodNode;

import com.google.common.eventbus.EventBus;

import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.SortingIndex;

@SortingIndex(1001)
public class DamageBarBGone implements IFMLLoadingPlugin {

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

	public static class ModContainer extends DummyModContainer {

		public ModContainer() {
			super(new ModMetadata());
			ModMetadata md = getMetadata();
			md.autogenerated = true;
			md.authorList = Arrays.asList("tonius11");
			md.modId = md.name = md.description = "DamageBarBGone";
		}

		@Override
		public boolean registerBus(EventBus bus, LoadController controller) {
			bus.register(this);
			return true;
		}

	}

	public static class ASMTransformer implements IClassTransformer {

		@Override
		public byte[] transform(String name, String transformedName,
				byte[] bytes) {
			if (bytes == null
					|| !transformedName
							.equals("net.minecraft.client.renderer.entity.RenderItem")) {
				return bytes;
			}

			ClassReader cr = new ClassReader(bytes);
			ClassNode cn = new ClassNode(Opcodes.ASM5);
			cr.accept(cn, 0);

			for (MethodNode mn : cn.methods) {
				if (mn.name.equals("func_94148_a")
						|| mn.name.equals("func_77021_b")
						|| mn.name.equals("renderItemOverlayIntoGUI")) {
					mn.localVariables = null;
					mn.instructions.clear();
					mn.instructions.add(new InsnNode(Opcodes.RETURN));
				}
			}

			ClassWriter cw = new ClassWriter(0);
			cn.accept(cw);
			bytes = cw.toByteArray();

			return bytes;
		}
	}

}
