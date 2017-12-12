package edu.calstatela;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AttendanceRecordController {
	@Autowired
	DatabaseBean databaseBean;

	@RequestMapping(value = "/attendanceRecords/{id}", method = RequestMethod.GET)
	public List<AttendanceRecord> getRecords(@PathVariable("id") int id) {
		return databaseBean.getRecords(id);
	}

	// returns for courseid and activity name

	@RequestMapping(value = "/attendanceRecordCourseActivity/{courseId}/{activityName}", method = RequestMethod.GET)
	public List<StudentAttendance> getStudentAttendanceList(@PathVariable("courseId") int id,
			@PathVariable("activityName") String activity) {
		return databaseBean.getStudentAttendanceList(id, activity);
	}

	// insert a new attendance record.

	@RequestMapping(value = "/attendanceRecords", method = RequestMethod.POST)
	public Map<String, String> insertAttendeanceRecord(@RequestBody AttendanceRecordPostData postData) {

		int id = databaseBean.insertAttendanceRecord(postData);

		return Collections.singletonMap("id", id + "");
	}

}
