package com.pikaqiu.controller;

import com.pikaqiu.converter.OrderForm2OrderDTOConverter;
import com.pikaqiu.dto.OrderDTO;
import com.pikaqiu.enums.ResultEnum;
import com.pikaqiu.exception.OrderException;
import com.pikaqiu.form.OrderForm;
import com.pikaqiu.service.OrderService;
import com.pikaqiu.utils.ResultVOUtil;
import com.pikaqiu.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/6/17.
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    Logger logger = LoggerFactory.getLogger(OrderController.class);

    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            logger.error("【创建订单】参数不正确, orderForm={}", orderForm);
            throw new OrderException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        // orderForm -> orderDTO
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            logger.error("【创建订单】购物车信息为空");
            throw new OrderException(ResultEnum.CART_EMPTY);
        }

        OrderDTO result = orderService.create(orderDTO);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", result.getOrderId());
        return ResultVOUtil.success(map);
    }
}
