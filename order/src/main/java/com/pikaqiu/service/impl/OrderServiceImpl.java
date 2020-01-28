package com.pikaqiu.service.impl;

import com.pikaqiu.client.ProductClient;
import com.pikaqiu.dataobject.OrderDetail;
import com.pikaqiu.dataobject.OrderMaster;
import com.pikaqiu.dataobject.ProductInfo;
import com.pikaqiu.dto.CartDTO;
import com.pikaqiu.dto.OrderDTO;
import com.pikaqiu.enums.OrderStatusEnum;
import com.pikaqiu.enums.PayStatusEnum;
import com.pikaqiu.repository.OrderDetailRepository;
import com.pikaqiu.repository.OrderMasterRepository;
import com.pikaqiu.service.OrderService;
import com.pikaqiu.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2018/6/17.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private ProductClient productClient;

    /**
     * 创建订单
     *
     * @param orderDTO
     * @return
     */
    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        //获取订单id
        String orderId = KeyUtil.genUniqueKey();

        //拿到所有的商品id
        List<String> productIdList = orderDTO.getOrderDetailList().stream().map(OrderDetail::getProductId).collect(Collectors.toList());

        //调用外部接口
        List<ProductInfo> productInfos = productClient.listForOrder(productIdList);

        BigDecimal totalPrice = BigDecimal.ZERO;

        //创建key为ProductId value 为ProductInfo 的map
        Map<String, ProductInfo> collect = productInfos.stream().collect(Collectors.toMap(ProductInfo::getProductId, item -> item));

        //计算总价
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {

            Integer count = orderDetail.getProductQuantity();

            ProductInfo productInfo = collect.get(orderDetail.getProductId());

            //总价 + （ 单个数量 * 单个价格）
            totalPrice = totalPrice.add(BigDecimal.valueOf(count).multiply(productInfo.getProductPrice()));

            BeanUtils.copyProperties(productInfo, orderDetail);

            orderDetail.setOrderId(orderId);

            orderDetail.setDetailId(KeyUtil.genUniqueKey());

            //订单详情入库
            orderDetailRepository.save(orderDetail);

        }

        //组装扣库存集合
        List<CartDTO> decrList = orderDTO.getOrderDetailList().stream().map(e -> {
            CartDTO cartDTO = new CartDTO();
            cartDTO.setProductId(e.getProductId());
            cartDTO.setProductQuantity(e.getProductQuantity());
            return cartDTO;
        }).collect(Collectors.toList());

        //扣库存
        productClient.decreaseStock(decrList);

        OrderMaster orderMaster = new OrderMaster();

        BeanUtils.copyProperties(orderDTO, orderMaster);

        orderMaster.setOrderAmount(totalPrice);

        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());

        orderDTO.setOrderId(orderId);

        orderMaster.setOrderId(orderId);

        orderMaster.setPayStatus(PayStatusEnum.NEW.getCode());

        //订单主对象入库
        orderMasterRepository.save(orderMaster);

        return orderDTO;
    }
}
