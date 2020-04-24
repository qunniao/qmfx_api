package com.zhancheng.core.vo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 问题和回答
 * zc_admin 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-21 11:00:45
 */
@Data
public class QuestionListVO {

	@ApiModelProperty(name = "id", value = "id")
	private Integer id;
	/**
	 * 分类名称
	 */
	@ApiModelProperty(name = "type", value = "分类名称")
	private String name;

	@ApiModelProperty(name = "list", value = "问题列表")
	private List<QuestionListItemVO> list;
}
