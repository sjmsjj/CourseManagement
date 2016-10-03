import java.util.*;
import java.io.*;

public class Program {
	private HashMap<Integer, Student> students;
	private HashMap<Integer, Course> courses;
	private HashMap<Integer, Instructor> instructors;
	private ArrayList<Record> records;
	private static final Program program = new Program();	
	private BufferedReader bf;
	
	private Program(){
		students = new HashMap<>();
		courses = new HashMap<>();
		instructors = new HashMap<>();
		records = new ArrayList<>();
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
	
	public int getFreeCourseNumber(){
		int number = 0;
		boolean found = false;
		for(Course c : courses.values()){
			found = false;
			for(Record r : records){
				if(c.getId() == r.getCourseId()){
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
	
	public int getFreeStudentNumber(){
		int number = 0;
		boolean found = false;
		for(Student c : students.values()){
			found = false;
			for(Record r : records){
				if(c.getId() == r.getStudentId()){
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
	
	public int getFreeInstructorNumber(){
		int number = 0;
		boolean found = false;
		for(Instructor i : instructors.values()){
			found = false;
			for(Record r: records){
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
	
	public void readStudentFile(){
		String fileName = "students.csv";
		try {
			bf = new BufferedReader(new FileReader(fileName));
			String line;
			while((line = bf.readLine()) != null){
				String[] studentInfo = line.split(",");
				int id = Integer.parseInt(studentInfo[0]);
				Student student = new Student(id, studentInfo[1], studentInfo[2], studentInfo[3]);
				students.put(id, student);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if(bf != null){
				try {
					bf.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void readInstructorFile(){
		String fileName = "instructors.csv";
		try {
			bf = new BufferedReader(new FileReader(fileName));
			String line;
			while((line = bf.readLine()) != null){
				String[] instructorInfo = line.split(",");
				int id = Integer.parseInt(instructorInfo[0]);
				Instructor instructor = new Instructor(id, instructorInfo[1], instructorInfo[2], instructorInfo[3]);
				instructors.put(id, instructor);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if(bf != null){
				try {
					bf.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void readCourseFile(){
		String fileName = "courses.csv";
		try {
			bf = new BufferedReader(new FileReader(fileName));
			String line;
			while((line = bf.readLine()) != null){
				String[] courseInfo = line.split(",");
				int id = Integer.parseInt(courseInfo[0]);
				Course course = new Course(id, courseInfo[1]);
				for(int i = 2; i < courseInfo.length; i++){
					course.addSemester(courseInfo[i]);
				}
				courses.put(id, course);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if(bf != null){
				try {
					bf.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void readRecordFile(){
		String fileName = "records.csv";
		try {
			bf = new BufferedReader(new FileReader(fileName));
			String line;
			try {
				while((line = bf.readLine()) != null){
					String[] recordInfo = line.split(",");
					int studentId = Integer.parseInt(recordInfo[0]);
					int courseId = Integer.parseInt(recordInfo[1]);
					int instructorId = Integer.parseInt(recordInfo[2]);
					int lenInfo = recordInfo.length;
					Record record = new Record(studentId, courseId, instructorId, recordInfo[lenInfo-1].charAt(0));
					if(lenInfo == 5){
						record.setComment(recordInfo[3]);
					}
					records.add(record);
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void readFiles(){
		readStudentFile();
		readInstructorFile();
		readCourseFile();
		readRecordFile();
	}
	
	public void doHW3(){
		readFiles();
		ArrayList<Integer> ans = new ArrayList<>();
		ans.add(getRecordNumber());
		ans.add(getStudentNumber());
		ans.add(getFreeStudentNumber());
		ans.add(getInstructorNumber());
		ans.add(getFreeInstructorNumber());
	    ans.add(getCourseNumber());
	    ans.add(getFreeCourseNumber());
	    ans.add(getCourseNumberBySemester("Fall"));
	    ans.add(getCourseNumberBySemester("Spring"));
	    ans.add(getCourseNumberBySemester("Summer"));
	    for(Integer number : ans){
	    	System.out.println(number);
	    }
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Program program = Program.getInstance();
		program.doHW3();
	}
}
