package com.jingbo.kongzhong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jingbo.kongzhong.entity.Department;
import com.jingbo.kongzhong.dao.DepartmentMapper;
import com.jingbo.kongzhong.service.IDepartmentService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author wangqiang
 * @since 2022-02-10
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

}
