
public class StudentCourseNode {
	int studentId;
	int courseId;
	public StudentCourseNode(int studentId, int courseId){
		this.studentId = studentId;
		this.courseId = courseId;
	}
	
	@Override
	public boolean equals(Object ob){
		if(this == ob){
			return true;
		}
		if(! (ob instanceof StudentCourseNode)){
			return false;
		}
		StudentCourseNode node = (StudentCourseNode) ob;
		return studentId == node.studentId && courseId == node.courseId;
	}
	
	@Override
	public int hashCode(){
		int h1 = String.valueOf(studentId).hashCode();
		int h2 = String.valueOf(courseId).hashCode();
		return (h1+h2)/2;
	}

}
