package com.kronos.graphixs.display.gui;

import imgui.ImGui;
import imgui.flag.ImGuiConfigFlags;
import imgui.flag.ImGuiWindowFlags;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;

public class GuiHandler {

	// ImGui objects
	private ImGui imgui;
	private ImGuiImplGlfw imGuiGlfw;
	private ImGuiImplGl3 imGuiGl3;

	public GuiHandler(long window) {
		// Initialize ImGui
		imgui = new ImGui();
		imgui.createContext();
		imgui.getIO().setConfigFlags(ImGuiConfigFlags.NavEnableKeyboard);

		// Setup ImGui GLFW binding
		imGuiGlfw = new ImGuiImplGlfw();
		imGuiGlfw.init(window, true);

		// Setup ImGui OpenGL3 binding
		imGuiGl3 = new ImGuiImplGl3();
		imGuiGl3.init();

		// Set ImGui style (optional)
		imgui.getStyle().setFrameRounding(2.0f);
		imgui.getStyle().setGrabRounding(2.0f);
		imgui.getStyle().setAntiAliasedLines(true);
		imgui.getStyle().setAntiAliasedFill(true);
	}

	public void dispose() {
		imGuiGl3.dispose();
		imGuiGlfw.dispose();
		imgui.destroyContext();
	}

	public void update() {
		// Poll events and update ImGui input
		imGuiGlfw.newFrame();
		imgui.newFrame();

		// Add your GUI elements here
		createSampleGUI();

		// Render ImGui
		imgui.render();
		render();
	}

	private void createSampleGUI() {
		// Create a sample window
		imgui.begin("Sample Window", ImGuiWindowFlags.NoResize);

		// Add GUI components here
		imgui.text("Hello, ImGui!");

		imgui.end();
	}

	// You can add more functions to create additional GUI components as needed

	public void render() {
		imGuiGl3.renderDrawData(ImGui.getDrawData());
	}
}
