package com.huijava.common.base;


import com.github.pagehelper.PageHelper;
import com.huijava.common.exception.ServiceException;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

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
    public int insertSelective(T model) {
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
    public int deleteByPrimaryKey(Integer id) {
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
    public int updateByPrimaryKeySelective(T model) {
        return mapper.updateByPrimaryKeySelective(model);
    }

    /**
     * 查询
     * @param id
     * @return
     */
    @Override
    public T selectByPrimaryKey(Integer id) {
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
    public T selectOneByFieldName(String fieldName, Object value) throws TooManyResultsException {
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
     * 分页查询
     * @param dateName 数据库属性
     * @param value 值
     * @param orderFieldName  排序条件
     * @param pageNum 当前页 1开始
     * @param size 当前页大小
     * @param sort 排序规则
     * @return
     */
    @Override
    public List<T> selectPageByDateName(String dateName, Object value,String orderFieldName,
                                        int pageNum, int size,String sort) {
        Example example = new Example(modelClass);
        //数据库属性名
        example.createCriteria().andCondition(dateName+" = ", value);
        //注意用的是类中的属性，不是数据库中的属性
        if("desc".equals(sort)){
            example.orderBy(orderFieldName).desc();
        }else {
            example.orderBy(orderFieldName).asc();
        }
        /*
         * Mapper接口方式的调用，推荐这种使用方式。
         */
        PageHelper.startPage(pageNum, size);
        return mapper.selectByExample(example);
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

    @Override
    public List<T> selectByExample(Example example) {
        return mapper.selectByExample(example);
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
