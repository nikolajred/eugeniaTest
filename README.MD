Online English school "Eugenia"

API
By default, application port is 8080

Endpoints

---------------------------------------------------------------------------------------------------
Teacher 
POST /

to add a teacher : 
Expected request example:

{
	"id" : 1,
	"name" :"Vasia",
	"lastname": "Pupkin",
	"email" :"vasiaPupkin@gmail.com"
}			




to remove a teacher that exists :
    endpoint :
        http://localhost:8080/teachers/delete
    Expected request example:
{
	"teacher":{
		"id" : 1	
	}
}


end of teacher
--------------------------------------------------------------------------------------------

Student
to see all students 

    GET
http://localhost:8080/students


to see a specific student 
http://localhost:8080/students/{id}
if none exist "sorry student {id} doesnt exist "


to add a student :
    endpoint :
    
    POST
/students/add
Expected request example:
{
	"id" : 1,
	"name": "John Dow",
	"Role" : "activeStudent"
	}


to edit a student:
    PUT
/students/edit/changeteacher
    Expected request example:
{
	"student":{
		"id":1
	},
	"teacher":{
		"id":4
	}
}
otherwise 404 NOT_FOUND "This teacher doesn't exist 2 Please go back and try again"
or 404 NOT_FOUND "This student doesn't exist 2 Please go back and try again"


to set lessonsLeft
    endpoint :
    localhost:8080/students/edit/lessons
    PUT
/students/add
  Expected request example:
{
	"student":{
		"id":1,
		"lessonsLeft" :20
	}
}

end of Student
___________________________________________________________________________________
SCHEDULE

to add a schedule 




end of schedule 
-----------------------------------------
