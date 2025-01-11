/**
 * 
 */
package com.shaderbuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.kronos.core.res.ResourceKey;

/**
 * 
 */
public class ShaderBuilder {
	ResourceKey loc;

	ArrayList<String> shader;

	HashMap<String, VType> vars = new HashMap<>();

	enum SType {
		core, es;
	}

	enum VType {
		Int, Float, vec2, vec3, vec4, mat4;
	}

	enum Mod {
		uniform, in, out;
	}

	int version;
	SType t;

	public static String V4I2 = "vec4 <?> = vec4(<?>,<?>);";

	public static String VSwizz = "<?> <?> = <?>.<?><?>;";

	public static String floor = "floor(<?>);";

	public static String IF = "if(<?>){";
	public static String gl_FragCoord = "gl_FragCoord";

	public ShaderBuilder(ResourceKey loc, int version, SType t) {
		super();
		this.loc = loc;
		this.version = version;
		this.t = t;
		shader = new ArrayList<>();
	}

	public void add(Object... strings) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < strings.length; i++) {
			sb.append(strings[i].toString());
		}
		shader.add(sb.toString());
	}

	public ShaderBuilder buildVersion() {
		add("#version ", version, " ", t);
		return this;
	}

	public ShaderBuilder uniform(VType v, String name) {
		add(Mod.uniform.name(), " ", vtype(v), " ", name, ";");
		addVar(name, v);
		return this;
	}

	public ShaderBuilder in(VType v, String name) {
		add(Mod.in.name(), " ", vtype(v), " ", name, ";");
		addVar(name, v);
		return this;
	}

	public ShaderBuilder out(VType v, String name) {
		add(Mod.out.name(), " ", vtype(v), " ", name, ";");
		addVar(name, v);
		return this;
	}

	public ShaderBuilder createInt(String name, int val) {
		add("int ", name, " = ", val, ";");
		addVar(name, VType.Int);
		return this;
	}

	public ShaderBuilder createFloat(String name, float val) {
		add("float ", name, " = ", val, ";");
		addVar(name, VType.Float);
		return this;
	}

	public ShaderBuilder createVec2(String name, float x, float y) {
		add("vec2 ", name, " = vec2(", x, ", ", y, ");");
		addVar(name, VType.vec2);
		return this;
	}

	public ShaderBuilder createVec3(String name, float x, float y, float z) {
		add("vec3 ", name, " = vec3(", x, ", ", y, ", ", z, ");");
		addVar(name, VType.vec3);
		return this;
	}

	public ShaderBuilder createVec4(String name, float x, float y, float z, float w) {
		add("vec4 ", name, " = vec4(", x, ", ", y, ", ", z, ", ", w, ");");
		addVar(name, VType.vec4);
		return this;
	}

	public ShaderBuilder mainStart() {
		add("void ", "main() {");
		newLine();

		return this;
	}

	public ShaderBuilder set(String des, String name) {
		if (des == "gl_Position") {
			add("gl_Position = ", name, ";");
			return this;
		}
		VType v = vars.get(des);
		if (v == null) {
			System.err.println("Variable Does not exist: " + des);
			return null;
		}
		if (verify(name, v)) {
			add(des, " = ", name, ";");
			return this;
		}
		return null;
	}

	public ShaderBuilder createVec4FromVec3(String vec4Name, String vec3Name, float w) {
		VType v = vars.get(vec3Name);
		if (v == null) {
			System.err.println("Variable does not exist: " + vec3Name);
			return null;
		}
		if (v == VType.vec3) {
			add("vec4 ", vec4Name, " = vec4(", vec3Name, ", ", w, ");");
			addVar(vec4Name, VType.vec4);
			return this;
		} else {
			System.err.println("Variable " + vec3Name + " is not of type vec3");
			return null;
		}
	}

	public ShaderBuilder addAssign(String des, String name) {
		VType v = vars.get(des);
		if (v == null) {
			System.err.println("Variable does not exist: " + des);
			return null;
		}
		if (verify(name, v)) {
			add(des, " += ", name, ";");
			return this;
		}
		return null;
	}

	public ShaderBuilder subAssign(String des, String name) {
		VType v = vars.get(des);
		if (v == null) {
			System.err.println("Variable does not exist: " + des);
			return null;
		}
		if (verify(name, v)) {
			add(des, " -= ", name, ";");
			return this;
		}
		return null;
	}

	public ShaderBuilder mulAssign(String des, String name) {
		VType v = vars.get(des);
		if (v == null) {
			System.err.println("Variable does not exist: " + des);
			return null;
		}
		if (verify(name, v)) {
			add(des, " *= ", name, ";");
			return this;
		}
		return null;
	}

	public ShaderBuilder divAssign(String des, String name) {
		VType v = vars.get(des);
		if (v == null) {
			System.err.println("Variable does not exist: " + des);
			return null;
		}
		if (verify(name, v)) {
			add(des, " /= ", name, ";");
			return this;
		}
		return null;
	}

	public ShaderBuilder mainEnd() {
		add("}");

		return this;
	}

	public ShaderBuilder newLine() {
		add("\n");
		return this;
	}

	public String vtype(VType v) {
		switch (v) {
		case Int:
			return "int";

		case Float:
			return "float";

		default:
			return v.name();
		}
	}

	public boolean verify(String name, VType v) {
		VType va = vars.get(name);
		if (va == null) {
			System.err.println("ERR No Value Mapped to: " + name);
			return false;
		}
		if (va != v) {
			System.err.println("ERR input variable does not match wanted: " + v.name() + " but got: " + va.name());
			return false;
		}
		return true;
	}

	public void addVar(String name, VType v) {
		System.out.println("Created Varaible: " + name + " With type: " + v.name());
		this.vars.put(name, v);
	}

	public String build() {
		StringBuilder sb = new StringBuilder();
		for (Iterator iterator = shader.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			sb.append(string);
		}
		return sb.toString();
	}

	public static String replaceTemplate(String template, String... values) {
		StringBuilder result = new StringBuilder(template);

		for (String value : values) {
			int placeholderIndex = result.indexOf("<?>");
			if (placeholderIndex == -1) {
				break;
			}
			result.replace(placeholderIndex, placeholderIndex + 3, value);
		}
		return result.toString();
	}

}
