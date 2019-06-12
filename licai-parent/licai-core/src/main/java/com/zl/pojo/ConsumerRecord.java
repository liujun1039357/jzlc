package com.zl.pojo;

import lombok.Data;
import lombok.ToString;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户登录记录
 * */

@Data
@ToString
public class ConsumerRecord implements Serializable {
    /**登录状态,默认为0 登录成功*/
    public static Integer SUCCESS= 0;
    /**登录状态, 1 登录失败*/
    public static Integer FAIL= 1;

    private static final long serialVersionUID = 1L;

    /**流水ID*/
    private String recordId;
    /**用户ID*/
    private String consumerId;
    /**ip*/
    private String ip;
    /**登录时间*/
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date loginTime;
    /**登录状态,默认为0 登录成功  1 登录失败*/
    private Integer loginFlag = SUCCESS;

}
