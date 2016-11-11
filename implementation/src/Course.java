
import java.util.*;

public class Course {
	private int id;
	private String name;
	private String descriptioin;
	private Set<String> offeredSemesters;
	
	private Set<Integer> enrolledStudents;
	private Map<Integer, Integer> instructors;
	private int capacity;
	private Set<Integer> prerequisites;
	
	public Course(int id, String name){
		this.id = id;
		this.name = name;
		offeredSemesters = new HashSet<>();
		
		enrolledStudents = new HashSet<>();
		instructors = new HashMap<>();
		capacity = 0;
		prerequisites = new HashSet<>();
	}
	
	public int getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public int getAvailableSeats(){
		return getCourseCapacity() - getCurrentStudentNumber();
	}
	
	public boolean isOffered(String semester){
		return offeredSemesters.contains(semester);
	}
	
	public void addSemester(String semester){
		offeredSemesters.add(semester);
	}
	
	public void removeSemester(String semester){
		if(offeredSemesters.contains(semester)){
			offeredSemesters.remove(semester);
		}
	}
	
	public Set<String> getOfferedSemesters(){
		return offeredSemesters;
	}
	
	public Set<Integer> getInstructors(){
		return instructors.keySet();
	}
	
	public void addStudent(int studentId){
		enrolledStudents.add(studentId);
	}
	
	public void dropStudent(int studentId){
		if(enrolledStudents.contains(studentId)){
			enrolledStudents.remove(studentId);
		}
	}
	
	public int getCourseCapacity(){
		return capacity;
	}
	
	public int getCurrentStudentNumber(){
		return enrolledStudents.size();
	}
	
	public Set<Integer> getPrerequisites(){
		return prerequisites;
	}
	
	public void addPrerequisite(int preId){
		prerequisites.add(preId);
	}
	
	public void addInstructor(int instructorId, int seats){
		instructors.put(instructorId, seats);
		capacity += seats;
	}
	
	public void removeInstructor(int instructorId){
		if(instructors.containsKey(instructorId)){
			instructors.remove(instructorId);
		}
	}
	
	public void addSeats(int seats){
		capacity += seats;
	}
}
