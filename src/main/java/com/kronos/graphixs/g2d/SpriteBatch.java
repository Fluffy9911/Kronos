package com.kronos.graphixs.g2d;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL40;
import org.lwjgl.system.MemoryStack;

import com.kronos.graphixs.shaders.render.ShaderProgram;
import com.kronos.graphixs.texture.Texture;

public class SpriteBatch {
	private static final int MAX_SPRITES = 1000;
	private List<Float> vertexData = new ArrayList<>();
	private int spriteCount = 0;
	private int currentTextureID = -1; // To keep track of the current texture bound.
	private ShaderProgram shader;
	private int vao, vbo;
	private int positionAttribLocation, texCoordAttribLocation;

	public SpriteBatch(ShaderProgram shader) {
		this.shader = shader;

		// Create VAO and VBO for the sprite batch
		vao = GL40.glGenVertexArrays();
		GL40.glBindVertexArray(vao);

		vbo = GL40.glGenBuffers();
		GL40.glBindBuffer(GL40.GL_ARRAY_BUFFER, vbo);

		// Define the attribute locations in the shader
		shader.addAttribute("position", 0);
		shader.addAttribute("texCoord", 1);
		positionAttribLocation = shader.getAttribute("position");
		texCoordAttribLocation = shader.getAttribute("texCoord");

		// Enable vertex attributes (position, texCoord)
		GL40.glEnableVertexAttribArray(positionAttribLocation);
		GL40.glEnableVertexAttribArray(texCoordAttribLocation);

		// Set the pointer for the position attribute (XYZ)
		GL40.glVertexAttribPointer(positionAttribLocation, 3, GL40.GL_FLOAT, false, 5 * Float.BYTES, 0);

		// Set the pointer for the texture coordinates (UV)
		GL40.glVertexAttribPointer(texCoordAttribLocation, 2, GL40.GL_FLOAT, false, 5 * Float.BYTES, 3 * Float.BYTES);

		GL40.glBindVertexArray(0);
	}

	public void begin() {
		vertexData.clear(); // Clear previous vertex data
		spriteCount = 0;
	}

	public void end() {
		flush();
	}

	// Method to draw a sprite with different textures
	public void draw(Texture texture, float x, float y, float width, float height) {
		if (spriteCount >= MAX_SPRITES) {
			flush(); // Flush if maximum sprites are reached
		}

		// Bind the texture if it's different from the currently bound texture
		if (currentTextureID != texture.getTextureId()) {
			flush(); // Flush the current batch
			GL40.glBindTexture(GL40.GL_TEXTURE_2D, texture.getTextureId()); // Bind the new texture
			currentTextureID = texture.getTextureId(); // Update the current texture ID
		}

		// Add the vertices for the quad (sprite)
		addQuadVertices(x, y, width, height);

		spriteCount++;
	}

	// Add vertices for a quad using GL_TRIANGLES
	private void addQuadVertices(float x, float y, float width, float height) {
		// Define the vertices for two triangles forming a quad
		float[] vertices = {
				// Triangle 1
				x, y, 0f, 0f, 0f, // Bottom-left
				x + width, y, 0f, 1f, 0f, // Bottom-right
				x + width, y + height, 0f, 1f, 1f, // Top-right

				// Triangle 2
				x, y, 0f, 0f, 0f, // Bottom-left
				x + width, y + height, 0f, 1f, 1f, // Top-right
				x, y + height, 0f, 0f, 1f, // Top-left
		};

		// Add the vertices to the vertex data list
		for (float vertex : vertices) {
			vertexData.add(vertex);
		}
	}

	// Flush the current batch to OpenGL
	private void flush() {
		if (spriteCount == 0) {
			return;
		}

		// Use the shader program
		shader.use();

		// Upload the vertex data to the GPU
		try (MemoryStack stack = MemoryStack.stackPush()) {
			FloatBuffer buffer = stack.mallocFloat(vertexData.size());
			for (Float vertex : vertexData) {
				buffer.put(vertex);
			}
			buffer.flip();

			// Bind the VBO and upload data
			GL40.glBindBuffer(GL40.GL_ARRAY_BUFFER, vbo);
			GL40.glBufferData(GL40.GL_ARRAY_BUFFER, buffer, GL40.GL_DYNAMIC_DRAW);

			// Bind the VAO and draw the elements
			GL40.glBindVertexArray(vao);

			// Draw using GL_TRIANGLES (6 vertices per sprite)
			GL40.glDrawArrays(GL40.GL_TRIANGLES, 0, spriteCount * 6); // 2 triangles per sprite, 6 vertices total
			GL40.glBindVertexArray(0);
		}

		// Reset vertex data and sprite count
		vertexData.clear();
		spriteCount = 0;
	}
}