package sg.edu.nus.iss.t4p.phoenix.action.presenterproducer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.edu.nus.iss.t4p.phoenix.entity.user.User;
import sg.edu.nus.iss.t4p.phoenix.core.action.BaseController;
import sg.edu.nus.iss.t4p.phoenix.core.constant.ConstantAttribute;
import sg.edu.nus.iss.t4p.phoenix.delegate.user.*;
import sg.edu.nus.iss.t4p.phoenix.utility.T4StringUtil;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/presenterproducerController/*")
public class PresenterProducerController {
	protected void doList(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException{

		List<User> presenterproducer = UserDelegate.getInstance().retrievePresenterProducerList(true);
		
		String callBackUrl = (String) request.getAttribute(ConstantAttribute.CALL_BACK_URL);
		if(T4StringUtil.isEmpty(callBackUrl))
			callBackUrl = request.getParameter(ConstantAttribute.CALL_BACK_URL);
			
		request.setAttribute(ConstantAttribute.CALL_BACK_URL, callBackUrl);
		
		request.setAttribute("presenterproducer", presenterproducer);
		request.getRequestDispatcher("/pages/presenterproducer/list_presenterproducer.jsp").forward(request, response);		
	
		System.out.print("PresenterProducer DoList");
	}
	

}
