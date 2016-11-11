import java.util.*;
import java.io.*;

public class Program {
	private int studentNumLimit;
	private int courseNumLimit;
	private int instructorNumLimit;
	
	private Map<Integer, Student> students;
	private Map<Integer, Course> courses;
	private Map<Integer, Instructor> instructors;
	private Map<StudentCourseNode, Record> records;
	
	private Set<StudentCourseNode> validRequests;
	private Set<StudentCourseNode> missPreRequests;
	private Set<StudentCourseNode> reTakenRequests;
	private Set<StudentCourseNode> overSizeRequests;
	
	private static final Program program = new Program();	
	
	private Program(){
		students = new HashMap<>();
		courses = new TreeMap<>();
		instructors = new HashMap<>();
		records = new LinkedHashMap<>();
		validRequests = new LinkedHashSet<>();
		missPreRequests = new LinkedHashSet<>();
		reTakenRequests = new LinkedHashSet<>();
		overSizeRequests = new LinkedHashSet<>();	
 	}
	
	public int getStudentNumLimit(){
		return studentNumLimit;
	}
	
	public void setStudentNumLimit(int num){
		studentNumLimit = num;
	}
	
	public int getCourseNumLimit(){
		return courseNumLimit;
	}
	
	public void setCourseNumLimit(int num){
		courseNumLimit = num;
	}
	
	public int getInstructorNumLimit(){
		return instructorNumLimit;
	}
	
	public void setInstructorNumLimit(int num){
		instructorNumLimit = num;
	}
	
	public static Program getInstance(){
		return program;
	}
	
	public Student getStudentByID(int id){
		return students.get(id);
	}
	
	public Course getCourseByID(int id){
		return courses.get(id);
	}
	
	public Instructor getInstructorByID(int id){
		return instructors.get(id);
	}
	
	public void addStudent(int studentId, Student student){
		students.put(studentId, student);
	}
	
	public void addInstructor(int instructorId,Instructor instructor){
		instructors.put(instructorId, instructor);
	}
	
	public void addCourse(int courseId, Course course){
		courses.put(courseId, course);
	}
	
	public void addRecord(int studentId, int courseId, int instructorId, char grade, String comment){
		Record record = new Record(studentId, courseId, instructorId, grade);
		StudentCourseNode node = new StudentCourseNode(studentId, courseId);
		if(comment != null){
			record.setComment(comment);
		}
		if(records.containsKey(node)){
			records.remove(node);
		}
		records.put(node, record);
		addFinishedCourseToStudent(studentId, courseId, grade);
		if(grade != 'F'){
			updateMissPreRequest(studentId, courseId);
		}
	}
	
	public void removeRecord(int studentId, int courseId){
		StudentCourseNode node = new StudentCourseNode(studentId, courseId);
		if(records.containsKey(node)){
			records.remove(node);
			removeFinishedCourseFromStudent(studentId, courseId);
		}
	}
	
	public Record getRecord(int studentId, int courseId){
		StudentCourseNode node = new StudentCourseNode(studentId, courseId);
		if(records.containsKey(node)){
			return records.get(node);
		}
		return null;
	}
	
	public void addFinishedCourseToStudent(int studentId, int courseId, char grade){
		Student student = getStudentByID(studentId);
		student.addFinishedCourse(courseId, grade);
	}
	
	public void removeFinishedCourseFromStudent(int studentId, int courseId){
		Student student = getStudentByID(studentId);
		student.removeFinishedCourse(courseId);
	}
	
	public void addPrerequisiteToCourse(int courseId, int preId){
		Course course = getCourseByID(courseId);
		course.addPrerequisite(preId);
	}
	
	public void addInstructorToCourse(int instructorId, int courseId, int seats){
		Course course = getCourseByID(courseId);
		Instructor instructor = getInstructorByID(instructorId);
		course.addInstructor(instructorId, seats);
		instructor.addCurrCourse(courseId);
		updateOverSizeRequest(course);
	}
	
	public void removeInstructorFromCourse(int instructorId, int courseId){
		Course course = getCourseByID(courseId);
		Instructor instructor = getInstructorByID(instructorId);
		course.removeInstructor(instructorId);
		instructor.removeCurrCourse(courseId);
	}
	
	public void addSeatToCourse(int courseId, int seats){
		Course course = getCourseByID(courseId);
		course.addSeats(seats);
		updateOverSizeRequest(course);
	}
	
	public String takeCourse(int studentId, int courseId){
		StudentCourseNode node = new StudentCourseNode(studentId, courseId);
		Student student = getStudentByID(studentId);
		Course course = getCourseByID(courseId);
		for(Integer pre :course.getPrerequisites()){
			if((!student.getFinishedCourses().containsKey(pre)) || student.getFinishedCourses().get(pre) == 'F'){
				missPreRequests.add(node);
				return studentId +"," + courseId + ": " + "denied: stuent " + studentId + " is missing course " + pre + " as prerequisite";
			}
		}
		if(student.getFinishedCourses().containsKey(courseId)){
			char grade = student.getFinishedCourses().get(courseId);
			if("ABC".indexOf(grade) >= 0){
				reTakenRequests.add(node);
				return studentId +"," + courseId + ": " + "denied: student " + studentId + " has already taken course " + courseId + 
						" with a grade of " + grade;
			}
		}
		if(course.getAvailableSeats() == 0){
			overSizeRequests.add(node);
			return studentId +"," + courseId + ": " + "denied: there are no more seats available for course " + courseId;
		}
		course.addStudent(studentId);
		validRequests.add(node);
		return studentId +"," + courseId + ": " + "valid";
	}
	

	
	public void updateMissPreRequest(int studentId, int courseId){
		Set<StudentCourseNode> set = new LinkedHashSet<>();
		set.addAll(missPreRequests);
		for(StudentCourseNode node : set){
			if(node.studentId == studentId){
				missPreRequests.remove(node);
				takeCourse(studentId, node.courseId);
			}
		}
	}
	
	public void updateOverSizeRequest(Course course){
		Set<StudentCourseNode> set = new LinkedHashSet<>();
		set.addAll(overSizeRequests);
		for(StudentCourseNode node : set){
			if(course.getCurrentStudentNumber() == course.getCourseCapacity()){
				break;
			}
			if(node.courseId == course.getId()){
				overSizeRequests.remove(node);
				takeCourse(node.studentId, node.courseId);
			}
		}
	}
	

	
	public int getStudentNumber(){
		return students.size();
	}
	
	public int getCourseNumber(){
		return courses.size();
	}
	
	public int getInstructorNumber(){
		return instructors.size();
	}
	
	public int getRecordNumber(){
		return records.size();
	}
	
	public int getCourseNumberBySemester(String semester){
		int number = 0;
		for(Course c : courses.values()){
			if(c.isOffered(semester)){
				number++;
			}
		}
		return number;
	}
	
	public int getFreeStudentNumber(){
		int number = 0;
		boolean found = false;
		for(Student c : students.values()){
			found = false;
			if(records.containsKey(c.getId())){
				found=true;
				break;
			}
			if(!found){
				number++;
			}
		}
		return number;
	}
	
	public int getFreeCourseNumber(){
		int number = 0;
		boolean found = false;
		for(Course c : courses.values()){
			found = false;
			for(Record record: records.values()){
				if(record.getCourseId() == c.getId()){
					found = true;
					break;
				}
			}
			if(!found){
				number++;
			}
		}
		return number;
	}
	
	public int getFreeInstructorNumber(){
		int number = 0;
		boolean found = false;
		for(Instructor i : instructors.values()){
			found = false;
			for(Record r : records.values()){
				if(i.getId() == r.getInstructorId()){
					found=true;
					break;
				}
			}
			if(!found){
				number++;
			}
		}
		return number;
	}
	
	public void displaySeats(){
		for(Course course: courses.values()){
			System.out.println(course.getId()+", " + course.getName() + ", " + course.getAvailableSeats());
		}
	}
	
	public void displayRecords(){
		for(Record record : records.values()){
			System.out.println(record);
		}
	}
	
	public void displayRequests(){
		for(StudentCourseNode node : validRequests){
			Student student = getStudentByID(node.studentId);
			Course course = getCourseByID(node.courseId);
			System.out.println(node.studentId + ", " + student.getName() + ", " + 
			                   node.courseId + ", " + course.getName());
		}
	}
	
	public void checkRequest(int studentId, int courseId){
		StudentCourseNode node = new StudentCourseNode(studentId, courseId);
		if(validRequests.contains(node)){
			System.out.println("request is valid");
		}
		else if(missPreRequests.contains(node)){
			System.out.println("student is missing one or more prerequisites");
		}
		else if(reTakenRequests.contains(node)){
			System.out.print("student has already taken the course with a grade of C or higher");
		}
		else{
			System.out.println("no remaining seats available for the course at this time");
		}
	}
	
	public void doHW6(){
		new DataReader().readFiles();;
		System.out.println(records.size());
		System.out.println(validRequests.size());
		System.out.println(missPreRequests.size());
		System.out.println(reTakenRequests.size());
		System.out.println(overSizeRequests.size());
		
		Scanner scan = new Scanner(System.in);
		System.out.print("$main: ");
		while(scan.hasNextLine()){
			String line = scan.nextLine().trim();
			if(line.equals("display_seats")){
				displaySeats();
			}
			else if(line.equals("display_records")){
				displayRecords();
			}
			else if(line.equals("display_requests")){
				displayRequests();
			}
			else if(line.equals("quit")){
				System.out.println("stopping the command loop");
				break;
			}
			else{
				String[] info = line.split(",");
				if(info[0].equals("add_record")){
					int studentId = Integer.parseInt(info[1].trim());
					int courseId = Integer.parseInt(info[2].trim());
					int instructorId = Integer.parseInt(info[3].trim());
					String comment = null;
					char grade;
					if(info.length == 5){
						grade = info[4].trim().charAt(0);
					}
					else{
						comment = info[4];
						grade = info[5].trim().charAt(0);
					}
					addRecord(studentId, courseId, instructorId, grade, comment);
				}
				else if(info[0].equals("add_seats")){
					int courseId = Integer.parseInt(info[1].trim());
					int seats = Integer.parseInt(info[2].trim());
					addSeatToCourse(courseId, seats);
				}
				else if(info[0].equals("check_request")){
					int studentId = Integer.parseInt(info[1].trim());
					int courseId = Integer.parseInt(info[2].trim());
					checkRequest(studentId, courseId);
				}
			}
			System.out.print("$main: ");
		}
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Program program = Program.getInstance();
		program.doHW6();
	}
}
