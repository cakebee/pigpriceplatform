package com.nmzl.pigpriceplatform.pojo;

import com.nmzl.pigpriceplatform.util.Constants;
import lombok.Data;

/**
 * 统一返回消息格式
 * @author : zxy
 * @date : 2020/4/6 10:01
 */
@Data
public class Msg {

    /** 状态码 */
    private int code = 20000;

    /** 描述 */
    private String message;

    /** 返回数据，如果状态码为失败则为空 */
    private Object data;

    /**
     * 设置message
     * @param message: 具体信息
     * @return : this
     * @author : zxy
     * @date : 2020/4/6 11:42
     */
    public Msg setMessage(String message){
        this.message = message;
        return this;
    }

    /**
     * 设置data
     * @param data: 数据
     * @return : this
     * @author : zxy
     * @date : 2020/4/6 11:42
     */
    public Msg setData(Object data){
        this.data = data;
        return this;
    }

    /**
     * 设置code
     * @param code: 状态码
     * @return : this
     * @author : zxy
     * @date : 2020/4/28 8:41 上午
     */
    public Msg setCode(int code) {
        this.code = code;
        return this;
    }

    /**
     * 获取一个成功的Msg，设置成功的code和message
     * @return : Msg
     * @author : zxy
     * @date : 2020/4/28 8:42 上午
     */
    public static Msg success() {
        return new Msg().setCode(Constants.CODE_SUCCESS);
    }

    /**
     * 获取一个失败的Msg，设置失败的code和message
     * @return : Msg
     * @author : zxy
     * @date : 2020/4/28 8:43 上午
     */
    public static Msg failed() {
        return new Msg().setCode(Constants.CODE_FAILED)
                .setMessage(Constants.MESSAGE_FAILED);
    }
}
