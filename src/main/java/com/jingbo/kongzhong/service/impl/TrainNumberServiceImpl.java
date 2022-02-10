package com.jingbo.kongzhong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jingbo.kongzhong.entity.TrainNumber;
import com.jingbo.kongzhong.dao.TrainNumberMapper;
import com.jingbo.kongzhong.service.ITrainNumberService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 车次管理表 服务实现类
 * </p>
 *
 * @author wangqiang
 * @since 2022-02-10
 */
@Service
public class TrainNumberServiceImpl extends ServiceImpl<TrainNumberMapper, TrainNumber> implements ITrainNumberService {

}
