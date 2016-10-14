package co.edureka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ContactusController {
 @ExceptionHandler(ResourceNotFoundException.class)
  public String resourceNotFoundException(){
	 return "page_not_found";
 }
 
 @RequestMapping("contactus")
 public void contactus() throws Exception{
	 
	  throw new ResourceNotFoundException();
	 
 }
 
	
	
}
