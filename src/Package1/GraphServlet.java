package Package1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class GraphServlet
 */
@WebServlet("/GraphServlet")
public class GraphServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GraphServlet() {
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
		if(request.getParameter("Graph")!=null)	{
			DAO dao1 = new DAO();
			dao1.reqactionPerformed();
			request.getRequestDispatcher("Graph.jsp").forward(request,response);
		}
		
		if(request.getParameter("Graph2")!=null)	{
			DAO dao1 = new DAO();
			dao1.dispatchactionPerformed();
			request.getRequestDispatcher("Graph.jsp").forward(request,response);
		}
		
		if(request.getParameter("Graph3")!=null)	{
			DAO dao1 = new DAO();
			dao1.getFileModifiedDetails();
			request.getRequestDispatcher("Graph.jsp").forward(request,response);
		}
	}

}
