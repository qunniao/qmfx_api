package com.zhancheng.core.vo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 问题和回答
 * zc_admin 实体类
 * 
 * @author BianShuHeng
 * @email 13525382973@163.com
 * @date 2019-10-21 11:00:45
 */
@Data
public class QuestionListItemVO {


	/**
	 * id
	 */
	private Integer id;

	/**
	 * 类型id
	 */
	private Integer typeId;

	/**
	 * 问题标题
	 */
	private String title;

	/**
	 * 封面图
	 */
	private String cover;


	/**
	 * 排序
	 */
	private Integer sort;

	/**
	 * 浏览次数
	 */
	private Integer watchNum;

	/**
	 * 是否删除 0:未删除; 1:删除
	 */
	@TableLogic
	private Integer isDeleted;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date gmtCreate;

	/**
	 * 修改时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date gmtModified;

	/**
	 * 问题类型名称
	 */
	private String name;
}
