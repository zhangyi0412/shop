package vip.zhangxiaoyi.dao;

import org.apache.ibatis.annotations.Param;
import vip.zhangxiaoyi.pojo.ProductType;

import java.util.List;

/**
 * Created by zhangyi on  18:33
 */
public interface ProductTypeDao {
    //查询所有分类
    public List<ProductType> findAll();
    //查询分类按照id
    ProductType findProductTypeById(Integer id);

    /**
     * 插入商品类型
     * @param name 商品名字
     */
    void add(String name);

    /**
     * 根据name查找
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
    boolean modifyName(@Param("id") int id, @Param("name") String name);

    /**
     * 删除类型
     * @param id
     * @return
     */
    int deleteProductType(int id);

    /**
     * 修改禁用启用状态
     * @param id
     * @return
     */
    int modifyStatus(@Param("id") int id,@Param("status") int status);
}
