package com.skilldistillery.helo.entities;

public abstract class Helicopter {

	@Override
	public String toString() {
		return "Helicopter model=" + model + ", speed=" + speed + ", range=" + range + ", price=" + price + ".";
	}

	private String model;
	private double speed;
	private int range;
	private long price;

	public Helicopter(String model, double speed, int range, long price) {
		this.model = model;
		this.speed = speed;
		this.range = range;
		this.price = price;
	}

	public void fly() {
		 System.out.println("The " + this.model + " can fly for " + this.range + " miles at a top speed of " + this.speed);

	}


	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public double getSpeedinMPH() {

		return speed;
	}

}
