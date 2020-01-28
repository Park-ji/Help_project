package mvc;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardDAO;
import dao.MemberDAO;
import vo.BoardVO;
import vo.MemberVO;

@WebServlet("*.mvc")
public class MVCServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String[] uri_parse = uri.split("[/.]");
		for (int i = 0; i < uri_parse.length; i++) {
			System.out.println(uri_parse[i]);
		}
		String process = uri_parse[uri_parse.length - 2];
		if (process.contentEquals("login")) {// 로그인
			String id = request.getParameter("id");
			System.out.println(id);
			String pw = request.getParameter("pw");
			System.out.println(pw);
			MemberDAO dao = new MemberDAO();
			int result = dao.loginMember(id, pw);
			System.out.println(result);
			request.setAttribute("loginstatus", result);
			if (result == 1) {
				HttpSession session = request.getSession();
				session.setAttribute("sessionid", id);
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/MainPage/" + process + ".jsp");
			dispatcher.forward(request, response);
			
		} else if (process.contentEquals("join")) {// 회원가입
			process = "join";
			RequestDispatcher dispatcher = request.getRequestDispatcher("/MainPage/" + process + ".jsp");
			dispatcher.forward(request, response);
		
		} else if (process.contentEquals("joinprocess")) {
			MemberDAO dao = new MemberDAO();
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String email = request.getParameter("mail1") + request.getParameter("mail2");
			int age = Integer.parseInt(request.getParameter("age"));
			String gender = request.getParameter("gender");
			MemberVO vo = new MemberVO(id, pw, name, phone, email, age, gender);
			int result = dao.joinMember(vo);
			request.setAttribute("joinstatus", result);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/MainPage/join.jsp");
			dispatcher.forward(request, response);
			//process = "help";
						
		} else if (process.contentEquals("helplist")) {
			BoardDAO dao = new BoardDAO();			
			ArrayList<BoardVO> list = dao.getBoardList();
			request.setAttribute("list", list);		
			RequestDispatcher dispatcher = request.getRequestDispatcher("/board/helplist.jsp");
			dispatcher.forward(request, response);
			
		} 	else if (process.contentEquals("search")) {
			String sido = request.getParameter("sido");
			String gugun = request.getParameter("gugun");
			String gender= request.getParameter("gender");
			String condition = request.getParameter("condition");
			
			if(sido.equals("시/도 선택")) sido = "%";
			if(gugun.equals("")) gugun = "%";
			if(gender.equals("무관")) gender = "%";
			if(condition.equals("")) condition = "%";	
			else condition = "%"+condition+"%";
			
			System.out.println(sido+", "+gugun+", "+gender+", "+condition);
			BoardDAO dao = new BoardDAO();
			ArrayList<BoardVO> list = dao.getSearchList(sido, gugun, gender, condition);
			request.setAttribute("list", list);
			System.out.println(list.size());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/board/helplist.jsp");
			dispatcher.forward(request, response);			
		} 
		else {
			process = "none";
			System.out.println("잘못된 요청입니다.");
		}
		

	}

}
