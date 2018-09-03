package com.huijava.common.base;


import com.huijava.common.exception.ServiceException;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Condition;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * 基于通用MyBatis Mapper插件的Service接口的实现
 * @author chenhx
 * @version ResultCodeEnum.java, v 0.1 2018-09-02 下午 5:15
 */
public abstract class AbstractService<T> implements Service<T> {

    /**
     * 通用基类Mapper
     */
    @Autowired
    protected Mapper<T> mapper;

    /**
     * 当前泛型真实类型的Class
     */
    private Class<T> modelClass;

    public AbstractService() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<T>) pt.getActualTypeArguments()[0];
    }

    /**
     * 插入一条数据
     * @param model
     * @return
     */
    @Override
    public int insert(T model) {
        return mapper.insertSelective(model);
    }

    /**
     * 插入多条数据
     * @param models
     * @return
     */
    @Override
    public int insert(List<T> models) {
        return mapper.insertList(models);
    }

    /**
     * 根据id删除一条数据
     * @param id
     * @return
     */
    @Override
    public int deleteById(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    /**
     * 删除多条数据
     * id之间用","号隔开
     * @param ids
     * @return
     */
    @Override
    public int deleteByIds(String ids) {
        return mapper.deleteByIds(ids);
    }

    /**
     * 更新
     * @param model
     * @return
     */
    @Override
    public int updateById(T model) {
        return mapper.updateByPrimaryKeySelective(model);
    }

    /**
     * 查询
     * @param id
     * @return
     */
    @Override
    public T selectById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    /**
     * 根据fieldName字段查询
     * @param fieldName
     * @param value
     * @return
     * @throws TooManyResultsException
     */
    @Override
    public T selectBy(String fieldName, Object value) throws TooManyResultsException {
        try {
            T model = modelClass.newInstance();
            Field field = modelClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(model, value);
            return mapper.selectOne(model);
        } catch (ReflectiveOperationException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * 查询
     * @param ids
     * @return
     */
    @Override
    public List<T> selectByIds(String ids) {
        return mapper.selectByIds(ids);
    }

    /**
     * 根据条件查找
     * @param condition
     * @return
     */
    @Override
    public List<T> selectByCondition(Condition condition) {
        return mapper.selectByCondition(condition);
    }

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<T> selectAll() {
        return mapper.selectAll();
    }

}
