/**
 * 
 */
package com.kronos.core.res;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 
 */
public class VariableSerializer {

	public static <T> void saveVal(T obj, String field, ResourceKey rk) {

		try {
			Field f = obj.getClass().getDeclaredField(field);
			f.setAccessible(true);

			f.set(obj, ResourceField.serializableField(rk.name, f.get(obj)));
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static <T> void saveAll(T obj, ResourceKey rk) {

		Field[] fields = obj.getClass().getDeclaredFields();

		// Loop through each field and filter out static and transient fields
		for (Field field : fields) {
			int modifiers = field.getModifiers();
			if (!Modifier.isStatic(modifiers) && !Modifier.isTransient(modifiers)) {
				try {
					field.setAccessible(true);
					field.set(obj, ResourceField.serializableField(rk.name + "_" + field.getName(), field.get(obj)));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
