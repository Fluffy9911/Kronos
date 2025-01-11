/**
 * 
 */
package com.kronos.graphixs.g2d.ui.transform.positioning;

import java.util.Arrays;

import org.joml.Vector2i;

import com.kronos.graphixs.g2d.ScreenCord;
import com.kronos.graphixs.g2d.ui.BasePosition;

/**
 * 
 */
public class Position {

	public static int percentAnchorX(Anchor a, float p) {
		return (int) (a.getPoint().x() * p);
	}

	public static int percentBetweenX(Anchor left, Anchor right, float p) {
		// Ensure that p is within the range [0, 1]
		p = Math.max(0, Math.min(1, p));

		// Calculate the x-coordinate at the specified percentage between left and right
		float xLeft = left.getPoint().x();
		float xRight = right.getPoint().x();
		float resultX = xLeft + (xRight - xLeft) * p;

		return (int) resultX;
	}

	public static int percentBetweenY(Anchor top, Anchor bottom, float p) {
		// Ensure that p is within the range [0, 1]
		p = Math.max(0, Math.min(1, p));

		// Calculate the y-coordinate at the specified percentage between top and bottom
		float yTop = top.getPoint().y();
		float yBottom = bottom.getPoint().y();
		float resultY = yTop + (yBottom - yTop) * p;

		return (int) resultY;
	}

	public static Vector2i centerBetweenAnchors(Anchor left, Anchor right) {
		// Calculate the center point between two anchors
		int centerX = (left.getPoint().x() + right.getPoint().x()) / 2;
		int centerY = (left.getPoint().y() + right.getPoint().y()) / 2;

		return new Vector2i(centerX, centerY);
	}

	public static Vector2i interpolatePoints(Anchor a, Anchor b, float t) {
		// Interpolate between two points based on parameter t
		float x = a.getPoint().x() + (b.getPoint().x() - a.getPoint().x()) * t;
		float y = a.getPoint().y() + (b.getPoint().y() - a.getPoint().y()) * t;

		return new Vector2i((int) x, (int) y);
	}

	public static int distanceBetweenAnchors(Anchor a, Anchor b) {
		// Calculate the Euclidean distance between two anchors
		float dx = a.getPoint().x() - b.getPoint().x();
		float dy = a.getPoint().y() - b.getPoint().y();

		return (int) Math.sqrt(dx * dx + dy * dy);
	}

	public static int midpointX(Anchor a, Anchor b) {
		// Calculate the midpoint of the x-coordinates between two anchors
		float xMidpoint = (a.getPoint().x() + b.getPoint().x()) / 2;
		return (int) xMidpoint;
	}

	public static int midpointY(Anchor a, Anchor b) {
		// Calculate the midpoint of the y-coordinates between two anchors
		float yMidpoint = (a.getPoint().y() + b.getPoint().y()) / 2;
		return (int) yMidpoint;
	}

	public static Vector2i findPointAtDistance(Anchor start, Anchor end, int distance) {
		// Find a point at a certain distance from the start anchor towards the end
		// anchor
		float totalDistance = distanceBetweenAnchors(start, end);
		float t = distance / totalDistance;

		return interpolatePoints(start, end, t);
	}

	public static int angleBetweenAnchors(Anchor a, Anchor b) {
		// Calculate the angle (in degrees) between two anchors
		float dx = b.getPoint().x() - a.getPoint().x();
		float dy = b.getPoint().y() - a.getPoint().y();
		double angle = Math.toDegrees(Math.atan2(dy, dx));

		return (int) angle;
	}

	public static void adjustPositions(ScreenCord bounds, BasePosition[] positions) {
		// Sort positions by their distance to the bounds center
		Arrays.sort(positions, (a, b) -> {
			double distA = distanceToCenter(a.pos(), bounds);
			double distB = distanceToCenter(b.pos(), bounds);
			return Double.compare(distA, distB);
		});

		// Iterate through positions and adjust them to comply with constraints
		for (int i = 0; i < positions.length; i++) {
			BasePosition current = positions[i];

			// Determine the closest or actual size within constraints
			int closestWidth = determineClosestSize((int) current.pos().getW(), (int) bounds.getW());
			int closestHeight = determineClosestSize((int) current.pos().getH(), (int) bounds.getH());

			// Check if the adjusted position overlaps with other positions
			boolean overlap = false;
			for (int j = 0; j < i; j++) {
				if (doRectanglesOverlap((int) current.pos().getX(), (int) current.pos().getY(), closestWidth,
						closestHeight, (int) positions[j].pos().getX(), (int) positions[j].pos().getY(),
						(int) positions[j].pos().getW(), (int) positions[j].pos().getH())) {
					overlap = true;
					break;
				}
			}

			// If no overlap, update the position
			if (!overlap) {
				current.setPos(new ScreenCord(current.pos().getX(), current.pos().getY(), closestWidth, closestHeight));
				current.setAp(new ScreenCord(current.anchoredPos().getX(), current.anchoredPos().getY(), closestWidth,
						closestHeight));
			}
		}
	}

	private static double distanceToCenter(ScreenCord point, ScreenCord bounds) {
		double centerX = bounds.getX() + bounds.getW() / 2.0;
		double centerY = bounds.getY() + bounds.getH() / 2.0;
		double dx = point.getX() + point.getW() / 2.0 - centerX;
		double dy = point.getY() + point.getH() / 2.0 - centerY;
		return Math.sqrt(dx * dx + dy * dy);
	}

	private static int determineClosestSize(int currentSize, int maxSize) {
		// You can implement your logic for determining the closest size here
		// For simplicity, this example simply returns the smaller of the two sizes
		return Math.min(currentSize, maxSize);
	}

	private static boolean doRectanglesOverlap(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2) {
		return x1 < x2 + w2 && x1 + w1 > x2 && y1 < y2 + h2 && y1 + h1 > y2;
	}
}
