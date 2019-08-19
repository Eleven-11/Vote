layui.config({
    base: $config.resUrl + 'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention: 'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool: 'tool',
    $api: 'api'
}).use(['form', 'layer', '$api', 'jquery', 'table', 'laypage', 'laytpl', 'ajaxExtention', '$tool'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery,
        laypage = layui.laypage,
        laytpl = layui.laytpl,
        $tool = layui.$tool,
        table = layui.table,
        $api = layui.$api;
    layer = layui.layer;
    layuiTable = layui.table;
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
            , url: $tool.getContext() + 'photoVote/voteList.do' //数据接口
            , method: 'post'
            , page: true //开启分页
            , limit: 5
            , limits: [5, 6, 7, 8, 9, 10]
            , cols: [[ //表头
                {type: 'numbers', title: '', fixed: 'left'}
                , {field: 'vid', title: '历史记录', width: '40%'}
                , {field: 'issueTime', title: '发布日期', width: '25%'}
                , {field: 'status', title: '状态', width: '20%', templet: '#upc'}
                , {fixed: 'right', title: '操作', width: 217, align: 'left', toolbar: '#barDemo'} //这里的toolbar值是模板元素的选择器
            ]]
            , done: function (res, curr) {//请求完毕后的回调
                console.log('请求成功')
                console.log(res)
                //如果是异步请求数据方式，res即为你接口返回的信息.curr：当前页码
            }
        });
        // function setOperate(data) {
        //     var hid = data.hid;
        //     var status = data.status; //获取状态
        //     var btn = "";
        //     //当状态为发布中的话按钮是关闭投票
        //     if (status == 35) {
        //         btn += '<button class="layui-btn layui-btn-xs " onclick="openUpdateModal(' + hid + ')">关闭投票</button>';
        //     }
        //         //当状态为已结束的话按钮就是删除
        //     else {
        //         btn += '<button class="layui-btn layui-btn-xs " onclick="delsaleLndent(' + hid + ')">删除</button>';
        //     }
        // }

        //为toolbar添加事件响应
        table.on('tool(userFilter)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var row = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象

            //区分事件
            if (layEvent === 'del') { //删除
                del(row.hid);
            } else if (layEvent === 'count') { //统计
                countVote(row.hid);
            } else if (layEvent === 'look') { //查看
                lookVote(row.hid);
            } else if (layEvent === 'cancel') { //关闭投票
                cancel(row.hid);
            }
        });
    }

    defineTable();


    //添加投票
    $(".add_btn").click(function () {
        var index = layui.layer.open({
            title: "添加投票",
            type: 2,
            content: "addVote.html",
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
    function del(hid) {
        layer.confirm('确认删除吗？', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送删除指令
            var req = {
                hid: hid
            };
            $api.DeleteVote(req, function (data) {
                layer.msg("删除成功", {time: 1000}, function () {
                    //obj.del(); //删除对应行（tr）的DOM结构
                    //重新加载表格
                    tableIns.reload();
                });
            });
        });
    }

    //关闭投票
    function cancel(hid) {
        layer.confirm('确认取消发布吗？', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送关闭指令
            var req = {
                hid: hid,
                status: status
            };
            $api.CancelVote(req, function (data) {
                layer.msg("取消成功", {time: 1000}, function () {
                    //obj.del(); //删除对应行（tr）的DOM结构
                    //重新加载表格
                    tableIns.reload();
                });
            });
        });
    }

    //统计
    function countVote(hid){
        var index = layui.layer.open({
            title: "统计",
            type: 2,
            content: "sumVote.html?hid=" + hid,   //记得修改
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


    //查看
    function lookVote(hid) {
        layer.prompt({title: '请输入账号', formType: 0}, function (content) {
         var req = {
             recordId:hid,
             content:content
         }
            var aa =   JSON.stringify(req);
            window.sessionStorage.setItem("aa", aa);
            $api.VerificationHao(JSON.stringify(req),{contentType:"application/json;charset=UTF-8"},function (data) {
                if (data.data == false) {
                    layer.msg("账号不存在或已投过票", {time: 1000}, function () {
                        //obj.del(); //删除对应行（tr）的DOM结构
                        //重新加载表格
                        tableIns.reload();
                    });
                } else {
                    var index = layui.layer.open({
                        title: "查看",
                        type: 2,
                        content: "lookVoteHistory.html?hid=" + hid,   //记得修改
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
        });
    }
});
