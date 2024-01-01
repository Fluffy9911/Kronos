package com.kronos.io.shader;

import java.io.File;

import com.kronos.Kronos;
import com.kronos.graphixs.shaders.ShaderProgram;
import com.kronos.io.FileLoader;
import com.kronos.io.ResourceIdentifier;

public class ShaderID {
	String fragment_name;
	String vertex_name, programid;

	public ShaderID(String fragment_name, String vertex_name, String programid) {
		super();
		this.fragment_name = fragment_name;
		this.vertex_name = vertex_name;
		this.programid = programid;

	}

	public void load(FileLoader loader, ResourceIdentifier loc) {
		File frag = new File(loc.getBasePath() + "/shaders/" + fragment_name + ".frag");
		File vert = new File(loc.getBasePath() + "/shaders/" + vertex_name + ".vert");
		String fs = "";
		String vs = "";

		if (frag.exists()) {
			fs = loader.tryLoad("shaders/" + fragment_name + ".frag", loc);
		}
		if (vert.exists()) {
			vs = loader.tryLoad("shaders/" + vertex_name + ".vert", loc);
		}

		if (fs == "") {
			fs = Kronos.graphixs.getFs();
		}
		if (vs == "") {
			vs = Kronos.graphixs.getVs();
		}

		ShaderProgram sp = new ShaderProgram(vs, fs);
		Kronos.graphixs.createShader(programid, sp);

	}

}
