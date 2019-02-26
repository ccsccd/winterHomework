package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.System.out;

@WebServlet("/User/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq=(HttpServletRequest)req;
        HttpServletResponse httpResp=(HttpServletResponse)resp;
        Object o=httpReq.getSession().getAttribute("已登录用户");
        if(o==null){
            httpResp.sendRedirect(httpReq.getContextPath() + "/oldMain.html");
            out.print("1.5");
        }else {
            chain.doFilter(req,resp);
        }
    }

    @Override
    public void destroy() {
    }
}
