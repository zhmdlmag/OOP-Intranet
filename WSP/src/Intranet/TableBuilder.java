package Intranet;

import java.util.ArrayList;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.stream.Stream;

import Interfaces.Extractable;

public class TableBuilder {
	
	public static String[] messageHeaders = new String[] {"Title", "From", "To", "Message", "Date"};
	
	public static String[] teacherHeaders = new String[] {"Firstname", "Lastname", "Title", "Faculty", "Rating"};
	
	public static String[] studentHeaders = new String[] {"Firstname", "Lastname", "Year of study", "Faculty", 
					"First Attestation", "Second Attestation", "Final"};
	
	public static String[] studentStatisticHeaders = new String[] {"Firstname", "Lastname", "Year of study", "Faculty", 
			"Total GPA"};
	
	public static String[] lessonHeaders = new String[] {"Lesson type", "Course", "Day", "Start time", "End time"};
	
	public static String[] bookHeaders = new String[] {"Name", "Author", "Content", "Count in stock"};
	
	public static String[] orderHeaders = new String[] {"Student", "Date", "Book", "Book Id"};
	
	public static String[] courseFileHeaders = new String[] {"Name", "Date", "Content"};
	
	public static String[] userHeaders = new String[] {"Firstname", "Lastname", "Login", "Password"};
	
	public static String[] transcriptHeaders = new String[] {"Course", "Digital mark", "Traditional mark", "Letter mark", "GPA", "Is complete", "Period of study"};

	public static String[] courseHeaders = new String[] {"Name", "Cypher", "Credits", "ECTS", "Faculty", "Teacher", "Students", "Description"};
	
	public static String[] newsHeaders = new String[] {"Title", "Type", "Date", "Info"};
	
	public static String[] requestHeaders = new String[] {"Request type", "From", "To", "Date"};
	
	public static String[] responseHeaders = new String[] {"Request type", "From", "To", "Date", "Response"};
	
	public static String[] logHeaders = new String[] {"User", "Action", "Date"};
	
	public static String[] statisticalReportHeaders = new String[] {"Variables", "N", "Mean", "Min", "Max"};
	
	public static String blank = "#";
	
	public static <E extends Extractable> void buildTable(Object[] headers, Vector<E> extractables, String mainHeader) {
		List<Object[]> tableList = new ArrayList<Object[]>();
		tableList.add(headers);
		tableList.addAll(TableBuilder.extractData(extractables));
		buildTable(tableList, mainHeader);
	}
	
	public static <E extends Extractable> void buildTable(Object[] headers, Vector<E> extractables) {
		buildTable(headers, extractables, blank);
	}
	
	public static <E extends Extractable> void buildTable(Object[] headers, E extractable, String mainHeader) {
		List<Object[]> tableList = new ArrayList<Object[]>();
		tableList.add(headers);
		tableList.add(extractable.extractData());
		buildTable(tableList, mainHeader);
	}
	
	public static <E extends Extractable> void buildTable(Object[] headers, E extractable) {
		buildTable(headers, extractable, blank);
	}
	
	public static void buildOptionsTable(String[] options) {
		IOUtils.writer.println("\n     " + options[0]);
		for (int i = 1; i < options.length; ++i) {
			IOUtils.writer.println("[" + i + "]  " + options[i]);
		}
		IOUtils.writer.println("");
	}
	
	public static <E extends Extractable> ArrayList<Object[]> extractData(Vector<E> extractables) {
		ArrayList<Object[]> objects = new ArrayList<Object[]>();
		for (int i = 0; i < extractables.size(); ++i) {
			Extractable extractable = (Extractable) extractables.get(i);
			objects.add(extractable.extractData());
		}
		return objects;
	}

	public static String buildLine(Map<Integer, Integer> columnLengths, String leftCross, String middleCross, String rightCross) {
		String line = columnLengths.entrySet().stream().reduce("", (ln, b) -> {
			String templn = "";
			if (ln.isBlank()) {
				templn = leftCross + "━";
			} else {
				templn = middleCross + "━";
			}
			templn = templn + Stream.iterate(0, (i -> i < b.getValue()), (i -> ++i)).reduce("", (ln1, b1) -> ln1 + "━",
					(a1, b1) -> a1 + b1);
			templn = templn + "━";
			return ln + templn;
		}, (a, b) -> a + b);
		line = line + rightCross + "\n";
		return line;
	}
	
	public static List<Object[]> transpose(List<Object[]> table) {
		List<Object[]> tableList = new ArrayList<Object[]>();
		Object[][] finalTable = new Object[table.get(0).length][table.size()];
		for (int i = 0; i < table.size(); ++i) {
			for (int j = 0; j < table.get(i).length; ++j) {
				finalTable[j][i] = table.get(i)[j];
			}
		}
		for (Object[] strings : finalTable) {
			tableList.add(strings);
		}
		return tableList;
	}
	
	public static List<String[]> processTable(List<Object[]> table) {
		List<String[]> tableList = new ArrayList<String[]>();
		
		int iterator = 0;
		for (Object[] objects : table) {
			String[] row = new String[objects.length + 1];
			if (iterator > 0) {
				row[0] = IOUtils.toString(iterator);				
			} else {
				row[0] = "ID";
			}
			iterator++;
			for (int i = 1; i <= objects.length; ++i) {
				row[i] = IOUtils.toString(objects[i - 1]);
			}
			tableList.add(row);
		}
		return tableList;
	}
	
	public static void buildTable(List<Object[]> table) {
		buildTable(table, blank);
	}
	
	public static String centerString (int width, String s) {
	    return String.format("%-" + width  + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
	}
	
	public static void buildTable(List<Object[]> table, String mainHeader) {
		
		boolean leftJustifiedRows = true;
		
		int maxWidth = 30;
		
		List<String[]> tableList = processTable(table);
		
		List<String[]> finalTableList = new ArrayList<>();
		for (String[] row : tableList) {
			boolean needExtraRow = false;
			int splitRow = 0;
			do {
				needExtraRow = false;
				String[] newRow = new String[row.length];
				for (int i = 0; i < row.length; i++) {
					if (row[i].length() < maxWidth) {
						newRow[i] = splitRow == 0 ? row[i] : "";
					} else if ((row[i].length() > (splitRow * maxWidth))) {
						int end = row[i].length() > ((splitRow * maxWidth) + maxWidth)
								? (splitRow * maxWidth) + maxWidth
								: row[i].length();
						newRow[i] = row[i].substring((splitRow * maxWidth), end);
						needExtraRow = true;
					} else {
						newRow[i] = "";
					}
				}
				finalTableList.add(newRow);
				if (needExtraRow) {
					splitRow++;
				}
			} while (needExtraRow);
		}
		
		String[][] finalTable = new String[finalTableList.size()][finalTableList.get(0).length];
		for (int i = 0; i < finalTable.length; i++) {
			finalTable[i] = finalTableList.get(i);
		}
	
		Map<Integer, Integer> columnLengths = new HashMap<>();
		Arrays.stream(finalTable).forEach(a -> Stream.iterate(0, (i -> i < a.length), (i -> ++i)).forEach(i -> {
			if (columnLengths.get(i) == null) {
				columnLengths.put(i, 0);
			}
			if (columnLengths.get(i) < a[i].length()) {
				columnLengths.put(i, a[i].length());
			}
		}));

		final StringBuilder formatString = new StringBuilder("");
		String flag = leftJustifiedRows ? "-" : "";
		columnLengths.entrySet().stream().forEach(e -> formatString.append("┃ %" + flag + e.getValue() + "s "));
		formatString.append("┃\n");

		String upperLine = buildLine(columnLengths, "┏", "┳", "┓");
		
		String middleLine = buildLine(columnLengths, "┣", "╋", "┫");
		
		String bottomLine = buildLine(columnLengths, "┗", "┻", "┛"); 
		
		if (mainHeader != blank) {
			Map<Integer, Integer> mainHeaderColumnLengths = new HashMap<>();
			int length = bottomLine.length() - 5;
			mainHeaderColumnLengths.put(0, length);
			String mainHeaderUpperLine = buildLine(mainHeaderColumnLengths,  "┏", "┳", "┓");
			String transitionLine = buildLine(columnLengths,  "┣", "┳", "┫");
			System.out.print(mainHeaderUpperLine);
			System.out.print("┃ " + centerString(length, mainHeader) + " ┃\n");
			System.out.print(transitionLine);
		} else {
			System.out.print(upperLine);			
		}

		Arrays.stream(finalTable).limit(1).forEach(a -> System.out.printf(formatString.toString(), a));
		System.out.print(middleLine);
	 
		Stream.iterate(1, (i -> i < finalTable.length), (i -> ++i))
				.forEach(a -> System.out.printf(formatString.toString(), finalTable[a]));
		System.out.print(bottomLine);
	}
}