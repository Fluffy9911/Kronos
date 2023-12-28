package com.kronos.graphixs.g2d.ui.components;

import com.kronos.Kronos;
import com.kronos.graphixs.color.Color;
import com.kronos.graphixs.display.Texture;
import com.kronos.graphixs.display.textures.TextureBuilder;
import com.kronos.graphixs.g2d.Graphixs2D;
import com.kronos.graphixs.g2d.TextureBatch;
import com.kronos.graphixs.g2d.fonts.FontRenderer;
import com.kronos.graphixs.g2d.ui.BaseComponent;
import com.kronos.graphixs.g2d.ui.BasePosition;
import com.kronos.graphixs.g2d.ui.transform.DragNDrop;
import com.kronos.graphixs.g2d.ui.transform.KeepInBox;
import com.kronos.io.Config;
import com.kronos.io.config.ConfigFile;

public class Panel extends BaseComponent implements Persistant {
	TextureBatch tb;
	Texture panel, text;
	Config config = Kronos.k_config;
	String te;
	KeepInBox box = new KeepInBox();
	DragNDrop dnd = new DragNDrop();
	ConfigFile c;

	public Panel(BasePosition bp, boolean cdren, boolean moveable) {
		super(bp, cdren, moveable, true);

	}

	public void createTexture(Graphixs2D g, Color b, Color bo, String t) {
		panel = TextureBuilder.buildTextureBordered((int) this.getPosition().pos().getW(),
				(int) this.getPosition().pos().getH(),
				config.readOrWriteInt("graphixs.2d.ui.panel_base_border_weight", 3), b, bo);
		tb = g.createBatch();
		te = t;
		hidden = false;
	}

	@Override
	public void update() {

		if (!hidden) {

			dnd.reposition(this.bp.getProvider(), bp, null);
			box.reposition(this.bp.getProvider(), bp, null);
			FontRenderer fr = FontRenderer.createDefault();

			tb.drawTexture((int) bp.pos().getX(), (int) bp.pos().getY(), (int) bp.pos().getW(), (int) bp.pos().getH(),
					panel);
			fr.renderTextConstraints(te, (int) bp.pos().getX(), (int) bp.pos().getY(), (int) bp.pos().getW(), 20,
					fr.useDefaultFont(), java.awt.Color.WHITE, tb);
			tb.render();
			tb.end();
		}
	}

	@Override
	public void load(ConfigFile file) {
		c = file;
		this.bp.pos().read(file.config, "position");
		this.bp.anchoredPos().read(file.config, "anchored_position");
	}

	@Override
	public String id() {
		// TODO Auto-generated method stub
		return "base_panel_id_" + te;
	}

	@Override
	public void write(ConfigFile file) {
		this.bp.pos().put(file.config, "position");
		this.bp.anchoredPos().put(file.config, "anchored_position");

	}

}
