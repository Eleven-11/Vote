layui.config({
    base: $config.resUrl+'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention: 'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool: 'tool',
    $api:'api'
}).use(['form', 'layer', '$api','jquery', 'table', 'laypage','laytpl', 'ajaxExtention', '$tool'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery,
        laypage = layui.laypage,
        laytpl = layui.laytpl,
        $tool = layui.$tool,
        table = layui.table,
        $api = layui.$api;

    var tableIns;//表格实例

    /**
     * 页面初始化
     * */
    function init() {
    }
    init();


    /**
     * 定义表格
     * */
    function defineTable() {
        tableIns = table.render({
            elem: '#user-data'
            , height: 415
            , url: $tool.getContext() + 'WJ/status.do' //数据接口
            , method: 'POST'
            , page:true //开启分页
            ,limits:[8,16,24,30]
            ,limit:8
            , cols: [[ //表头
                {type:'numbers',title:'序号',fixed: 'left',width: '10%'}

                ,{field: 'history', title: '历史记录', width: '10%'}
                , {field: 'publicationDate', title: '发布日期', width: '15%'}
                ,{field: 'status', title:'状态', templet: '#buttonTpl', minWidth: 80, align: 'center'}
                , {fixed: 'right', title: '操作', width: 300, align: 'left', toolbar: '#barDemo'} //这里的toolbar值是模板元素的选择器
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
            if (layEvent === 'del') { //删除
                delUser(row.statusId);
            } else if (layEvent === 'count') { //统计
                //do something
                editUser(row.statusId);
            }else if(layEvent === 'cancel'){//关闭投票
                cancel(row.statusId);
            }else if(layEvent === 'yul'){//查看
                yul(row.statusId);
            }else if(layEvent === 'Check'){//查看
                Check(row.statusId);
            }
        });
    }
    defineTable();


    //查询
    form.on("submit(queryUser)", function (data) {
        var status = data.field.status;
        var loginName = data.field.loginName;
        var realName = data.field.realName;


        //表格重新加载
        tableIns.reload({
            where:{
                status:status,
                loginName:loginName,
                realName:realName
            }
        });

        return false;
    });

    //添加用户
    $(".add_btn").click(function () {
        var index = layui.layer.open({
            title: "创建空白问卷调查",
            type: 2,
            content: "addWjs.html",
            success: function (layero, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
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
    function delUser(statusId){
        layer.confirm('确认删除吗？', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送删除指令
            var req = {
                statusId: statusId
            };

            $api.DeleteStatus(req,function (data) {
                layer.msg("删除成功",{time:1000},function(){
                    //obj.del(); //删除对应行（tr）的DOM结构
                    //重新加载表格
                    tableIns.reload();
                });
            });
        });
    }



    //编辑
    function editUser(statusId){
        var index = layui.layer.open({
            title: "编辑用户",
            type: 2,
            content: "Count.html?statusId="+statusId,
            success: function (layero, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
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
    //关闭投票
    function cancel(statusId){
        layer.confirm('确认关闭投票吗？', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送关闭指令
            var req = {
                statusId: statusId,
                status:status
            };
            $api.UpdateStatus(req,function (data) {
                layer.msg("取消成功",{time:1000},function(){
                    //obj.del(); //删除对应行（tr）的DOM结构
                    //重新加载表格
                    tableIns.reload();
                });
            });
        });
    }
    function yul(statusId){
        var a = layer.prompt({title: '请输入账号',formType: 0}, function(content){
            var req= {
                content:content,
                recordId:statusId
            }
            var aa =   JSON.stringify(req);
            window.sessionStorage.setItem("aa", aa);
            $api.Account(JSON.stringify(req),{contentType:"application/json;charset=UTF-8"},function (data) {
                //top.layer.close(index);(关闭遮罩已经放在了ajaxExtention里面了)
                if(data.data==false){
                    layer.msg("账号不正确或该账号已经提交过！", {time: 1000}, function () {
                        layer.closeAll("iframe");
                        //刷新父页面
                        parent.location.reload();
                    });
                }else {
                    var index = layui.layer.open({
                        title: "预览",
                        type: 2,
                        content: "CheckStatus.html?statusId="+statusId,
                        success: function (layero, index) {
                            setTimeout(function () {
                                layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
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
                    layer.close(a);
                }

            });
        });

    }

    function Check(statusId){
        var index = layui.layer.open({
            title: "编辑用户",
            type: 2,
            content: "CheckAccount.html?statusId="+statusId,
            success: function (layero, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
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
