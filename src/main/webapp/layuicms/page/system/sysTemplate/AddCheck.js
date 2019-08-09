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

    function init() {
        var aa = window.sessionStorage.getItem("aa");// 从 sessionStorage 获取数据
        var bb = JSON.parse(aa);
            $("[name='title']").val(bb.title);
            $("[name='statementVote']").val(bb.statementVote);
            var a = 1;
            for(var i=0;i<bb.questionList.length;i++){
                var rule = '';
                for (var j = 0 ; j < bb.questionList[i].optionList.length ; j++) {
                    var c = a++;//定义自增变量，让radio或checkbox的id不重复
                    if (bb.questionList[i].optionRule == 78) {
                        rule +=
                            "<div class='male'>" +
                            "   <input id='item" + c + "' type='radio' name='optionRule" + i + "' value='" + bb.questionList[i].optionRule + "' class='radio_'>" +
                            "   <label for='item" + c + "'></label>" +
                            "   <span style='margin-left: 10px'>" +
                            "       <input type='text' class='layui-input option' value='" + bb.questionList[i].optionList[j].option + "' style='border:none;' name='option' readonly='true'>" +
                            "   </span>" +
                            "</div>"
                    } else {
                        rule +=
                            "<div class='male'>" +
                            "   <input id='item" + c + "' type='checkbox' name='optionRule' value='" + bb.questionList[i].optionRule + "' class='checkbox_'>" +
                            "   <label for='item" + c + "'></label>" +
                            "   <span style='margin-left: 10px'>" +
                            "       <input type='text' class='layui-input option' value='" + bb.questionList[i].optionList[j].option + "' style='border:none;' name='option' readonly='true'>" +
                            "   </span>" +
                            "</div>"
                    }

                }

                var tm=

                    "       <div class='xx' style='padding-top: 30px;width: 40px;padding-left:50px'>"+
                    "           <div class='widget circle_rect hcenter vmiddle' style='border-radius: 50%; width: 30px; height: 30px; left: 280px; top: 452px; background-color: rgb(43, 117, 242); font-size: 18px; text-align: center; line-height: 26px;'>"+
                    "               <div class='text' style='padding-top:0px;'>"+
                    "                   <p><font color='#ffffff' style='line-height: 30px'>"+(i+1)+"</font></p>"+
                    "               </div>"+
                    "               <div class='region gesture'></div>"+
                    "           </div>"+
                    "       </div>"+
                    "       <div class='rich-text ss'>"+
                    "           <p style='line-height:0; margin-bottom:5px;width: 500px'>"+
                    "               <span class='--mb--rich-text' style='font-family:SourceHanSansSC; font-weight:400; font-size:18px; color:rgb(16, 16, 16);line-height:27px;'>"+
                    <!--题目-->
                    "                   <input type='text' class='layui-input question' value='"+bb.questionList[i].question+"' style='border:none;' name='question' readonly='true'>"+
                    "               </span>"+
                    "           </p>"+
                    "       </div>"+
                    "       <div class='aa'>"+
                    rule +
                    "       </div>\n"+
                    "<div class='xian'>" +
                    "</div>";

                $('.tm').append(tm);

                }
            form.render("radio");//重新绘制表单，让修改生效

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



        var req ={

            title:title1,
            statementVote:statementVote,
            numberVote:numberVote,
            questionList:list
        };

        $api.Add(JSON.stringify(req),{contentType:"application/json;charset=UTF-8"},function (data) {
            //top.layer.close(index);(关闭遮罩已经放在了ajaxExtention里面了)
            layer.msg("发布成功！", {time: 1000}, function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });
        });

        return false;
    })


    //存为模板
    form.on("submit(add)", function (data) {

        var title1 = data.field.title;
        layer.prompt({title: '请输入模板标题',value:title1,formType: 2}, function(templateTitle){


            var statementVote = data.field.statementVote;
            var numberVote = data.field.numberVote;
            var list = [];
            var templateTitle=templateTitle;
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







            var req ={

                title:title1,
                statementVote:statementVote,
                numberVote:numberVote,
                questionList:list,
                templateTitle:templateTitle

            };

            // layer.msg('您输入模板标题为：'+ templateTitle );

            $api.Add1(JSON.stringify(req),{contentType:"application/json;charset=UTF-8"},function (data) {
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

//创建模板
    $(".check").click(function () {
        var index = layui.layer.open({
            title: "创建模板",
            type: 2,
            content: "CheckTemplate.html",
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

});


