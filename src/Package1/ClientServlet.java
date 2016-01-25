package Package1;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ClientServlet
 */
@WebServlet("/ClientServlet")
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	Client client= new Client();
	int counter=0;
	DAO dao= new DAO();
	String filename;
	String peer;
    public ClientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("email") != null)
		{
			 String id =  request.getParameter("emailid");
			 SendMail.sendmail(id);
			 request.getRequestDispatcher("authorization.jsp").forward(request,response);
		}
		if (request.getParameter("auth") != null)
		{
			 String id =  request.getParameter("id");
			 String password =  request.getParameter("password");
			 String success = dao.getAuthorization(id,password);
			 System.out.println("suc"+success);
			 if(success!=null)
			 {
					request.getRequestDispatcher("p2p.jsp").forward(request,response);
			 }
			 else
			 {
				
				 request.setAttribute("ErrorMessage","Not Authorized!!");
				 request.getRequestDispatcher("authorization.jsp").forward(request,response);
			 }
			
		}
		System.out.println(counter);
		if(request.getParameter("searchFiles") != null) 
		{
			List<String> files=client.getAllfiles();
			request.setAttribute("files",files);
			
			 request.getRequestDispatcher("p2p.jsp").forward(request,response);
		}
		
		if (request.getParameter("filebutton") != null) 
		{
			filename= request.getParameter("filename");
		List<String> lstPeers=client.getPeers(filename);
		request.setAttribute("PeerList",lstPeers);
		request.getRequestDispatcher("p2p2.jsp").forward(request,response);
		}
		if (request.getParameter("peerbutton") != null)
		{
			 peer=  request.getParameter("peername");
			 
			int score= dao.checkScoreforClient("Harshini");
			if(score>=5)
			{
				 dao.updateFrequency("Harshini", peer);
				 String ip=dao.getipaddress(peer);
				    int port= Integer.parseInt(dao.getipport(peer));
				    client.getFile(filename,ip,port);
				    request.setAttribute("LowScoreError", "File is downlaoded!! Enter feedback for peer..");
				    request.getRequestDispatcher("p2p3.jsp").forward(request,response);
					
			}
			else
			{
				 System.out.println("Inside else");
				request.setAttribute("LowScoreError", "You are not a trusted peer for "+peer+" So file is not downloaded!!");
				request.getRequestDispatcher("p2p2.jsp").forward(request,response);
			}
			
			
		}
		if (request.getParameter("save") != null)
		{
			int feedback=  Integer.parseInt (request.getParameter("feedback"));
			dao.updatefeedback(peer, feedback);
			request.getRequestDispatcher("p2p.jsp").forward(request,response);
		}
		}

}
