package com.jc.annotations;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import com.jc.annotations.atunit.ClassNameFinder;
import com.jc.io.util.BinaryFile;
import com.jc.io.util.ProcessFiles;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.annotation.Annotation;


/**
 * 修改class文件，将com.jc.annotations.atunit所注解的方法都删掉，但不懂源码文件
 * 
 * 确定要删除，运行需加参数 -r
 * @author jevoncode
 *
 */
public class AtUnitRemover implements ProcessFiles.Strategy {
	private static boolean remove = false;

	public static void main(String[] args) throws Exception {
		if (args.length > 0 && args[0].equals("-r")) {
			remove = true;
			String[] nargs = new String[args.length - 1];
			System.arraycopy(args, 1, nargs, 0, nargs.length);
			args = nargs;
		}
		new ProcessFiles(new AtUnitRemover(), "class").start(args);
	}

	public void process(File cFile) {
		boolean modified = false;
		try {
			String cName = ClassNameFinder.thisClass(BinaryFile.read(cFile));
			if (!cName.contains("."))
				return; // Ignore unpackaged classes
			ClassPool cPool = ClassPool.getDefault();
			CtClass ctClass = cPool.get(cName);
			for (CtMethod method : ctClass.getDeclaredMethods()) {
				MethodInfo mi = method.getMethodInfo();
				AnnotationsAttribute attr = (AnnotationsAttribute) mi.getAttribute(AnnotationsAttribute.visibleTag);
				if (attr == null)
					continue;
				for (Annotation ann : attr.getAnnotations()) {
					if (ann.getTypeName().startsWith("com.jc.annotations.atunit")) {
						System.out.println(ctClass.getName() + " Method: " + mi.getName() + " " + ann);
						if (remove) {
							ctClass.removeMethod(method);
							modified = true;
						}
					}
				}
			}
			// Fields are not removed in this version (see text).
			if (modified)
				ctClass.toBytecode(new DataOutputStream(new FileOutputStream(cFile)));
			ctClass.detach();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}