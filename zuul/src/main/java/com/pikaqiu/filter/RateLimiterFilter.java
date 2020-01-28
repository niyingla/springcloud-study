package com.pikaqiu.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import com.pikaqiu.exception.RateLimiterException;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVLET_DETECTION_FILTER_ORDER;

/**
 * Created by Administrator on 2018/7/15.
 */
@Component
public class RateLimiterFilter extends ZuulFilter {
    //每秒放入100个令牌 所以每秒只能访问100次
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(2);

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        //官方推荐写法 令牌应该放在最前
        return SERVLET_DETECTION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    //拦截时运行的代码 判断是否放行
    @Override
    public Object run() throws ZuulException {
        //尝试获取令牌
        if (!RATE_LIMITER.tryAcquire()) {
            throw new RateLimiterException();
        }

        return null;
    }
}
