package com.bytensnapper.generic.method.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class MethodUtil {

	/**
	 * A generic method used to create in instance of any class by reflection using default
	 * constructor by passing T type Class
	 * 
	 * @param clazz
	 *            Class for the T type
	 * @return T instance of Class T type
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static <T> T createInstance(Class<T> clazz) throws InstantiationException, IllegalAccessException {

		return clazz.newInstance();

	}

	/**
	 * A generic method used to create in instance of any class by reflection using default
	 * constructor by passing full qualified class name of T type
	 * 
	 * @param className
	 *            full qualified class name of T type
	 * @return T instance of Class T type
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public static <T> T createInstance(String className) throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {

		@SuppressWarnings("unchecked")
		Class<T> clazz = (Class<T>) Class.forName(className);
		return clazz.newInstance();
	}

	/**
	 * A generic method used to create in instance of any class by reflection using constructor
	 * matching argument types and passing these arguments
	 * 
	 * @param className
	 *            full qualified class name of T type
	 * @param argClasses
	 *            Class[] array of constructor argument classes to matching
	 *            constructor
	 * @param args
	 *            Object[] array of constructor arguments
	 * @return T instance of Class T type
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodExceptionconstructor
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static <T> T createInstanceWithArgs(String className, Class<?>[] argClasses, Object[] args)
			throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		@SuppressWarnings("unchecked")
		Class<T> clazz = (Class<T>) Class.forName(className);
		Constructor<T> constructor = clazz.getConstructor(argClasses);
		T createdInstance = constructor.newInstance(args);
		return createdInstance;

	}

	/**
	 * A generic method used to add any type T of object to list of the same type
	 * T
	 * 
	 * @param t
	 *            instance of class T
	 * @param list
	 *            List{@code<T>} of class T
	 * @return List{@code<T>} after adding new element
	 */
	public static <T> List<T> addToList(T t, List<T> list) {
		list.add(t);
		return list;
	}
	
	/**
	 * A generic method used to add loop print toString method
	 * @param list
	 */
	public static <E> void printInfo(List<E> list) {
		for (E element : list) {
			System.out.println(element.toString());
		}
	}
}
