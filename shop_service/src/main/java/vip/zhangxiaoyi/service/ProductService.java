package vip.zhangxiaoyi.service;

import vip.zhangxiaoyi.dto.ProductDto;
import vip.zhangxiaoyi.pojo.Product;

/**
 * Created by zhangyi on  1:00
 */
public interface ProductService {

    boolean add(ProductDto productDto);
    /*
    通过名字查找商品
     */
    Product findProductByName(String name);
}
