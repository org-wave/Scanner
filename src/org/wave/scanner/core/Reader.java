package org.wave.scanner.core;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.Test.None;
import org.wave.scanner.enums.ErrorEnum;
import org.wave.scanner.enums.FileEnum;
import org.wave.utils.reflection.ReflectionUtil;


public class Reader {

	private static final String EXTENSION = ".java";

	public List<Method> read(File file) {
		List<Method> methods = new ArrayList<Method>();

		String className = this.getClassName(file);
		try {
			Class<?> klass = Class.forName(className);

			for (Method method : klass.getMethods()) {
				if (ReflectionUtil.isAnnotated(method, Test.class)) {
					boolean isDefaultExpected = method.getAnnotation(Test.class).expected().equals(None.class);
					if (isDefaultExpected) {
						methods.add(method);
					}
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(ErrorEnum.EMPTY_FILE.getMessage());
		}

		return methods;
	}
	private String getClassName(File file) {
		String className = file.getPath();
		className = className.replace(FileEnum.TEST_FOLDER.getValue().concat(File.separator), "");
		className = className.replace(EXTENSION, "");
		className = className.replace(File.separator, ".");

		return className;
	}

}
