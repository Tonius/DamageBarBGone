package tonius.damagebarbgone;

import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodNode;

public class ASMTransformer implements IClassTransformer {

	@Override
	public byte[] transform(String name, String transformedName, byte[] bytes) {
		if (bytes == null || !transformedName.equals("net.minecraft.item.Item")) {
			return bytes;
		}

		ClassReader cr = new ClassReader(bytes);
		ClassNode cn = new ClassNode(Opcodes.ASM5);
		cr.accept(cn, 0);

		l: {
			MethodNode m = null;
			for (MethodNode n : cn.methods) {
				if (n.name.equals("showDurabilityBar")) {
					m = n;
					break;
				}
			}
			if (m == null) {
				break l;
			}

			m.localVariables = null;
			m.instructions.clear();
			m.instructions.add(new InsnNode(Opcodes.ICONST_0));
			m.instructions.add(new InsnNode(Opcodes.IRETURN));

			ClassWriter cw = new ClassWriter(0);
			cn.accept(cw);
			bytes = cw.toByteArray();
		}

		return bytes;
	}

}
