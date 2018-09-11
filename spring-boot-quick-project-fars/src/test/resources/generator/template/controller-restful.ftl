package ${controllerPackage};

import ${basePackage}.common.result.ResultModel;
import ${entityPackage}.${modelNameUpperCamel};
import ${servicePackage}.${modelNameUpperCamel}Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * ${modelNameUpperCamel}Controller
 * @author ${author}
 * @date ${date}
 */
@RestController
@RequestMapping("${baseRequestMapping}")
public class ${modelNameUpperCamel}Controller {
    @Resource
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    /**
     * 添加${modelNameUpperCamel}
     * @param ${modelNameLowerCamel} 对象
     * @return ResultModel统一响应结果
     */
    @PostMapping
    public ResultModel add(@RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.insert(${modelNameLowerCamel});
        return ResultModel.success();
    }

    /**
     * 根据ID进行删除
     * @param id 主键
     * @return ResultModel统一响应结果
     */
    @DeleteMapping("/{id}")
    public ResultModel delete(@PathVariable Integer id) {
        ${modelNameLowerCamel}Service.deleteById(id);
        return ResultModel.success();
    }

    /**
     * 根据ID进行修改${modelNameUpperCamel}对象
     * @param ${modelNameLowerCamel} 对象中必须有ID主键
     * @return ResultModel统一响应结果
     */
    @PutMapping
    public ResultModel update(@RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.updateById(${modelNameLowerCamel});
        return ResultModel.success();
    }

    /**
     * 查询详情
     * @param id 主键
     * @return ResultModel统一响应结果
     */
    @GetMapping("/{id}")
    public ResultModel detail(@PathVariable Integer id) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.selectById(id);
        return ResultModel.success(${modelNameLowerCamel});
    }

   /**
     * 分页查询
     * @param page 当前页 默认0 不分页
     * @param size 每页的条数 默认为0 查询所有
     * @return ResultModel统一响应结果
     */
    @GetMapping
    public ResultModel list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.selectAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultModel.success(pageInfo);
    }

}