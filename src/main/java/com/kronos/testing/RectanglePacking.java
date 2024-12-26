package com.kronos.testing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.kronos.graphixs.color.Colors;

public class RectanglePacking {
	public static void main(String[] args) {
		// Define the dimensions of the larger rectangle
		int largeRectWidth = 1920;
		int largeRectHeight = 1080;

		// Create a list of smaller rectangles (width, height)
		List<Rectangle> rectangles = new ArrayList<>();
		Random r = new Random();

		for (int i = 0; i < 50; i++) {
			int w = 32;
			int h = 32;
			rectangles.add(new Rectangle(w, h));
		}

		// Sort rectangles by height in descending order
		Collections.sort(rectangles, Comparator.comparingInt(Rectangle::getHeight).reversed());

		// Place rectangles using the FFDH algorithm
		placeRectangles(rectangles, largeRectWidth, largeRectHeight);

		// Create a buffered image to visualize the result
		BufferedImage image = new BufferedImage(largeRectWidth, largeRectHeight, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, largeRectWidth, largeRectHeight);

		for (int i = 0; i < rectangles.size(); i++) {
			Rectangle rect = rectangles.get(i);
			g.setColor(Colors.randColor().asCol());
			g.fillRect(rect.x + 32, rect.y + 32, rect.width, rect.height);
		}

		// Display the image in a JFrame
		JFrame frame = new JFrame("Rectangle Packing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(largeRectWidth, largeRectHeight);
		frame.add(new JLabel(new ImageIcon(image)));
		frame.setVisible(true);
	}

	public static void placeRectangles(List<Rectangle> rectangles, int largeRectWidth, int largeRectHeight) {
		int currentX = 0;
		int currentY = 0;
		int currentRowHeight = 0;

		for (Rectangle rect : rectangles) {
			if (currentX + rect.width > largeRectWidth) {
				// Move to the next row
				currentX = 0;
				currentY += currentRowHeight;
				currentRowHeight = 0;
			}
			if (currentY + rect.height > largeRectHeight) {
				throw new RuntimeException("Cannot fit all rectangles within the large rectangle");
			}
			rect.x = currentX;
			rect.y = currentY;
			currentX += rect.width;
			currentRowHeight = Math.max(currentRowHeight, rect.height);
		}
	}

	static class Rectangle {
		int width;
		int height;
		int x;
		int y;

		Rectangle(int width, int height) {
			this.width = width;
			this.height = height;
		}

		int getHeight() {
			return height;
		}
	}
}
