package Package1;
import java.net.*; 
import java.util.ArrayList;
import java.util.Enumeration;
import java.io.*;

public class Server {
	public static final int BUFFER_SIZE = 0;

	public static void main (String [] args ) throws IOException {

		DAO dao= new DAO();
		
		String 	  ip =InetAddress.getLocalHost().getHostAddress();
			
			Enumeration e = NetworkInterface.getNetworkInterfaces();
			while(e.hasMoreElements())
			{
			    NetworkInterface n = (NetworkInterface) e.nextElement();
			    Enumeration ee = n.getInetAddresses();
			    while (ee.hasMoreElements())
			    {
			        InetAddress i = (InetAddress) ee.nextElement();
			       if(i.getHostAddress().startsWith("10."))
			       {
			    	 ip = i.getHostAddress();
			        System.out.println(i.getHostAddress());
			     	
			       }
			     
			    }
			}
			
			
		ServerSocket serverSocket =null;
		Socket socket = null;
		try{
			ArrayList<String> fileList = new ArrayList<String>();
			fileList.add("ISAProject.pdf");
		
			serverSocket = new ServerSocket(6300); 
			dao.storePeerDetails("Harshini",ip,6300);
			dao.storePeerFileList("Harshini",ip, fileList);
		}
		catch(Exception ex){
			 ex.printStackTrace();
			    System.out.println("Server error");

		}
		
		try {
			while(true){
			    	socket = serverSocket.accept();
			        System.out.println("connection Established");
			        ServerThread st=new ServerThread(socket);
			        String fileName = dao.getFileName();
					System.out.println("Server side is ...... :"+fileName);
			        st.start();
			        System.out.println("Accepted connection : " + socket);

			    }
		} finally{
			socket.close();
			serverSocket.close();
		}
	}//main
}	

		

class ServerThread extends Thread{  

	FileInputStream fin =null;   
	OutputStream os =null;
	BufferedInputStream bin =null;
    Socket s=null;

    public ServerThread(Socket s){
        this.s=s;
    }

    public void run() {
    	try{
    		
    		DAO dao= new DAO();
    		
    		     String fileName = dao.getFileName();
    		     System.out.println(fileName);
    		     String fileSearch = "C:\\Users\\Sri Divya\\Desktop\\"+fileName;
    			File transferFile = new File (fileSearch);
    			byte [] bytearray = new byte [(int)transferFile.length()];
    			fin = new FileInputStream(transferFile);
    			bin = new BufferedInputStream(fin);
    			bin.read(bytearray,0,bytearray.length); 
    			os = s.getOutputStream();
    			os.write(bytearray,0,bytearray.length);
    			os.flush(); 
    			System.out.println("File transfer complete");
		
    	}
    	catch(IOException e){
    			e.printStackTrace();
    	}

    	finally	{    
    			try{
    				System.out.println("Connection Closing..");
    				if (bin!=null){
    					bin.close();
    					System.out.println(" Socket Input Stream Closed");
    				}

    				if(os!=null){
    					os.close();
    					System.out.println("Socket Out Closed");
    				}
    				if (s!=null){
    					s.close();
    					System.out.println("Socket Closed");
    				}

    			}
    			catch(IOException ie){
    				System.out.println("Socket Close Error");
    			}
    		}//end finally
    	}//run
	}
		
