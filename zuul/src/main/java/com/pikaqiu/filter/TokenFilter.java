package com.pikaqiu.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * Created by Administrator on 2018/7/15.
 */
//@Component
   /*public class TokenFilter extends ZuulFilter {
 @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        //官方推荐写法
        return PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    //拦截时运行的代码 判断是否放行
    @Override
    public Object run() throws ZuulException {
        //获取请求上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        //获取到请求
        HttpServletRequest request = currentContext.getRequest();
        //获取请求参数中的token
        String token = request.getParameter("token");

        //判断是否为null
        if (StringUtils.isEmpty(token)) {
            //不发送响应 (响应被拦截)
            currentContext.setSendZuulResponse(false);

            currentContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());

        }

        System.out.println(token);

        return null;
    }
}*/
