package com.haiyu.manager.dao;

import com.haiyu.manager.dto.WalkUserSearchDTO;
import com.haiyu.manager.pojo.WalkUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName WalkUserMapper
 * @Author znb
 * @Date 2021/3/8 13:06
 * @Description WalkUserMapper
 * @Version 1.0
 */
@Mapper
public interface WalkUserMapper {

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
     * @param entity
     * @return
     */
    int updateWalkUser(WalkUser entity);

    /**
     *
     * @param userId
     * @return
     */
    int deleteById(Integer userId);
}
