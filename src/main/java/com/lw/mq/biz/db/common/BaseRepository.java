package com.lw.mq.biz.db.common;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BaseRepository<T> {

    long insert(T t);

    void insertBatch(@Param("entityList")List<T> tList);

    T getById(@Param("id") long id);

    List<T> getByIds(@Param("ids") List<Long> ids);

    T get(Map<String, Object> conditionMap);

    long count(Map<String, Object> conditionMap);

    List<T> getList(Map<String, Object> conditionMap);

    List<T> getAll();

    List<T> getListByPage(Map<String, Object> conditionMap);

    int update(T t);

    void delete(long id);

    void batchDelete(@Param("ids") List<Long> ids);

}
