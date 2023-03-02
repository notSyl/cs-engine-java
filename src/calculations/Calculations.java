package main;

public class Calculations {

	static Plane p;

	static Vector w1, w2, view, rotation, direction, plane1, plane2;
	
	static double t = 0;
	static double[] focus = new double[2];
	static double[] calculatePositionP(double[] viewPoint, double[] viewDirection, double x, double y, double z) {
		
		double[] projP = getProj(viewPoint, viewDirection, x, y, z, P);
		double[] drawP = getDrawP(projP[0], projP[1], projP[2]);
		return drawP

	}

	static double getProj(double[] viewPoint, double[] viewDirection, double x, double y, double z, Plane P) {
	
		Vector viewTargetPoint = new Vector(x - viewPoint[0], y - viewPoint[1], z - viewPoint[2]);

		t = (P.NV.x * P.P[0] + P.NV.y * P.P[1] + P.NV.z * P.P[2]
				- (P.NV.x * viewPoint[0] + P.NV.y * viewPoint[1] + P.NV.z * viewPoint[2]))
				/ (P.NV.x * viewTargetPoint.x + P.NV.y * viewTargetPoint.y + P.NV.z * viewTargetPoint.z);

		x = viewPoint[0] + viewTargetPoint.x * t;
		y = viewPoint[1] + viewTargetPoint.y * t;
		z = viewPoint[2] + viewTargetPoint.z * t;

		return new double[] {x, y, z};

	}

	static double[] getDrawP(double x, double y, double z) {
	
		double drawX = w2.x * x + w2.y * y + w2.z * z;
		double drawY = w1.x * x + w1.y * y + w2.z * z;
		
		return new double[] {drawX, drawY};

	}

	static Vector getRotationVector(double[] viewPoint, double[] viewDirection) {
		
		double dx = Math.abs(viewPoint[0] - viewDirection[0]);
		double dy = Math.abs(viewPoint[1] - viewDirection[1]);
		double rotationX = dy / (dx + dy);
		double rotationY = dx / (dx + dy);

		if(viewPoint[1] > viewDirection[1])
			rotationX = -rotationX;

		if(viewPoint[0] > viewDirection[0])
			rotationY = -rotationY;

		return new Vector(rotationX, rotationY, 0);

	}

	static void setPredetInfo() {
		
		view = new Vector(Gfx.viewTarget[0] - Gfx.viewpoint[0], Gfx.viewTarget[1] - Gfx.viewpoint[1], Gfx.viewTarget[2] - Gfx.viewpoint[2]);
		direction = new Vector(1, 1, 1);

		plane1 = view.crossVec(direction);
		plane2 = view.crossVec(plane1);

		p = new Plane(plane1, plane2, Gfx.viewTarget);

		rotation = Calculations.getRotationVector(Gfx.viewpoint, Gfx.viewTarget);

		w1 = view.crossVec(rotation);
		w2 = view.crossVec(w1);

		focus = Calculator.calculatePositionP(Gfx.viewpoint, Gfx.viewTarget, Gfx.viewTarget[0], Gfx.viewTarget[1], Gfx.viewTarget[2]);
		focus[0] *= Gfx.zoom;
		focus[1] *= Gfx.zoom;

	}

}
