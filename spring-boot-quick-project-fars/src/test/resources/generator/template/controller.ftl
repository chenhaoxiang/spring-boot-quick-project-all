package ${controllerPackage};

import ${basePackage}.common.result.ResultModel;
import ${entityPackage}.${modelNameUpperCamel};
import ${servicePackage}.${modelNameUpperCamel}Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * ${modelNameUpperCamel}Controller
 * @author ${author}
 * @date ${date}
 */
@Controller
@RequestMapping("${baseRequestMapping}")
public class ${modelNameUpperCamel}Controller {
    @Resource
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    /**
     * 添加${modelNameUpperCamel}
     * @param ${modelNameLowerCamel} 对象
     * @return ResultModel统一响应结果
     */
    @PostMapping("add")
    @ResponseBody
    public ResultModel add(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.insert(${modelNameLowerCamel});
        return ResultModel.success();
    }

    /**
     * 根据ID进行删除
     * @param id 主键
     * @return ResultModel统一响应结果
     */
    @PostMapping("delete")
    @ResponseBody
    public ResultModel delete(@RequestParam Integer id) {
        ${modelNameLowerCamel}Service.deleteById(id);
        return ResultModel.success();
    }

    /**
     * 根据ID进行修改${modelNameUpperCamel}对象
     * @param ${modelNameLowerCamel} 对象中必须有ID主键
     * @return ResultModel统一响应结果
     */
    @PostMapping("update")
    @ResponseBody
    public ResultModel update(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.updateById(${modelNameLowerCamel});
        return ResultModel.success();
    }

    /**
     * 查询详情
     * @param id 主键
     * @return ResultModel统一响应结果
     */
    @PostMapping("detail")
    @ResponseBody
    public ResultModel detail(@RequestParam Integer id) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.selectById(id);
        return ResultModel.success(${modelNameLowerCamel});
    }

    /**
     * 分页查询
     * @param page 当前页 默认0 不分页
     * @param size 每页的条数 默认为0 查询所有
     * @return ResultModel统一响应结果
     */
    @PostMapping("list")
    @ResponseBody
    public ResultModel list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.selectAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultModel.success(pageInfo);
    }
}
