/**
 * 
 */
package com.kronos.net.data.packet;

import java.util.HashMap;
import java.util.Map;

import com.kronos.Kronos;
import com.kronos.graphixs.scene.Scene3D;
import com.kronos.graphixs.shaders.BaseShader;

/**
 * 
 */
public class SceneData extends ConfigPacket {
	public enum types {
		MESH, CAM, LIGHTS, SHADER;
	}

	Scene3D s;

	public SceneData(Scene3D s) {
		super();
		this.s = s;
	}

	public void update() {

	}

	@Override
	public void initServerSide() {
		HashMap<String, BaseShader> ss = Kronos.graphixs.shaders;
		for (Map.Entry<String, BaseShader> entry : ss.entrySet()) {
			String key = entry.getKey();
			BaseShader val = entry.getValue();

		}

	}

	@Override
	public void initClientSide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void sentClientSide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void recieveClientSide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void sentServerSide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void recieveServerSide() {
		// TODO Auto-generated method stub

	}

	public String build(types t, String val) {
		return t.toString() + "=" + val;
	}

	public String readVal(String in) {
		if (in.startsWith(types.SHADER.toString())) {
			return in.substring(in.indexOf("="));
		}
		if (in.startsWith(types.MESH.toString())) {
			return in.substring(in.indexOf("="));
		}
		if (in.startsWith(types.CAM.toString())) {
			return in.substring(in.indexOf("="));
		}
		if (in.startsWith(types.LIGHTS.toString())) {
			return in.substring(in.indexOf("="));
		}
		return "null";
	}

	public types readType(String in) {
		if (in.startsWith(types.SHADER.toString())) {
			return types.SHADER;
		}
		if (in.startsWith(types.MESH.toString())) {
			return types.MESH;
		}
		if (in.startsWith(types.CAM.toString())) {
			return types.CAM;
		}
		if (in.startsWith(types.LIGHTS.toString())) {
			return types.LIGHTS;
		}
		return null;
	}
}
