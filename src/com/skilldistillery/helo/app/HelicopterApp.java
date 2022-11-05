package com.skilldistillery.helo.app;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.skilldistillery.helo.entities.AirField;
import com.skilldistillery.helo.entities.AttackHelicopter;
import com.skilldistillery.helo.entities.Cargo;
import com.skilldistillery.helo.entities.CargoHelicopter;
import com.skilldistillery.helo.entities.Fighter;
import com.skilldistillery.helo.entities.Helicopter;

public class HelicopterApp {
	private AirField airField;
	private Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		HelicopterApp helo = new HelicopterApp();
		helo.launch();
	}

	public void launch() {

			
			airField = new AirField();
			int choice = 0;
			while (choice != 9) {
				displayUserMenu();
				try {
				choice = scanner.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("unrecognized response please try again:");
					choice = 0;
					scanner.nextLine();
				}
				if (choice == 1) {
					airField.printAllHelicopters();
				} else if (choice == 2) {
					makeEmAllFly(airField);
				} else if (choice == 3) {
					Helicopter fastest = airField.getFastest();
					System.out.println("The " + fastest.getModel() + " is the fastest with a top speed of "
							+ fastest.getSpeed() + ".");
					System.out.println("Here is the rest of its info if you're interested.. \n" + fastest);
				} else if (choice == 4) {
					Helicopter rangeyest = airField.getRangeyest();
					System.out.println(
							"The " + rangeyest.getModel() + " has the longest range of " + rangeyest.getRange() + ".");
					System.out.println("Here is the rest of its info if you're interested.. \n" + rangeyest);
					
				} else if (choice == 5) {
					loadEmUp(airField);
				} else if (choice == 6) {
					dogFight(airField);
				} else if (choice == 7) {
					Helicopter helo = createHelo();
					airField.addHelo(helo);
				} else if (choice == 8) {
					deleteHelo();
				} else if (choice == 9) {
					System.out.println("GoodBye");
					break;
				} else if (choice == 0) {
					displayUserMenu();
				} else if (choice == 10) {
					flyOne();
				} else if (choice == 11) {
					hirePilot();
				} else if (choice == 12) {
					save();
				} else {
					System.out.println("unrecognized response please try again:");
				}
			}
	}

	private void save() {
		try {
		    FileWriter fw = new FileWriter("helicopter.txt");
		    PrintWriter pw = new PrintWriter(fw);
		    for (Helicopter helo : airField.getFleet()) {
		    	if (helo instanceof Fighter) {
		    		pw.print("Attack, ");
		    	} else if (helo instanceof Cargo) {
		    		pw.print("Cargo, ");
		    	}
		    	
		    	
		      pw.println(helo.getModel() + ", " + helo.getSpeed() + ", " + helo.getRange() + ", " + helo.getPrice());
		    }
		    System.out.println("Succussfully Saved");
		    pw.close();
		  }
		  catch (IOException e) {
		    e.printStackTrace();
		  }

		
	}

	private void hirePilot() {
		System.out.println("Enter Name of pilot you want to hire: ");
		String name = scanner.nextLine();
		System.out.println("One moment while we Review his application...");
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			System.out.println(e);
		}
		if (Helicopter.pilots.add(name)) {
			System.out.println("Looks like a great candidate and he can start now!");
		} else {
			System.out.println("Sorry we found some issues on his resume, we will pass on this one.");
		}
		
		
	}

	private void flyOne() {
		ArrayList<Helicopter> helos = airField.getFleet();
		airField.printAllHelicopters();
		System.out.println("Please enter model of Helicopter you'd like to fly");
		String heloName = scanner.nextLine();
		boolean flown = false;
		for (Helicopter helo : helos) {
			if (helo.getModel().equalsIgnoreCase(heloName)) {
				helo.fly();
				flown = true;
				break;
			}
		}
		if (!flown) {
			System.out.println("Sorry couldn't find that specific Helicopter in the AirField.");
			System.out.println("maybe you could add it..");
		}
	}

	private void deleteHelo() {
		airField.printAllHelicopters();
		System.out.println("Please enter the model of the Helicopter you wish to delete");
		String heloToDelete = scanner.next();
		System.out.println(heloToDelete);
		scanner.nextLine();
		if (airField.removeHelo(heloToDelete)) {
			System.out.println("Successfully deleted.");
		} else {
			System.out.println("Delete failed.");
		}
	}

	private Helicopter createHelo() {
		Helicopter helo = null;
		System.out.println("Please enter A for an attack helicoptor or C for cargo helicopter");
		String type = scanner.next();
		scanner.nextLine();
		while (!(type.equalsIgnoreCase("A")) && !(type.equalsIgnoreCase("C"))) {
			System.out.println("Sorry that is not a valid option A for Attack or C for Cargo");
			type = scanner.next();
			System.out.println(type);
			scanner.nextLine();
			
		}
		System.out.println("Please Enter Model");
		String model = scanner.nextLine();
		System.out.println("Please Enter Top Speed");
		double speed = scanner.nextDouble();
		System.out.println("Please Enter Range");
		int range = scanner.nextInt();
		System.out.println("Please Enter Price");
		long price = scanner.nextLong();
		if (type.equalsIgnoreCase("A")) {
			helo = new AttackHelicopter(model, speed, range, price);	
		} else if (type.equalsIgnoreCase("C")) {
			helo = new CargoHelicopter(model, speed, range, price);	
		}
		return helo;
	}

	private void loadEmUp(AirField airField2) {
		ArrayList<Helicopter> helos = airField.getFleet();
		for (Helicopter helo : helos) {
			if (helo instanceof Cargo) {

				((CargoHelicopter) helo).loadCargo();
			}
		}

	}

	private void dogFight(AirField airField2) {
		ArrayList<Helicopter> helos = airField.getFleet();
		for (Helicopter helo : helos) {
			if (helo instanceof Fighter) {

				((AttackHelicopter) helo).fight();
			}
		}

	}

	private void makeEmAllFly(AirField airField) {
		ArrayList<Helicopter> helos = airField.getFleet();
		for (Helicopter helo : helos) {
			helo.fly();
		}
	}

	public void displayUserMenu() {
		System.out.println("Enter number corresponding to menu choice:");
		System.out.println("1. List fleet\n" + "2. Fly all helicopters\n" + "3. View fastest helicopter\n"
				+ "4. View helicopter with longest range\n" + "5. Load all Cargo helicopters\n" + "6. Dogfight!\n"
				+ "7. Add a helicopter to Fleet\n" + "8. Remove a helicopter from Fleet\n" + "9. Quit"
				+ "\n0. see menu again" + "\n10. Pick a Helicopter to fly" + "\n11. Hire a new pilot"
				+ "\n12. save existing helicopters");
	}

}
