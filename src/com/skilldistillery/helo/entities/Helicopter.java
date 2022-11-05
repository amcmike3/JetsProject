package com.skilldistillery.helo.entities;

import java.util.ArrayList;

public abstract class Helicopter {

	@Override
	public String toString() {
		return "Helicopter model=" + model + ", speed=" + speed + ", range=" + range + ", price=" + price + ", pilot=" + this.pilot + ".";
	}

	private String model;
	private double speed;
	private int range;
	private long price;
	private String pilot;
	public static ArrayList<String> pilots = new ArrayList<>();
	static {
		pilots.add("Viper");
		pilots.add("Noodles"); 
		pilots.add("Pinapple");
		pilots.add("Emotional Damage");
		pilots.add("Cry Baby");
		pilots.add("Top Gun");
		pilots.add("G.O.A.T");
		pilots.add("DogWash");
		pilots.add("Kitty");
		pilots.add("Falcon");
		
	}

	public Helicopter(String model, double speed, int range, long price) {
		int randNum = (int) (Math.random() * pilots.size() );
		this.model = model;
		this.speed = speed;
		this.range = range;
		this.price = price;
		this.setPilot(pilots.get(randNum));
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

	public String getPilot() {
		return pilot;
	}

	public void setPilot(String pilot) {
		this.pilot = pilot;
	}



}
