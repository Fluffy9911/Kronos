package com.kronos.graphixs.geometry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL40;

import com.kronos.graphixs.FrameBuffer;
import com.kronos.graphixs.shaders.Shader;

public class Mesh {
	private int vaoID;
	private int vboID;
	private int eboID;
	private int vertCount;
	private int indexCount;
	private List<AttributeInfo> attributeList = new ArrayList<>();
	private float[] v;
	private int[] i;

	/**
	 * @param c
	 * @return
	 * @see java.util.List#addAll(java.util.Collection)
	 */
	public boolean addAll(Collection<? extends AttributeInfo> c) {
		return attributeList.addAll(c);
	}

	public Mesh(float[] vertices, int[] indices, int verts, int ind) {
		this.vertCount = verts;
		this.indexCount = ind;
		v = vertices;
		i = indices;
	}

	public Mesh build() {
		buildVAO(v, i);
		return this;
	}

	/**
	 * @param vertices
	 * @param indices
	 */
	private void buildVAO(float[] vertices, int[] indices) {
		vaoID = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vaoID);

		// Create and bind the VBO (Vertex Buffer Object) for vertices
		vboID = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertices, GL15.GL_STATIC_DRAW);

		// Create and bind the EBO (Element Buffer Object) for indices
		eboID = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, eboID);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indices, GL15.GL_STATIC_DRAW);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);

		for (Iterator iterator = attributeList.iterator(); iterator.hasNext();) {
			AttributeInfo ai = (AttributeInfo) iterator.next();

			GL40.glVertexAttribPointer(ai.getLoc(), ai.size, ai.type, false, ai.stride, ai.offset);
			GL40.glEnableVertexAttribArray(ai.getLoc());
		}

		GL30.glBindVertexArray(0);
	}

	public void addAttribute(String attributeName, int loc, int size, int type, int stride, int offset) {

		attributeList.add(new AttributeInfo(attributeName, loc, size, type, stride, offset));

	}

	public void render(Shader shader) {
		// Bind the VAO and draw the mesh
		GL40.glUseProgram(shader.getProgram_id());
		GL30.glBindVertexArray(vaoID);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, eboID);
		GL11.glDrawElements(GL11.GL_TRIANGLES, indexCount, GL11.GL_UNSIGNED_INT, 0);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		GL30.glBindVertexArray(0);
		GL40.glUseProgram(0);
	}

	public void renderPPO(Shader shader, FrameBuffer fb) {
		// Bind the VAO and draw the mesh
		GL40.glUseProgram(shader.getProgram_id());
		GL30.glBindVertexArray(vaoID);
		fb.bindTexture();
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, eboID);
		GL11.glDrawElements(GL11.GL_TRIANGLES, indexCount, GL11.GL_UNSIGNED_INT, 0);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		GL30.glBindVertexArray(0);
		GL40.glUseProgram(0);
		fb.unbindTexture();
	}

	public void cleanup() {
		// Clean up VAO, VBO, and EBO
		GL30.glBindVertexArray(0);
		GL30.glDeleteVertexArrays(vaoID);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL15.glDeleteBuffers(vboID);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		GL15.glDeleteBuffers(eboID);
	}

	/**
	 * @return the vertCount
	 */
	public int getVertCount() {
		return vertCount;
	}

	/**
	 * @return the indexCount
	 */
	public int getIndexCount() {
		return indexCount;
	}

	public static class AttributeInfo {
		String name;
		int size;
		int type;
		int stride;
		int offset;
		int loc;

		public AttributeInfo(String name, int loc, int size, int type, int stride, int offset) {
			this.name = name;
			this.size = size;
			this.type = type;
			this.stride = stride;
			this.offset = offset;
			this.loc = loc;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @return the size
		 */
		public int getSize() {
			return size;
		}

		/**
		 * @return the type
		 */
		public int getType() {
			return type;
		}

		/**
		 * @return the stride
		 */
		public int getStride() {
			return stride;
		}

		/**
		 * @return the offset
		 */
		public int getOffset() {
			return offset;
		}

		/**
		 * @return the loc
		 */
		public int getLoc() {
			return loc;
		}

	}

}
