package vip.zhangxiaoyi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.zhangxiaoyi.common.exception.ProductTypeException;
import vip.zhangxiaoyi.dao.ProductTypeDao;
import vip.zhangxiaoyi.pojo.ProductType;
import vip.zhangxiaoyi.service.ProductTypeService;

import java.util.List;

/**
 * Created by zhangyi on  18:45
 */
@Service
public class ProductTypeServiceImpl implements ProductTypeService {
    @Autowired
    ProductTypeDao dao;
    @Override
    public List<ProductType> findAll() {
        return dao.findAll();
    }

    @Override
    public ProductType findProductTypeById(Integer id) throws ProductTypeException {
       return dao.findProductTypeById(id);
    }

    @Override
    public void add(String name) {
        dao.add(name);
    }

    @Override
    public ProductType findProductTypeByName(String name) {
        return dao.findProductTypeByName(name);
    }

    @Override
    public boolean modifyName(int id, String name) {
        return dao.modifyName(id,name);
    }

    @Override
    public boolean deleteProductType(int id) {
        return dao.deleteProductType(id)==1?true:false;
    }

    @Override
    public boolean modifyStatus(int id) {
        ProductType productType=dao.findProductTypeById(id);
        if(productType.getStatus()==1){
            return dao.modifyStatus(id,0)==1?true:false;
        }else {
            return dao.modifyStatus(id,1)==1?true:false;
        }

    }
}
