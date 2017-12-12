package edu.calstatela;
import java.util.ArrayList;
public class AttendanceRecord{
   private int id;
   private int course_id;
   private String activity;
   private ArrayList<StudentAttendance> roll = new ArrayList<>();
   public AttendanceRecord(){}

   public AttendanceRecord(int id, int course_id, String activity, ArrayList<StudentAttendance> roll){
       this.id=id;
       this.course_id=course_id;
       this.activity=activity;
       this.roll=roll; 
    }
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public void setRoll(ArrayList<StudentAttendance> roll){
        this.roll=roll;
    }
    public ArrayList<StudentAttendance> getRoll(){
        return roll;
    }
}
