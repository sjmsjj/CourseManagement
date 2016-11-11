
public class Participant {
	protected int id;
	private String name;
	private String address;
	private String phoneNumber;
	protected Program program;
	
	public Participant(){
		
	}
	
	public Participant(int id, String name, String address, String phoneNumber){
		this.id = id;
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		program = Program.getInstance();
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setAddress(String address){
		this.address = address;
	}
	
	public String getAddress(){
		return address;
	}
	
	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}
	
	public String getPhoneNumber(){
		return phoneNumber;
	}
	

}
