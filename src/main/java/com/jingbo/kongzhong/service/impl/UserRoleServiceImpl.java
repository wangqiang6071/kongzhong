package com.jingbo.kongzhong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jingbo.kongzhong.entity.UserRole;
import com.jingbo.kongzhong.dao.UserRoleMapper;
import com.jingbo.kongzhong.service.IUserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户与角色表 服务实现类
 * </p>
 *
 * @author wangqiang
 * @since 2022-02-10
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
