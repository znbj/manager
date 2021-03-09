package com.haiyu.manager.dto;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName WalkUserSearchDTO
 * @Author znb
 * @Date 2021/3/8 9:52
 * @Description WalkUserSearchDTO
 * @Version 1.0
 */
@Data
public class WalkUserSearchDTO {


    private String trueName;

    private String phone;

    private Date startTime;

    private Date endTime;
    /**
     * 审核状态:0审核中,1审核通过,2不合格,3未实名
     */
    private Integer AuditStatus;
    /**
     * 每页数量 查询参数用
     * */
    private int pageSize;
    /**
     * 当前页 查询参数用
     * */
    private int pageIndex;

}
