package com.zhancheng.core.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhancheng.core.dto.AwardQueryDTO;
import com.zhancheng.core.entity.Award;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhancheng.core.entity.WalletHistory;
import com.zhancheng.core.vo.AwardVO;
import com.zhancheng.core.vo.CartListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 奖励
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-11-19 13:06:30
 */
@Repository
public interface AwardMapper extends BaseMapper<Award> {

    Award queryInfo(Award award);

    Award queryInfoByOpid(Integer userId, Integer shareId, Integer orderProductId);

    List<AwardVO> queryListByUserId(Integer shareId);

    IPage<AwardVO> queryPage(Page page, @Param("query") AwardQueryDTO awardQueryDTO);
}
