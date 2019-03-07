package com.huijava.common.base;

import org.apache.ibatis.exceptions.TooManyResultsException;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Service 层 基础接口，其他Service 接口 请继承该接口
 */
public interface Service<T> {
    /**
     * 持久化
     * @param model
     */
    int insertSelective(T model);

    /**
     * 批量持久化
     * @param models
     */
    int insert(List<T> models);

    /**
     * 通过主鍵刪除
     * @param id
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 批量刪除 eg：ids -> “1,2,3,4”，逗号分隔id
     * @param ids
     */
    int deleteByIds(String ids);

    /**
     * 更新
     * @param model
     */
    int updateByPrimaryKeySelective(T model);

    /**
     * 通过ID查找
     * @param id
     * @return
     */
    T selectByPrimaryKey(Integer id);

    /**
     * 通过Model中某个成员变量名称（非数据表中column的名称,实体类中属性的名称）查找,value需符合unique约束
     * 必须是唯一主键的才能使用
     * @param fieldName
     * @param value
     * @return
     * @throws TooManyResultsException
     */
    T selectOneByFieldName(String fieldName, Object value) throws TooManyResultsException;

    /**
     * 分页查询,只能有一个条件，且是等于的情况
     * @param dateName
     * @param value
     * @param orderFieldName
     * @param pageNum
     * @param size
     * @param sort
     * @return
     */
    List<T> selectPageByDateName(String dateName, Object value,String orderFieldName,
                                 int pageNum, int size,String sort);

    /**
     * 通过多个ID查找//eg：ids -> “1,2,3,4”
     * @param ids
     * @return
     */
    List<T> selectByIds(String ids);

    /**
     * 根据条件查找
     * @param condition
     * @return
     */
    List<T> selectByCondition(Condition condition);

    /**
     * 根据条件查找
     *
     * @param example
     * @return
     */
    List<T> selectByExample(Example example);

    /**
     * 获取所有
     * @return
     */
    List<T> selectAll();
}
