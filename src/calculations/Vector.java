package calculations;

public class Vector {

	double x, y, z;

	public Vector(double x, double y, double z) {
		
		double length = Math.sqrt(x**2 + y**2 + z**2);

		if(length > 0) {
		
			this.x = x/length;
			this.y = y/length;
			this.z = z/length

		}

	}

	Vector crossVector(Vector v) {
		
		Vector CrossVector = new Vector(
				y * v.z - z * v.y,
				z * v.x - x * v.z,
				x * v.y - y * v.x);
		
		return CrossVector

	}

}
