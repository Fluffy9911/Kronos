package com.kronos.core;

import java.util.Optional;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GraphicsCard;
import oshi.hardware.HardwareAbstractionLayer;

public class SystemInfoUtil {

	private static final SystemInfo systemInfo = new SystemInfo();
	private static final HardwareAbstractionLayer hal = systemInfo.getHardware();

	public static String getGPUVramInfo() {
		// Initialize OpenGL capabilities
		GL.createCapabilities();

		// Check for NVX GPU memory info extension
		if (GL.getCapabilities().GL_NVX_gpu_memory_info) {
			// Fetch VRAM information
			int totalVram = GL11.glGetInteger(NVXGpuMemoryInfo.GL_GPU_MEMORY_INFO_TOTAL_AVAILABLE_MEMORY_NVX) / 1024; // Convert
																														// KB
			// to MB
			int freeVram = GL11.glGetInteger(NVXGpuMemoryInfo.GL_GPU_MEMORY_INFO_CURRENT_AVAILABLE_VIDMEM_NVX) / 1024; // Convert
																														// KB
			// to MB
			int usedVram = totalVram - freeVram;

			return String.format("Total VRAM: %d MB, Used VRAM: %d MB, Free VRAM: %d MB", totalVram, usedVram,
					freeVram);
		} else {
			return "GPU VRAM information is not supported on this system.";
		}
	}

	/**
	 * Get GPU usage percentage.
	 *
	 * @return The GPU usage percentage.
	 */
	public static double getGPUVRAM() {
		Optional<GraphicsCard> gpu = hal.getGraphicsCards().stream().findFirst();
		return gpu.get().getVRam();
	}

	/**
	 * Get CPU usage percentage.
	 *
	 * @return The CPU usage percentage.
	 */
	public static double getCPUUsage() {
		CentralProcessor processor = hal.getProcessor();

		// Fetch CPU load averages; may take a few seconds to stabilize
		double load = processor.getSystemCpuLoad(1);
		return load * 100;
	}

}
