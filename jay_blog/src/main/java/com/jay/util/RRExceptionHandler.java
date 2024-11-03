//package com.jay.util;
//
//import com.alibaba.fastjson.JSON;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.lang.Nullable;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerExceptionResolver;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * Created by xiang.wei on 2018/10/13
// *
// * @author xiang.wei
// */
//@Component
//public class RRExceptionHandler implements HandlerExceptionResolver {
//    private  Logger logger = LoggerFactory.getLogger(RRExceptionHandler.class);
//
//
//    /**
//     * 处理异常
//     *
//     * @param request
//     * @param response
//     * @param e
//     * @return
//     */
//    @Nullable
//    @Override
//    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
//                                         @Nullable Object handle, Exception e) {
//
//        try {
//            response.setContentType("application/json;charset=utf-8");
//            response.setCharacterEncoding("UTF-8");
////            response.getWriter().print(JSON.toJSONString());
//
//        } catch (Exception e1) {
//            logger.error("捕获异常失败");
//        }
//
//        return new ModelAndView();
//    }
//
//}
