package com.pikaqiu.repository;

import com.pikaqiu.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2018/6/17.
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
}
