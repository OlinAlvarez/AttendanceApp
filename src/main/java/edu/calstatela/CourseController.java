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

//mapping to tell spring boot that this class is a REST Controller
@RestController
public class CourseController {
	
	@Autowired
	DatabaseBean databaseBean;
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping("/courses")
	public List<Course> getCourses() {
		return databaseBean.getCourses();
	}

}
