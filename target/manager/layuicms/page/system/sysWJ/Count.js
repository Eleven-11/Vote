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
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var statusId = queryArgs['statusId'];
        var req = {
            statusId:statusId
        };
        $api.Count(req,function (res) {
            var data = res.data;
            $("[name='title']").val(data.title);
            var a = 1;
            for(var i=0;i<res.data.questionList.length;i++){
                var rule = '';
                for (var j = 0 ; j < res.data.questionList[i].optionList.length ; j++) {
                    var c = a++;//定义自增变量，让radio或checkbox的id不重复
                    if (res.data.questionList[i].optionRule == 78) {
                        // alert(res.data.questionList[0].optionList[0].count)
                        rule +=
                            "<div class='male'>" +
                            "   <input id='item" + c + "' type='radio' name='optionRule" + i + "' value='" + res.data.questionList[i].optionList[j].optionId + "' class='radio_'>" +
                            "   <label for='item" + c + "'></label>" +
                            "   <span style='margin-left: 10px'>" +
                            "       <input type='text' class='layui-input option' value='" + res.data.questionList[i].optionList[j].option + " ' style='border:none;' name='option' readonly='true'>" +
                            "   </span>" +
                            "<div class='' >" +
                            "   <span style='margin-left: 10px'>" +
                            "       <input type='text' class='layui-input option' value='" +"已选人数:" + res.data.questionList[i].optionList[j].count + "' style='border:none;' name='Sum' readonly='true'>" +
                            "   </span>" +
                            "</div>"+
                            "</div>"
                    } else {
                        rule +=
                            "<div class='male'>" +
                            "   <input id='item" + c + "' type='checkbox' name='optionRule' value='"+ res.data.questionList[i].optionList[j].optionId + "' class='checkbox_'>" +
                            "   <label for='item" + c + "'></label>" +
                            "   <span style='margin-left: 10px'>" +
                            "       <input type='text' class='layui-input option' value='" + res.data.questionList[i].optionList[j].option + "' style='border:none;' name='option' readonly='true'>" +
                            "   </span>" +
                            "<div class=''>" +
                            "   <span style='margin-left: 10px'>" +
                            "       <input type='text' class='layui-input option' value='" +"已选人数:" + res.data.questionList[i].optionList[j].count + "' style='border:none;' name='Sum' readonly='true'>" +
                            "   </span>" +
                            "</div>"+
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
                    "                   <input type='text' class='layui-input question' value='"+res.data.questionList[i].question+"' style='border:none;' name='question' readonly='true'>"+
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
        });
    }
    init();

});


