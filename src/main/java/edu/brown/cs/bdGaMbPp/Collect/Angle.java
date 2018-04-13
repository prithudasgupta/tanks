package edu.brown.cs.bdGaMbPp.Collect;


public class Angle {
	
	private double degrees;
	private double rotationSpeed;
	
	public Angle(double startDegrees, double rotateSpeed) {
		if (startDegrees < 0.0 || startDegrees > 360) {
			System.out.println("ERROR: Invalid Start");
		}
		else {
			degrees = startDegrees;
			rotationSpeed = rotateSpeed;
		}
	}
	
	public void rotateClockwise() {
		degrees = degrees + rotationSpeed;
		if (degrees > 360) {
			degrees = degrees - 360;
		}
	}
	
	public void rotateCounterClockwise() {
		degrees = degrees - rotationSpeed;
		if (degrees < 0) {
			degrees = degrees + 360;
		}
	}
	

}
