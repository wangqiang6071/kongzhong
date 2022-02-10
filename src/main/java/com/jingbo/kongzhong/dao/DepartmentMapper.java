package com.jingbo.kongzhong.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jingbo.kongzhong.entity.Department;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 部门表 Mapper 接口
 * </p>
 *
 * @author wangqiang
 * @since 2022-02-10
 */
@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {

}
