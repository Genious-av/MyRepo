package application.interceptor;

import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
@Component
public class AppInterceptor implements HandlerInterceptor{
	static final String login="AAA";
	static final String password="111";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String authCoded=request.getHeader("Authorization");
		if(authCoded==null) {
			//response.getWriter("no authorization header");
			response.sendError(401);
			return false;
		}
		
		
		String authDecoded;
		try {
			authDecoded = new String(Base64.getDecoder().decode(authCoded.substring(6)));
		} catch (Exception e) {
			response.sendError(401);
			return false;
		}
		String[] authInfo=authDecoded.split(":");
		
		boolean authResult = authInfo[0].equals(login)&& authInfo[1].equals(password);
		
		if(!authResult) response.sendError(401);
		
		return authResult;
	}

}
