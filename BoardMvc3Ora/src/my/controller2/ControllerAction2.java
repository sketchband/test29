package my.controller2;

import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.activation.CommandMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.action2.CommandAction2;


public class ControllerAction2 extends HttpServlet {
	private Map commandMap = new HashMap();
	@Override
	public void init(ServletConfig config) throws ServletException {
		String path = config.getServletContext().getRealPath("/");
		String props = config.getInitParameter("propertyConfig");
		
		Properties pr = new Properties();
		
		FileInputStream f = null;
		
		try {
			f = new FileInputStream(path+props);
			pr.load(f);
		}catch(IOException e) {
			throw new ServletException(e);
		}finally {
			if(f!=null) try {f.close();}catch(IOException ex) {}
		}
		
		Iterator keylter = pr.keySet().iterator();
		
		while(keylter.hasNext()) {
			String command = (String) keylter.next();
			String className = pr.getProperty(command);
			try {
				Class commandClass = Class.forName(className);
				Object commandInstance = commandClass.newInstance();
				commandMap.put(command, commandInstance);
		}catch(ClassNotFoundException e) {
			throw new ServletException(e);
		}catch(InstantiationException e) {
			throw new ServletException(e);
		}catch(IllegalAccessError e) {
			throw new ServletException(e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		execute(req,resp);
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		execute(req,resp);
	}
	private void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = null;
		CommandAction2 com = null;
		try {
			String command = req.getRequestURI();
			System.out.println(command);
			com = (CommandAction2)commandMap.get(command);
			System.out.println(com.toString());
			view = com.requestPro(req, resp);
			System.out.println(view);
		}catch(Throwable e) {
			throw new ServletException(e);
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher(view);
		dispatcher.forward(req, resp);
	}
}
