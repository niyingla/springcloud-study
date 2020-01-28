package com.pikaqiu.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Administrator on 2018/6/17.
 */
@Data
@Entity
//当表名不符合规范时  需要@table指定 像gi-saas就是不规范 因为对象中没有
public class ProductCategory {

    @Id
    @GeneratedValue
    private Integer categoryId;

    /**
     * 类目名字.
     */
    private String categoryName;

    /**
     * 类目编号.
     */
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;
}
