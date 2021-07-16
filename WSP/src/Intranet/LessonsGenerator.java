package Intranet;

import java.util.ArrayList;

import Enums.Day;
import Enums.LessonForm;

/**
 * The LessonsGenerator class provides options of generating lessons
 * of the course added by manager.
 */

public class LessonsGenerator {

	private static ArrayList<Day> days = new ArrayList<>();
	private static ArrayList<LessonForm> lessons = new ArrayList<>();
	
	static {
		days.add(Day.MONDAY);
		days.add(Day.TUESDAY);
		days.add(Day.WEDNESDAY);
		days.add(Day.THURSDAY);
		days.add(Day.FRIDAY);
		days.add(Day.SATURDAY);
		days.add(Day.SUNDAY);
		lessons.add(LessonForm.LECTURE);
		lessons.add(LessonForm.PRACTICE);
		lessons.add(LessonForm.LAB_WORK);
	}
	
	/**
	 * Default LessonGenerator constructor
	 */
	public LessonsGenerator() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Generates lessons automatically, by checking for intersections with other lessons.
	 * Takes course as parameter and retrieves lecture/practice/lab-work hours
	 * to generate lesson.
	 * @param course course created by manager
	 */
	public static void generateLessons(Course course) {
		for (LessonForm lessonForm : lessons) {
			int lessonHours = 0;
			switch (lessonForm) {
				case LECTURE -> lessonHours = course.getLectureHours();
				case LAB_WORK -> lessonHours = course.getLabWorkHours();
				case PRACTICE -> lessonHours = course.getPracticeHours();
			}
			
			if (lessonHours == 0) continue;
			
			outerLoop:
			for (Day day : days) {
				for (int startTime = 8; startTime < 20 - lessonHours; ++startTime) {
					boolean canSet = true;
					int endTime = startTime + lessonHours;
					for (Lesson lesson : DataBase.getInstance().getLessons()) {
						if (intersect(lesson, startTime, endTime, day)) {
							canSet = false;
						}
					}
					if (canSet) {
						DataBase.getInstance().addLesson(new Lesson(lessonForm, startTime, endTime, course, day));
						break outerLoop;
					}
				}
			}
		}	
	}
	/**
	 * Checks whether the lesson is in the specified time interval.
	 * @param lesson lesson we want to check
	 * @param startTime start time of the time interval
	 * @param endTime end time of the time interval
	 * @param day day we check for
	 * @return is lesson in the specified time interval
	 */
	public static boolean intersect(Lesson lesson, Integer startTime, Integer endTime, Day day) {
		if (lesson.getDay() == day && 
			( (startTime > lesson.getStartTimeHour() && startTime < lesson.getEndTimeHour()) ||
			(endTime > lesson.getStartTimeHour() && endTime < lesson.getEndTimeHour()) ||
			(lesson.getStartTimeHour() > startTime && lesson.getStartTimeHour() < endTime) ) ) {
			return true;
		}
		return false;
	}
}
