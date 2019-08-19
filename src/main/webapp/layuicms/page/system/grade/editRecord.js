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

    /*
     * 初始化菜单信息
     * */
    function init() {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var admId = queryArgs['admId'];
        //alert(JSON.stringify(admId));

        var url = $tool.getContext()+'adm/findById.do';
        var req = {
            admId:admId
        };
        //findById
        $api.GetAdm(req,function (res) {
            var data = res.data;
            //alert(JSON.stringify(data));
            $("#title").val(data.templet.title);
            $("#explains").val(data.templet.explains);
            $("#peonum").val(data.templet.peonum);
            //for(Student student:list){}
            var uids="";
            var users="";
            $(data.templet.evaluatList).each(function (index,evaluate) {
                uids+=evaluate.uid+"  ";
                $(evaluate.userList).each(function (index,user) {
                    users+=user.uname+"  "
                });
                $("#uname").val(users);
            });
            $("#uid").val(uids);
            $(data.templet.rubrics).each(function (index,item) {
                //alert(JSON.stringify(data.templet));
                //alert(JSON.stringify(data.templet.rubrics));
                if(item.resId==37){
                    $("input[name='de']").attr("checked","checked");
                    $("#count").show();
                    $("#fraction").val(item.fraction);
                    $(item.answerList).each(function (index,answer) {
                        if(index==0){
                            $("#gao").val(answer.price);
                        }else if (index==1){
                            $("#jiaogao").val(answer.price);
                        }else if(index==2){
                            $("#yiban").val(answer.price);
                        }else if(index==3){
                            $("#jiaocha").val(answer.price);
                        }
                    });
                }
                if(item.resId==38){
                    $("input[name='neng']").attr("checked","checked");
                    $("#count2").show();
                    $("#fraction1").val(item.fraction);
                    $(item.answerList).each(function (index,answer) {
                        if(index==0){
                            $("#ngao").val(answer.price);
                        }else if (index==1){
                            $("#njiaogao").val(answer.price);
                        }else if(index==2){
                            $("#nyiban").val(answer.price);
                        }else if(index==3){
                            $("#njiaocha").val(answer.price);
                        }
                    });
                }
                if(item.resId==39){
                    $("input[name='qin']").attr("checked","checked");
                    $("#count3").show();
                    $("#fraction2").val(item.fraction);
                    $(item.answerList).each(function (index,answer) {
                       if(index==0){
                           $("#qgao").val(answer.price);
                       }else if (index==1){
                           $("#qjiaogao").val(answer.price);
                       }else if(index==2){
                           $("#qyiban").val(answer.price);
                       }else if(index==3){
                           $("#qjiaocha").val(answer.price);
                       }
                    });
                    //alert(JSON.stringify(item.fraction));
                }
                if(item.resId==40){
                    $("input[name='ji']").attr("checked","checked");
                    $("#count4").show();
                    $("#fraction3").val(item.fraction);
                    $(item.answerList).each(function (index,answer) {
                        if(index==0){
                            $("#jgao").val(answer.price);
                        }else if (index==1){
                            $("#jjiaogao").val(answer.price);
                        }else if(index==2){
                            $("#jyiban").val(answer.price);
                        }else if(index==3){
                            $("#jjiaocha").val(answer.price);
                        }
                    });
                }if(item.resId==41){
                    $("input[name='lian']").attr("checked","checked");
                    $("#count5").show();
                    $("#fraction4").val(item.fraction);
                    $(item.answerList).each(function (index,answer) {
                        if(index==0){
                            $("#lgao").val(answer.price);
                        }else if (index==1){
                            $("#ljiaogao").val(answer.price);
                        }else if(index==2){
                            $("#lyiban").val(answer.price);
                        }else if(index==3){
                            $("#ljiaocha").val(answer.price);
                        }
                    });
                }

            });
            form.render();//重新绘制表单，让修改生效
        });
    }
    init();


    form.on('checkbox(type)',function (data) {
        if (data.elem.checked) {
            $("#count").show();
        }else{
            $("#count").hide();
        }
    });
    form.on('checkbox(type2)',function (data) {
        if (data.elem.checked) {
            $("#count2").show();
        }else{
            $("#count2").hide();
        }
    });
    form.on('checkbox(type3)',function (data) {
        if (data.elem.checked) {
            $("#count3").show();
        }else{
            $("#count3").hide();
        }
    });
    form.on('checkbox(type4)',function (data) {
        if (data.elem.checked) {
            $("#count4").show();
        }else{
            $("#count4").hide();
        }
    });
    form.on('checkbox(type5)',function (data) {
        if (data.elem.checked) {
            $("#count5").show();
        }else{
            $("#count5").hide();
        }
    });
    //预览
    form.on("submit(edit_ready)",function () {

    });
    /**
     * 表单提交
     * */
    form.on("submit(addTemplet1)", function (data) {
        var title = $("#title").val();
        var explains = $("#explains").val();
        var peonum = $("#peonum").val();
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
        s_uid.push(parseInt(uid));
        // alert(data);//弹框确定是否取到值
        //弹出loading(遮罩层已经统一放在了ajaxExtention里面了)
        //var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        //请求
        var req = {
            title: title,
            explains: explains,
            peonum: peonum,
            rubricList:rubricList,
            s_uid: s_uid
            // checkedUser:$("#checked_User").val()
        };
        //console.log(req);
        //console.log(JSON.stringify(req));
        //alert(JSON.stringify(req));
        //alert(req);//弹框确定是否取到值
        $api.updateTemplet(JSON.stringify(req),{contentType:'application/json;charset=utf-8'},function () {
            //top.layer.close(index);(关闭遮罩已经放在了ajaxExtention里面了)
            layer.msg("添加成功！",{time:1000},function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });
        });
        return false;
    });
});


