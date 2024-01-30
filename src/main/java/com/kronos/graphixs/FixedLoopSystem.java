package com.kronos.graphixs;

import java.util.concurrent.TimeUnit;

public abstract class FixedLoopSystem {

	private int updatesPerSecond;
	double deltaTime = 0;
	int framenum = 0;
	private long last = 0, current;
	private boolean running = false;
	private boolean paused = false, flag = false;
	private int fps = 0;
	private int frameCount = 0;
	double TARGET_FPS = 60;

	public FixedLoopSystem(int updatesPerSecond) {
		TARGET_FPS = updatesPerSecond;
	}

	public void start() {
		running = true;
		flag = true;

		// This value would probably be stored elsewhere.
		double GAME_HERTZ = TARGET_FPS / 2;
		// Calculate how many ns each frame should take for our target game hertz.
		double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
		// At the very most we will update the game this many times before a new render.
		// If you're worried about visual hitches more than perfect timing, set this to
		// 1.
		int MAX_UPDATES_BEFORE_RENDER = 5;
		// We will need the last update time.
		double lastUpdateTime = System.nanoTime();
		// Store the last time we rendered.
		double lastRenderTime = System.nanoTime();

		double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;

		// Simple way of finding FPS.
		int lastSecondTime = (int) (lastUpdateTime / 1000000000);

		while (running) {
			if (flag) {
				// This value would probably be stored elsewhere.
				GAME_HERTZ = TARGET_FPS / 2;
				// Calculate how many ns each frame should take for our target game hertz.
				TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
				// At the very most we will update the game this many times before a new render.
				// If you're worried about visual hitches more than perfect timing, set this to
				// 1.
				MAX_UPDATES_BEFORE_RENDER = 5;
				// We will need the last update time.
				lastUpdateTime = System.nanoTime();
				lastRenderTime = System.nanoTime();

				// If we are able to get as high as this FPS, don't render again.

				TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;
				flag = false;
			}
			double now = System.nanoTime();
			if (TARGET_FPS == 0) {
				if (!paused) {

					update();
					lastRenderTime = now;
					deltaTime = (float) (now - lastRenderTime);
					frameCount++;
					int thisSecond = (int) (lastRenderTime / 1000000000);
					if (thisSecond > lastSecondTime) {
						// System.out.println("NEW SECOND " + thisSecond + " " + frameCount);
						fps = frameCount;
						frameCount = 0;
						lastSecondTime = thisSecond;
					}
				}
			} else {

				int updateCount = 0;

				if (!paused) {
					// Do as many game updates as we need to, potentially playing catchup.
					while (now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER) {
						lastUpdateTime += TIME_BETWEEN_UPDATES;
						updateCount++;
					}

					// If for some reason an update takes forever, we don't want to do an insane
					// number of catchups.
					// If you were doing some sort of game that needed to keep EXACT time, you would
					// get rid of this.
					if (now - lastUpdateTime > TIME_BETWEEN_UPDATES) {
						lastUpdateTime = now - TIME_BETWEEN_UPDATES;
					}

					// Render. To do so, we need to calculate interpolation for a smooth render.
					float interpolation = Math.min(1.0f, (float) ((now - lastUpdateTime) / TIME_BETWEEN_UPDATES));

					update();
					frameCount++;

					this.deltaTime = now - lastRenderTime;

					lastRenderTime = now;
					// Update the frames we got.
					int thisSecond = (int) (lastUpdateTime / 1000000000);
					if (thisSecond > lastSecondTime) {

						fps = frameCount;
						frameCount = 0;
						lastSecondTime = thisSecond;
					}

					// Yield until it has been at least the target time between renders. This saves
					// the CPU from hogging.
					while (now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS
							&& now - lastUpdateTime < TIME_BETWEEN_UPDATES && TARGET_FPS != 0) {
						Thread.yield();

						// This stops the app from consuming all your CPU. It makes this slightly less
						// accurate, but is worth it.
						// You can remove this line and it will still work (better), your CPU just
						// climbs on certain OSes.
						// FYI on some OS's this can cause pretty bad stuttering. Scroll down and have a
						// look at different peoples' solutions to this.
						try {
							Thread.sleep(1);
						} catch (Exception e) {
						}

						now = System.nanoTime();
					}
				}
			}
		}

	}

	public void stop() {
		running = false;
	}

	public abstract void update();

	public void setRate(int fps) {
		TARGET_FPS = fps;
		flag = true;
	}

	public Accessor getAccessor() {
		return new Accessor(this);
	}

	private static class Accessor implements LoopAccessor {
		FixedLoopSystem fls;

		public Accessor(FixedLoopSystem fls) {
			this.fls = fls;
		}

		@Override
		public double getFps() {
			// TODO Auto-generated method stub
			return fls.fps;
		}

		@Override
		public double getDeltaTime() {
			// TODO Auto-generated method stub
			return TimeUnit.NANOSECONDS.toMillis((long) fls.deltaTime);
		}

		@Override
		public void setFps(int fps) {
			fls.setRate(fps);

		}

		@Override
		public double target() {
			// TODO Auto-generated method stub
			return fls.TARGET_FPS;
		}

	}

	/**
	 * @return the deltaTime in NANOSECONDS
	 */
	public double getDeltaTime() {
		return deltaTime;
	}

	/**
	 * @return the running
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * @return the fps
	 */
	public int getFps() {
		return fps;
	}

	/**
	 * @return the frameCount
	 */
	public int getFrameCount() {
		return frameCount;
	}

	/**
	 * @return the tARGET_FPS
	 */
	public double getTARGET_FPS() {
		return TARGET_FPS;
	}

}
