/**
 * 
 */
package com.kronos.graphixs.g2d.ui.handlers;

import java.awt.Rectangle;

import com.kronos.graphixs.g2d.fonts.FontRenderer;
import com.kronos.graphixs.g2d.ui.BasePosition;

/**
 * 
 */
public interface ITextHandler {

	public Rectangle size(FontRenderer fr);

	public BasePosition asPosition(FontRenderer fr);

	public String getText();

}
