package ctrl;

import dao.MemberService;
import metier.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/member.do")
public class MemberController extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String m = request.getParameter("m");
        if(m != null) {
            System.out.println(m);
            m = m.trim();
            if(m.equals("form")) {
                form(request, response);
            }else if(m.equals("login")){
                login(request, response);
            }else{

            }

        }else {

        }
    }

    private void form(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "Index.jsp";
        response.sendRedirect(view);
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String pass = request.getParameter("pass");
        System.out.println(username + " "+ pass);
        if(username != null) username = username.trim();
        if(pass != null) pass = pass.trim();

        HttpSession session = null;
        //Member member = null;
        MemberService service = MemberService.getInstance();
        int rCode = service.checkLogin(username, pass);
        request.setAttribute("rCode", rCode);
        System.out.println("!" + rCode);

        String view = "home";
        response.sendRedirect(view);
    }
}
