layui.config({
    base: $config.resUrl+'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention: 'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool: 'tool',
    $api:'api'
}).use(['form', 'layer', 'jquery', 'table', 'laypage', 'ajaxExtention', '$tool','$api'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery,
        laypage = layui.laypage,
        $tool = layui.$tool,
        table = layui.table,
        $api = layui.$api;

    var tableIns;//表格实例

    /**
     * 页面初始化
     * */
    function init() {

        //初始化下拉框
        $api.GetFirstClassMenus(null,function (res) {
            var data = res.data;
            if(data.length > 0){
                var html = '<option value="">--请选择--</option>';
                for(var i=0;i<data.length;i++){
                    html += '<option value="'+data[i].id+'">'+data[i].title+'</option>>';
                }
                $('#parentMenu').append($(html));
                form.render();
            }
        });
    }
    init();


    /**
     * 定义表格
     * 2.初始化表格
     * */
    function defineTable() {
        tableIns = table.render({
            elem: '#Guser-data'
            , height: 415
            , url: $tool.getContext() + 'gradeUser/findAll.do' //数据接口 3.找到路径
            , method: 'post'
            , page:true //开启分页
            , cols: [[ //表头
                  {type:'numbers',title:'序号',fixed: 'left'},
                  {field: 'username', title: '用户名', width: 200}
                , {field: 'uname', title: '姓名', width: 200}
                , {field: 'tel', title: '手机号码', width: '15%',}
                , {field: 'role', title: '用户角色', width: 200}
                , {fixed: 'right', title: '操作', width: 250, align: 'left', toolbar: '#barDemo'} //这里的toolbar值是模板元素的选择器
            ]]
            , done: function (res, curr) {//请求完毕后的回调
                //如果是异步请求数据方式，res即为你接口返回的信息.curr：当前页码
            }
        });

        //为toolbar添加事件响应
        table.on('tool(userFilter)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var row = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象

            //区分事件
            if (layEvent === 'delUser') { //删除
                delUser(row.username);
            } else if (layEvent === 'edit') { //编辑
                //do something
                editRecord(row.recordId);
            }
        });
    }
    defineTable();


    //查询
    form.on("submit(queryMenu)", function (data) {
        var parentMenuId = data.field.parentMenuId;
        var menuName = data.field.menuName;
        var menuCode = data.field.menuCode;

        //表格重新加载
        tableIns.reload({
            where:{
                parentMenuId:parentMenuId,
                menuName:menuName,
                menuCode:menuCode
            }
        });

        return false;
    });

    //添加评测打分
    $(".add_user").click(function () {
        var index = layui.layer.open({
            title: "增加用户",
            type: 2,
            content: "addUser.html",
            success: function (layero, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回历史记录列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        });

        //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
        $(window).resize(function () {
            layui.layer.full(index);
        });
        layui.layer.full(index);
    });

    //删除
    function delUser(username){
        layer.confirm('确认删除吗？', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送删除指令
            var req = {
                username: username
            };

            $api.DeleteGradeUser(req,function (data) {
                layer.msg("删除成功",{time:1000},function(){
                    //obj.del(); //删除对应行（tr）的DOM结构
                    //重新加载表格
                    tableIns.reload();
                });
            });
        });
    }

    //关闭投票
    function editRecord(recordId){
        layer.confirm('确认关闭投票吗？', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送删除指令
            var req = {
                recordId: recordId
            };

            $api.UpdateResId(req,function (data) {
                layer.msg("关闭成功",{time:1000},function(){
                    //重新加载表格
                    tableIns.reload();
                });
            });
        });
    }
    //编辑
    // function editRecord(id){
    //     var index = layui.layer.open({
    //         title: "编辑",
    //         type: 2,
    //         content: "editRecord.html?id="+id,
    //         success: function (layero, index) {
    //             setTimeout(function () {
    //                 layui.layer.tips('点击此处返回菜单列表', '.layui-layer-setwin .layui-layer-close', {
    //                     tips: 3
    //                 });
    //             }, 500)
    //         }
    //     });
    //
    //     //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
    //     $(window).resize(function () {
    //         layui.layer.full(index);
    //     });
    //     layui.layer.full(index);
    // }
});