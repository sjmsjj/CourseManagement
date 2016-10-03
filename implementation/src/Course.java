
import java.util.*;

public class Course {
	private int id;
	private String name;
	private String descriptioin;
	private Set<String> offeredSemesters;
	
	public Course(int id, String name){
		this.id = id;
		this.name = name;
		offeredSemesters = new HashSet<>();
	}
	
	public void addSemester(String semester){
		offeredSemesters.add(semester);
	}
	
	public boolean isOffered(String semester){
		return offeredSemesters.contains(semester);
	}
	
	public int getId(){
		return id;
	}
	
	
	

}
