package vip.zhangxiaoyi.service;

import vip.zhangxiaoyi.common.exception.ProductTypeException;
import vip.zhangxiaoyi.pojo.ProductType;

import java.util.List;

/**
 * Created by zhangyi on  18:44
 */
public interface ProductTypeService {
    /**
     * 查找所有类别
     * @return
     */
    public List<ProductType> findAll();

    /**
     * 根据id查找
     * @param id
     * @return
     * @throws ProductTypeException
     */
    ProductType findProductTypeById(Integer id) throws ProductTypeException;

    /**
     * 插入
     * @param name
     */
    void add(String name);

    /**
     * 根据名字查找
     * @param name
     * @return
     */
    ProductType findProductTypeByName(String name);

    /**
     * 修改类型名字
     * @param id
     * @param name
     * @return
     */
    boolean modifyName(int id, String name);

    /**
     * 删除类别
     * @param id
     * @return
     */
    boolean deleteProductType(int id);

    /**
     * 修改开启禁用状态
     * @param id
     * @return
     */
    boolean modifyStatus(int id);
}
