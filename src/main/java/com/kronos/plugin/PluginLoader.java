/**
 * 
 */
package com.kronos.plugin;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.jar.JarFile;

import org.apache.logging.log4j.Logger;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import com.kronos.Kronos;
import com.kronos.core.event.EngineListener;
import com.kronos.io.Config;

/**
 * 
 */
public class PluginLoader {
	File pfolder;
	Logger l = Kronos.debug.getLogger();
	Reflections reflections;
	ArrayList<PluginData> pd = new ArrayList<PluginData>();
	ArrayList<Method> mm = new ArrayList<>();
	public static URLClassLoader cu;

	public PluginLoader(File pfolder) {
		this.pfolder = pfolder;
		List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
		classLoadersList.add(ClasspathHelper.contextClassLoader());
		classLoadersList.add(ClasspathHelper.staticClassLoader());
		reflections = new Reflections(new ConfigurationBuilder().setUrls(ClasspathHelper.forClassLoader())
				.addClassLoaders(this.getClass().getClassLoader())
				.setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
				.setScanners(new MethodAnnotationsScanner(), new SubTypesScanner(false)));
		Set<Class<?>> c = reflections.getSubTypesOf(Object.class);

		for (Class<?> cl : c) {

			l.debug("Reflected Class: {}", cl.getName());

		}
	}

	public ArrayList<PluginData> loadPlugins() throws Exception {
		l.debug("Starting Plugin Search");
		loadToClassPath((ArrayList<File>) loadAll());

		return (ArrayList<PluginData>) executeAll(mm);
	}

	private List<File> loadAll() throws IOException {
		ArrayList<File> jf = new ArrayList<>();

		Files.walk(pfolder.toPath()).filter((p) -> {
			l.debug("File: {}", p.toFile().getAbsolutePath());
			return !p.toFile().isDirectory() && p.toFile().getAbsolutePath().endsWith(".jar");
		}).forEach((pp) -> {
			l.debug("Found Plugin: {}", pp.toString());
			jf.add(pp.toFile());
		});
		return jf;
	}

	private void loadToClassPath(ArrayList<File> files) throws Exception {

		for (Iterator iterator = files.iterator(); iterator.hasNext();) {
			File jarFile = (File) iterator.next();

			l.debug("Loading classes of: {}", jarFile.getName());
			ArrayList<String> names = (ArrayList<String>) getAllClassNamesFromJar(jarFile.getAbsolutePath());

			URLClassLoader ucl = new URLClassLoader(new URL[] { jarFile.toURI().toURL() },
					this.getClass().getClassLoader());

			for (Iterator iterator2 = names.iterator(); iterator2.hasNext();) {
				String string = (String) iterator2.next();
				Class<?> c = ucl.loadClass(string);

				ArrayList<Method> ms = new ArrayList<Method>(Arrays.asList(c.getMethods()));
				ms.removeIf((m) -> {
					return !isMethodAnnotated(m, PluginEntry.class) && !(m.getReturnType().equals(PluginData.class)
							&& m.isAccessible() && m.getParameterCount() == 0);
				});
				this.mm.addAll(ms);
				l.debug("Loaded Class: {}", string);
				// executeAll(gatherMethods(c));
			}
		}

	}

	public static boolean isMethodAnnotated(Method method, Class<?> annotationName) {
		Annotation[] annotations = method.getDeclaredAnnotations();

		for (Annotation annotation : annotations) {
			if (annotation.annotationType().equals(annotationName)) {
				return true;
			}
		}

		return false;
	}

	private List<Method> gatherMethods(Class<?> inputtedClass) {
		List<Method> result = new ArrayList<>();
		String packageName = inputtedClass.getPackage().getName();

		// Create a URLClassLoader for the package of the inputted class
		URLClassLoader classLoader = new URLClassLoader(
				ClasspathHelper.forPackage(packageName).toArray(new java.net.URL[0]));

		// Configure Reflections to scan the specified package
		Reflections r = new Reflections(new ConfigurationBuilder().setUrls(ClasspathHelper.forClassLoader(classLoader))
				.setScanners(new MethodAnnotationsScanner()));

		// Configure Reflections to scan all loaded classes

		// Find methods with the specified annotation

		Set<Method> annotatedMethods = r.getMethodsAnnotatedWith(PluginEntry.class);
		l.debug("Methods: {}", annotatedMethods.size());
		for (Method method : annotatedMethods) {
			if (java.lang.reflect.Modifier.isStatic(method.getModifiers())) {
				result.add(method);
			}
		}

		return result;
	}

	private List<PluginData> executeAll(List<Method> mms)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		ArrayList<PluginData> pd = new ArrayList<>();
		System.out.println(System.getProperty("java.class.path"));
		for (Iterator iterator = mms.iterator(); iterator.hasNext();) {
			Method m = (Method) iterator.next();
			if (m.getParameterCount() == 0) {
				pd.add((PluginData) m.invoke(null, null));
			} else {
				l.debug("Unable to execute method: {}, in class: {}, due to it not being a static method that has no paramaters and returns a valid PluginData instance",
						m.getName(), m.getClass().getSimpleName());
			}
		}

		for (Iterator iterator = pd.iterator(); iterator.hasNext();) {
			PluginData p = (PluginData) iterator.next();
			p.plugin.init();

			Kronos.registerListener(new EngineListener() {

				@Override
				public void configChange(Config c) {
					// TODO Auto-generated method stub

				}

				@Override
				public void engineStart() {
					// TODO Auto-generated method stub

				}

				@Override
				public void engineEnd() {
					p.plugin.end();

				}

			});
			Kronos.graphixs.add(p.plugin.getPluginResource());
		}

		return pd;
	}

	private static List<String> getAllClassNamesFromJar(String jarFilePath) throws Exception {
		List<String> classNames = new ArrayList<>();

		try (JarFile jarFile = new JarFile(jarFilePath)) {
			jarFile.stream().filter(entry -> entry.getName().endsWith(".class")).forEach(entry -> {

				String className = entry.getName().replace('/', '.').replace(".class", "");
				classNames.add(className);
			});
		}

		return classNames;
	}
}