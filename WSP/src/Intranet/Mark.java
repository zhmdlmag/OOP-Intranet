package Intranet ;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import Enums.LetterGrade;
import Enums.TraditionalGrade;
import Exceptions.InvalidMarkException;

public class Mark implements Serializable
{	
	private static HashMap<Integer, LetterGrade> letterGrade = new HashMap<Integer, LetterGrade>();
		
	private static HashMap<Integer, TraditionalGrade> traditionalGrade = new HashMap<Integer, TraditionalGrade>();
		
	private static HashMap<Integer, Double> gpa = new HashMap<Integer, Double>();
		
	private double firstAttestationGrade;
		
	private double secondAttestationGrade;
		
	private double finalGrade;
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(Double.toString(value));
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	public static double average(double value, int count) {
		return Mark.round((count > 0) ? value / count : 0, 2);
	}
		
	private boolean complete = false;
	
	static {
		letterGrade.put(0, LetterGrade.F);
		letterGrade.put(1, LetterGrade.F);
		letterGrade.put(2, LetterGrade.F);
		letterGrade.put(3, LetterGrade.F);
		letterGrade.put(4, LetterGrade.F);
		letterGrade.put(5, LetterGrade.F);
		letterGrade.put(6, LetterGrade.F);
		letterGrade.put(7, LetterGrade.F);
		letterGrade.put(8, LetterGrade.F);
		letterGrade.put(9, LetterGrade.F);
		letterGrade.put(10, LetterGrade.D);
		letterGrade.put(11, LetterGrade.D_PLUS);
		letterGrade.put(12, LetterGrade.C_MINUS);
		letterGrade.put(13, LetterGrade.C);
		letterGrade.put(14, LetterGrade.C_PLUS);
		letterGrade.put(15, LetterGrade.B_MINUS);
		letterGrade.put(16, LetterGrade.B);
		letterGrade.put(17, LetterGrade.B_PLUS);
		letterGrade.put(18, LetterGrade.A_MINUS);
		letterGrade.put(19, LetterGrade.A);
		letterGrade.put(20, LetterGrade.A);
		
		traditionalGrade.put(0, TraditionalGrade.FAILURE);
		traditionalGrade.put(1, TraditionalGrade.FAILURE);
		traditionalGrade.put(2, TraditionalGrade.FAILURE);
		traditionalGrade.put(3, TraditionalGrade.FAILURE);
		traditionalGrade.put(4, TraditionalGrade.FAILURE);
		traditionalGrade.put(5, TraditionalGrade.FAILURE);
		traditionalGrade.put(6, TraditionalGrade.FAILURE);
		traditionalGrade.put(7, TraditionalGrade.FAILURE);
		traditionalGrade.put(8, TraditionalGrade.FAILURE);
		traditionalGrade.put(9, TraditionalGrade.FAILURE);
		traditionalGrade.put(10, TraditionalGrade.NEEDS_IMPROVEMENT);
		traditionalGrade.put(11, TraditionalGrade.NEEDS_IMPROVEMENT);
		traditionalGrade.put(12, TraditionalGrade.AVERAGE);
		traditionalGrade.put(13, TraditionalGrade.AVERAGE);
		traditionalGrade.put(14, TraditionalGrade.AVERAGE);
		traditionalGrade.put(15, TraditionalGrade.GOOD);
		traditionalGrade.put(16, TraditionalGrade.GOOD);
		traditionalGrade.put(17, TraditionalGrade.GOOD);
		traditionalGrade.put(18, TraditionalGrade.EXCELENT);
		traditionalGrade.put(19, TraditionalGrade.EXCELENT);
		traditionalGrade.put(20, TraditionalGrade.EXCELENT);
		
		gpa.put(0, 0.0);
		gpa.put(1, 0.0);
		gpa.put(2, 0.0);
		gpa.put(3, 0.0);
		gpa.put(4, 0.0);
		gpa.put(5, 0.0);
		gpa.put(6, 0.0);
		gpa.put(7, 0.0);
		gpa.put(8, 0.33);
		gpa.put(9, 0.67);
		gpa.put(10, 1.0);
		gpa.put(11, 1.33);
		gpa.put(12, 1.67);
		gpa.put(13, 2.0);
		gpa.put(14, 2.33);
		gpa.put(15, 2.67);
		gpa.put(16, 3.0);
		gpa.put(17, 3.33);
		gpa.put(18, 3.67);
		gpa.put(19, 4.0);
		gpa.put(20, 4.0);
	}

	public Mark() {
		
	}
	
	public double getDigitalGrade() {
		return firstAttestationGrade + firstAttestationGrade + finalGrade;	
	}
		
	public LetterGrade getLetterGrade() {
		if (!isComplete()) return LetterGrade.NULL;
		Integer val = (int) Math.round(getDigitalGrade() / 5);
		return letterGrade.get(val);	
	}
		
	public TraditionalGrade getTraditionalGrade() {
		if (!isComplete()) return TraditionalGrade.NULL;
		Integer val = (int) Math.round(getDigitalGrade() / 5);
		return traditionalGrade.get(val);	
	}
		
	public double getGpa() {
		if (!isComplete()) return 0;
		Integer val = (int) Math.round(getDigitalGrade() / 5);
		return gpa.get(val);	
	}
		
	public double getFirstAttestationGrade() {
		return firstAttestationGrade;	
	}
	
	public void setFirstAttestationGrade(double firstAttestationGrade) throws InvalidMarkException {
		if (isComplete()) {
			throw new InvalidMarkException("Course already complete!");
		} else if (firstAttestationGrade < 0) {
			throw new InvalidMarkException("Mark cannot be negative!");
		} else if (firstAttestationGrade > 30) {
			throw new InvalidMarkException("First attestation mark cannot exceed 30!");
		} else {
			this.firstAttestationGrade = firstAttestationGrade;			
		}
	}
		
	public double getSecondAttestationGrade() {
		return secondAttestationGrade;	
	}
	
	public void setSecondAttestationGrade(double secondAttestationGrade) throws InvalidMarkException {
		IOUtils.writer.println(isComplete());
		if (isComplete()) {
			throw new InvalidMarkException("Course already complete!");
		} else if (secondAttestationGrade < 0) {
			throw new InvalidMarkException("Mark cannot be negative!");
		} else if (secondAttestationGrade > 30) {
			throw new InvalidMarkException("Second attestation mark cannot exceed 30!");
		} else {
			this.secondAttestationGrade = secondAttestationGrade;			
		}
	}
		
	public double getFinalGrade() {
		return finalGrade;	
	}
	
	public boolean isComplete() {
		return complete;
	}
	
	public String isCompleteToString() {
		if (complete) {
			return "Complete";
		}
		return "Not complete";
	}
	
	public void setComplete(boolean complete) {
		complete = true;
		this.complete = complete;
	}
	
	public void setFinalGrade(double finalGrade) throws InvalidMarkException {
		if (isComplete()) {
			throw new InvalidMarkException("Course already complete!");
		} else if (finalGrade < 0) {
			throw new InvalidMarkException("Mark cannot be negative!");
		} else if (finalGrade > 40) {
			throw new InvalidMarkException("Final mark cannot exceed 40!");
		} else {		
			setComplete(true);
			this.finalGrade = finalGrade;
		}
	}
		
	public String toString() {
		return String.format("%s %s %s %s", firstAttestationGrade, secondAttestationGrade, finalGrade, isCompleteToString());	
	}
	
}