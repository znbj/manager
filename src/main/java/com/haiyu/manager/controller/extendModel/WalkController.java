package com.haiyu.manager.controller.extendModel;

import com.alibaba.fastjson.JSON;
import com.haiyu.manager.common.CommonResult;
import com.haiyu.manager.dto.UserSearchDTO;
import com.haiyu.manager.dto.WalkUserSearchDTO;
import com.haiyu.manager.pojo.BaseAdminUser;
import com.haiyu.manager.pojo.WalkUser;
import com.haiyu.manager.response.PageDataResult;
import com.haiyu.manager.service.WalkBtaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName WalkController
 * @Author znb
 * @Date 2021/3/8 9:44
 * @Description WalkController
 * @Version 1.0
 */
@RestController
@RequestMapping("bta")
@Slf4j
public class WalkController {

    @Autowired
    private WalkBtaService walkBtaService;

    @RequestMapping(value = "/getUserList", method = RequestMethod.GET)
    public CommonResult getUserList(@RequestParam String queryStr, @RequestParam int page, @RequestParam int limit) {
        try {
            log.info("用户列表查询=pdr:");
            WalkUserSearchDTO walkUserSearchDTO = JSON.parseObject(queryStr, WalkUserSearchDTO.class);
            Long passingRecordCount = walkBtaService.findPassingRecordCount(walkUserSearchDTO);
            walkUserSearchDTO.setPageIndex(page);
            walkUserSearchDTO.setPageSize(limit);
            List<WalkUser> passingRecordData = walkBtaService.findPassingRecordData(walkUserSearchDTO);
            return CommonResult.ok(passingRecordData, "分页查询历史过车记录成功", passingRecordCount);
        } catch (Exception e) {
            log.error("用户列表查询异常！", e);
            return CommonResult.failed("用户列表查询异常:" + e.getMessage());
        }
    }


    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public CommonResult setUser(WalkUser user) {
        log.info("设置用户[更新]！user:" + user);
        if(user.getUser_id() == null){
            return CommonResult.failed("id不可为空");
        }else{
            boolean b = walkBtaService.updateUser(user);
            if (b) {
                return CommonResult.ok("成功");
            } else {
                return CommonResult.failed("修改失败");
            }
        }
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public CommonResult deleteUser(Integer id) {
        log.info("设置用户[删除]！user:" + id);
        if (id == null) {
            return CommonResult.failed("id不可为空");
        } else {
            boolean b = walkBtaService.deletById(id);
            if (b) {
                return CommonResult.ok("成功");
            } else {
                return CommonResult.failed("修改失败");
            }
        }
    }
}
