layui.config({
    base: $config.resUrl+'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention: 'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool: 'tool',
    $api:'api'
}).use(['form', 'layer', 'jquery', 'ajaxExtention', '$tool','$api'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery,
        $tool = layui.$tool ,
        $api = layui.$api;

    var record_Id;
    var zhanghao = new Array();
    /**
     * 页面初始化
     * */
    function init() {
        //初始化下拉框
        initUid();
    }

    init();

    /**
     * 初始化账号
     * */
    function initUid() {

        var queryArgs = $tool.getQueryParam();
        record_Id = queryArgs['record_Id'];

        if (record_Id!=null){
            var req={
                record_Id:record_Id,
            }

            $api.GetZH(req,function (res) {
                var data = res.data;
                for (var a=0;a<data.length;a++){
                    zhanghao.push(data[a].content);
                }
                // console.log(zhanghao)
                if (zhanghao.length > 0) {
                    var html = '';
                    for (var i = 0; i < zhanghao.length; i++) {
                        if (data[i].isVote == 0) {
                            html += '<i style="color: #ff0000;">'+ zhanghao[i] +'</i><i>&emsp;&emsp;</i>';
                        } else {
                            html += '<i>'+ zhanghao[i] +'</i><i>&emsp;&emsp;</i>';
                        }
                        if (zhanghao[i+1] != null){
                            if (data[i+1].isVote == 0) {
                                html += '<i style="color: #ff0000;">'+ zhanghao[i+1] +'</i><i>&emsp;&emsp;</i> ';
                                i++;
                            } else {
                                html += '<i>'+ zhanghao[i+1] +'</i><i>&emsp;&emsp;</i> ';
                                i++;
                            }
                        }
                        if (zhanghao[i+1] != null) {
                            if (data[i+1].isVote == 0) {
                                html += '<i style="color: #ff0000;">'+ zhanghao[i+1] +'</i><i>&emsp;&emsp;</i> ';
                                i++;
                            } else {
                                html += '<i>'+ zhanghao[i+1] +'</i><i>&emsp;&emsp;</i> ';
                                i++;
                            }
                        }
                    }
                    $('#findzh').append($(html));
                    form.render();
                }
            });
        } else {
            $api.FindZH(null,function (res) {
                var data = res.data;
                zhanghao=data;
                // console.log(data);
                if (data.length > 0) {
                    var html = '';
                    for (var i = 0; i < data.length; i++) {
                        html += '<i>'+ data[i] +'</i><i>&emsp;&emsp;</i>';
                        if (data[i+1] != null){
                            html += '<i>'+ data[i+1] +'</i><i>&emsp;&emsp;</i> ';
                            i++;
                        }
                        if (data[i+2] != null){
                            html += '<i>'+ data[i+2] +'</i><i>&emsp;&emsp;</i> ';
                            i++;
                        }
                    }
                    $('#findzh').append($(html));
                    form.render();
                }
            });
        }

    }


    /**
     * 表单提交
     * */
    form.on("submit(addry)", function (data) {

        for (var i=0; i<zhanghao.length; i++){
            // console.log(zhanghao[i]);
        }

        layer.msg("成功！", {time: 1000}, function () {
            layer.closeAll("iframe");
            //刷新父页面
            parent.location.reload();
        });
        return false;
    })

    /**
     * 导出excel
     */
    form.on("submit(daochu)", function (data) {

        var req = {
            record_Id:record_Id,
            zhanghao:zhanghao.toString(),
        }

        $api.DaoChu(req,function (data) {
            if(data="success"){
                layer.msg("成功！", {time: 1000}, function () {
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                });
            }
        });
        return false;
    })

});


