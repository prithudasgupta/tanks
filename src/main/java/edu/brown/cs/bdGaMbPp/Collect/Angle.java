package edu.brown.cs.bdGaMbPp.Collect;


public class Angle {
	
	private double degrees;
	private double rotationSpeed;
	
	public Angle(double startDegrees, double rotateSpeed) {
		setDegrees(startDegrees);
		rotationSpeed = rotateSpeed;
	}
	
	public void rotateClockwise() {
		degrees = degrees - rotationSpeed;
		if (degrees < 0) {
			degrees = degrees + 360;
		}
	}
	
	public void rotateCounterClockwise() {
		degrees = degrees + rotationSpeed;
		if (degrees > 360) {
			degrees = degrees - 360;
		}
	}
	
	public double getDegrees() {
		return degrees;
	}
	
	public void setDegrees(double newAngle) {
		if (newAngle < 0) {
			int rotations = ((int) (newAngle / -360)) + 1;
			for (int i = 0; i < rotations; i++) {
				newAngle += 360;
			}
		}
			degrees = newAngle % 360.0;
	}
	
	private double toRadians() {
		return ((degrees * Math.PI) / 180);
	}
	
	public double getSin() {
		return Math.sin(toRadians());
	}
	
	public double getCos() {
		return Math.cos(toRadians());
	}
	
	
	

}
