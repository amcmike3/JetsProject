package com.skilldistillery.helo.entities;

public class AttackHelicopter extends Helicopter implements Fighter {


	
	@Override
	public String toString() {
		return "Attack" + super.toString() ;
	}

	public AttackHelicopter(String model, double speed, int range, long price) {
		super(model, speed, range, price);
	}

	public void fight() {
		System.out.println(this.getModel() + " says pew pew BAAAANNNGG!");
	}


}
