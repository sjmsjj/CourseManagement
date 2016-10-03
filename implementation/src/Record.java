
public class Record {
	private int studentId;
	private int courseId;
	private int instructorId;
	private String comment;
	private char grade;
	
	public Record(int sId, int cId, int iId, char g){
		studentId = sId;
		courseId = cId;
		instructorId = iId;
		grade = g;
	}
	
	public void setComment(String comment){
		this.comment = comment;
	}
	
	public int getStudentId(){
		return studentId;
	}
	
	public int getCourseId(){
		return courseId;
	}
	
	public int getInstructorId(){
		return instructorId;
	}

}
