package vip.zhangxiaoyi.back.controller;


import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vip.zhangxiaoyi.back.vo.ProductVo;
import vip.zhangxiaoyi.dto.ProductDto;
import vip.zhangxiaoyi.pojo.Product;
import vip.zhangxiaoyi.pojo.ProductType;
import vip.zhangxiaoyi.service.ProductService;
import vip.zhangxiaoyi.service.ProductTypeService;

import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangyi on  22:57
 */
@Controller
@RequestMapping("/back/product")
public class ProductController {
    @Autowired
    public ProductTypeService productTypeService;
    @Autowired
    private ProductService productService;

    /**
     * 提前准备好所有的类别
     * @return
     */
    @ModelAttribute("prodcutTypes")
    public List<ProductType> preLoadTypes(){
        List<ProductType> list=new ArrayList<>();
        List<ProductType> all = productTypeService.findAll();
        for (ProductType productType : all) {
            if(productType.getStatus()==1){
                list.add(productType);//只保留启用的类别
            }
        }
        return list;
    }
    @RequestMapping("/findAll")
    public String findAll(@RequestParam(defaultValue = "1") Integer pageNUm){
        return "productManager";
    }
    @RequestMapping("/addProduct")
    public String addProduct(ProductVo productVo, HttpSession session, Model model){//由于添加的时候有很多参数，不可能一一接受，必须使用面向对象的思想，来封装一个vo,即一个包装类来接受。
        //那这个vo和product这个pojo有什么不同呢，vo是和表单对应，而pojo是和数据库映射。
        //由于service工程没有文件上传的jar包，所以vo不能直接丢到service。
        //vo转化为dto
        ProductDto productDto=new ProductDto();//这里是否可以交给spring来管理？
        try {
            PropertyUtils.copyProperties(productDto,productVo);
            String uploadPath=session.getServletContext().getRealPath("/WEB-INF/upload");//待会注意打断点，观察这里
            productDto.setFileUploadPath(uploadPath);
            productDto.setInputStream(productVo.getFile().getInputStream());
            productDto.setFileName(productVo.getFile().getOriginalFilename());
            boolean flag=productService.add(productDto);
            String msg;//返回提示信息
            if(flag){
                 msg="上传成功";
            }else {
                 msg="上传失败";
            }
            model.addAttribute("msg",msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "forward:findAll";
    }
    @RequestMapping("/nameExsit")
    @ResponseBody
    public Map<String,Object> nameExist(String name){
        Map<String,Object> map=new HashMap<>();
        Product product=productService.findProductByName(name);
        if (product!=null){
            map.put("valid",false);
            map.put("message","名字不可用，名字已经存在");
        }else {
            map.put("valid",true);
            map.put("message","名字可用");
        }
        return map;
    }
}
