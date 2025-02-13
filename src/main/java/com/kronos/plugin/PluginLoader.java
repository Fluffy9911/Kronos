/**
 * 
 */
package com.kronos.plugin;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.jar.JarFile;

import org.apache.logging.log4j.Logger;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import com.kronos.Kronos;
import com.kronos.core.event.EngineListener;
import com.kronos.io.Config;
import com.kronos.plugin.info.AuthorInfo;

/**
 * 
 */
public class PluginLoader {
	File pfolder;
	Logger l = Kronos.debug.getLogger();
	Reflections reflections;
	ArrayList<PluginData> pd = new ArrayList<PluginData>();
	HashSet<Method> mm = new HashSet<>();
	public static URLClassLoader cu;
	ArrayList<AuthorInfo> authors = new ArrayList<>();
	HashMap<String, Class<?>> classMap = new HashMap<>();
	public boolean showloaded = true;

	HashMap<PluginData, List<Class<?>>> pluginClasses = new HashMap<>();

	public PluginLoader(File pfolder) {
		this.pfolder = pfolder;
		if (!this.pfolder.exists()) {
			this.pfolder.mkdirs();
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

			return !p.toFile().isDirectory() && p.toFile().getAbsolutePath().endsWith(".jar");
		}).forEach((pp) -> {
			l.debug("Found Plugin: {}", pp.toString());
			jf.add(pp.toFile());
		});
		l.debug("Found Plugins; {}", jf.toString());

		return jf;
	}

	private void loadToClassPath(ArrayList<File> files) throws Exception {
		ArrayList<URL> los = new ArrayList<>();
		for (Iterator iterator = files.iterator(); iterator.hasNext();) {
			File jarFile = (File) iterator.next();

			los.add(jarFile.toURI().toURL());
		}
		URLClassLoader ucl = new URLClassLoader(los.toArray(new URL[los.size()]));
		for (Iterator iterator = los.iterator(); iterator.hasNext();) {
			URL url = (URL) iterator.next();
			loadClassesAndMethods(new File(url.getFile()), ucl);
		}

		l.debug("Loaded: {} Plugins", files.size());
	}

	/**
	 * @param jarFile
	 * @throws Exception
	 * @throws MalformedURLException
	 * @throws ClassNotFoundException
	 * @throws SecurityException
	 */
	public void loadClassesAndMethods(File jarFile, URLClassLoader ucl)
			throws Exception, MalformedURLException, ClassNotFoundException, SecurityException {
		l.debug("Loading classes of: {}", jarFile.getName());
		ArrayList<String> names = (ArrayList<String>) getAllClassNamesFromJar(jarFile.getAbsolutePath());

		for (Iterator iterator2 = names.iterator(); iterator2.hasNext();) {
			String string = (String) iterator2.next();

			try {
				Class<?> c = ucl.loadClass(string);
				if (classMap.get(string) != null) {
					c = classMap.get(string);
				} else {
					c = classMap.put(string, c);
				}
				if (c == null) {
					c = ucl.loadClass(string);
				}
				ArrayList<Method> ms = new ArrayList<Method>(Arrays.asList(c.getMethods()));

				ms.removeIf((m) -> {
					return !isMethodAnnotated(m, PluginEntry.class) && !(m.getReturnType().equals(PluginData.class)
							&& m.isAccessible() && m.getParameterCount() == 0) && !mm.contains(m);
				});
				this.mm.addAll(ms);
				if (showloaded)
					l.debug("Loaded Class: {}", string);
				// executeAll(gatherMethods(c));
			} catch (NoClassDefFoundError e) {
				l.debug("Skipping Class {}, unable to load", string);
				continue;
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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

	private List<PluginData> executeAll(HashSet<Method> mms)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		ArrayList<PluginData> pd = new ArrayList<>();

		for (Iterator iterator = mms.iterator(); iterator.hasNext();) {
			Method m = (Method) iterator.next();
			if (m.getParameterCount() == 0) {
				pd.add((PluginData) m.invoke(null));
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
			authors.add(p.plugin.getAuthorInfo());
			l.debug("Plugin: {}", p.plugin.getAuthorInfo().toString());
			readPArgs(p);
		}

		checkDependencies();
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

	/**
	 * @return
	 */
	public File getFolder() {
		// TODO Auto-generated method stub
		return pfolder;
	}

	public boolean hasDependencies(AuthorInfo au) {
		if (au == null || au.getDependencies() == null) {
			// No dependencies or invalid AuthorInfo
			return false;
		}

		String[] deps = au.getDependencies();

		for (String dependency : deps) {
			boolean dependencyFound = false;

			for (AuthorInfo author : authors) {
				if (dependency.equals(author.getUid())) {
					// Dependency found
					dependencyFound = true;
					break;
				}
			}

			if (!dependencyFound) {
				// Dependency not found
				return false;
			}
		}

		// All dependencies found
		return true;
	}

	public void checkDependencies() {
		for (AuthorInfo au : authors) {
			if (!hasDependencies(au)) {
				l.warn("Plugin: {} has missing dependencies: {}", au.getUid(), au.getDependencies());
				if (au.isSoftDeps()) {
					l.warn("Plugin: {} has soft dependencies enabled continueing execution: {}", au.getUid(),
							au.getDependencies());
				} else {

					Kronos.shutdown();

				}
			}
		}
	}

	public void readPArgs(PluginData p) {
		String[] args = p.getPluginargs();

		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-pagent")) {
				PluginAgentLoader.attach(new File(args[i + 1]));
			}

		}

	}

}
