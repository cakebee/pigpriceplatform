package com.nmzl.pigpriceplatform.pojo;

import com.nmzl.pigpriceplatform.util.Constants;
import org.springframework.stereotype.Component;

/**
 * @author zxy
 * @date 2020/4/6 11:27
 */
@Component
public class MsgFactory{
    public Msg success() {
        Msg msg = new Msg();
        msg.setCode(Constants.CODE_SUCCESS);
        msg.setMessage(Constants.MESSAGE_SUCCESS);
        return msg;
    }

    public Msg fail() {
        Msg msg = new Msg();
        msg.setCode(Constants.CODE_FAILED);
        msg.setMessage(Constants.MESSAGE_FAILED);
        return msg;
    }
}
