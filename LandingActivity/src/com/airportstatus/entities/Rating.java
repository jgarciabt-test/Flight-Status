package com.airportstatus.entities;

public class Rating {

	private String observations = "";
	private String onTime = "";
	private String late15 = "";
	private String late30 = "";
	private String late45 = "";
	private String cancelled = "";
	private String diverted = "";
	private String delayMin = "";
	private String delayMax = "";
	private String onTimeStars = "";
	
	public Rating(){
		
	}

	public String getLate15() {
		return late15;
	}

	public void setLate15(String late15) {
		this.late15 = late15;
	}

	public String getLate30() {
		return late30;
	}

	public void setLate30(String late30) {
		this.late30 = late30;
	}

	public String getLate45() {
		return late45;
	}

	public void setLate45(String late45) {
		this.late45 = late45;
	}

	public String getCancelled() {
		return cancelled;
	}

	public void setCancelled(String cancelled) {
		this.cancelled = cancelled;
	}

	public String getDiverted() {
		return diverted;
	}

	public void setDiverted(String diverted) {
		this.diverted = diverted;
	}
	
	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public String getOnTime() {
		return onTime;
	}

	public void setOnTime(String onTime) {
		this.onTime = onTime;
	}

	public String getDelayMin() {
		return delayMin;
	}

	public void setDelayMin(String delayMin) {
		this.delayMin = delayMin;
	}

	public String getDelayMax() {
		return delayMax;
	}

	public void setDelayMax(String delayMax) {
		this.delayMax = delayMax;
	}

	public String getOnTimeStars() {
		return onTimeStars;
	}

	public void setOnTimeStars(String onTimeStars) {
		this.onTimeStars = onTimeStars;
	}
	
	
}
