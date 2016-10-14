package co.edureka.controller;

import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.PieChart;
import com.googlecode.charts4j.Slice;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.urls.StandardCategoryURLGenerator;
import org.jfree.data.category.DefaultCategoryDataset;



@Controller
public class ChartController {

	private static String course=null; 
	private static String year=null; 
	
	@RequestMapping("/monthlychart")
	public ModelAndView monthlyChart(){		
		
		return new ModelAndView("monthwise");
		
	}
	
	@RequestMapping(value="forMonth",method=RequestMethod.GET)
	public ModelAndView monthWise(@RequestParam Map<String,String> param){
		HashMap<String,Integer> chartData=new HashMap<String,Integer>();
		ArrayList<String> offered=new ArrayList<String>();
		String month=param.get("month");
		String year=param.get("year");
		System.out.println(month+"------"+year);
		Connection con=DBConnection.getConnection();
		
		String begin=year+"-"+month+"-"+"01";
		String end=year+"-"+month+"-"+"31";
      
        ResultSet set=null;int slices=0;
               try{
        	Statement st=con.createStatement();
        	String sql="SELECT course,count(*) FROM calendar WHERE start_date >= '"+begin+"' and start_date <= '"+end+"' group by course " ;    
        	System.out.println(sql);
        	set=st.executeQuery(sql);
        	String course=null;
        	int count=0;
        	
        	while(set.next()){
        		course=set.getString(1);
        		count=set.getInt(2);
        		System.out.println(course+"___________"+count);
        		chartData.put(course,count);
        		offered.add(course);
        		slices++;
        	}  
        	}catch(Exception e){ System.out.println("Error while checking");e.printStackTrace();}
        	Slice [] data=new Slice[slices];
        	Color [] colors={ Color.newColor("CACACA"),Color.newColor("DF7417"),Color.newColor("951800"),
        			Color.newColor("0B0101"),Color.newColor("424990"),Color.newColor("3B7D11"),
        			Color.newColor("C03516"),Color.newColor("15D0C7"),Color.newColor("22012B"),
        			Color.newColor("EDE925"),Color.newColor("6F4E0B"),Color.newColor("7A7978")
        			
        	};
        	for(int x=0;x<slices;x++){
        		data[x]=Slice.newSlice(chartData.get(offered.get(x)),colors[x],offered.get(x),offered.get(x));
        	}
        	System.out.println(" Before creating chart");
        	//String arg[]=new String[slices];
        	PieChart pieChart = GCharts.newPieChart(data);
        	
        	HashMap<String,String >months=new HashMap<String,String>();
        	months.put("01","January");months.put("02","Fabuary");months.put("03","March");months.put("04","April");
        	months.put("05","May");months.put("06","June");months.put("07","July");months.put("08","August");
        	months.put("09","September");months.put("10","October");months.put("11","November");months.put("12","December");
        	pieChart.setTitle("Courses started in "+ months.get(month)+" "+ year , Color.BLACK, 15);
        	
        	pieChart.setSize(750, 360);
    		pieChart.setThreeD(true);
        	System.out.println("last");
            return new ModelAndView("display","pieUrl",pieChart.toURLString());
	}
	
	
	@RequestMapping("selectcourse")
	public ModelAndView barcourse(){
		Connection con=DBConnection.getConnection();
		ArrayList<String>courses=new ArrayList<String>();
	
        ResultSet set=null;
               try{
        	Statement st=con.createStatement();
        	String sql="SELECT distinct course from calendar " ;    
        	System.out.println(sql);
        	set=st.executeQuery(sql);
        	String course=null;
        
        	
        	while(set.next()){
        		course=set.getString(1);
        		System.out.println(course+"___________");
        		courses.add(course);
        	}  
        	}catch(Exception e){ System.out.println("Error while checking");e.printStackTrace();}
		
		return new ModelAndView("selectCourse","courses",courses);
	}
	
	@RequestMapping("Barchart")
	public String barchart(HttpServletRequest req,HttpServletResponse res){
		System.out.println("Generating Graph for "+req.getParameter("course")+" "+req.getParameter("year"));
		ChartController.course=req.getParameter("course");
		ChartController.year=req.getParameter("year");
		return "Barchart";
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping("/GraphGen")
	 public void genGraph(HttpServletRequest req,
             HttpServletResponse resp) {
try {
	System.out.println("Lets Generate "+ChartController.course+" "+ChartController.year);
      OutputStream out = resp.getOutputStream();
// Create a simple Bar chart
System.out.println("Setting dataset values");
DefaultCategoryDataset dataset = new DefaultCategoryDataset();
Map<Integer,Integer>map=new HashMap<Integer,Integer>();
map.put(1,0);map.put(2,0);map.put(3,0);map.put(4,0);
map.put(5,0);map.put(6,0);map.put(7,0);map.put(8,0);
map.put(9,0);map.put(10,0);map.put(11,0);map.put(12,0);
String lower=ChartController.year+"-01-01";
String upper=ChartController.year+"-12-31";
Connection con=DBConnection.getConnection();
ResultSet set=null;
try{
	Statement st=con.createStatement();
	String sql="SELECT month(start_date) from calendar where course='"+ChartController.course+"' and start_date >= '"+lower+"' and  start_date <= '"+upper+"'    " ;    
	System.out.println(sql);
	set=st.executeQuery(sql);
	
	
	while(set.next()){
		int mon=set.getInt(1);
		int count=map.get(mon);
		count++;
		map.put(mon, count);
	}  
	}catch(Exception e){ System.out.println("Error while checking");e.printStackTrace();}

  Set<Integer>keys=map.keySet();
  for(Integer key:keys)
  {
    System.out.println(key+ "      "+map.get(key));
  }
dataset.setValue(map.get(1),ChartController.course,"Jan");
dataset.setValue(map.get(2),ChartController.course,"Feb");
dataset.setValue(map.get(3),ChartController.course,"Mar");
dataset.setValue(map.get(4),ChartController.course,"Apr");
dataset.setValue(map.get(5),ChartController.course,"May");
dataset.setValue(map.get(6),ChartController.course,"Jun");

dataset.setValue(map.get(7),ChartController.course,"Jul");
dataset.setValue(map.get(8),ChartController.course,"Aug");
dataset.setValue(map.get(9),ChartController.course,"Sep");
dataset.setValue(map.get(10),ChartController.course,"Oct");
dataset.setValue(map.get(11),ChartController.course,"Nov");
dataset.setValue(map.get(12),ChartController.course,"Dec");


JFreeChart chart =
ChartFactory.createBarChart3D(ChartController.course+" Batches in "+ChartController.year,
"Months",
"Number Of Batches",
dataset,
PlotOrientation.VERTICAL,
true,
true,
false);

chart.setBackgroundPaint(java.awt.Color.green);

// Set the background colour of the chart
chart.getTitle().setPaint(java.awt.Color.blue);

// Adjust the colour of the title
CategoryPlot plot = chart.getCategoryPlot();

// Get the Plot object for a bar graph

plot.setBackgroundPaint(java.awt.Color.white);  
plot.setRangeGridlinePaint(java.awt.Color.red);
CategoryItemRenderer renderer = plot.getRenderer();
renderer.setSeriesPaint(0, java.awt.Color.red);
renderer.setSeriesPaint(1, java.awt.Color.green);
renderer.setItemURLGenerator(

new StandardCategoryURLGenerator(
"index1.html",
"series",
"section"));
renderer.setToolTipGenerator(
new StandardCategoryToolTipGenerator());

resp.setContentType("image/png");
ChartUtilities.writeChartAsPNG(out, chart, 625, 500);
} catch (Exception e) {
System.err.println(
"Problem occurred creating chart." + e.getMessage());
}

}
	
	
	
}
