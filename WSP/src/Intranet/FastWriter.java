package Intranet;

import java.io.PrintWriter;

public class FastWriter {
	private PrintWriter writer = new PrintWriter(System.out);
	
	public FastWriter() {
		// TODO Auto-generated constructor stub
	}
	
	public void println(Object o) {
		writer.println(o);
		writer.flush();
	}
	
	public void print(Object o) {
		writer.print(o);
		writer.flush();
	}
}
