package vip.zhangxiaoyi.back.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vip.zhangxiaoyi.common.exception.ProductTypeException;
import vip.zhangxiaoyi.common.utils.AjaxResult;
import vip.zhangxiaoyi.pojo.ProductType;
import vip.zhangxiaoyi.service.ProductTypeService;

import java.util.List;

/**
 * Created by zhangyi on  16:55
 */
@Controller
@RequestMapping("/back/productType")
public class ProductTypeController {
    @Autowired
    private ProductTypeService productTypeService;

    @RequestMapping("/findAll")
    public String findAll(Model model, @RequestParam(defaultValue = "1") Integer pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<ProductType> productTypes = productTypeService.findAll();
        PageInfo<ProductType> pageInfo = new PageInfo<>(productTypes);
        model.addAttribute("pageInfo", pageInfo);
        return "productTypeManager";
    }

    @RequestMapping("/findProductTypeById")
    @ResponseBody
    public AjaxResult findProductTypeById(Integer id) {
        try {
            ProductType productType = productTypeService.findProductTypeById(id);
            return AjaxResult.success("查询成功", productType);
        } catch (ProductTypeException e) {
            return AjaxResult.failure("查询失败");
        }
    }

    @RequestMapping("/addProductType")
    @ResponseBody
    public AjaxResult addProductType(String name) {
        //查询该名字是否存在，如果不存在才插入，如果存在，返回失败提示信息。
        ProductType productType = productTypeService.findProductTypeByName(name);
        if (productType!=null) {
            return AjaxResult.success("该商品类型名已经存在", null);
        } else {
            productTypeService.add(name);
            return AjaxResult.success("添加成功", null);
        }
    }

    @RequestMapping("/modifyName")
    @ResponseBody
    public AjaxResult modifyName(int id, String name) {
        ProductType productType = productTypeService.findProductTypeByName(name);
        if (productType!=null) {
            return AjaxResult.failure("修改失败，该类型名存在");
        }
        boolean flag = productTypeService.modifyName(id, name);
        if (flag) {
            return AjaxResult.success("修改成功", null);
        } else {
            return AjaxResult.failure("修改失败，请重试");
        }
    }
    @RequestMapping("/deleteProductType")
    @ResponseBody
    public AjaxResult deleteProductType(int id) {
      boolean flag= productTypeService.deleteProductType(id);
      if(flag)
          return AjaxResult.success("删除成功",null);
      else
          return  AjaxResult.failure("删除失败");
    }
    @RequestMapping("/modifyStatus")
    @ResponseBody
    public AjaxResult modifyStatus(int id) {
        boolean flag= productTypeService.modifyStatus(id);
        if(flag)
            return AjaxResult.success("修改成功",null);
        else
            return  AjaxResult.failure("修改失败");
    }

}
