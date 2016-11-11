import java.util.*;

public class Instructor extends Participant{
	private Set<Integer> qualifiedCourses;
	private Set<Integer> currTeachCourses;
	
	public Instructor(int id, String name, String address, String phoneNumber){
		super(id, name, address, phoneNumber);
		qualifiedCourses = new HashSet<>();
		currTeachCourses = new HashSet<>();
	}
	
	public void createReport(int studentId, int courseId, char grade, String comment){
		program.addRecord(studentId, courseId, id, grade, comment);
	}
	
	public boolean canTeach(int courseId){
		return qualifiedCourses.contains(courseId);
	}
	
	public void addCourse(int courseId){
		qualifiedCourses.add(courseId);
	}
	
	public void addCurrCourse(int courseId){
		currTeachCourses.add(courseId);
	}
	
	public void removeCurrCourse(int courseId){
		if(currTeachCourses.contains(courseId)){
			currTeachCourses.remove(courseId);
		}
	}
	
	public boolean isActive(){
		return currTeachCourses.size() > 0;
	}
	
	

}
