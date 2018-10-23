package vip.zhangxiaoyi.common.exception;

/**
 * Created by zhangyi on  11:19
 */
public class ProductTypeException extends Exception {
    public ProductTypeException() {
        super();
    }

    public ProductTypeException(String message) {
        super(message);
    }

    public ProductTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductTypeException(Throwable cause) {
        super(cause);
    }

    protected ProductTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
