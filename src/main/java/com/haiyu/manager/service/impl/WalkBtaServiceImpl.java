package com.haiyu.manager.service.impl;

import com.haiyu.manager.dao.WalkUserMapper;
import com.haiyu.manager.dto.WalkUserSearchDTO;
import com.haiyu.manager.pojo.WalkUser;
import com.haiyu.manager.service.WalkBtaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ClassName WalkBtaServiceImpl
 * @Author znb
 * @Date 2021/3/8 13:06
 * @Description WalkBtaServiceImpl
 * @Version 1.0
 */
@Service
@Slf4j
@Transactional(rollbackFor = RuntimeException.class)
public class WalkBtaServiceImpl implements WalkBtaService {

    @Resource
    private WalkUserMapper walkUserMapper;


    /**
     * 根据参数查询符合的数量
     *
     * @param entity 实体参数
     * @return 数量
     */
    @Override
    public Long findPassingRecordCount(WalkUserSearchDTO entity) {
        return walkUserMapper.findPassingRecordCount(entity);
    }

    /**
     * 查询记录数据分页
     *
     * @param entity 实体参数
     * @return 記錄
     */
    @Override
    public List<WalkUser> findPassingRecordData(WalkUserSearchDTO entity) {
        return walkUserMapper.findPassingRecordData(entity);
    }

    /**
     * @param user
     * @return
     */
    @Override
    public boolean updateUser(WalkUser user) {
        user.setUpdateTime(new Date());
        return walkUserMapper.updateWalkUser(user) > 0;
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public boolean deletById(Integer userId) {
        return walkUserMapper.deleteById(userId) > 0;
    }


}
