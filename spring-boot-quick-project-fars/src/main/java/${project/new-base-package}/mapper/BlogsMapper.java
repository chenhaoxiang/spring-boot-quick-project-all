package ${project.new-base-package}.mapper;

import ${project.new-base-package}.entity.Blogs;

public interface BlogsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Blogs record);

    int insertSelective(Blogs record);

    Blogs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Blogs record);

    int updateByPrimaryKey(Blogs record);
}