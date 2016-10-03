
public class Student extends Participant{
	private boolean isCourseAssistant;
	
	public Student(int id, String name, String address, String phoneNumber){
		super(id, name, address, phoneNumber);
	}
	
	public boolean isAssistant(){
		return isCourseAssistant;
	}
}
