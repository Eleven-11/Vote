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
    });

    /*
     * 初始化菜单信息
     * */
    function init() {
        //findById
        var dd=window.sessionStorage.getItem("cc");
        var da=JSON.parse(dd);
            //alert(JSON.stringify(data));
            $("[name='title']").val(da.title);
            $("[name='explains']").val(da.explains);
            $("[name='peonum']").val(da.peonum);
            console.log(da);

            var show="<div class='bottom_top'>"+"<hr style='height:5px;width:960px;border:5px solid rgba(242,246,249,1);border-top:1px solid #101010;margin: 0 auto'/>"
            +"<div class='statu'>" +"<div class='test'><p><br></p></div>"+"<div class='region gesture' style='display: none;'></div>"
                + "</div>"+"<div class='mm'>"

            $(da.rubricList).each(function (index,ritem) {
                show+="<div class='status'>"+"<div class='test'><input type='text' name='resId'  value='"+ritem.resId+"' style='border: none;text-align: center;background-color: #eaf9ef;width: 20%' readonly='true' hidden='hidden'>"
                   +"("+"<input type='text' name='fraction'  value='"+ritem.fraction+"' style='border: none;text-align: center;background-color: #eaf9ef;width: 20%' readonly='true'>"+")"
                    + "</div>"
                +"<div class='region gesture' style='display:none;'></div>"
                +"</div>";
            });
            show+="</div>";
            show+="</div>";
            var g=0;
            $(da.uname_s).each(function (st,list) {
                show+="<div class='bottom_center'>";
                show+="<hr style='height:1px;width:960px;border:none;border-top:1px solid #555555;margin: 0 auto'/>"
                +"<div class='ren'>"+"<div class='test2'>"
                   +"<input type='text' name='uid' value='"+list+"' style='border: none;text-align: center;background-color: #eaf9ef;width: 100px' readonly='true'>"
                    +"</div>"
                    +"<div class='region gesture' style='display:none;'></div>"
                    +"</div>"+"<div class='nn'>";
                var dq="<div class='dq'>";
                var dq2="";
                var redq="<div class='dq'>";
                var cj="";
                var nn="<div class='fs'>";
                var nnn="<div class='fs'>";
                $(da.rubricList).each(function (ind,rubric) {
                    var i = 0;
                $(rubric.answerList).each(function (index,answer) {
                    i+=1;
                    if(i==3){
                        dq+="</div>";
                        dq2=dq;
                        dq=redq;
                        i=0;
                    }
                    if (index==0){
                        cj="高&emsp;";
                    }
                    if (index==1){
                        cj="较高"
                    }
                    if (index==2){
                        cj="一般"
                    }
                    if (index==3){
                        cj="较差"
                    }
                    dq+="<div class='radio'>"+"<label class='Android-Radio'><input type='radio' name='price"+g+"' value='"+answer.price+"'>"
                        +"<span class='CheckState'></span>"
                        +"</label>"+"<div class='region gesture' style='display:none;'></div>"+"</div>"
                    +"<div class='group2'>" +"<div class='text3'><p>"+cj+"</p></div>"
                        +"</div>";
                });
                    nn+=dq2;
                    nn+=dq;
                    nn+="</div>";
                    nn+="</div>";
                    show+=nn;
                    g+=1;
                    nn=nnn;
                    dq=redq;

                });
                // show+=dq2;
                // show+=dq;
                // show+="</div>";
                // show+="</div>";
                show+="</div>";
                show+="</div>";
            });

            show+="<div class='bottom_xia'><hr style=\"height:1px;width:960px;border:none;border-top:1px solid #555555;margin: 0 auto\" />"
                +'<div class="text">'+'<div class="btn">'+'<div class="submit_1">'+'<a class="layui-btn layui-btn-normal add_btn submit" >提交</a>'
                +' </div>'+'<div class="region gesture" style="display:none;"></div>'+' </div>'+'</div>'+'</div>';


            $(".bottom").append(show);

            form.render();//重新绘制表单，让修改生效

    }
    init();

});


