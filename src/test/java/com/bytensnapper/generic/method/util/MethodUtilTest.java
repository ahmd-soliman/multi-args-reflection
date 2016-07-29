package com.bytensnapper.generic.method.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.bytensnapper.generic.method.model.Car;
import com.bytensnapper.generic.method.model.Person;
import com.bytensnapper.generic.method.model.Pet;

public class MethodUtilTest {

	private final String personClassName = "com.bytensnapper.generic.method.model.Person";
	private final String carClassName = "com.bytensnapper.generic.method.model.Car";

	@Test
	public void testCreateInstance() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Person person = MethodUtil.createInstance(personClassName);
		Assert.assertNotNull(person);

	}

	@Test(expected = ClassCastException.class)
	public void testCreateInstanceClassCastException() throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {

		Person person = MethodUtil.createInstance(carClassName);
	}

	@Test
	public void testCreateInstanceWithArgs() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		Class<?>[] argClasses = new Class[] { Car.class, Pet.class };

		Car personalCar = new Car();
		personalCar.setBrand("BMW");
		personalCar.setColor("Silver");
		personalCar.setModelName("i8");

		Pet cutePet = new Pet();
		cutePet.setAnimalType("Cat");
		cutePet.setPetName("Kitty");
		cutePet.setSize(Pet.Size.SMALL);

		Object[] args = new Object[] { personalCar, cutePet };

		Person person = MethodUtil.createInstanceWithArgs(personClassName, argClasses, args);

		Assert.assertNotNull(person.getCar());
		Assert.assertNotNull(person.getPet());

		Assert.assertEquals("BMW", person.getCar().getBrand());
		Assert.assertEquals("Silver", person.getCar().getColor());
		Assert.assertEquals("i8", person.getCar().getModelName());

		Assert.assertEquals("Cat", person.getPet().getAnimalType());
		Assert.assertEquals("Kitty", person.getPet().getPetName());
		Assert.assertEquals(Pet.Size.SMALL, person.getPet().getSize());
	}

	@Test(expected = NoSuchMethodException.class)
	public void testCreateInstanceWithArgsWrongArgClasses() throws ClassNotFoundException, NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {

		/*
		 * Wrong order for argument class added to argClasses causes
		 * NoSuchMethodException
		 */
		Class<?>[] argClasses = new Class[] { Pet.class, Car.class };

		Car personalCar = new Car();
		personalCar.setBrand("BMW");
		personalCar.setColor("Silver");
		personalCar.setModelName("i8");

		Pet cutePet = new Pet();
		cutePet.setAnimalType("Cat");
		cutePet.setPetName("Kitty");
		cutePet.setSize(Pet.Size.SMALL);

		Object[] args = new Object[] { personalCar, cutePet };

		Person person = MethodUtil.createInstanceWithArgs(personClassName, argClasses, args);

		Assert.assertNotNull(person.getCar());
		Assert.assertNotNull(person.getPet());

		Assert.assertEquals("BMW", person.getCar().getBrand());
		Assert.assertEquals("Silver", person.getCar().getColor());
		Assert.assertEquals("i8", person.getCar().getModelName());

		Assert.assertEquals("Cat", person.getPet().getAnimalType());
		Assert.assertEquals("Kitty", person.getPet().getPetName());
		Assert.assertEquals(Pet.Size.SMALL, person.getPet().getSize());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateInstanceWithArgsWrongArgs() throws ClassNotFoundException, NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {

		Class<?>[] argClasses = new Class[] { Car.class, Pet.class };

		Car personalCar = new Car();
		personalCar.setBrand("BMW");
		personalCar.setColor("Silver");
		personalCar.setModelName("i8");

		Pet cutePet = new Pet();
		cutePet.setAnimalType("Cat");
		cutePet.setPetName("Kitty");
		cutePet.setSize(Pet.Size.SMALL);

		/*
		 * Wrong order for arguments added to args causes
		 * IllegalArgumentException: argument type mismatch
		 */
		Object[] args = new Object[] { cutePet, personalCar };

		Person person = MethodUtil.createInstanceWithArgs(personClassName, argClasses, args);

		Assert.assertNotNull(person.getCar());
		Assert.assertNotNull(person.getPet());

		Assert.assertEquals("BMW", person.getCar().getBrand());
		Assert.assertEquals("Silver", person.getCar().getColor());
		Assert.assertEquals("i8", person.getCar().getModelName());

		Assert.assertEquals("Cat", person.getPet().getAnimalType());
		Assert.assertEquals("Kitty", person.getPet().getPetName());
		Assert.assertEquals(Pet.Size.SMALL, person.getPet().getSize());
	}

	@Test
	public void testAddToList() {

		Person person = new Person();
		person.setName("Ahmed");
		person.setEmailAddress("ahmed.hosny@bytesnapper.com");
		person.setAddress("Giza,Egypt");

		List<Person> personList = new ArrayList<Person>();

		MethodUtil.addToList(person, personList);
		Assert.assertTrue(!personList.isEmpty());
		Assert.assertEquals("Ahmed", personList.get(0).getName());
		Assert.assertEquals("Giza,Egypt", personList.get(0).getAddress());
		Assert.assertEquals("ahmed.hosny@bytesnapper.com", personList.get(0).getEmailAddress());

		Car car = new Car();
		car.setBrand("Audi");
		car.setColor("Red");
		car.setModelName("R8");

		List<Car> carList = new ArrayList<Car>();

		MethodUtil.addToList(car, carList);

		Assert.assertTrue(!carList.isEmpty());
		Assert.assertEquals("Audi", carList.get(0).getBrand());
		Assert.assertEquals("Red", carList.get(0).getColor());
		Assert.assertEquals("R8", carList.get(0).getModelName());

	}

	@Test
	public void testprintInfo() {

		ByteArrayOutputStream outSream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outSream));

		Person person1 = new Person();
		person1.setName("Ahmed");
		person1.setEmailAddress("ahmed.hosny@bytesnapper.com");
		person1.setAddress("Giza,Egypt");

		Person person2 = new Person();
		person2.setName("Ali");
		person2.setEmailAddress("ali@bytesnapper.com");
		person2.setAddress("Cairo,Egypt");

		List<Person> personList = new ArrayList<Person>();

		personList.add(person1);
		personList.add(person2);
		MethodUtil.printInfo(personList);
		// expected output including \n characters
		String expected = "Ahmed;Giza,Egypt;ahmed.hosny@bytesnapper.com\nAli;Cairo,Egypt;ali@bytesnapper.com\n";
		Assert.assertEquals(expected, outSream.toString());
		// reset output stream to original state
		System.setOut(System.out);
	}
}
