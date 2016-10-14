package co.edureka.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TrainerController {
	public static ArrayList<String> list;
	public static String toUpdate=null;
	
	@RequestMapping("/addTrainer")
	public String newTrainer(){
		return "addTrainer";
	}
	
	@RequestMapping("/subTrainer")
	public ModelAndView addTrainer(@RequestParam Map<String,String> param){
		System.out.println("Ya its working");
		String name=param.get("name");
		String exp=param.get("exp");
		
		String email=param.get("email");
		String phone=param.get("phone");
		String address=param.get("address");
		String message=null;
		
		Connection con=DBConnection.getConnection();
        int i=0;
       
        try{
        	Statement st=con.createStatement();
        	String sql="insert into trainers(name,email,phone,experience,address) values ('"+name+"','"+email+"','"+phone+"','"+exp+"','"+address+"' )" ;    
        	System.out.println(sql);
        	i=st.executeUpdate(sql);
        	
        	if(i!=0){
        		System.out.println("Inserted Successful");
        		message=name+" have been added as trainer successfully.";
        	}
        	else{
        		System.out.println("Insertion failed");
        		message="Error occured while inserting Trainer record";
        	}
        }catch(Exception e){ System.out.println("Error while checking"); e.printStackTrace();}
             
         
        return new ModelAndView("addTrainer", "message", message);
		
		
	}
	
	@RequestMapping("/deleteTrainer")
	public ModelAndView deleteTrainer(){
		
		Connection con=DBConnection.getConnection();
      
        ArrayList<String>trainers=new ArrayList<String>();
        ResultSet set=null;
        try{
        	Statement st=con.createStatement();
        	String sql="select * from trainers" ;    
        	System.out.println(sql);
        	set=st.executeQuery(sql);
        	
        	while(set.next()){
        		String trainer=set.getString(1);
        		trainers.add(trainer);
        	}
        }catch(Exception e){ System.out.println("Error while checking");e.printStackTrace();}
        System.out.println(trainers);
        return new ModelAndView("deleteTrainer","model",trainers);
	}
	
	@RequestMapping("trainerDeletion")
	public ModelAndView courseDeletion(@RequestParam Map<String,String>param){
		String message=null;
		String delete=param.get("trainer");
		System.out.println("Trainer to be deleted "+delete);
		Connection con=DBConnection.getConnection();
        int i=0;
       
        try{
        	Statement st=con.createStatement();
        	String sql="delete from trainers where name='"+delete+"'   " ;    
        	System.out.println(sql);
        	i=st.executeUpdate(sql);
        	
        	if(i!=0){
        		message=delete+"  have been deleted from database successfully ";
        	}
        	else{
        		message="Error Occurred while deleting "+delete;
        	}
        }catch(Exception e){ System.out.println("Error while checking");e.printStackTrace();}
		
		
		return new ModelAndView("trainerDeletion","model",message);
	}
	
	
	@RequestMapping("/updateTrainer")
	public ModelAndView updateTrainer(){
		
		Connection con=DBConnection.getConnection();
       
        ArrayList<String>trainers=new ArrayList<String>();
        ResultSet set=null;
        String email="Email ID ";
        String exp="Experience";
        String phone ="Phone Number";
        Map<String, Object> model = new HashMap<String, Object>();
        try{
        	Statement st=con.createStatement();
        	String sql="select * from trainers" ;    
        	System.out.println(sql);
        	set=st.executeQuery(sql);
        	
        	while(set.next()){
        		String trainer=set.getString(1);
        	    trainers.add(trainer);
        	}
        	list=trainers;
        }catch(Exception e){ System.out.println("Error while checking");e.printStackTrace();}
        System.out.println(trainers);
        model.put("trainers",trainers);
        model.put("email",email);
        model.put("exp",exp);
        model.put("phone",phone);
        model.put("name", "Trainer name");
        return new ModelAndView("updateTrainer","model",model);
	}

	@RequestMapping("/modifyTrainer")
	public ModelAndView modifyCourse(@RequestParam Map<String,String>param){
		
		Connection con=DBConnection.getConnection();
        String trainer=param.get("trainer");
        toUpdate=trainer;
        ArrayList<String>courses=new ArrayList<String>();
        ResultSet set=null;
        int exp =0;
        String email="abc@abc.com";
        String name="Trainer Name";
        long phone=0;
        try{
        	Statement st=con.createStatement();
        	String sql="select * from trainers where name='"+trainer+"' " ;    
        	System.out.println(sql);
        	set=st.executeQuery(sql);
        	
        	while(set.next()){
        		name=set.getString(1);
        		exp=set.getInt(2);
        		 email=set.getString(3);
        		 phone=Long.parseLong(set.getString(4));
        		
        	}
        }catch(Exception e){ System.out.println("Error while checking");e.printStackTrace();}
        System.out.println(courses);
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("exp", exp);
        model.put("email", email);
        model.put("phone",phone);
        model.put("trainers",list);
        model.put("name", name);
        
        return new ModelAndView("updateTrainer","model",model);
	}
	
	@RequestMapping("/trainerUpdated")
	public ModelAndView courseUpdated(@RequestParam Map<String,String>param){
		String name=param.get("tname");
		String exp=param.get("texp");
		String email=param.get("temail");
		String phone=param.get("tphone");
		int exper=Integer.parseInt(exp);
		long contact=Long.parseLong(phone);
		Connection con=DBConnection.getConnection();
        int i=0;
        String message=null;
        String trainer=param.get("tname");
        ArrayList<String>courses=new ArrayList<String>();
       
        try{
        	Statement st=con.createStatement();
        	String sql="update trainers set name='"+name+"' ,experience='"+exper+"',email='"+email+"',phone='"+contact+"' where name='"+TrainerController.toUpdate+"' " ;    
        	System.out.println(sql);
        	i=st.executeUpdate(sql);
        	
        	if(i!=0){
        	message="Trainer details have been updated successfully";	
        	}
        }catch(Exception e){ System.out.println("Error while checking");e.printStackTrace();}
        System.out.println(courses);
       
               
        return new ModelAndView("trainerUpdated","model",message);
	}
	
	
    
}
