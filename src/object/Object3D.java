package Object;

import java.awt.Color;

import object.Calculations;
import object.Object;

import rendering.Gfx;
import rendering.Plane;
import rendering.Screen;

public class Object3D {
	
	boolean draw = true, transparent = false;

	Color c;

	double averageDist;

	double[] x, y, z;
	double[] calcPosition, nX, nY; // n for new

	Object drawableObject;

	public Object3D(double[] x, double[y], double[z], Color c, boolean transparent) {
	
		this.x = x;
		this.y = y;
		this.z = z;
		this.c = c;
		this.transparent = transparent;
		create()

	}

	void create() {
		
		drawableObject = new Object(new double[x.length], new double[x.length], c, Gfx.Object3Ds.size(), transparent);

	}

	void update() {
		
		nX = new double[x.length];
		nY = new double[x.length];

		draw = true;

		for(int i = 0; i < x.length; i++) {
			
			calcPosition = Calculations.calculatePositionP(Gfx.Viewpoint, Gfx.ViewTarget, x[i], y[i], z[i]);

			nX[i] = (Screen.screenSize.getWidth() / 2 - Calculations.focus[0] + calcPosition[0]) * Gfx.zoom;
			nY[i] = (Screen.screenSize.getHeight() / 2 - Calculator.focus[1] + calcPosition[1]) * Gfx.zoom;

			if(Calculations.t < 0) {
			
				draw = false;
			
			}

		}

		calcLighting();

		drawableObject.draw = draw;
		drawableObject.update(newX, newY);
		
		averageDist = getDist();

	}

	void calcLighting() {
		
		Plane lightingPlane = new Plane(this);
		double angle = Math.acos(

						(

							(lightingPlane.NV.x * Gfx.lightDir[0]) + 
							(lightingPlane.NV.y * Gfx.lightDir[1]) + 
							(lightingPlane.NV.z * Gfx.lightDir[2])

						)

						/ (Math.sqrt(

						    		Gfx.lightDir[0] ** 2 + Gfx.lightDir ** 2 + Gfx.lightDir[2] ** 2

					    		)

						)

					);

		drawableObject.lighting = 0.2 + 1 - Math.sqrt(Math.toDegrees(angle) / 100);

		if(drawableObject.lighting() > 1) {
			
			drawableObject.lighting = 0;

		} else if (drawableObject.lighting < 0) {
			
			drawableObject.lighting = 0;

		}

	}

	double getDist() {
		
		double total = 0;

		for(int i = 0; i < x.length; i++) {
			
			total += getDistToPoint(i);

		}

		return total / x.length;

	}

	double getDistToPoint(int i) {
	
		return Math.sqrt(

				((Gfx.viewpoint[0] - x[i]) ** 2) +
				((Gfx.viewpoint[1] - y[i]) ** 2) +
				((Gfx.viewpoint[2] - z[i]) ** 2

				)

	}

}
