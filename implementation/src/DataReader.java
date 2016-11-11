import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DataReader {
	private Program program;
	private BufferedReader bf;
	
	public DataReader(){
		program = Program.getInstance();
	}
	
	public void readFiles(){
		readStudentFile();
		readInstructorFile();
		readCourseFile();
		readRecordFile();
		readPrerequisiteFile();
		readAssignmentFile();
		readRequestFile();
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
				program.addStudent(id, student);
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
				program.addInstructor(id, instructor);
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
				program.addCourse(id, course);
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
					String comment = null;
					if(lenInfo == 5){
						comment = recordInfo[3];
					}
					program.addRecord(studentId, courseId, instructorId, recordInfo[lenInfo-1].charAt(0), comment);
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
	
	public void readPrerequisiteFile(){
		String fileName = "prereqs.csv";
		try {
			bf = new BufferedReader(new FileReader(fileName));
			String line;
			try {
				while((line = bf.readLine()) != null){
					String[] preInfo = line.split(",");
					int preId = Integer.parseInt(preInfo[0]);
					int courseId = Integer.parseInt(preInfo[1]);
					program.addPrerequisiteToCourse(courseId, preId);
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
	
	public void readAssignmentFile(){
		String fileName = "assignments.csv";
		try {
			bf = new BufferedReader(new FileReader(fileName));
			String line;
			try {
				while((line = bf.readLine()) != null){
					String[] assignInfo = line.split(",");
					int instructorId = Integer.parseInt(assignInfo[0]);
					int courseId = Integer.parseInt(assignInfo[1]);
					int seats = Integer.parseInt(assignInfo[2]);
					program.addInstructorToCourse(instructorId, courseId, seats);
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
	
	public void readRequestFile(){
		String fileName = "requests.csv";
		try {
			bf = new BufferedReader(new FileReader(fileName));
			String line;
			try {
				while((line = bf.readLine()) != null){
					String[] requestInfo = line.split(",");
					int studentId = Integer.parseInt(requestInfo[0]);
					int courseId = Integer.parseInt(requestInfo[1]);
					String re = program.takeCourse(studentId, courseId);
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
}
