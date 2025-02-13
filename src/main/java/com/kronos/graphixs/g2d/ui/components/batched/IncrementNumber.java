package com.kronos.graphixs.g2d.ui.components.batched;

import java.awt.Color;

import org.joml.Vector2i;

import com.kronos.graphixs.color.Colors;
import com.kronos.graphixs.g2d.Abstract2DGraphixs;
import com.kronos.graphixs.g2d.TextureBatch;
import com.kronos.graphixs.g2d.fonts.FontRenderer;
import com.kronos.graphixs.g2d.ui.BaseComponent;
import com.kronos.graphixs.g2d.ui.BasePosition;
import com.kronos.graphixs.g2d.ui.components.clickables.BasicClickable;
import com.kronos.graphixs.texture.Texture;
import com.kronos.graphixs.texture.TextureBuilder;
import com.kronos.io.config.ConfigFile;

public class IncrementNumber extends BaseComponent {
	float incr = 1;
	float num = 1;

	BasicClickable bigger, smaller;

	public IncrementNumber(BasePosition bp, boolean cdren, boolean moveable, boolean hidden, String id) {
		super(bp, cdren, moveable, hidden, id);
		Vector2i c = center(bp.pos().getX(), bp.pos().getY(), bp.pos().getW(), bp.pos().getH());
		bigger = new BasicClickable(bp.translate(c.x() - 10, -11, 10, 10), false, false, hidden,
				id + "_increment_button") {
			@Override
			public void render(TextureBatch batch, FontRenderer fr, Abstract2DGraphixs g) {
				this.drawHere(batch, getBase());
			}

			@Override
			public void endClick() {

			}

			@Override
			public void click() {
				// TODO Auto-generated method stub
				num += incr;
			}

			@Override
			public Texture getHover() {
				// TODO Auto-generated method stub
				return TextureBuilder.buildTextureBordered(40, 40, 2, Colors.Blue, Colors.Navy);
			}

			@Override
			public Texture getClicked() {
				// TODO Auto-generated method stub
				return TextureBuilder.buildTextureBordered(40, 40, 2, Colors.Red, Colors.Salmon);
			}

			@Override
			public Texture getBase() {
				// TODO Auto-generated method stub
				return TextureBuilder.buildTextureBordered(40, 40, 2, Colors.Blue, Colors.Blue);
			}
		};
		smaller = new BasicClickable(bp.translate(c.x() - 10, 0, 10, 10), false, false, hidden,
				id + "_decrement_button") {

			@Override
			public void render(TextureBatch batch, FontRenderer fr, Abstract2DGraphixs g) {
				this.drawHere(batch, getBase());
			}

			@Override
			public void endClick() {

			}

			@Override
			public void click() {
				// TODO Auto-generated method stub
				num -= incr;
			}

			@Override
			public Texture getHover() {
				// TODO Auto-generated method stub
				return TextureBuilder.buildTextureBordered(40, 40, 2, Colors.Lime, Colors.Mint);
			}

			@Override
			public Texture getClicked() {
				// TODO Auto-generated method stub
				return TextureBuilder.buildTextureBordered(40, 40, 2, Colors.Red, Colors.Salmon);
			}

			@Override
			public Texture getBase() {
				// TODO Auto-generated method stub
				return TextureBuilder.buildTextureBordered(40, 40, 2, Colors.Lime, Colors.Lime);
			}
		};

		this.addChild("increment_B", bigger);
		this.addChild("decrement_B", smaller);

	}

	public void setNum(float val) {
		num = val;
	}

	@Override
	public void render(TextureBatch batch, FontRenderer fr, Abstract2DGraphixs g) {
		Vector2i c = center(bp.pos().getX(), bp.pos().getY(), bp.pos().getW(), bp.pos().getH());
		fr.renderText("Value: " + num, c.x(), c.y(), fr.useDefaultFont(), Color.BLACK, batch);
		bigger.getPosition().pos().setX(c.x() - 10);
		smaller.getPosition().pos().setX(c.x() - 10);
		bigger.getPosition().pos().setY(c.y() + 11);
		smaller.getPosition().pos().setY(c.y());
//		bigger.render(batch, fr, g);
//		smaller.render(batch, fr, g);
		super.render(batch, fr, g);
	}

	Vector2i center(float x, float y, float w, float h) {
		return new Vector2i((int) ((w / 2) + x), (int) ((h / 2) + y));

	}

//	@Override
//	public void readWriteDatas(Config c) {
//		System.out.println("Value:" + num);
//		num = c.readOrWriteInt("number_value", num);
//		incr = c.readOrWriteInt("increment_value", incr);
//		super.readWriteDatas(c);
//	}

	@Override
	public void write(ConfigFile file) {
		file.appendFloat("number_value", num);
		file.appendFloat("increment_value", incr);
		super.write(file);
	}

	@Override
	public void load(ConfigFile file) {
		num = file.readOrWriteFloat("number_value", num);
		incr = file.readOrWriteFloat("increment_value", incr);
		super.load(file);
	}

	public float getNum() {
		return num;
	}

}
