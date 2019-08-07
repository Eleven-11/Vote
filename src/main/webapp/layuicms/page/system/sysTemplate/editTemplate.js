layui.config({
    base: $config.resUrl+'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention: 'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool: 'tool',
    $api:'api'
}).use(['form', 'layer','$api', 'tree', 'jquery', 'ajaxExtention', '$tool'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery,
        $tool = layui.$tool,
        $api = layui.$api;


    /**
     * 初始化用户信息
     * */
    // var templateId;
    function init() {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var templateId = queryArgs['templateId'];
        var req = {
            templateId:templateId
        };
        $api.GetTitle(req,function (res) {
            var data = res.data;
            $("[name='title']").val(data.title);
            $("[name='statementVote']").val(data.statementVote);
            $("[name='numberVote']").val(data.numberVote);

            for(var i=0;i<res.data.questionList.length;i++){
                // alert(res.data.questionList.length)
                var dn='';
                for(var j=0;j<res.data.questionList[i].optionList.length;j++){
                    var dn_index = j+1;
                    dn += '<div class="layui-form-item parent-menu aa">\n' +
                        '                    <label class="layui-form-label">选项'+dn_index+':</label>\n' +
                        '                    <div class="layui-input-block">\n' +
                        '                        <input type="text" class="layui-input" lay-verify="required" name="option" value="'+res.data.questionList[i].optionList[j].option+'" placeholder="请输入选项">\n' +
                        '                    </div>\n' +
                        '                </div>';

                }
                if(res.data.questionList[i].optionRule == 78){
                    var rule ;
                    rule = "    <div class=\"layui-form-item\">\n" +
                        "        <label  class=\"layui-form-label\">选项规则:</label>\n" +
                        "        <div class=\"layui-input-block\" id=\"optionRule\">\n" +
                        '   <input type="radio" class="layui-input" name="optionRule' + index + '"' + 'value='+res.data.questionList[i].optionRule+' title="单选" checked="checked">' +
                        '<div class=\"layui-unselect layui-form-radio\"><i class=\"layui-anim layui-icon\"></i><span>单选</span></div>\n'+

                        '<input type="radio" class="layui-input" name="optionRule' + index + '"' + 'value='+res.data.questionList[i].optionRule+'  title="多选" >' +
                        '<div class=\"layui-unselect layui-form-radio layui-form-radioed\"><i class=\"layui-anim layui-icon\"></i><span>多选</span></div>\n'+

                        "        </div>\n" +
                        "    </div>\n" ;
                }else{
                    rule = "    <div class=\"layui-form-item\">\n" +
                        "        <label  class=\"layui-form-label\">选项规则:</label>\n" +
                        "        <div class=\"layui-input-block\" id=\"optionRule\">\n" +
                        '   <input type="radio" class="layui-input" name="optionRule' + index + '"' + 'value='+res.data.questionList[i].optionRule+' title="单选" >' +
                        '<div class=\"layui-unselect layui-form-radio\"><i class=\"layui-anim layui-icon\"></i><span>单选</span></div>\n'+

                        '<input type="radio" class="layui-input" name="optionRule' + index + '"' + 'value='+res.data.questionList[i].optionRule+'  title="多选" checked="checked" >' +
                        '<div class=\"layui-unselect layui-form-radio layui-form-radioed\"><i class=\"layui-anim layui-icon\"></i><span>多选</span></div>\n'+

                        "        </div>\n" +
                        "    </div>\n" ;
                }
                    // var tm_index=i+1;
                    var aa = index++;
                    var tm ="<div id=\"hh\">"+
                        '    <div class="layui-form-item">\n' +
                        '        <label class="layui-form-label">题目'+index+':</label>\n' +
                        '        <div class="layui-input-block">\n' +
                        '            <input type="text" class="layui-input" name="question" value="'+res.data.questionList[i].question+'" placeholder="请输入题目">\n' +
                        '        </div>\n' +
                        '    </div>\n' +
                        rule+
                        '    <div class="uuu">\n' +
                        dn +
                        '    </div>\n' +
                        '    <div class="layui-form-item ss">\n' +
                        '                <div class="layui-input-block" >\n' +
                        '                    <button  type="button"   class="layui-btn layui-btn-primary layui-btn-sm" onclick="a($(this))">新增选项</button>\n' +
                        '                </div>\n' +
                        '     </div>' +
                        '    <hr class=\"vv\"></div>';


                $(".cc").before(tm);

                }
            form.render("radio");//重新绘制表单，让修改生效
        });
    }
    init();
    /**
     * 表单提交
     * */
    form.on("submit(addWjs)", function (data) {
        var title1 = data.field.title;
        var statementVote = data.field.statementVote;
        var numberVote = data.field.numberVote;
        var list = [];
        var title = $("#yy").children("#hh");
        for (var i = 0;i<title.length;i++){
            //题目
            var d = $($($($(title[i]).children(".layui-form-item")[0]).children(".layui-input-block")[0]).children("input")).val();
            //alert(d);
            //选项规则
            var rule =  $($($($(title[i]).children(".layui-form-item")[1]).children(".layui-input-block")[0]).children(".layui-input")[0]).is(":checked");
            var c;
            if(rule){
                c=78
            }else{
                c=79
            }
            //alert(rule)
            //答案
            var option_list = [];
            var option =  $($(title[i]).children(".uuu")).children(".layui-form-item")
            for (var q = 0;q<option.length;q++){
                var b = $($($(option[q]).children(".layui-input-block")).children("input")).val();
                var ans = {option:b};
                option_list.push(ans);
                //alert(b);
            }
            var answer = {question:d,optionRule:c,optionList:option_list};
            list.push(answer);
        }
        var count = [];
        for(var j = 0;j<numberVote;j++) {
            var data = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"];
            var nums = "";
            for (var i = 0; i < 8; i++) {
                var r = parseInt(Math.random() * 61);
                nums += data[r];
            }
            count.push(nums);
        }
        var qq={
            sum:count
        }
        var ss =   JSON.stringify(qq);
        window.sessionStorage.setItem("ss", ss);


        var req ={

            title:title1,
            statementVote:statementVote,
            numberVote:numberVote,
            questionList:list,
            sum:count
        };

        $api.Add(JSON.stringify(req),{contentType:"application/json;charset=UTF-8"},function (data) {
            //top.layer.close(index);(关闭遮罩已经放在了ajaxExtention里面了)
            layer.msg("发布成功！", {time: 1000}, function () {
                var index = layui.layer.open({
                    title: "用户账号",
                    type: 2,
                    content: "Acount.html",
                    success: function (layero, index) {
                        setTimeout(function () {
                            layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
                                tips: 3
                            });
                        }, 500)
                    }
                });
                $(window).resize(function () {
                    layui.layer.full(index);
                });
                layui.layer.full(index);
                // layer.closeAll("iframe");
                // //刷新父页面
                // parent.location.reload();
            });
        });

        return false;
    })


    //存为模板
    form.on("submit(addTemplate)", function (data) {

        var title1 = data.field.title;
        layer.prompt({title: '请输入模板标题',value:title1,formType: 2}, function(templateTitle){
            var statementVote = data.field.statementVote;
            var numberVote = data.field.numberVote;
            var list = [];
            var templateTitle=templateTitle;
            var title = $("#yy").children("#hh");
            for (var i = 0;i<title.length;i++){
                // alert(title.length)
                //题目
                var d = $($($($(title[i]).children(".layui-form-item")[0]).children(".layui-input-block")[0]).children("input")).val();
                //alert(d);
                //选项规则
                var rule =  $($($($(title[i]).children(".layui-form-item")[1]).children(".layui-input-block")[0]).children(".layui-input")[0]).is(":checked");
                var c;
                if(rule){
                    c=78
                }else{
                    c=79
                }
                //alert(rule)
                //答案
                var option_list = [];
                var option =  $($(title[i]).children(".uuu")).children(".layui-form-item")
                for (var q = 0;q<option.length;q++){
                    var b = $($($(option[q]).children(".layui-input-block")).children("input")).val();
                    var ans = {option:b};
                    option_list.push(ans);
                    //alert(b);
                }
                var answer = {question:d,optionRule:c,optionList:option_list};
                list.push(answer);

            }
            var req ={

                title:title1,
                statementVote:statementVote,
                numberVote:numberVote,
                questionList:list,
                templateTitle:templateTitle
            };
            // layer.msg('您输入模板标题为：'+ templateTitle );
            $api.Baocun(JSON.stringify(req),{contentType:"application/json;charset=UTF-8"},function (data) {
                //top.layer.close(index);(关闭遮罩已经放在了ajaxExtention里面了)
                layer.msg("存为模板成功！", {time: 1000}, function () {
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                });
            });

        });
        return false;
    })


    // $(".check").click(function () {
    //     var index = layui.layer.open({
    //         title: "预览",
    //         type: 2,
    //         content: "CheckTemplate.html?templateId="+templateId,
    //         success: function (layero, index) {
    //             setTimeout(function () {
    //                 layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
    //                     tips: 3
    //                 });
    //             }, 500)
    //         }
    //     });
    //     //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
    //     $(window).resize(function () {
    //         layui.layer.full(index);
    //     });
    //     layui.layer.full(index);
    // });
    /**
     * 预览
     * */
    form.on("submit(ylang)", function (data) {
        var title1 = data.field.title;
        var statementVote = data.field.statementVote;
        var numberVote = data.field.numberVote;
        var list = [];
        var title = $("#yy").children("#hh");
        for (var i = 0;i<title.length;i++){
            //题目
            var d = $($($($(title[i]).children(".layui-form-item")[0]).children(".layui-input-block")[0]).children("input")).val();
            // alert(d);
            //选项规则
            var rule =  $($($($(title[i]).children(".layui-form-item")[1]).children(".layui-input-block")[0]).children(".layui-input")[0]).is(":checked");
            var c;
            if(rule){
                c=78
            }else{
                c=79
            }
            //alert(rule)
            //答案
            var option_list = [];
            var option =  $($(title[i]).children(".uuu")).children(".layui-form-item")
            for (var q = 0;q<option.length;q++){
                var b = $($($(option[q]).children(".layui-input-block")).children("input")).val();
                var ans = {option:b};
                option_list.push(ans);
                // alert(b);
            }
            var answer = {question:d,optionRule:c,optionList:option_list};
            list.push(answer);
        }
        var req ={
            title:title1,
            statementVote:statementVote,
            numberVote:numberVote,
            questionList:list
        };
        var cc =   JSON.stringify(req);
        window.sessionStorage.setItem("cc", cc);// 保存数据到 sessionStorage
        var index = layui.layer.open({
            title: "预览",
            type: 2,
            content: "SyCheck.html",
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
            layui.layer.full(index);
        });
        layui.layer.full(index);
        return false;
    })

});


