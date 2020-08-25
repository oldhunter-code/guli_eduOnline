import request from '@/utils/request' 
export default{
  //1、讲师列表（条件查询分页）
  getTeacherListPage(current,limit,teacherQuery){ //分页查询的三个参数，当前页，每页记录数，分页条件
    return request({ 
        url: `/eduManager/pageTeacherCondition/${current}/${limit}/`, //后台接口地址
        //url: '/eduManager/pageTeacherCondition/'+current+"/"+limit,
        method: 'post',
        //teacherQuery条件对象，后端使用RequestBody获取数据
        //data表示把对象转换成json进行传递到接口里面
        data:teacherQuery
      })
  },
  //删除讲师
  deleteTeacherId(id) {
    return request({
        url: `/eduManager/${id}`,
        method: 'delete'
      })
},
}
// export function getList(params) { //另外写法
//   return request({
//     url: '/table/list',
//     method: 'get',
//     params
//   })
// }