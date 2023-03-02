package object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import main.Screen;

import rendering.Gfx;

public class Object {
	
	Polygon P;
	Color c;
	double lighting = 1;
	boolean draw = true, visible = true, transparent;

	public Object(double[] x, double[] y, Color c, boolean transparent) {
		
		P = new Polygon();

		for(int i = 0; i < x.length; i++) {
			
			P.addPoint((int) x[i], (int) y[i]);

		}

		this.c = c;
		this.transparent = transparent;

	}

	void update(double[] x, double[] y) {
		
		P.reset();

		for(int i = 0; i < x.length, i++) {
			
			P.xpoints[i] = (int) x[i];
			P.ypoints[i] = (int) y[i];
			P.npoints = x.length;

		}

	}

	void draw(Graphics g) {
		
		if(draw && visible) {
			
			g.setColor(new Color((int)(c.getRed() * lighting), (int)(c.getGreen()*lighting), (int)(c.getBlue() * lighting)));

			if(transparent) {
				
				g.drawPolygon(P)

			} else {
			
				g.fillPolygon(P)

			}

			if(Gfx.Outlines) {
				
				g.setColor(new Color(0, 0, 0));
				g.drawPolygon(P);

			}

			if(Gfx.objectOver == this) {
				
				g.setColor(new Color(255, 255, 255, 100));
				g.fillPolygon(P);

			}

		}

	}

	boolean mouseOver() {
		
		return P.contains(Screen.screenSize.getWidth()/2, Screen.screenSize.getHeight()/2);

	}

}
