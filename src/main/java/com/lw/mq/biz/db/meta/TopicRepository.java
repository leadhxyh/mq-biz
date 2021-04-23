package com.lw.mq.biz.db.meta;

import com.lw.mq.biz.db.common.BaseRepository;
import com.lw.mq.biz.entity.LastUpdateEntity;
import com.lw.mq.biz.entity.TopicEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TopicRepository extends BaseRepository<TopicEntity> {

    LastUpdateEntity getLastUpdate();

}
