import java.util.*;

public class Instructor extends Participant{
	Set<Integer> qualifiedCourses = new HashSet<>();
	
	public Instructor(int id, String name, String address, String phoneNumber){
		super(id, name, address, phoneNumber);
	}
	
	public boolean canTeach(int courseId){
		return qualifiedCourses.contains(courseId);
	}
	
	public void addCourse(int courseId){
		qualifiedCourses.add(courseId);
	}

}
