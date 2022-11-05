package com.skilldistillery.helo.entities;

public class CargoHelicopter extends Helicopter implements Cargo{

	@Override
	public String toString() {
		return "Cargo " + super.toString() ;
	}

	public CargoHelicopter(String model, double speed, int range, long price) {
		super(model, speed, range, price);
	}

	public void loadCargo() {
		System.out.println("UGH im tired of moving heavy things into this " + this.getModel());
	}


}
