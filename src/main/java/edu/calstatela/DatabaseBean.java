package edu.calstatela;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

@Component
public class DatabaseBean {
	/*
	 * GET /courses
	 * 
	 * returns a list of courses available with all the information listed above
	 * 
	 * GET /attendanceRecords/{courseId}
	 * 
	 * returns attendance records of course with specified id
	 * 
	 * GET /attendanceRecordCourseActivity/{courseId}/{activityName}
	 * 
	 * returns attendance records of course with specified course id and
	 * activity matches activityName. For example:
	 * /attendanceRecordCourseActivity/4/final
	 * 
	 * POST /attendanceRecords
	 * 
	 * creates attendance records using data from the POST body.
	 */
	public static final String LIST_COURSES = "select * from courses;";
	/*
	 * still need to find queries to find the students for the course attendance
	 * and the query to find roll
	 */
	public static final String LIST_STUDENTS = "select * from students ";
	public static final String LIST_COURSE_ATTENDANCE_RECORD = "select distinct s.name,a.id, a.activity, a.present from attendance a, students s where a.course_id=? and a.student_id=s.id;";
	public static final String LIST_ATTENDANCE_RECORD_ACTIVITY = "select s.name, a.activity, a.present from attendance a, students s where a.course_id=? and a.name=?;";
	public static final String ADD_ATTENDANCE_RECORDS = "insert into attendance(course_id,student_id,activity,present) values (?,?,?,?);";
	public static final String LIST_ROLL = "select ";
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert insertAttendanceRecord;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.insertAttendanceRecord = new SimpleJdbcInsert(dataSource).withTableName("attendance")
				.usingGeneratedKeyColumns("id");
		;

	}

	public List<Course> getCourses() {

		List<Course> courses = this.jdbcTemplate.query(LIST_COURSES, new RowMapper<Course>() {

			// id, name, time, units, location, instructor, ArrayList<Student>
			// students
			public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String time = rs.getString("time");
				int units = rs.getInt("units");
				String location = rs.getString("location");
				String instructor = rs.getString("instructor");

				ArrayList<Student> students = (ArrayList<Student>) getStudents(id);

				return new Course(id, name, time, units, location, instructor, students);
			}
		});
		return courses;
	}

	public List<AttendanceRecord> getRecords(int course_id) {
		List<AttendanceRecord> records = this.jdbcTemplate.query(LIST_COURSE_ATTENDANCE_RECORD,
				new Object[] { course_id }, new RowMapper<AttendanceRecord>() {
					/*
					 * id integer auto_increment,
					 * student_id integer references
					 * students(id), course_id integer references courses(id),
					 * activity varchar(255), present varchar(255),
					 */
					public AttendanceRecord mapRow(ResultSet ts, int rowNum) throws SQLException {
						String activity = ts.getString("activity");
						int id = ts.getInt("id");
						
						ArrayList<StudentAttendance> roll = (ArrayList<StudentAttendance>) getStudentAttendanceList(
								course_id, activity);

						return new AttendanceRecord(id, course_id, activity, roll);
					}
				});
		return records;
	}

	public List<StudentAttendance> getStudentAttendanceList(int course_id, String activity_name) {
		String STUDENT_ATTENDANCE_LIST = "select a.student_id,a.present from attendance a where a.activity=\""
				+ activity_name + "\" and a.course_id=" + course_id + ";";
		ArrayList<StudentAttendance> roll = (ArrayList<StudentAttendance>) this.jdbcTemplate
				.query(STUDENT_ATTENDANCE_LIST, new RowMapper<StudentAttendance>() {
					public StudentAttendance mapRow(ResultSet ts, int rowNum) throws SQLException {
						int student_id = ts.getInt("student_id");
						String present = ts.getString("present");
						return new StudentAttendance(present, student_id);
					}
				});
		return roll;
	}

	public List<Student> getStudents(int course_id) {
		String LIST_STUDENTS_IN_COURSE = "select distinct s.name, s.id, s.cin from students s , attendance a where a.course_id="
				+ course_id + " and s.id=a.student_id ;";
		ArrayList<Student> students = (ArrayList<Student>) this.jdbcTemplate.query(LIST_STUDENTS_IN_COURSE,
				new RowMapper<Student>() {
					public Student mapRow(ResultSet ts, int rowNum) throws SQLException {
						int id = ts.getInt("id");
						String name = ts.getString("name");
						int cin = ts.getInt("cin");

						return new Student(id, name, cin);
					}
				});
		return students;
	}

	// Insert an employee using SimpleJdbcInsert, see
	// http://docs.spring.io/spring/docs/current/spring-framework-reference/html/jdbc.html#jdbc-simple-jdbc-insert-2
	public int insertAttendanceRecord(AttendanceRecordPostData postData) {
		// postdata has an arraylist of attendancerecords
		int temp = 2;
		Map<String, Object> parameters = new HashMap<String, Object>();
		for (AttendanceRecord recordToAdd : postData.ar) {

			
			parameters.put("course_id", recordToAdd.getCourse_id());
			parameters.put("activity", recordToAdd.getActivity());
			for (StudentAttendance sa : recordToAdd.getRoll()) {
				parameters.put("student_id", sa.getStudent_id());
				parameters.put("present", sa.getPresent());
			Number	tmp = insertAttendanceRecord.executeAndReturnKey(parameters);
			}

		}
		
		return  temp;

	}
}
