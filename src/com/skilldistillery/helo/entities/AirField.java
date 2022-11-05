package com.skilldistillery.helo.entities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AirField {

	private String line;
	private ArrayList<Helicopter> fleet;

	public AirField() {
		fleet = readFromFile("helicopter.txt");
	}

	public ArrayList<Helicopter> readFromFile(String fileName) {
		try (BufferedReader bufIn = new BufferedReader(new FileReader(fileName))) {
			fleet = new ArrayList<>();
			while ((line = bufIn.readLine()) != null) {
				String[] heloStats = line.split(", ");
				if (heloStats[0].equals("Attack")) {
					AttackHelicopter tempHelo = new AttackHelicopter(heloStats[1], Double.parseDouble(heloStats[2]),
							Integer.parseInt(heloStats[3]), Long.parseLong(heloStats[4]));
					fleet.add(tempHelo);
				} else if (heloStats[0].equals("Cargo")) {
					CargoHelicopter tempHelo = new CargoHelicopter(heloStats[1], Double.parseDouble(heloStats[2]),
							Integer.parseInt(heloStats[3]), Long.parseLong(heloStats[4]));
					fleet.add(tempHelo);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return fleet;

	}

	public void printAllHelicopters() {
		for (Helicopter helo : fleet) {
			System.out.println(helo);
		}
	}

	public ArrayList<Helicopter> getFleet() {
		return this.fleet;
	}

	public Helicopter getFastest() {
		Helicopter fastest = fleet.get(0);
		for (Helicopter helo : fleet) {
			if (helo.getSpeedinMPH() > fastest.getSpeedinMPH()) {
				fastest = helo;
			}
		}
		return fastest;
	}

	public Helicopter getRangeyest() {
		Helicopter rangeyest = fleet.get(0);
		for (Helicopter helo : fleet) {
			if (helo.getRange() > rangeyest.getRange()) {
				rangeyest = helo;
			}
		}
		return rangeyest;
	}
	@Override
	public String toString() {
		return "AirField [line=" + line + ", fleet=" + fleet + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(fleet, line);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AirField other = (AirField) obj;
		return Objects.equals(fleet, other.fleet) && Objects.equals(line, other.line);
	}

	public void addHelo(Helicopter helo) {
		fleet.add(helo);
		
	}

	public boolean removeHelo(String heloToDelete) {
		int index = 0;
		boolean deleted = false;
		for (Helicopter helo : fleet) {
			if (heloToDelete.equalsIgnoreCase(helo.getModel())) {
				fleet.remove(index);
				deleted = true;
				break;
			}
			index++;
		}
		return deleted;
	}

}
