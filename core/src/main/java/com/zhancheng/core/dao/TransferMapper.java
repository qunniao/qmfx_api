package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.core.entity.Transfer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.core.vo.CommissionListVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 钱包收支流水
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:29
 */
@Repository
public interface TransferMapper extends BaseMapper<Transfer> {

    /**
     * 查询佣金
     * @param page 分页数据
     * @param userId 用户id
     * @return CommissionListVO
     */
    IPage<CommissionListVO> queryCommission(Page page, @Param("userId") Integer userId);

}
