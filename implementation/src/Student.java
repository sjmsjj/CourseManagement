
import java.util.*;

public class Student extends Participant{
	private boolean isGraduated;
	private Map<Integer, Character> finishedCourses;
	private Set<Integer> currentEnrolledCourses;
	private boolean isCourseAssistant;
	
	public Student(int id, String name, String address, String phoneNumber){
		super(id, name, address, phoneNumber);
		finishedCourses = new HashMap<>();
		currentEnrolledCourses = new HashSet<>();
	}
	
	public boolean checkGraduateStatus(){
		return isGraduated;
	}
	
	public void addCourse(int courseId){
		program.takeCourse(id, courseId);
	}
	
	public void dropCourse(int courseId){
		if(currentEnrolledCourses.contains(courseId)){
			Course course = program.getCourseByID(courseId);
			course.dropStudent(id);
			currentEnrolledCourses.remove(courseId);
		}
	}
	public Map<Integer, Character> getFinishedCourses(){
		return finishedCourses;
	}
	
	public void addFinishedCourse(int courseId, char grade){
		finishedCourses.put(courseId, grade);
	}
	
	public void removeFinishedCourse(int courseId){
		if(finishedCourses.containsKey(courseId)){
			finishedCourses.remove(courseId);
		}
	}
	
	public boolean isEnrolled(){
		return currentEnrolledCourses.size() > 0;
	}
	
	public boolean isAssistant(){
		return isCourseAssistant;
	}
	
	public Record getCourseRecord(int courseId){
		return program.getRecord(id, courseId);
	}
	
	public char getCourseGrade(int courseId){
		return getCourseRecord(courseId).getGrade();
	}
}
