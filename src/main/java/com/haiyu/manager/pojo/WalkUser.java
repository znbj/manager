package com.haiyu.manager.pojo;



import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;



@Table(name = "walk_user")
@Data
public class WalkUser {
    @Id
    @Column(name = "user_id")
    private Integer user_id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "imageUrl")
    private String imageUrl;

    @Column(name = "phone")
    private String phone;

    @Column(name = "trueName")
    private String trueName;

    @Column(name = "introduction")
    private String introduction;
    /**
     * 状态，0正常，1冻结
     */
    @Column(name = "state")
    private Integer state;
    /**
     * 是否实名,0否,1是
     */
    @Column(name = "isRealName")
    private Integer isRealName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "createTime")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "updateTime")
    private Date updateTime;

    @Column(name = "coin")
    private String coin;

    @Column(name = "lateSignIn")
    private Date lateSignIn;
    /**
     * 审核状态:0审核中,1审核通过,2不合格,3未实名
     */
    @Column(name = "auditStatus")
    private Integer auditStatus;

    //    IDImageUrl
    @Column(name = "idImageUrl")
    private String idImageUrl;


    @Column(name = "remarks")
    private String remarks;

}
