package sg.edu.nus.iss.t4p.phoenix.core.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sg.edu.nus.iss.t4p.phoenix.core.constant.ConstantAttribute;
import sg.edu.nus.iss.t4p.phoenix.core.enumeration.UrlPathEnum;
import sg.edu.nus.iss.t4p.phoenix.utility.T4StringUtil;


/**
* Class for Authentication Filter
* @author Kenny Hartono A0092674W
* @version 1.0
*/

@WebFilter("/*")
public class AuthenticationFilter implements Filter{

	private static List<UrlPathEnum> UN_FILTERED;
	static{
		UN_FILTERED = new ArrayList<UrlPathEnum>();
		UN_FILTERED.add(UrlPathEnum.PAGE_HOME);
		UN_FILTERED.add(UrlPathEnum.ACTION_LOGIN);
		UN_FILTERED.add(UrlPathEnum.ACTION_LOGOUT);
	}
	
	
	@Override
	public void destroy() {/*IGNORED*/}

	@Override
	public void init(FilterConfig arg0) throws ServletException {/*IGNORED*/}
	
	
	/**
	 * Authentication Filter
	 * @see javax.servlet.Filter
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		String pathInfo = httpRequest.getPathInfo();
		if(T4StringUtil.isEmpty(pathInfo) || UN_FILTERED.contains(UrlPathEnum.getByControlPath(pathInfo)) )
			chain.doFilter(request, response);
		else{
			if (httpRequest.getSession().getAttribute(ConstantAttribute.USER_SESSION)!=null)
				chain.doFilter(request, response);
			else{
				HttpServletResponse httpResponse = (HttpServletResponse)response;
				httpResponse.sendRedirect(httpRequest.getContextPath()+UrlPathEnum.PAGE_HOME.getFrontControlPath());
			}
		}
		
		
	}

}
