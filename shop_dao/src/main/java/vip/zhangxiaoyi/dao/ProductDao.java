package vip.zhangxiaoyi.dao;

import vip.zhangxiaoyi.pojo.Product;

/**
 * Created by zhangyi on  1:04
 */
public interface ProductDao {
    void add(Product product);

    /**
     * 通过名字查找商品
     * @param name 商品名字
     * @return 返回商品
     */
    Product findProductByName(String name);
}
