var  a=0;
layui.config({
    base: $config.resUrl+'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention:'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool:'tool',
    $api:'api'
}).use(['form', 'layer','$api', 'jquery','ajaxExtention','$tool'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery,
        $tool = layui.$tool,
        $api = layui.$api;


    /**
     * 初始化页面
     * */
    var index=0;
    function init() {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var templateId = queryArgs['templateId'];

        alert(templateId);
        var req = {
            templateId:templateId,

        };
        $api.Gettemp(req,function (res) {
            var data = res.data;
            console.log(data);
            $("[name='bigTitle']").val(data.bigTitle);
            $("[name='explain']").val(data.explain);

            for(var i=0;i<data.list.length;i++){
                var zbt='';

                var bc='';

                for(var g = 0;g<data.list[i].beice.length;g++){
                    var  f= g+1
                    var bz='';
                    for(var j=0;j<data.list[i].bz_coll.length;j++){
                        var only='';
                        //子标准
                        for(var z=0;z<data.list[i].bz_coll[j].zbz_coll.length;z++){
                            // alert(res.data.list[i].bz_coll[j].zbz_coll[z].substandardName);
                            if(res.data.list[i].bz_coll[j].id===78){
                                only+=  '<div class="xuanxian">'+
                                    '<input id="item'+a+'" type="radio" title="'+res.data.list[i].bz_coll[j].zbz_coll[z].substandardName+'" name="item'+a+'" value="'+res.data.list[i].bz_coll[j].id+'" class="radio_">'+
                                    '<label for="item'+a+'"></label>'+
                                    '</div>'
                            }else{
                                only+= '<div class="xuanxian">'+
                                    '<input type="checkbox" id="checkbox'+a+'" class="checkbox_"/>'+
                                    '<label for="checkbox'+a+'"></label>'+
                                    '<input type="text" class="dx" name="substandardName" value="'+res.data.list[i].bz_coll[j].zbz_coll[z].substandardName+'">'+
                                    '</div>'
                            }
                        }
                        a++;
                        var u = j+1;
                        //标准
                        bz+='<div class="timuquyu">\n' +
                            '                    <div class="timu">\n' +
                            '                        <span>'+u+'</span>\n' +
                            '                        <span>\n' +
                            '                            <input type="text" name="standardName" class="rm" value="'+res.data.list[i].bz_coll[j].standardName+'" >\n' +
                            '                        </span>\n' +
                            '                    </div>\n' +
                            '                        <div class="zbz">\n' +
                            only+
                            '                        </div>\n' +
                            '                </div>'

                        layui.form.render("radio");


                    }

                    bc+=  ' <div class="renmingquyu">\n' +
                        '                    <div class="renming">\n' +
                        '                        <div class="shizi" style="padding: 0px;border-radius:50%" >\n' +
                        '                            <p style="margin: 0px;padding-left: 6px">\n' +
                        '                            <font color="red" >'+f+'</font>\n' +
                        '                        </p>\n' +
                        '                        </div>\n' +
                        '                        <div>\n' +
                        '                            <input type="text" class="rm" name="uname" value="'+res.data.list[i].beice[g].tpphotoUser.uname+'">\n' +
                        '                        </div>\n' +
                        '                    </div>\n' +
                        '                    <div id="bz_coll">' +
                        bz +
                        '</div>'
                    '</div>'
                }



                //子标题
                zbt+= '<div class="zibiaotifenquyu">\n' +
                    '                <div class="hr"></div>\n' +
                    '                <div class="subtitle">\n' +
                    '                    <div><img src="../../../common/images/img6.jpg" alt="" style="width: 41px; height: 41px"></div>\n' +
                    '                    <div class="sub">\n' +
                    '                        <input class="zbt" type="text"name="subtitleContent" value="'+res.data.list[i].subtitleContent+'">\n' +
                    '                    </div>\n' +
                    '                </div>'+
                    '<div class="renmingColl">'+
                    bc+
                    '                </div>'+
                    '                </div>';

                $('#zbt_coll').append(zbt);
                layui.form.render("radio");
            }



            form.render();//重新绘制表单，让修改生效
        });
    }
    init();



});






