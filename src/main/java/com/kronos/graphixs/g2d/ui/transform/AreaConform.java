package com.kronos.graphixs.g2d.ui.transform;

import java.util.Arrays;

import com.kronos.graphixs.g2d.ScreenProvider;
import com.kronos.graphixs.g2d.ui.BasePosition;
import com.kronos.graphixs.g2d.ui.Transformer;

public class AreaConform implements Transformer {

	ConformType type;
	int padding = 0;
	private static final float PADDING_TOP = 10.0f; // Adjust according to your requirements
	private static final float PADDING_BETWEEN = 5.0f; // Adjust according to your requirements

	public AreaConform(ConformType type, int padding) {
		super();
		this.type = type;
		this.padding = padding;
	}

	@Override
	public void reposition(ScreenProvider provider, BasePosition position, BasePosition... constraints) {
		// TODO Auto-generated method stub
		positionTop(provider, position, constraints);
	}

	public void positionTop(ScreenProvider provider, BasePosition position, BasePosition... constraints) {
		int area = getArea(position);

		int children_a = 0;

		for (int i = 0; i < constraints.length; i++) {
			children_a += (int) (constraints[i].pos().getW() * constraints[i].pos().getH());
			constraints[i].setAp(position.pos());
		}

		if (children_a > area) {
			// we need to scale down each child, but we need to determine which to scale
			// first, so biggest area
//sort array then scale down evenly and proportionaly for larger comps
			Arrays.sort(constraints, (a, b) -> Integer.compare(getArea(b), getArea(a)));

			// Determine the scaling factor based on the total area and children area
			double scale = Math.sqrt((double) area / children_a);

			// Apply scaling to each child
			for (int i = 0; i < constraints.length; i++) {
				constraints[i].pos().scale((float) scale);
			}
		}

		// now that we have dealt with our sizing we need to position our children based
		// on the type which for this method is to push everything to the top of the
		// panel (position var)
//we also have a padding var - all children must have the following applied. not be outside the panel, not intersect with other children, must have a padding between other children and panel 
		// of an int type padding
		float currentY = position.pos().getY(); // Initialize current Y position

		// Apply padding to the top of the panel
		currentY += PADDING_TOP; // Adjust PADDING_TOP according to your requirements
		KeepInBox box = new KeepInBox();
		// Iterate over the sorted and scaled constraints
		for (int i = 0; i < constraints.length; i++) {
			BasePosition child = constraints[i];

			box.reposition(provider, child, null);
			// Check if the child is within the panel bounds
			if (child.pos().getY() + child.pos().getH() <= position.pos().getY() + position.pos().getH()) {
				// Apply padding between the child and the panel
				child.pos().setY(currentY + PADDING_BETWEEN); // Adjust PADDING_BETWEEN
				// according to your requirements

				// Update the current Y position for the next child
				currentY = child.pos().getY() + child.pos().getH();
			}
		}
	}

	public int getArea(BasePosition position) {
		int area = (int) (position.pos().getW() * position.pos().getH());
		return area;
	}

}
