/**
 * 
 */
package com.kronos.graphixs.texture;

import java.util.Map;

/**
 * 
 */
public interface TextureInfo {

	public int level();

	public int dimension();

	public int format();

	public int formatInternal();

	public int type();

	Map<Integer, Integer> params();

}
