package Package1;
import java.io.IOException;
 
import java.net.Socket;  
import java.net.UnknownHostException;
  
import java.util.List;
import java.util.Scanner;  
import java.io.*;
  
public class Client {  
	static DAO dao= new DAO();
	
	public List<String> getAllfiles()
	{
		
		return(dao.getAllfiles());
	}
	
	
	public List<String> getPeers(String filename)
	{
		dao.setFileName(filename);
		return(dao.getPeerforfilewithScores(filename));
	}
	public String getScore(String peer)
	{
		
		int score1= Integer.parseInt(dao.getscore(peer));
		int credibility =Integer.parseInt(dao.getcredibility(peer));
		
		if(score1<=5)
		{
			return("Score is very less!!"+ score1 +" Peer is not trusted.Do you wish to continue? Y or N");
		}
		else
		{
			return("Score of peer is "+score1+ "/10 and no.of downloads is "+credibility);
			
		}
	}
	@SuppressWarnings("unused")
	public void getFile(String file_name,String ip, int port) throws UnknownHostException, IOException 
	{
		int filesize=1022386;
		int bytesRead;
		int currentTot = 0;
		   
		     String fileNm = null;
					{		
			fileNm = "C:\\Users\\Sri Divya\\Desktop\\PeerDownload\\"+file_name;
		     Socket socket = new Socket(ip,port); 
				byte [] bytearray = new byte [filesize];
				
					FileOutputStream fos = new FileOutputStream(fileNm); 
					InputStream is = socket.getInputStream();

				BufferedOutputStream bos = new BufferedOutputStream(fos); 
				bytesRead = is.read(bytearray,0,bytearray.length);
				currentTot = bytesRead; 
				do { 
					bytesRead = is.read(bytearray, currentTot, (bytearray.length-currentTot));
					if(bytesRead >= 0) currentTot += bytesRead;
					}
				while(bytesRead > -1);
				bos.write(bytearray, 0 , currentTot);
				bos.flush();
				bos.close();
				socket.close();
				}
		
	}
	 
  
}  