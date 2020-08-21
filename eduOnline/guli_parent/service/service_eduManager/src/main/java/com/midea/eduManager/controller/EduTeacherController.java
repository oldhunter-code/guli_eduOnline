package com.midea.eduManager.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.midea.commonutils.R;
import com.midea.eduManager.entity.EduTeacher;
import com.midea.eduManager.entity.vo.TeacherQuery;
import com.midea.eduManager.service.EduTeacherService;
import com.midea.servicebase.exception.MyException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-08-07
 */
@RestController //返回jason数据 @Controller 和 @ResponseBody的结合
@RequestMapping("/eduManager")
@CrossOrigin
public class EduTeacherController {
  @Autowired
    private EduTeacherService teacherService;

  @GetMapping("findAll")
    public R findAllTeacher(){
      List<EduTeacher> list = teacherService.list(null);

      try {
         int i=1/0;
      } catch (Exception e) {
          e.printStackTrace();
          throw new MyException(20001,"执行了自定义异常");
      }
      return R.ok().data("items",list);
  }

    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}") //delete标签 访问路径为 localhost:8081/eduManage/delete/1
    public R removeTeacher(@PathVariable String id) {
        boolean flag = teacherService.removeById(id);
        if (flag){
          return R.ok();
        }else {
          return R.error();
        }
    }

    //分页查询讲师的方法
    //current 当前页  limit每页的记录数
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageTeacher(@PathVariable long current,
                         @PathVariable long limit){
      //创建page对象
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<EduTeacher> pageTeacher = new
                com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(current,limit);
        //调用方法实现分页,参数：分页对象，条件
        teacherService.page(pageTeacher,null);
        long total =pageTeacher.getTotal();//总记录数
        List<EduTeacher> records=pageTeacher.getRecords(); //数据list集合
        return R.ok().data("total",total).data("items",records);
        /*  其他写法
        *    Map map = new HashMap();
             map.put("total",total);
             map.put("rows",records);
             return R.ok().data(map);
        * */
    }

    //4、条件查询带分页的方法
    //@RequestBody 表示以json传递数据，把json数据封装到对应对象里面，使用这个标签必须用post提交方式，required=false表示这个参数可以为空
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current,
                                  @PathVariable long limit,
                                 @RequestBody(required = false) TeacherQuery teacherQuery){
           //创建page对象
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<EduTeacher> pageTeacher=
                new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(current,limit);
         //构建条件
        QueryWrapper<EduTeacher>  wrapper= new QueryWrapper<>();
        // 多条件组合查询
        // mybatis学过 动态sql
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(name)) {
            //构建条件,前面带的是字段名
            wrapper.like("name",name); //名字，模糊查询
        }
        if(!StringUtils.isEmpty(level)) {
            wrapper.eq("level",level); //等级
        }
        if(!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create",begin); //开始时间
        }
        if(!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create",end);//结束时间
        }
        teacherService.page(pageTeacher,wrapper);
        long total =pageTeacher.getTotal();//总记录数
        List<EduTeacher> records=pageTeacher.getRecords(); //数据list集合
        return R.ok().data("total",total).data("items",records);

    }
    //5添加讲师的方法
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean save = teacherService.save(eduTeacher);
        if(save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //根据讲师id进行查询
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable String id) {
        EduTeacher eduTeacher = teacherService.getById(id);
        return R.ok().data("teacher",eduTeacher);
    }

    //讲师修改功能
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
      boolean flag = teacherService.updateById(eduTeacher);//更新成功直接返回，失败不执行插入语句
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}
