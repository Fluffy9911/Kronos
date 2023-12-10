package com.kronos.graphixs;

import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glDeleteTextures;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameteri;
import static org.lwjgl.opengl.GL30.GL_DEPTH24_STENCIL8;
import static org.lwjgl.opengl.GL30.GL_DEPTH_STENCIL_ATTACHMENT;
import static org.lwjgl.opengl.GL30.GL_FRAMEBUFFER;
import static org.lwjgl.opengl.GL30.GL_FRAMEBUFFER_COMPLETE;
import static org.lwjgl.opengl.GL30.GL_RENDERBUFFER;
import static org.lwjgl.opengl.GL30.glBindFramebuffer;
import static org.lwjgl.opengl.GL30.glBindRenderbuffer;
import static org.lwjgl.opengl.GL30.glCheckFramebufferStatus;
import static org.lwjgl.opengl.GL30.glDeleteFramebuffers;
import static org.lwjgl.opengl.GL30.glDeleteRenderbuffers;
import static org.lwjgl.opengl.GL30.glFramebufferRenderbuffer;
import static org.lwjgl.opengl.GL30.glFramebufferTexture2D;
import static org.lwjgl.opengl.GL30.glGenFramebuffers;
import static org.lwjgl.opengl.GL30.glGenRenderbuffers;
import static org.lwjgl.opengl.GL30.glRenderbufferStorage;

import org.lwjgl.opengl.GL40;

import com.kronos.Kronos;

public class FrameBuffer {
	private int framebufferID;
	private int textureID;
	private int renderBufferID; // Optional render buffer
	private int width;
	private int height;
	public static int aid = 0;

	public FrameBuffer(int width, int height, boolean useRenderBuffer) {
		this.width = width;
		this.height = height;

		// Create a framebuffer object
		framebufferID = glGenFramebuffers();
		glBindFramebuffer(GL_FRAMEBUFFER, framebufferID);

		GL40.glDrawBuffer(GL40.GL_COLOR_ATTACHMENT0 + aid);

		// Create a texture to render to
		textureID = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, textureID);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, 0);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

		glFramebufferTexture2D(GL_FRAMEBUFFER, GL40.GL_COLOR_ATTACHMENT0 + aid, GL_TEXTURE_2D, textureID, 0);

		// Optional: Create a render buffer for depth/stencil attachments
		if (useRenderBuffer) {
			renderBufferID = glGenRenderbuffers();
			glBindRenderbuffer(GL_RENDERBUFFER, renderBufferID);
			glRenderbufferStorage(GL_RENDERBUFFER, GL_DEPTH24_STENCIL8, width, height);
			glFramebufferRenderbuffer(GL_FRAMEBUFFER, GL_DEPTH_STENCIL_ATTACHMENT, GL_RENDERBUFFER, renderBufferID);
		}

		// Check framebuffer completeness
		int framebufferStatus = glCheckFramebufferStatus(GL_FRAMEBUFFER);
		Kronos.debug.getLogger().info("Frame Buffer Status Status: {} ", framebufferStatus);
		if (framebufferStatus != GL_FRAMEBUFFER_COMPLETE) {
			System.err.println("Framebuffer is not complete! Status: " + framebufferStatus);
		}

		// Unbind the framebuffer
		glBindFramebuffer(GL_FRAMEBUFFER, 0);
		// aid++;
	}

	public void start() {
		glBindFramebuffer(GL_FRAMEBUFFER, framebufferID);
	}

	public void end() {
		glBindFramebuffer(GL_FRAMEBUFFER, 0);
	}

	public int getTextureID() {
		return textureID;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void bindTexture() {
		glBindTexture(GL_TEXTURE_2D, textureID);
	}

	public void unbindTexture() {
		glBindTexture(GL_TEXTURE_2D, 0);
	}

	public void cleanup() {
		glDeleteFramebuffers(framebufferID);
		System.out.println("Deleted framebuffer with ID: " + framebufferID);

		glDeleteTextures(textureID);
		System.out.println("Deleted texture with ID: " + textureID);

		if (renderBufferID != 0) {
			glDeleteRenderbuffers(renderBufferID);
			System.out.println("Deleted render buffer with ID: " + renderBufferID);
		}
	}
}
