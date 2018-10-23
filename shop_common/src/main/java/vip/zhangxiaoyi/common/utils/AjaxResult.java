package vip.zhangxiaoyi.common.utils;

import java.io.Serializable;

/**
 * Created by zhangyi on  11:14
 */
public class AjaxResult implements Serializable {
    //状态吗
    private  int status;
    //返回的提示信息
    private  String message;
    //返回的目标结果
    private  Object data;

    public AjaxResult(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static AjaxResult success(String msg, Object data){
        return  new AjaxResult(1,msg,data);
    }
    public static AjaxResult failure(String msg){
        return  new AjaxResult(0,msg,null);
    }
}
