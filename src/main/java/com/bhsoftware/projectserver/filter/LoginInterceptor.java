package com.bhsoftware.projectserver.filter;


/**
 * 自定义登录拦截器
 */
//@Component
//public class LoginInterceptor implements HandlerInterceptor {
//    @Override
//    public boolean preHandle (HttpServletRequest httpServletRequest,
//                              HttpServletResponse httpServletResponse, Object o) throws Exception {
//        HttpSession session = httpServletRequest.getSession();
//        String contextPath=session.getServletContext().getContextPath();
//        String[] requireAuthPages = new String[]{
//                "index",
//        };
//        String uri = httpServletRequest.getRequestURI();
//        uri = StringUtils.remove(uri, contextPath+"/");
//        String page = uri;
//        if(begingWith(page, requireAuthPages)){
//            User user = (User) session.getAttribute("user");
//            if(user==null) {
//                httpServletResponse.sendRedirect("login");
//                return false;
//            }
//        }
//        return true;
//    }
//    private boolean begingWith(String page, String[] requiredAuthPages) {
//        boolean result = false;
//        for (String requiredAuthPage : requiredAuthPages) {
//            if(StringUtils.startsWith(page, requiredAuthPage)) {
//                result = true;
//                break;
//            }
//        }
//        return result;
//    } }
