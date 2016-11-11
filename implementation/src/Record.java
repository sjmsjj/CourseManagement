
public class Record {
	private int studentId;
	private int courseId;
	private int instructorId;
	private String semester;
	private String comment;
	private char grade;
	
	public Record(int sId, int cId, int iId, char g){
		studentId = sId;
		courseId = cId;
		instructorId = iId;
		grade = g;
	}
	
	public boolean hasComment(){
		return comment != null;
	}
	
	public void setInstructor(int instructorId){
		this.instructorId = instructorId;
	}
	
	public void setSemester(String semester){
		this.semester = semester;
	}
	
	public void setGrade(char grade){
		this.grade = grade;
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
	
	public char getGrade(){
		return grade;
	}
	
	public String getComment(){
		return comment;
	}
	
	@Override
	public String toString(){
		return studentId + ", " + courseId +", " + instructorId + ", " + comment + ", " + grade;
	}
	
}
