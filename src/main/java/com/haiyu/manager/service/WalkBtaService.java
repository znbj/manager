package com.haiyu.manager.service;


import com.haiyu.manager.dto.WalkUserSearchDTO;
import com.haiyu.manager.pojo.WalkUser;

import java.util.List;

/**
 * @ClassName
 * @Author znb
 * @Date 2021/2/5 10:47
 * @Description
 * @Version 1.0
 */
public interface WalkBtaService {

    /**
     * 根据参数查询符合的数量
     * @param entity 实体参数
     * @return 数量
     */
    Long findPassingRecordCount(WalkUserSearchDTO entity);
    /**
     * 查询记录数据分页
     * @param entity 实体参数
     * @return 記錄
     */
    List<WalkUser> findPassingRecordData(WalkUserSearchDTO entity);

    /**
     *
     * @param user
     * @return
     */
    boolean updateUser(WalkUser user);

    /**
     *
     * @param userId
     * @return
     */
    boolean deletById(Integer userId);
}
