package Intranet;

import java.util.Vector;

public class IOUtils {
	public static FastReader input = new FastReader();
	public static FastWriter writer = new FastWriter();
	public static String toString(Object object) {
		if (object == null) {
			return "null";
		}
		return object.toString();
	}
	
	public static void waitKey() {
		IOUtils.writer.print("[X]  Press any key to escape: ");
		String key = IOUtils.input.nextLine();
	}
	
	public static <E> E read(Vector<E> objects, String text) {
		IOUtils.writer.print(String.format("[X]  Choose %s: ", text));
		Integer index = input.nextInt();
		if (index >= 1 && index <= objects.size()) {
			return objects.get(index - 1);
		}
		writer.println("Incorrect index!");
		return null;
	}
	
	public static <E> E readArray(E[] objects, String text) {
		IOUtils.writer.print(String.format("[X]  Choose %s: ", text));
		Integer index = input.nextInt();
		if (index >= 1 && index < objects.length) {
			return objects[index];
		}
		writer.println("Incorrect index!");
		return null;
	}
	
	public static Double readDouble(String text) {
		IOUtils.writer.print(String.format("[X]  Type %s: ", text));
		Double value = input.nextDouble();
		return value;
	}
	
	public static Integer readInteger(String text) {
		IOUtils.writer.print(String.format("[X]  Type %s: ", text));
		Integer value = input.nextInt();
		return value;
	}
	
	public static String readString(String text) {
		IOUtils.writer.print(String.format("[X]  Type %s: ", text));
		String value = input.nextLine();
		return value;
	}
	
	public static void remove(String item, Boolean removed) {
		if (removed) {
			IOUtils.writer.println(String.format("%s removed!", item));
		} else {
			IOUtils.writer.println(String.format("%s not found!", item));
		}
	}
}