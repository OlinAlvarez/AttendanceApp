package edu.calstatela;
import java.util.HashMap;
public class StudentAttendance{
    private String present;
    private int student_id;

    public StudentAttendance(){}

    public StudentAttendance(String present, int student_id){
        this.present=present;
        this.student_id=student_id;
    }
    public int getStudent_id() {
		return student_id;
	}
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
    public String getPresent() {
		return present;
	}
	public void setPresent(String present) {
		this.present = present;
	}
}
