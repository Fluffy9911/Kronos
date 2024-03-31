/**
 * 
 */
package com.kronos.graphixs.g2d.pixelcanvas;

import java.io.File;

import com.kronos.io.streamedlists.StreamDynamicList2D;

/**
 * 
 */
public class StreamedCanvas extends Canvas2D {
	StreamDynamicList2D<Integer> list = new StreamDynamicList2D<Integer>(new File("kronos\\ccnvs"));

	/**
	 * @param width
	 * @param height
	 */
	public StreamedCanvas(int width, int height) {
		super(width, height);

	}

}
