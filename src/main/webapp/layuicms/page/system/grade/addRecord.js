layui.config({
    base: $config.resUrl+'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention:'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool:'tool',
    $api:'api'
}).use(['form', 'layer', 'jquery','ajaxExtention','$tool','$api'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery,
        $tool = layui.$tool,
        $api = layui.$api;

layui.use(['layer','form','laydate'],function () {
    var layer = layui.layer,
        laydate = layui.laydate,
        form = layui.form;
        $ = layui.jquery;
        //选择人员
    $("#uname").click(function () {
        layer.open({
            type: 2,
            title:"添加被评测人员",
            id:"link",
            area: ['75%', '80%'],
            fixed: false, //不固定
            maxmin: true,
            content:'addUname.html'
        });
    });

});


form.on('checkbox(type)',function (data) {
    console.log(data.elem);
    if (data.elem.checked) {
        $("#count").show();
    }else{
        $("#count").hide();
    }
});
    form.on('checkbox(type2)',function (data) {
        console.log(data.elem);
        if (data.elem.checked) {
            $("#count2").show();
        }else{
            $("#count2").hide();
        }
    });
    form.on('checkbox(type3)',function (data) {
        console.log(data.elem);
        if (data.elem.checked) {
            $("#count3").show();
        }else{
            $("#count3").hide();
        }
    });
    form.on('checkbox(type4)',function (data) {
        console.log(data.elem);
        if (data.elem.checked) {
            $("#count4").show();
        }else{
            $("#count4").hide();
        }
    });
    form.on('checkbox(type5)',function (data) {
        console.log(data.elem);
        if (data.elem.checked) {
            $("#count5").show();
        }else{
            $("#count5").hide();
        }
    });
    //预览
    form.on("submit(see_ready)",function (data) {
        var title = $("#title").val();
        var explains = $("#explains").val();
        var peonum = $("#peonum").val();
        //德能勤绩廉高较高一般较差的分数分值
        var rubricList = new Array();
        if ($("input[name='de']").prop('checked')){
            var de_fraction=$("#fraction").val();
            var gao=$("#gao").val();
            var jiaogao=$("#jiaogao").val();
            var yiban=$("#yiban").val();
            var jiaocha=$("#jiaocha").val();
            var data={resId:37,fraction:de_fraction,answerList:[{resId:42,price:gao},
                    {resId:43,price:jiaogao},{resId:44,price:yiban},{resId:45,price:jiaocha}]}
            rubricList.push(data);
        }
        if ($("input[name='neng']").prop('checked')) {
            var neng_fraction = $("#fraction1").val();
            var ngao = $("#ngao").val();
            var njiaogao = $("#njiaogao").val();
            var nyiban = $("#nyiban").val();
            var njiaocha = $("#njiaocha").val();
            var data2 = {
                resId: 38, fraction: neng_fraction, answerList: [{resId: 42, price: ngao},
                    {resId: 43, price: njiaogao}, {resId: 44, price: nyiban}, {resId: 45, price: njiaocha}]
            }
            rubricList.push(data2);
        }
        if ($("input[name='qin']").prop('checked')) {
            var qin_fraction=$("#fraction2").val();
            var qgao=$("#qgao").val();
            var qjiaogao=$("#qjiaogao").val();
            var qyiban=$("#qyiban").val();
            var qjiaocha=$("#qjiaocha").val();
            var data3={resId:39,fraction:qin_fraction,answerList:[{resId:42,price:qgao},
                    {resId:43,price:qjiaogao},{resId:44,price:qyiban},{resId:45,price:qjiaocha}]}
            rubricList.push(data3);
        }
        if ($("input[name='ji']").prop('checked')) {
            var ji_fraction=$("#fraction3").val();
            var jgao=$("#jgao").val();
            var jjiaogao=$("#jjiaogao").val();
            var jyiban=$("#jyiban").val();
            var jjiaocha=$("#jjiaocha").val();
            var data4={resId:40,fraction:ji_fraction,answerList:[{resId:42,price:jgao},
                    {resId:43,price:jjiaogao},{resId:44,price:jyiban},{resId:45,price:jjiaocha}]}
            rubricList.push(data4);
        }
        if ($("input[name='lian']").prop('checked')) {
            var lian_fraction=$("#fraction4").val();
            var lgao=$("#lgao").val();
            var ljiaogao=$("#ljiaogao").val();
            var lyiban=$("#lyiban").val();
            var ljiaocha=$("#ljiaocha").val();
            var data5={resId:41,fraction:lian_fraction,answerList:[{resId:42,price:lgao},
                    {resId:43,price:ljiaogao},{resId:44,price:lyiban},{resId:45,price:ljiaocha}]}
            rubricList.push(data5);
        }
        var uid = $("#uid").val();
        var uid_s=uid.split(",");
        var uname=$("#uname").val();
        var uname_s=uname.split(",");
        //alert(uname_s);
        var req={
            title: title,
            explains: explains,
            peonum: peonum,
            rubricList:rubricList,
            uid_s:uid_s,
            uname_s:uname_s
        };
        var Yulan=JSON.stringify(req);
        window.sessionStorage.setItem("Yulan",Yulan);
        var index = layui.layer.open({
            title: "预览",
            type: 2,
            content: "addYuLan.html",
            success: function (layero, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回使用列表', '.layui-layer-setwin .layui-layer-close', {
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
     //存为模板
        form.on("submit(save)",function (data) {
            layer.prompt({title: '请输入模板标题',value:"",formType: 0}, function(templetTitle, index){
                layer.msg('你输入的模板标题为:'+templetTitle);
                var templetTitle=templetTitle;
                //alert(JSON.stringify(templetTitle));
                var title = $("#title").val();
                var explains = $("#explains").val();
                var peonum = $("#peonum").val();
                //德能勤绩廉高较高一般较差的分数分值
                var rubricList = new Array();
                if ($("input[name='de']").prop('checked')){
                    var de_fraction=$("#fraction").val();
                    var gao=$("#gao").val();
                    var jiaogao=$("#jiaogao").val();
                    var yiban=$("#yiban").val();
                    var jiaocha=$("#jiaocha").val();
                    var data={resId:37,fraction:de_fraction,answerList:[{resId:42,price:gao},
                            {resId:43,price:jiaogao},{resId:44,price:yiban},{resId:45,price:jiaocha}]}
                    rubricList.push(data);
                }
                if ($("input[name='neng']").prop('checked')) {
                    var neng_fraction = $("#fraction1").val();
                    var ngao = $("#ngao").val();
                    var njiaogao = $("#njiaogao").val();
                    var nyiban = $("#nyiban").val();
                    var njiaocha = $("#njiaocha").val();
                    var data2 = {
                        resId: 38, fraction: neng_fraction, answerList: [{resId: 42, price: ngao},
                            {resId: 43, price: njiaogao}, {resId: 44, price: nyiban}, {resId: 45, price: njiaocha}]
                    }
                    rubricList.push(data2);
                }
                if ($("input[name='qin']").prop('checked')) {
                    var qin_fraction=$("#fraction2").val();
                    var qgao=$("#qgao").val();
                    var qjiaogao=$("#qjiaogao").val();
                    var qyiban=$("#qyiban").val();
                    var qjiaocha=$("#qjiaocha").val();
                    var data3={resId:39,fraction:qin_fraction,answerList:[{resId:42,price:qgao},
                            {resId:43,price:qjiaogao},{resId:44,price:qyiban},{resId:45,price:qjiaocha}]}
                    rubricList.push(data3);
                }
                if ($("input[name='ji']").prop('checked')) {
                    var ji_fraction=$("#fraction3").val();
                    var jgao=$("#jgao").val();
                    var jjiaogao=$("#jjiaogao").val();
                    var jyiban=$("#jyiban").val();
                    var jjiaocha=$("#jjiaocha").val();
                    var data4={resId:40,fraction:ji_fraction,answerList:[{resId:42,price:jgao},
                            {resId:43,price:jjiaogao},{resId:44,price:jyiban},{resId:45,price:jjiaocha}]}
                    rubricList.push(data4);
                }
                if ($("input[name='lian']").prop('checked')) {
                    var lian_fraction=$("#fraction4").val();
                    var lgao=$("#lgao").val();
                    var ljiaogao=$("#ljiaogao").val();
                    var lyiban=$("#lyiban").val();
                    var ljiaocha=$("#ljiaocha").val();
                    var data5={resId:41,fraction:lian_fraction,answerList:[{resId:42,price:lgao},
                            {resId:43,price:ljiaogao},{resId:44,price:lyiban},{resId:45,price:ljiaocha}]}
                    rubricList.push(data5);
                }
                var s_uid=new Array();
                var uid = $("#uid").val();
                s_uid.push(uid);
                // alert(title);
                // alert(explains);
                // alert(peonum);
                //alert(uid);
                // alert(data);//弹框确定是否取到值
                //弹出loading(遮罩层已经统一放在了ajaxExtention里面了)
                //var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
                //请求
                // layer.prompt({title: '请输入模板标题',value:templetTitle,formType: 0}, function(admId, index){
                //     var admId=admId;
                // });
                var req = {
                    templetTitle:templetTitle,
                    title: title,
                    explains: explains,
                    peonum: peonum,
                    rubricList:rubricList,
                    s_uid: s_uid
                };

                //console.log(req);
                //console.log(JSON.stringify(req));
                //alert(JSON.stringify(req));
                //alert(req);//弹框确定是否取到值
                $api.addAdmKeep(JSON.stringify(req), {contentType: 'application/json;charset=utf-8'}, function () {
                    //top.layer.close(index);(关闭遮罩已经放在了ajaxExtention里面了)
                    layer.msg("添加成功！", {time: 1000}, function () {
                        layer.closeAll("iframe");
                        //刷新父页面
                        parent.location.reload();
                    });
                });
            });
            return false;
        });

        // function test(){
        //     var title = '';
        //     var explains = '';
        //     var peonum = '';
        //     var de_price = 1;
        //     var de_gaoPrice = 1;
        //     var data = {title:title,explains:explains,peonum:peonum,rubricList:{resId:37,answerList:[]}};
        //
        // }
    /**
     * 表单提交
     * */
    form.on("submit(addTemplet)", function (data) {
        //标题的值
        var title = $("#title").val();
        //投票说明的值
        var explains = $("#explains").val();
        //人数的值
        var peonum =$("#peonum").val();
        //德能勤绩廉高较高一般较差的分数分值
        var rubricList = new Array();
        if ($("input[name='de']").prop('checked')){
            var de_fraction=$("#fraction").val();
            var gao=$("#gao").val();
            var jiaogao=$("#jiaogao").val();
            var yiban=$("#yiban").val();
            var jiaocha=$("#jiaocha").val();
            var data={resId:37,fraction:de_fraction,answerList:[{resId:42,price:gao},
                    {resId:43,price:jiaogao},{resId:44,price:yiban},{resId:45,price:jiaocha}]}
                    rubricList.push(data);
        }
        if ($("input[name='neng']").prop('checked')) {
            var neng_fraction=$("#fraction1").val();
            var ngao=$("#ngao").val();
            var njiaogao=$("#njiaogao").val();
            var nyiban=$("#nyiban").val();
            var njiaocha=$("#njiaocha").val();
            var data2={resId:38,fraction:neng_fraction,answerList:[{resId:42,price:ngao},
                    {resId:43,price:njiaogao},{resId:44,price:nyiban},{resId:45,price:njiaocha}]}
            rubricList.push(data2);
        }
        if ($("input[name='qin']").prop('checked')) {
            var qin_fraction=$("#fraction2").val();
            var qgao=$("#qgao").val();
            var qjiaogao=$("#qjiaogao").val();
            var qyiban=$("#qyiban").val();
            var qjiaocha=$("#qjiaocha").val();
            var data3={resId:39,fraction:qin_fraction,answerList:[{resId:42,price:qgao},
                    {resId:43,price:qjiaogao},{resId:44,price:qyiban},{resId:45,price:qjiaocha}]}
            rubricList.push(data3);
        }
        if ($("input[name='ji']").prop('checked')) {
            var ji_fraction=$("#fraction3").val();
            var jgao=$("#jgao").val();
            var jjiaogao=$("#jjiaogao").val();
            var jyiban=$("#jyiban").val();
            var jjiaocha=$("#jjiaocha").val();
            var data4={resId:40,fraction:ji_fraction,answerList:[{resId:42,price:jgao},
                    {resId:43,price:jjiaogao},{resId:44,price:jyiban},{resId:45,price:jjiaocha}]}
            rubricList.push(data4);
        }
        if ($("input[name='lian']").prop('checked')) {
            var lian_fraction=$("#fraction4").val();
            var lgao=$("#lgao").val();
            var ljiaogao=$("#ljiaogao").val();
            var lyiban=$("#lyiban").val();
            var ljiaocha=$("#ljiaocha").val();
            var data5={resId:41,fraction:lian_fraction,answerList:[{resId:42,price:lgao},
                    {resId:43,price:ljiaogao},{resId:44,price:lyiban},{resId:45,price:ljiaocha}]}
            rubricList.push(data5);
        }

        //被测人员id的值
        var s_uid=new Array();
       var uid=$("#uid").val();
       s_uid.push(uid);

        console.log(JSON.stringify(rubricList));
       //请求
        var req={
            title:title,
            explains:explains,
            peonum:peonum,
            rubricList:rubricList,
            s_uid:s_uid,
        }

        //console.log(req);
        //console.log(JSON.stringify(req));
        //alert(JSON.stringify(req));
        //alert(req);//弹框确定是否取到值
        $api.addTemplet(JSON.stringify(req),{contentType:'application/json;charset=utf-8'},function (data) {
            //top.layer.close(index);(关闭遮罩已经放在了ajaxExtention里面了)
            layer.msg("添加成功！",{time:1000},function () {
                //alert(data.data);
                var index = layui.layer.open({
                    title: "账号名单",
                    type: 2,
                    content: "IsVote.html?recordId="+data.data,
                    success: function (layero, index) {
                        setTimeout(function () {
                            layui.layer.tips('点击此处返回评测打分列表', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        }, 500)
                    }
                });
                $(window).resize(function () {
                    layui.layer.full(index);
                });
                layui.layer.full(index);

                //刷新父页面
                //parent.location.reload();
           });
        });
        return false;
    });
});


