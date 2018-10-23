package vip.zhangxiaoyi.service.impl;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import vip.zhangxiaoyi.dao.ProductDao;
import vip.zhangxiaoyi.dto.ProductDto;
import vip.zhangxiaoyi.pojo.Product;
import vip.zhangxiaoyi.pojo.ProductType;
import vip.zhangxiaoyi.service.ProductService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by zhangyi on  1:01
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao dao;
    @Override
    public boolean add(ProductDto productDto) {
        //先上传文件
        //获取上传文件夹
        String uploadPath=productDto.getFileUploadPath();
        //获取上传文件名后缀
        String fileName=productDto.getFileName();
        int dot=fileName.lastIndexOf(".");
        String suffix=fileName.substring(dot);
        //拼接上传文件路径
        fileName=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+ new Random().nextInt(100)+suffix;
        String filePath=uploadPath+"\\"+fileName;
        //上传
        try {
            StreamUtils.copy(productDto.getInputStream(),new FileOutputStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //再保存相关数据到数据库

        //将dto转化为pojo
        Product product=new Product();
        ProductType productType=new ProductType();
        productType.setId(productDto.getProductTypeId());
        try {
            PropertyUtils.copyProperties(product,productDto);
            product.setProductType(productType);
            product.setImage(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        dao.add(product);
        return true;
    }

    @Override
    public Product findProductByName(String name) {
        Product product=dao.findProductByName(name);
        return product;
    }
}
