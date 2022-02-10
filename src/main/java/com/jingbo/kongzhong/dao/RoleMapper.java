package com.jingbo.kongzhong.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jingbo.kongzhong.entity.Permission;
import com.jingbo.kongzhong.entity.Role;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
/**
 * @author wangqiang
 * @Date: 2022/2/8 09:44
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    List<Permission> selectPermissonByRoleId(Integer roleId);
}