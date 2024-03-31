/**
 * 
 */
package com.kronos.graphixs.g2d.ui.components.panel;

import com.kronos.graphixs.g2d.Abstract2DGraphixs;
import com.kronos.graphixs.g2d.TextureBatch;
import com.kronos.graphixs.g2d.fonts.FontRenderer;
import com.kronos.graphixs.g2d.ui.BaseComponent;
import com.kronos.graphixs.g2d.ui.BasePosition;

/**
 * 
 */
public class ComponentPanel extends BaseComponent {
	boolean showOnHover = false, resizeable = false;

	/**
	 * @param bp
	 * @param cdren
	 * @param moveable
	 * @param hidden
	 * @param id
	 */
	public ComponentPanel(BasePosition bp, boolean moveable, boolean hidden, String id) {
		super(bp, true, moveable, hidden, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(TextureBatch batch, FontRenderer fr, Abstract2DGraphixs g) {

		super.render(batch, fr, g);

		forEachChild((c, ch) -> {
			c.render(batch, fr, g);
		});

	}

}
