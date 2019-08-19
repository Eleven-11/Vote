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

    var uid = new Array();
    var zhanghao = new Array();
    var isTP = new Array();
    var tpzh;
    var tableIns;//表格实例
    /**
     * 页面初始化
     * */
    function init() {

    }
    init();
    // layer.ready(function(){
    //     layer.msg('很高兴一开场就见到你');
    // });



    /**
     * 定义表格
     * */
    function defineTable() {
        tableIns = table.render({
            elem: '#record-data'
            , id: 'record_Id'
            , height: 350
            , url: $tool.getContext() + 'jilu/jiluPage.do' //数据接口
            , method: 'post'
            , limit: 5
            , limits: [5,6,7,8,9,10]
            , page: true //开启分页
            , cols: [[ //表头
                  {type:'numbers',title:'序号',fixed: 'left'}
                , {field:'templet_Id',title:'历史记录'}
                , {field: 'r_time', title: '发布日期'}
                , {field: 'resId', title: '状态', templet:'#barDemo1'}
                , {fixed: 'right', title: '操作', width: 500, align: 'left', toolbar: '#barDemo'} //这里的toolbar值是模板元素的选择器
            ]]
            , done: function (res, curr) {//请求完毕后的回调

            }
        });

        //为toolbar添加事件响应
        table.on('tool(menuFilter)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var row = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象

            //区分事件
            if (layEvent === 'del') { //删除
                DeleteJiLu(row.record_Id);
            } else if (layEvent === 'edit') { //编辑
                //do something
                editJiLu(row.record_Id, row.resId);
            } else if (layEvent === 'editstatus') { //关闭投票
                //do something
                editStatus(row.record_Id);
            } else if (layEvent === 'tongji') { //统计
                //do something
                tongji(row.record_Id);
            } else if (layEvent === 'tpzh') { //查看投票账号
                //do something
                cktpzh(row.record_Id);
            }
        });
    }
    defineTable();

    //查询
    form.on("submit(queryAdm)", function (data) {
        var record_Id = data.field.record_Id;
        //表格重新加载
        tableIns.reload({
            where:{
                record_Id:record_Id
            }
        });
        return false;
    });

    //创建空白评测打分
    $(".usersAdd_btn").click(function () {
        var index = layui.layer.open({
            title: "创建空白评测打分",
            type: 2,
            content: "addRecord.html",
            success: function (layero, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回评测打分记录', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            },
        });

        //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
        $(window).resize(function () {
            layui.layer.full(index);
        });
        layui.layer.full(index);
    });

    //删除
    function DeleteJiLu(record_Id){
        layer.confirm('确认删除吗？', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送删除指令
            var req = {
                record_Id: record_Id
            };

            $api.DeleteJiLu(req,function (data) {
                layer.msg("删除成功",{time:1000},function(){
                    //obj.del(); //删除对应行（tr）的DOM结构
                    //重新加载表格
                    tableIns.reload();
                });
            });
        });
    }

    //关闭投票
    function editStatus(record_Id){
        layer.confirm('确认关闭投票吗？', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送删除指令
            var req = {
                record_Id: record_Id
            };

            $api.editStatus(req,function (data) {
                layer.msg("关闭成功",{time:1000},function(){
                    //obj.del(); //删除对应行（tr）的DOM结构
                    //重新加载表格
                    tableIns.reload();
                });
            });
        });
    }

    //统计
    function tongji(record_Id){

        layui.layer.open({
            type: 2,
            title: '统计',
            area : ['600px' , '500px'],
            content: '../../../page/system/GradeRecord/TongJi.html?record_Id='+record_Id,
            end:function () {

            },
        })
    }

    //查看
    function editJiLu(record_Id, resId){
        // console.log(resId)
        if (record_Id!=null) {
            var req = {
                record_Id: record_Id,
            }
            $api.GetZH(req, function (res) {
                var data = res.data;
                for (var a=0; a<data.length; a++){
                    zhanghao.push(data[a].content);
                    isTP.push(data[a].isVote);
                }
                // console.log(zhanghao)
                if (resId == "发布中") {
                    layer.prompt({title: '请输入投票账号',}, function (val, index2) {
                        if ($.inArray(val, zhanghao) == -1) {
                            layer.msg("该投票账号不存在,或不能进行此项投票", {time: 1000}, function () {});
                        } else {
                            var srzh = zhanghao.length;
                            while (srzh--) {
                                if (zhanghao[srzh] === val) {
                                    if (isTP[srzh] == 0) {
                                        layer.msg("该投票账号已进行过投票!", {time: 1000}, function () {
                                            break;
                                        });
                                    } else {
                                        layer.close(index2);
                                        tpzh = val;
                                        var index = layui.layer.open({
                                            title: "查看投票",
                                            type: 2,
                                            content: "../../../page/system/GradeRecord/DaFenBiaoFB.html?record_Id=" + record_Id + "&tpzh=" + tpzh,
                                            success: function (layero, index) {
                                                setTimeout(function () {
                                                    layui.layer.tips('点击此处返回评测打分记录', '.layui-layer-setwin .layui-layer-close', {
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
                                }
                            }
                        }
                    });
                } else if (resId == "已结束") {
                    var index2 = layui.layer.open({
                        title: "查看投票",
                        type: 2,
                        content: "../../../page/system/GradeRecord/DaFenBiaoFB3.html?record_Id=" + record_Id + "&tpzh=" + tpzh,
                        success: function (layero, index) {
                            setTimeout(function () {
                                layui.layer.tips('点击此处返回', '.layui-layer-setwin .layui-layer-close', {
                                    tips: 3
                                });
                            }, 500)
                        }
                    });
                    //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
                    $(window).resize(function () {
                        layui.layer.full(index2);
                    });
                    layui.layer.full(index2);
                }
            })
        }
    }

    //查看投票账号
    function cktpzh(record_Id){

        layui.layer.open({
            type: 2,
            title: '查看账号',
            area : ['500px' , '500px'],
            content: '../../../page/system/GradeRecord/findZH.html?record_Id='+record_Id,
            end:function () {

            },
        })
    }

});
