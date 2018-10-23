package vip.zhangxiaoyi.pojo;

/**
 * Created by zhangyi on  1:20
 */
public class Product{

    private Integer id;
    private String name;
    private Double price;
    private String info;
    private String image;
    private ProductType productType;//这里的话，就不是简单的和数据库对应了。而是和类productType对应

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}


