package servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAO;
import entity.Student;

@WebServlet({ "", "/index", "/new", "/search", "/insert", "/edit", "/delete" })
public class Home extends HttpServlet {

	private static final long serialVersionUID = -8753957661320028246L;
	private DAO dao = new DAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String path = req.getServletPath();
		if (path.equals("/index")) {
			getIndex(req, resp);
		} else if (path.equals("/new")) {
			getNew(req, resp);
		} else if (path.equals("/edit")) {
			getEdit(req, resp);
		} else if (path.equals("/delete")) {
			doRemove(req, resp);
		} else {
			getIndex(req, resp);
		}
	}

	private void getIndex(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Student> students = dao.findAll();
		req.setAttribute("students", students);
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}

	private void getNew(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/new.jsp").forward(req, resp);
	}
	
	private void getEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		Student student = dao.findById(id);
		req.setAttribute("student", student);
		req.getRequestDispatcher("/edit.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String path = req.getServletPath();
		if (path.equals("/search")) {
			doSearch(req, resp);
		} else if (path.equals("/edit")) {
			doEdit(req, resp);
		}
	}

	private void doSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String major = req.getParameter("major");
		List<Student> students = dao.findByMajor(major);
		req.setAttribute("students", students);
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
	
	private void doEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		Double mark = Double.parseDouble(req.getParameter("mark"));
		String major = req.getParameter("major");
		
		Student newStudent = new Student();
		newStudent.setId(id);
		newStudent.setName(name);
		newStudent.setMark(mark);
		newStudent.setMajor(major);
		
		Student student = dao.edit(newStudent);
		
		System.out.println(newStudent.toString());

		if (dao.create(student) != null) {
			req.setAttribute("messages", "Sửa thành công");
			req.getRequestDispatcher("/edit.jsp").forward(req, resp);
		} else {
			req.setAttribute("messages", "Sửa thất bại");
			req.getRequestDispatcher("/edit.jsp").forward(req, resp);
		}
	}
	
	private void doRemove(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		Student student = dao.findById(id);
		Student deleted = dao.remove(student);
		if (dao.create(deleted) == null) {
			req.setAttribute("messages", "Xóa thành công");
			resp.sendRedirect("index");
		} else {
			req.setAttribute("messages", "Xóa thất bại");
			resp.sendRedirect("index");
		}
	}
}
