package com.bytensnapper.reflection.model;

public class Pet {

	public enum Size {
		TINY, SMALL, LARGE;
	}

	private String petName;

	private String animalType;

	private Size size;

	/**
	 * @return the petName
	 */
	public String getPetName() {
		return petName;
	}

	/**
	 * @param petName
	 *            the petName to set
	 */
	public void setPetName(String petName) {
		this.petName = petName;
	}

	/**
	 * @return the animalType
	 */
	public String getAnimalType() {
		return animalType;
	}

	/**
	 * @param animalType
	 *            the animalType to set
	 */
	public void setAnimalType(String animalType) {
		this.animalType = animalType;
	}

	/**
	 * @return the size
	 */
	public Size getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(Size size) {
		this.size = size;
	}
}
