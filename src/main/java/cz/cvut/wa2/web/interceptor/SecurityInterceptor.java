package cz.cvut.wa2.web.interceptor;

import cz.cvut.wa2.entity.Person;
import cz.cvut.wa2.entity.Role;
import cz.cvut.wa2.service.PersonService;
import cz.cvut.wa2.web.controller.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jakubchalupa
 * @since 18.04.16
 */
public class SecurityInterceptor implements HandlerInterceptor {

    @Autowired
    protected PersonService personService;

    private static final String SECURITY_HEADER = "X-Auth";

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if(o instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) o;

            //from method
            CheckAccess methodAnnotation = method.getMethodAnnotation(CheckAccess.class);
            if(methodAnnotation == null) {
                //from class
                methodAnnotation = method.getBeanType().getAnnotation(CheckAccess.class);
            }

            if(methodAnnotation == null) {
                //not secured
                return true;
            }

            String securityHeader = httpServletRequest.getHeader(SECURITY_HEADER);
            if(securityHeader == null || securityHeader.length() == 0) {
                throw new UnauthorizedException();
            }

            //pole roli, ktere jsou nutne pro tento resource
            Role.Type[] rolesNeeded = methodAnnotation.value();

            Person person = personService.findPersonByToken(securityHeader);
            if(person != null) {
                for(Role.Type roleNeeded : rolesNeeded) {
                    if(person.hasRole(roleNeeded)) {
                        return true;
                    }
                }
            }

            throw new UnauthorizedException();
        }

        throw new UnauthorizedException();
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        //empty
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        //empty
    }

}
