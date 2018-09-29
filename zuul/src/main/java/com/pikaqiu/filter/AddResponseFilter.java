package com.pikaqiu.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.UUID;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SEND_FORWARD_FILTER_ORDER;

/**
 * Created by Administrator on 2018/7/15.
 */
@Component
public class AddResponseFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return POST_TYPE;
    }

    @Override
    public int filterOrder() {
        //官方推荐写法
        return SEND_FORWARD_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {

        //真实环境中
        // 建议将权限接口信息写在redis中
        // 根据请求路径判断是否在拦截路径中存在  然后每个 请求角色 （买家 卖家  ）
        // 或者每一个请求服务 写一个filter

        return true;
    }

    //拦截时运行的代码 判断是否放行
    @Override
    public Object run() throws ZuulException {

        //真实环境中   只写拦截后的判断操作

        RequestContext currentContext = RequestContext.getCurrentContext();

        HttpServletResponse response = currentContext.getResponse();

        response.setHeader("X-Foo", UUID.randomUUID().toString().replace("-", ""));

        return null;
    }
}
