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
            elem: '#adm-data'
            , height: 415
            , url: $tool.getContext() + 'adm/admfindAll.do '//数据接口 3.找到路径
            , method: 'post'
            , page:true //开启分页
            , cols: [[ //表头
                  {type:'numbers',title:'序号',fixed: 'left'},
                  {field: 'templetTitle', title: '模板标题', width: 200}
                , {field: 'aTime', title: '生成日期', width: 200}
                , {fixed: 'right', title: '操作', width: 333, align: 'left', toolbar: '#barDemo'} //这里的toolbar值是模板元素的选择器
            ]]
            , done: function (res, curr) {//请求完毕后的回调
                //如果是异步请求数据方式，res即为你接口返回的信息.curr：当前页码
            }
        });

        //为toolbar添加事件响应
        table.on('tool(admFilter)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var row = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象

            //区分事件
            if (layEvent === 'delAdm') { //删除
                delAdm(row.admId);
                //alert(JSON.stringify(row.admId));
            } else if (layEvent === 'editAdm') { //取消置顶
                editAdm(row.admId);
            } else if (layEvent=== 'noAdm') {
                noAdm(row.admId);
            } else if (layEvent=='preview'){
                preview(row.admId);
            } else if (layEvent=='useAdm'){
                useAdm(row.admId);
            }
        });
    }
    defineTable();

    //添加评测打分
    $(".add_btn1").click(function () {
        var index = layui.layer.open({
            title: "创建模板",
            type: 2,
            content: "addAdm.html",
            success: function (layero, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回模板打分列表', '.layui-layer-setwin .layui-layer-close', {
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
    function delAdm(admId){
        layer.confirm('确认删除吗？', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送删除指令
            var req = {
                admId: admId
            };
            $api.DeleteAdm(req,function (data) {
                layer.msg("删除成功",{time:1000},function(){
                    //obj.del(); //删除对应行（tr）的DOM结构
                    //重新加载表格
                    tableIns.reload();
                });
            });
        });
    }

    //取消置顶
    function editAdm(admId){
        layer.confirm('确认取消置顶吗？', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送删除指令
            var req = {
                admId: admId
            };
            $api.IsTopResId(req,function (data) {
                layer.msg("取消成功",{time:1000},function(){
                    //重新加载表格
                    tableIns.reload();
                });
            });
        });
    }
    //置顶
    function noAdm(admId){
        layer.confirm('确认置顶吗？', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送删除指令
            var req = {
                admId: admId
            };
            $api.NoTopResId(req,function (data) {
                layer.msg("置顶消成功",{time:1000},function(){
                    //重新加载表格
                    tableIns.reload();
                });
            });
        });
    }
    function preview(admId){
        var index = layui.layer.open({
            title: "预览",
            type: 2,
            content: "vote.html?admId="+admId,
            success: function (layero, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回菜单列表', '.layui-layer-setwin .layui-layer-close', {
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
    }


    function useAdm(admId){
        var index = layui.layer.open({
            title: "使用",
            type: 2,
            content: "editAdm.html?admId="+admId,
            success: function (layero, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回评测打分模板列表', '.layui-layer-setwin .layui-layer-close', {
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
    }
});
