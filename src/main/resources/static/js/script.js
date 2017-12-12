
(function($) {

	//relative path
	var COURSE_RESOURCE = "courses";
	
	
    $(function() {    

	makeCourseRequest();
	
    //See JQuery getJSON doc: http://api.jquery.com/jquery.getjson/
 
    function makeCourseRequest() {
    
	console.log();
      $.getJSON(COURSE_RESOURCE, function(data) {
        //JQuery parses the JSON string using JSON.parse for you.
        console.log(data);
        
        generateCourseButtons(data);
      });
    }


	function generateCourseButtons(courseData){
		buttonBody = document.getElementById("course-buttons");

		courseData.forEach(function(courseObject){
			var buttonnode= document.createElement('input');
			buttonnode.setAttribute('type','button');
			buttonnode.setAttribute('id',courseObject.id);
			buttonnode.setAttribute('value',courseObject.name);
			buttonnode.onclick = function(){
				listCourseInfo(courseObject.id);
			}

			
			
			console.log(buttonnode);
			buttonBody.appendChild(buttonnode);
		});

		
	}

	
 


	 function listCourseInfo(selectedCourseId) {
	courseInfoBody= document.getElementById("selected-course-body");
		//var selectedCourseId = document.getElementById("course-select-button").courseId;
		$.getJSON("courses",function(coursesInfo){

				coursesInfo.forEach( function(course){
						
						if(course.id===selectedCourseId){
							var list= document.createElement('LI');
							list.appendChild(document.createTextNode('Course name:' + course.name));
							courseInfoBody.appendChild(list);
							
							var list2=document.createElement('LI');
							list2.appendChild(document.createTextNode('units: ' +course.units));
							courseInfoBody.appendChild(list2);
							
							var list3=document.createElement('LI');
							list3.appendChild(document.createTextNode('instructor: '+course.instructor));
							courseInfoBody.appendChild(list3);
							
							var list4 =document.createElement('LI');
							list4.appendChild(document.createTextNode('Location: '+course.location));
							courseInfoBody.appendChild(list4);
							
							var list5= document.createElement('LI');
							list5.appendChild(document.createTextNode('time: '+course.time));
							courseInfoBody.appendChild(list5);

							listAttendanceRecord(course.id);
						}
				});
		});
	}

	
	//need to get the request for attenace given the course recrod
    function listAttendanceRecord(courseRecordId){

    	$.getJSON('attendanceRecords/'+courseRecordId, function(info){
    			$.getJSON(COURSE_RESOURCE,function(courseInfo){

    				console.log(info);
    				generateRollList(info,courseInfo);
    		
    			});
    		
    	});
    }

    function generateRollList(attendanceList,courseList){
    		
    		var rollHeader = document.getElementById("table-header");
    		var rollInfo = document.getElementById("roll-information");
    		var index = attendanceList[0].course_id;
    		var course = {};
    		
    		courseList.forEach(function(x){
    			console.log(x);
    				if(index === x.id){
    					course =x;
    				}

    			}

    		);
    		var headerTr = document.createElement('tr');
    		headerTr.appendChild( document.createElement('td') );
    		headerTr.cells[0].appendChild( document.createTextNode("student name") );
    		console.log(course);

    		course.students.forEach(function(student){
    			
    			var tr =document.createElement('tr');
    			tr.appendChild( document.createElement('td') );
    			tr.cells[0].appendChild( document.createTextNode(student.name) );
    			rollInfo.appendChild(tr);

    			
    			var length = attendanceList[0].roll.length;
    			for (var i = 0; i < attendanceList.length; i= i+length) {
    				
    				
    				
    				attendanceList[i].roll.forEach(function(present){
    					if(present.student_id === student.id){
    						
    						tr.appendChild( document.createElement('td') );
    						tr.cells[1].appendChild( document.createTextNode(present.present + " ")  );
    						
    					}
    				});	

    			}
    			rollHeader.appendChild(headerTr);	

		   		
                
    			
    		});
    		var upgradeButtons = document.getElementById("new-buttons");
    		
    		var createActivity = document.createElement('input');
			createActivity.setAttribute('type','button');
			createActivity.setAttribute('value','Create new activity');
			createActivity.onclick = function(){

				var checkbox = document.createElement('input');
				checkbox.setAttribute('type','checkbox');
				checkbox.setAttribute('value','present');
				checkbox.setAttribute('value','tardy');
				course.students.forEach(function(student){
    			
    			var tr =document.createElement('tr');
    			tr.appendChild( document.createElement('td') );
    			tr.cells[0].appendChild( document.createTextNode(student.name) );
    			rollInfo.appendChild(tr);

    			
    			var length = attendanceList[0].roll.length;
    			for (var i = 0; i < attendanceList.length; i= i+length) {
    				
    				
    				
    				attendanceList[i].roll.forEach(function(present){
    					if(present.student_id === student.id){
    						
    						tr.appendChild( 'td' );
    						tr.cells[1].appendChild(checkbox);
    						
    					}
    				});	

    			}    			
    		});

			}

			var update = document.createElement('input');
			update.setAttribute('type','button');
			update.setAttribute('value','update');
			update.onclick = function(){
				var attendanceRecord = {course_id:course.id, activity:"new activity", roll: [{student_id:1, present:'tardy'},
				{student_id:2, present:'present'},
				{student_id:3, present:'present'}]};

				$.post('attendanceRecords',JSON.stringify(attendanceRecord),function(data){
					console.log(data);
				},"json");
			}

			upgradeButtons.appendChild(createActivity);
			upgradeButtons.appendChild(update);
    }
    		
    		
    		
    		

    	
    	
	});

})(jQuery);
