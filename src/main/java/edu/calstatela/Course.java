package edu.calstatela;
import java.util.ArrayList;
public class Course{

    private int id;
    private String name;
    private String time;
    private int    units;
    private String location;
    private String instructor;
    private ArrayList<Student> students = new ArrayList<>();
    
    public Course(){}
    
    public Course(int id, String name,String time, int units, String location, String instructor, ArrayList<Student> students){
        this.id=id;
        this.name=name;
        this.time=time;
        this.units=units;
        this.location=location;
        this.instructor=instructor;
        this.students=students;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}
    

}
