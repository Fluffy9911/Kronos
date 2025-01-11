package com.kronos.io.config.configbuilder;

import java.util.LinkedHashMap;
import java.util.Map;

import com.kronos.io.config.configbuilder.node.BoolNode;
import com.kronos.io.config.configbuilder.node.CommentNode;
import com.kronos.io.config.configbuilder.node.FloatNode;
import com.kronos.io.config.configbuilder.node.IntegerNode;
import com.kronos.io.config.configbuilder.node.NewLineNode;
import com.kronos.io.config.configbuilder.node.SectionHeaderNode;
import com.kronos.io.config.configbuilder.node.StringNode;
import com.kronos.io.file.AdvancedStreamedFileImpl;

public class AdvancedBlockedConfig extends AdvancedStreamedFileImpl {

	private LinkedHashMap<String, Object> nodes;

	public AdvancedBlockedConfig(String filePath) {
		super(filePath);
		this.nodes = new LinkedHashMap<>();
	}

	public void out(String pref, String app) {
		for (Map.Entry<String, Object> entry : nodes.entrySet()) {
			String key = entry.getKey();
			Object val = entry.getValue();
			if (val instanceof Node) {
				Node n = (Node) val;
				this.appendString(n.to(pref, app));
				this.appendString("\n");
			}
			if (val instanceof Block) {
				Block b = (Block) val;
				this.appendString(b.blockOut(pref, app, 0));
				this.appendString("\n");
			}
		}
	}

	public void addConfig(String s, Node n) {
		nodes.put(s, n);
	}

	public void addBlock(String s, Block b) {
		nodes.put(s, b);
	}

	public <T extends Node> T getNode(String s) {
		return (T) nodes.get(s);
	}

	public <T extends Block> T getBlock(String s) {
		return (T) nodes.get(s);
	}

	public void addInt(String name, int val, int min, int max) {
		this.addConfig(name, new IntegerNode(name, val, min, max));
	}

	public void addIntArray(String name, int val[], int min, int max) {
		this.addConfig(name, new IntegerNode(name, val, min, max));
	}

	public void addIntSpaced(String name, int val, int min, int max, int spaces) {
		this.addBlock(name + "spaces", new NewLineNode(spaces));
		this.addConfig(name, new IntegerNode(name, val, min, max));
	}

	public void addIntArraySpaced(String name, int val[], int min, int max, int spaces) {
		this.addBlock(name + "spaces", new NewLineNode(spaces));
		this.addConfig(name, new IntegerNode(name, val, min, max));
	}

	public void addIntComment(String name, int val, int min, int max, String desc) {
		this.addBlock(desc, new CommentNode(desc));
		this.addConfig(name, new IntegerNode(name, val, min, max));
	}

	public void addIntArrayComment(String name, int val[], int min, int max, String desc) {
		this.addBlock(desc, new CommentNode(desc));
		this.addConfig(name, new IntegerNode(name, val, min, max));
	}

	public void addIntSpacedComment(String name, int val, int min, int max, int spaces, String desc) {
		this.addBlock(name + "spaces", new NewLineNode(spaces));
		this.addBlock(desc, new CommentNode(desc));
		this.addConfig(name, new IntegerNode(name, val, min, max));
	}

	public void addIntArraySpacedComment(String name, int val[], int min, int max, int spaces, String desc) {
		this.addBlock(name + "spaces", new NewLineNode(spaces));
		this.addBlock(desc, new CommentNode(desc));
		this.addConfig(name, new IntegerNode(name, val, min, max));
	}

	public void addFloat(String name, float val, float min, float max) {
		this.addConfig(name, new FloatNode(name, val, min, max));
	}

	public void addFloatArray(String name, float val[], float min, float max) {
		this.addConfig(name, new FloatNode(name, val, min, max));
	}

	public void addFloatSpaced(String name, float val, float min, float max, int spaces) {
		this.addBlock(name + "spaces", new NewLineNode(spaces));
		this.addConfig(name, new FloatNode(name, val, min, max));
	}

	public void addFloatArraySpaced(String name, float val[], float min, float max, int spaces) {
		this.addBlock(name + "spaces", new NewLineNode(spaces));
		this.addConfig(name, new FloatNode(name, val, min, max));
	}

	public void addFloatComment(String name, float val, float min, float max, String desc) {
		this.addBlock(desc, new CommentNode(desc));
		this.addConfig(name, new FloatNode(name, val, min, max));
	}

	public void addFloatArrayComment(String name, float val[], float min, float max, String desc) {
		this.addBlock(desc, new CommentNode(desc));
		this.addConfig(name, new FloatNode(name, val, min, max));
	}

	public void addFloatSpacedComment(String name, float val, float min, float max, int spaces, String desc) {
		this.addBlock(name + "spaces", new NewLineNode(spaces));
		this.addBlock(desc, new CommentNode(desc));
		this.addConfig(name, new FloatNode(name, val, min, max));
	}

	public void addFloatArraySpacedComment(String name, float val[], float min, float max, int spaces, String desc) {
		this.addBlock(name + "spaces", new NewLineNode(spaces));
		this.addBlock(desc, new CommentNode(desc));
		this.addConfig(name, new FloatNode(name, val, min, max));
	}

	public void addBoolean(String name, boolean val) {
		this.addConfig(name, new BoolNode(name, val));
	}

	public void addBooleanArray(String name, boolean val[]) {
		this.addConfig(name, new BoolNode(name, val));
	}

	public void addBooleanSpaced(String name, boolean val, int spaces) {
		this.addBlock(name + "spaces", new NewLineNode(spaces));
		this.addConfig(name, new BoolNode(name, val));
	}

	public void addBooleanArraySpaced(String name, boolean val[], int spaces) {
		this.addBlock(name + "spaces", new NewLineNode(spaces));
		this.addConfig(name, new BoolNode(name, val));
	}

	public void addBooleanComment(String name, boolean val, String desc) {
		this.addBlock(desc, new CommentNode(desc));
		this.addConfig(name, new BoolNode(name, val));
	}

	public void addBooleanArrayComment(String name, boolean val[], String desc) {
		this.addBlock(desc, new CommentNode(desc));
		this.addConfig(name, new BoolNode(name, val));
	}

	public void addBooleanSpacedComment(String name, boolean val, int spaces, String desc) {
		this.addBlock(name + "spaces", new NewLineNode(spaces));
		this.addBlock(desc, new CommentNode(desc));
		this.addConfig(name, new BoolNode(name, val));
	}

	public void addBooleanArraySpacedComment(String name, boolean val[], int spaces, String desc) {
		this.addBlock(name + "spaces", new NewLineNode(spaces));
		this.addBlock(desc, new CommentNode(desc));
		this.addConfig(name, new BoolNode(name, val));
	}

	public void addString(String name, String val) {
		this.addConfig(name, new StringNode(name, new String[] { val }));
	}

	public void addStringArray(String name, String val[]) {
		this.addConfig(name, new StringNode(name, val));
	}

	public void addStringSpaced(String name, String val, int spaces) {
		this.addBlock(name + "spaces", new NewLineNode(spaces));
		this.addConfig(name, new StringNode(name, new String[] { val }));
	}

	public void addStringArraySpaced(String name, String val[], int spaces) {
		this.addBlock(name + "spaces", new NewLineNode(spaces));
		this.addConfig(name, new StringNode(name, val));
	}

	public void addStringComment(String name, String val, String desc) {
		this.addBlock(desc, new CommentNode(desc));
		this.addConfig(name, new StringNode(name, new String[] { val }));
	}

	public void addStringArrayComment(String name, String val[], String desc) {
		this.addBlock(desc, new CommentNode(desc));
		this.addConfig(name, new StringNode(name, val));
	}

	public void addStringSpacedComment(String name, String val, int spaces, String desc) {
		this.addBlock(name + "spaces", new NewLineNode(spaces));
		this.addBlock(desc, new CommentNode(desc));
		this.addConfig(name, new StringNode(name, new String[] { val }));
	}

	public void addStringArraySpacedComment(String name, String val[], int spaces, String desc) {
		this.addBlock(name + "spaces", new NewLineNode(spaces));
		this.addBlock(desc, new CommentNode(desc));
		this.addConfig(name, new StringNode(name, val));
	}

	public void addSection(String section) {
		this.addBlock(section + "section", new SectionHeaderNode(section));
	}

	public void addSectionSpaced(String section, int spaces) {
		this.addBlock(section + "spaces", new NewLineNode(spaces));
		this.addBlock(section + "section", new SectionHeaderNode(section));
	}
}
