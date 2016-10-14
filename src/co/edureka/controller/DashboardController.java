package co.edureka.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class DashboardController {
	
	@RequestMapping("dashboard")
	public ModelAndView dashboard(){
		Map<String,Object>data=new HashMap<String,Object>();
		ArrayList<Courses>courseDetails=new ArrayList<Courses>();
		ArrayList<Trainer>trainerDetails=new ArrayList<Trainer>();
		ArrayList<Course>notAssignedCourses=new ArrayList<Course>();
		ArrayList<Course>assignedCourses=new ArrayList<Course>();
		Connection con=DBConnection.getConnection();
       
        ResultSet set1=null;
        try{
        	Statement st=con.createStatement();
        	String courses_sql="select * from courses";
        	System.out.println(courses_sql);
        	set1=st.executeQuery(courses_sql);
        	
        	while(set1.next()){
        		String courseId=set1.getString("id");
        		String courseName=set1.getString("name");
        		int coursePrice=Integer.parseInt(set1.getString("price"));
        		//int courseSessions=Integer.parseInt(set1.getString(4));
        		Courses course=new Courses(courseName,courseId,coursePrice);
        		courseDetails.add(course);
        	}
        }catch(Exception e){e.printStackTrace();}
		data.put("courseDetails",courseDetails);
		ResultSet set2=null;
		 try{
	        	Statement st2=con.createStatement();
	        	String trainers_sql="select * from trainers";
	        	System.out.println(trainers_sql);
	        	set2=st2.executeQuery(trainers_sql);
	        	
	        	while(set2.next()){
	        		String trainerName=set2.getString("name");
	        		int experience=set2.getInt("experience");
	        		String trainerEmail=set2.getString("email");
	        		Long phone=Long.parseLong(set2.getString("phone"));
	        		Trainer trainer=new Trainer(trainerName,experience,trainerEmail,phone,null);
	        		trainerDetails.add(trainer);
	        	}
	        }catch(Exception e){e.printStackTrace();}
			data.put("trainerDetails",trainerDetails);
		
			ResultSet set3=null;
			 try{
		        	Statement st2=con.createStatement();
		        	String sql3="select course,start_date,end_date,sessions from calendar where trainer is null";
		        	System.out.println(sql3);
		        	set3=st2.executeQuery(sql3);
		        	
		        	while(set3.next()){
		        		String courseName=set3.getString(1);
		        		String start_date=set3.getString(2);
		        		String end_date=set3.getString(3);
		        		int sessions=Integer.parseInt(set3.getString(4));
		        		
		        		Course course=new Course(courseName,start_date,end_date,sessions);
		        		notAssignedCourses.add(course);
		        	}
		        }catch(Exception e){e.printStackTrace();}
			data.put("notAssignedCourses", notAssignedCourses);
			
			
			
			ResultSet set4=null;
			 try{
		        	Statement st4=con.createStatement();
		        	String sql4="select course,start_date,end_date,sessions from calendar where trainer is not null";
		        	System.out.println(sql4);
		        	set4=st4.executeQuery(sql4);
		        	
		        	while(set4.next()){
		        		String courseName=set4.getString(1);
		        		String start_date=set4.getString(2);
		        		String end_date=set4.getString(3);
		        		int sessions=Integer.parseInt(set4.getString(4));
		        		
		        		Course course=new Course(courseName,start_date,end_date,sessions);
		        		assignedCourses.add(course);
		        	}
		        	System.out.println("Courses are assigned");
		        }catch(Exception e){e.printStackTrace();}
		      data.put("assignedCourses",assignedCourses);
			 
		return new ModelAndView("dashboard","data",data);
	}

}
