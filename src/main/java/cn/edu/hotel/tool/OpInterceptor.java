package cn.edu.hotel.tool;

import cn.edu.hotel.response.AjaxResult;
import cn.edu.hotel.response.MsgType;
import cn.edu.hotel.response.ResponseTool;
import cn.edu.hotel.enums.Role;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * 操作员拦截器
 * 操作员以上的权限才可以放行
 */
public class OpInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if(session.getAttribute("role").equals(Role.OPERATOR.getValue()) ||
                session.getAttribute("role").equals(Role.ADMIN.getValue())){
            return true;
        }else {
            setCorsMappings(request, response);
            PrintWriter writer = response.getWriter();
            AjaxResult result = ResponseTool.failed(MsgType.PERMISSION_DENIED);
            ObjectMapper mapper = new ObjectMapper();
            writer.write(mapper.writeValueAsString(result));
            return false;
        }
    }

    private void setCorsMappings(HttpServletRequest request, HttpServletResponse response){
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
