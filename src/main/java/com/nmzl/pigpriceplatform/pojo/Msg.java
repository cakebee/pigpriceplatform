package com.nmzl.pigpriceplatform.pojo;

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
}
