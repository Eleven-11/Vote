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
    var evaluatLists;
    var  lengths;
    function init() {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var recordId = queryArgs['recordId'];
        //alert(JSON.stringify(admId));


        var url = $tool.getContext()+'record/findByIdRecord.do';
        var req = {
            recordId:recordId,
        };
        //findById
        $api.GetRecord(req,function (res) {
            var dataRe = res.data;
            evaluatLists=dataRe.templet.evaluatList.length;
            lengths = dataRe.templet.rubrics.length;

            //alert(JSON.stringify(data));
            $("[name='title']").val(dataRe.templet.title);
            $("[name='explains']").val(dataRe.templet.explains);
            $("[name='peonum']").val(dataRe.templet.peonum);
            console.log(dataRe);
            console.log(dataRe.templet.rubrics);
            var show="<div class='bottom_top'>"+"<hr style='height:5px;width:960px;border:5px solid rgba(242,246,249,1);border-top:1px solid #101010;margin: 0 auto'/>"
            +"<div class='statu'>" +"<div class='test'><p><br></p></div>"+"<div class='region gesture' style='display: none;'></div>"
                + "</div>"+"<div class='mm'>"

            $(dataRe.templet.rubrics).each(function (index,ritem) {
                $(ritem.resourceList).each(function (index,resource) {
                show+="<div class='status'>"+"<div class='test'><input type='text' name='resId'  value='"+ritem.resId+"' style='border: none;text-align: center;background-color: #eaf9ef;width: 20%' readonly='true' hidden='hidden'>"
                  + "<input type='text' name='resName'  value='"+resource.resName+"' style='border: none;text-align: center;background-color: #eaf9ef;width: 20%' readonly='true' >"
                   +"("+"<input type='text' name='fraction'  value='"+ritem.fraction+"' style='border: none;text-align: center;background-color: #eaf9ef;width: 20%' readonly='true'>"+")"
                    + "</div>"
                +"<div class='region gesture' style='display:none;'></div>"
                +"</div>";
            });
            });
            show+="</div>";
            show+="</div>";
            var g=0;

            $(dataRe.templet.evaluatList).each(function (index,list) {
                show+="<div class='bottom_center'>";
                $(list.userList).each(function (index,u) {
                show+="<hr style='height:1px;width:960px;border:none;border-top:1px solid #555555;margin: 0 auto'/>"
                +"<div class='ren'>"+"<div class='test2'><input type='text' name='uname' value='"+u.uname+"' style='border: none;text-align: center;background-color: #eaf9ef;width: 100px' readonly='true'>"
                    +"<input type='text' name='uname' value='"+list.uid+"' style='border: none;text-align: center;background-color: #eaf9ef;width: 100px' readonly='true' hidden='hidden' class='uid_'>"
                    +"</div>"
                    +"<div class='region gesture' style='display:none;'></div>"
                    +"</div>"+"<div class='nn'>";
                var dq="<div class='dq'>";
                var dq2="";
                var redq="<div class='dq'>";
                var cj="";
                var nn="<div class='fs'>";
                var nnn="<div class='fs'>";
                $(dataRe.templet.rubrics).each(function (ind,rubric) {
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
                    dq+="<div class='radio'>"+"<label class='Android-Radio'><input type='radio'  name='price"+g+"' value='"+answer.answerId+"' class='radio_'>"
                        +"<span class='CheckState'></span>"
                        +"</label>"+"<div class='region gesture' style='display:none;'></div>"+"</div>"
                    +"<div class='group2'>" +"<div class='text3'><input type='text' name='resId' value='"+answer.resId+"' hidden='hidden'><p>"+cj+"</p></div>"
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
            });
            // show+="<div class='bottom_xia'><hr style=\"height:1px;width:960px;border:none;border-top:1px solid #555555;margin: 0 auto\" />"
            //     +'<div class="text">'+'<div class="btn">'+'<div class="submit_1">'+'<botton layer-filter="tijiao" class="layui-btn layui-btn-normal" lay-submit="">提交</botton>'
            //     +' </div>'+'<div class="region gesture" style="display:none;"></div>'+' </div>'+'</div>'+'</div>';

            $(".bottom").append(show);
            form.render();//重新绘制表单，让修改生效

        });
    }
    init();

    /*
    提交
     */
    form.on("submit(present)",function (data) {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var recordId = queryArgs['recordId'];
        // alert(recordId);
        //console.log(evaluatLists);
        var at=window.sessionStorage.getItem("aa");
        var bt=JSON.parse(at);
        var content=bt.content;


        var obj=document.getElementsByClassName("radio_");
        var radio_select=[];
        for (var k in obj){
            if(obj[k].checked) {
                radio_select.push(obj[k]);
            }
        }
        var k=0;
        var dog = [];
        for (var p=0;p<evaluatLists;p++) {
            var uid=$($($($($($('.bottom')[0]).children('.bottom_center')[p]).children('.ren')[0]).children('.test2')[0]).children('input')[1]).val();
            var oids=[];
            for (var i = 0;i<lengths;i++){
                k=k+1;
                //alert(radio_select[k-1].value);
                oids.push({"oid":radio_select[k-1].value});
                if(i==lengths-1){
                    break;
                }


            }
            var  s = {"uid":uid,"catList":oids}
            dog.push(s);
        }
        //alert(JSON.stringify(ss))


        var ti={
            recordId:recordId,
            dogList:dog,
            content:content
        }
        //alert(JSON.stringify(ti))
        console.log(ti);
        //alert(JSON.stringify(ti));
        $api.tiRecord(JSON.stringify(ti), {contentType: 'application/json;charset=utf-8'}, function () {
            //top.layer.close(index);(关闭遮罩已经放在了ajaxExtention里面了)
            layer.msg("提交成功！", {time: 1000}, function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });
        });
    });
});


