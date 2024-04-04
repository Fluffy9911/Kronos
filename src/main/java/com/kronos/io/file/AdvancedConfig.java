/**
 * 
 */
package com.kronos.io.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.kronos.io.config.ConfigHeader;
import com.kronos.io.config.IConfigKey;

/**
 * 
 */
public abstract class AdvancedConfig<T extends IConfigKey> extends AdvancedStreamedFileImpl {
	public ConfigHeader header = new ConfigHeader("basic_config", 1);
	public LinkedHashMap<String, T> configMap;

	public static <T extends IConfigKey> AdvancedConfig<T> parse(String fileName, AdvancedConfig<T> i) {
		AdvancedConfig<T> ec = i;
		LinkedHashMap<String, T> configMap = ec.configMap;

		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = br.readLine()) != null) {
				line = line.trim();
				T kk = i.createEmptyKey();
				kk.from(line);

				configMap.put(kk.name(), kk);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ec;
	}

	public abstract T createEmptyKey();

	/**
	 * @param filePath
	 */
	public AdvancedConfig(String filePath) {
		super(filePath);
	}

	public void generateConfigFile(EasyConfig easyConfig) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(easyConfig.asFile()))) {
			for (Map.Entry<String, T> entry : configMap.entrySet()) {
				String key = entry.getKey();
				IConfigKey value = entry.getValue();
				bw.write(value.to());
				bw.newLine();
				bw.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public abstract void appendIntAdv(String name, String description, int value, int min, int max);

	public abstract void appendFloatAdv(String name, String description, float value, float min, float max);

	public abstract void appendString(String name, String value);

	public abstract void appendStringArray(String name, String[] value);

	public abstract void appendComment(String c);

	public abstract void appendSection(String c);

	public abstract void appendInt(String name, int i, int min, int max);

	public abstract void appendIntArray(String name, int[] value, int min, int max);

	public abstract void appendFloat(String name, float i, float min, float max);

	public abstract void appendFloatArray(String name, float[] value, float min, float max);
}