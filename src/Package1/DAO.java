package Package1;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.jdbc.JDBCCategoryDataset;



public class DAO {
	
	DAO(){}

	Connection conn = getConnection();
	
		   private Connection getConnection(){
	   
	   		try{	   
	         
	   			Class.forName("com.mysql.jdbc.Driver").newInstance();
	   			 conn = DriverManager.getConnection
	   			   ("jdbc:mysql://infosecurity.ccepwwlnrdlg.us-west-2.rds.amazonaws.com:3306/infosecurity", "debuggers", "debuggers");
	   		}
	   		catch(Exception e){
	   			System.out.println(e);
	   		}
	   		return conn;
	   }
		   
		   //check score for client
		   
		   public int checkScoreforClient(String reqPeer)
		   {
			   try{
					
		    		Statement stm = conn.createStatement(); 
			    	String query = "select score from peerdetails where peername=\'"+reqPeer+"\'";
			    	ResultSet rs = stm.executeQuery(query);
			    	while(rs.next())
			    	return( Integer.parseInt(rs.getString("score")));
			    }
			    catch(Exception e){
			    		e.printStackTrace();
			    }
				return 0; 
			   
		   }
  
		   
	//
		   public void updateFrequency(String reqPeer,String disPeer)
		   {
			   try
			   {

		    		Statement stm = conn.createStatement(); 
		    		Statement stm2 = conn.createStatement(); 
			    	String query1 = "update peerdetails set reqfreq= reqfreq+1 where peername=\'"+reqPeer+"\'";
			    	String query2= "update peerdetails set dispatchfreq= dispatchfreq+1 where peername=\'"+disPeer+"\'";
			    	
			     stm.executeUpdate(query1);
			     stm2.executeUpdate(query2);
			     
			   }
			   catch(Exception e)
			   {
				   
				   e.printStackTrace();
			   }
			   
		   }
		   
		   
		   //
			public  List<String> getAllfiles() {
				ArrayList<String> files = new ArrayList<String>();
				try{
					
		    		Statement stm = conn.createStatement(); 
			    	String query = "select distinct filename FROM  filelist";
			    	ResultSet rs = stm.executeQuery(query);
			    	while(rs.next())
			    	files.add(rs.getString("filename"));
			    }
			    catch(Exception e){
			    		e.printStackTrace();
			    }
				return files;
			}
			
			//
			
			public String getAuthorization(String id,String password){
				try{
					System.out.println("1");
		    		Statement stm = conn.createStatement(); 
		    		System.out.println("2");
			    	String query = "SELECT * FROM infosecurity.idlist where password=\'"+password+"\' and sso=\'"+id+"\'";
			    	System.out.println("3");
			    	ResultSet rs = stm.executeQuery(query);
			    	while(rs.next())
			    	return(rs.getString("sso"));
			       
			    }
			    catch(Exception e){
			    		e.printStackTrace();
			    }
			return null;
				
			}
	public void storePeerDetails(String peername,String ipaddress,Integer port) {
		try{
			int success=0;
			Statement stm = conn.createStatement(); 
				String query = "Select peername from peerdetails";
				ResultSet rs = stm.executeQuery(query);
				while (rs.next()) { 
					if(rs.getString("peername").equals(peername)){
						success = 1;
						break;
					}
				}
				Statement stmt1 = conn.createStatement(); 
				
				if(success==0)
				{
					String query1 = "insert into peerdetails Values(\'"+ipaddress+"\',5,"+port+",0,\'"+peername+"\',0,0)";
					stmt1.executeUpdate(query1);
				}
				else
				{
					String query1 = "update peerdetails set ipaddress=\'"+ipaddress+"\' where peername=\'"+peername+"\'";
					stmt1.executeUpdate(query1);
					
				}
			}
	
		catch(Exception e){
			e.printStackTrace();
		}
	}
		
    public void updatefeedback(String peer ,int feedback)
    {

    	try{
    		int score = 0;
    		int count = 0;
    		int newcount=0;
        	Connection conn = getConnection();
        	Statement stm = conn.createStatement(); 
        	String query = "Select score,count from  peerdetails where peername = \'"+peer+"\' ";
        	ResultSet rs = stm.executeQuery(query);
        	while (rs.next()) { 
        		score = rs.getInt("score");
        		count = rs.getInt("count");
        		newcount = count+1;
        		score = (score*count + feedback)/(newcount);
    			String updatequery = "update peerdetails set score = "+score+" ,count = "+newcount+" where peername =\'"+peer+"\' ";
    			stm.executeUpdate(updatequery);
    			break;
    		}
    	}    	
    	catch(Exception e){
    			e.printStackTrace();
    	} 

    }
    
    public void reduceScore(String peer)
    {

    	try{
    		int score = 0;
    		
        	Connection conn = getConnection();
        	Statement stm = conn.createStatement(); 
        	String query = "Select score,count from  peerdetails where peername = \'"+peer+"\' ";
        	ResultSet rs = stm.executeQuery(query);
        	while (rs.next()) { 
        		score = rs.getInt("score") - 1;
    			String updatequery = "update peerdetails set score = "+score+" where peername =\'"+peer+"\' ";
    			stm.executeUpdate(updatequery);
    			break;
    		}
    	}    	
    	catch(Exception e){
    			e.printStackTrace();
    	} 

    }

    public void storePeerFileList(String peername,String ipaddress,ArrayList<String> fileList){
    	try{
    		Statement stm = conn.createStatement(); 
    		 for(String newFile : fileList){
        	 String queryInsert = "insert into filelist values(\'"+ ipaddress + "\', \'" + newFile + "\',\'"
						+ peername + "\')";
        	 stm.executeUpdate(queryInsert);		 
    	 }
    	
    	}
    	catch (Exception e) {
			e.printStackTrace();
		}
    }

    public void storeAccessDetails(String peername,String filename,String accesstype){
    	try{
    		 Calendar calendar = Calendar.getInstance();
    	     String currentdate= calendar.getTime().toString();
    		Statement stm = conn.createStatement(); 
    		
        	 String queryInsert = "insert into  accesslist values(\'"+ filename + "\', \'" + accesstype + "\',"
        	 		+ "\'"+ currentdate+"\',"
        	 		+ "\'"+ peername + "\')";
        	 stm.executeUpdate(queryInsert);		 
    	
    
    	}
    	catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    
    public void addUser(String username,String password){
    	try{
    		
    		Statement stm = conn.createStatement(); 
    		
        	 String queryInsert = "insert into  idlist values(\'"+ password + "\', \'" + username + "\')";
        	 stm.executeUpdate(queryInsert);		 
    	
    
    	}
    	catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	public String getscore(String peer)
	{
try{
			
    		Statement stm = conn.createStatement(); 
	    	String query = "select score FROM  peerdetails where peername=\'"+peer+"\'";
	    	ResultSet rs = stm.executeQuery(query);
	    	while(rs.next())
	    	return(rs.getString("score"));
	       
	    }
	    catch(Exception e){
	    		e.printStackTrace();
	    }
	return peer;
	}
	public String getcredibility(String peer)
	{
try{
			
    		Statement stm = conn.createStatement(); 
	    	String query = "select count FROM  peerdetails where peername=\'"+peer+"\'";
	    	ResultSet rs = stm.executeQuery(query);
	    	while(rs.next())
	    	return(rs.getString("count"));
	       
	    }
	    catch(Exception e){
	    		e.printStackTrace();	    }
	return peer;
	}
	
	
	public String getipaddress(String peer)
	{
		
	try{
			
    		Statement stm = conn.createStatement(); 
	    	String query = "select ipaddress,port FROM  peerdetails where peername=\'"+peer+"\'";
	    	ResultSet rs = stm.executeQuery(query);
	    	while(rs.next())
	    	return(rs.getString("ipaddress"));
	       
	    }
	    catch(Exception e){
	    		e.printStackTrace();
	    }
	return peer;
		
	}
	
	public String getipport(String peer)
	{
		
	try{
			
    		Statement stm = conn.createStatement(); 
	    	String query = "select ipaddress,port FROM  peerdetails where peername=\'"+peer+"\'";
	    	ResultSet rs = stm.executeQuery(query);
	    	while(rs.next())
	    	return(rs.getString("port"));
	       
	    }
	    catch(Exception e){
	    		e.printStackTrace();
	    }
	return peer;
		
	}
	//
	
	public  List<String> getPeerforfile(String filename) {
		ArrayList<String> peers = new ArrayList<String>();
		try{
			
    		Statement stm = conn.createStatement(); 
	    	String query = "select distinct peername FROM  filelist where filename=\'"+filename+"\'";
	    	ResultSet rs = stm.executeQuery(query);
	    	while(rs.next())
	    	peers.add(rs.getString("peername"));
	    }
	    catch(Exception e){
	    		e.printStackTrace();
	    }
		return peers;
	}
	public  List<String> getPeerforfilewithScores(String filename) {
		ArrayList<String> peers = new ArrayList<String>();
		try{
			
    		Statement stm = conn.createStatement(); 
	    	String query = "select distinct filelist.peername,peerdetails.SCORE,peerdetails.count FROM  filelist join peerdetails on  filelist.peername=peerdetails.peername where filename=\'"+filename+"\'";
	    	ResultSet rs = stm.executeQuery(query);
	    	while(rs.next())
	    	peers.add(rs.getString("peername") + " - " + rs.getString("SCORE") +" - "+ rs.getString("count"));
	    }
	    catch(Exception e){
	    		e.printStackTrace();
	    }
		
		return peers;
	}
	
	public void setFileName(String file) {
		// TODO Auto-generated method stub
		try{
    		Connection conn = getConnection();
	    	Statement stmt = conn.createStatement(); 
	    	String query = "delete from  storefilename";
	    	 stmt.executeUpdate(query);
	    	 query = "Insert into  storefilename values(\'"+file+"\')";
	    	 stmt.executeUpdate(query);
	       	
	    
	    	}
	    catch(Exception e){
	    		e.printStackTrace();
	    }
	    	
			}

	public String getFileName() {
		try{
    		Connection conn = getConnection();
	    	Statement stmt = conn.createStatement(); 
	    
	    	String query = "SELECT filename FROM  storefilename";
	    	 ResultSet rs = stmt.executeQuery(query);
	    	 while(rs.next())
	 	    	return(rs.getString("filename"));
	    
	    	}
	    catch(Exception e){
	    	e.printStackTrace();
	    }
		return null;
	}

	public void reqactionPerformed() {
		// TODO Auto-generated method stub
		try{
			Connection conn = getConnection();
	    	String query = "SELECT all peername,reqfreq FROM  peerdetails";
	    	JDBCCategoryDataset dataset = new JDBCCategoryDataset(conn, query);
	     	JFreeChart chart = ChartFactory.createBarChart("Request Frequency Graph","peername","reqfreq", dataset, PlotOrientation.VERTICAL, false, true, true);
	    	CategoryPlot cplot = chart.getCategoryPlot();
	    	((BarRenderer)cplot.getRenderer()).setBarPainter(new StandardBarPainter());

	        BarRenderer r = (BarRenderer)chart.getCategoryPlot().getRenderer();
	        r.setSeriesPaint(0,Color.decode("#ff652f"));
	     
	    	NumberAxis rangeAxis = (NumberAxis) cplot.getRangeAxis();
	    	  	rangeAxis.setTickUnit(new NumberTickUnit(1));
	    	ChartFrame frame = new ChartFrame("Request Frequency", chart);
	    	frame.setVisible(true);
	    	frame.setSize(400,650);
	    	
		}
		
			catch(Exception e){
			JOptionPane.showMessageDialog(null,e);
		}
		
	}
		public void dispatchactionPerformed() {
			// TODO Auto-generated method stub
			try{
				Connection conn = getConnection();
		    
		    	String query = "SELECT all peername,dispatchfreq FROM  peerdetails";
		    	JDBCCategoryDataset dataset = new JDBCCategoryDataset(conn, query);
		    	
		    	JFreeChart chart = ChartFactory.createBarChart("Dispatch Frequecy Graph","peername","dispatchfreq", dataset, PlotOrientation.VERTICAL, false, true, true);
		
		    	CategoryPlot cplot = chart.getCategoryPlot();
		    	((BarRenderer)cplot.getRenderer()).setBarPainter(new StandardBarPainter());

		        BarRenderer r = (BarRenderer)chart.getCategoryPlot().getRenderer();
		        r.setSeriesPaint(0,Color.decode("#ff652f"));
		     
		    	NumberAxis rangeAxis = (NumberAxis) cplot.getRangeAxis();
		    	  	rangeAxis.setTickUnit(new NumberTickUnit(1));
		    	ChartFrame frame = new ChartFrame("Dispatch Frequency", chart);
		    	frame.setVisible(true);
		    	frame.setSize(400,650);
		    	
			}
			
				catch(Exception e){
				JOptionPane.showMessageDialog(null,e);
			}
		

	}
	    //acces modified
		public String getFileModifiedDetails() {
			try{
	    		Connection conn = getConnection();
		    	Statement stmt = conn.createStatement(); 
		    
		    	String query = "SELECT * FROM infosecurity.accesslist;";
		    	 ResultSet rs = stmt.executeQuery(query);
		    	 JTable table = new JTable(buildTableModel(rs));

		    	    // Closes the Connection

		    	    JOptionPane.showMessageDialog(null, new JScrollPane(table));
		    
		    	}
		    catch(Exception e){
		    	e.printStackTrace();
		    }
			return null;
		}
		
		public static DefaultTableModel buildTableModel(ResultSet rs)
		        throws SQLException {

		    java.sql.ResultSetMetaData metaData = rs.getMetaData();

		    // names of columns
		    Vector<String> columnNames = new Vector<String>();
		    int columnCount = metaData.getColumnCount();
		    for (int column = 1; column <= columnCount; column++) {
		        columnNames.add(metaData.getColumnName(column));
		    }

		    // data of the table
		    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		    while (rs.next()) {
		        Vector<Object> vector = new Vector<Object>();
		        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
		            vector.add(rs.getObject(columnIndex));
		        }
		        data.add(vector);
		    }

		    return new DefaultTableModel(data, columnNames);

		}
       	
	}


	   

	  

    


	