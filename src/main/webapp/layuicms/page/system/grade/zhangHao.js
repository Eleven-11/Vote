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
    var id;
    function init() {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var recordId = queryArgs['recordId'];
        //alert(recordId);
        id=recordId;
        var ver={
            recordId:recordId
        }
        $api.GetVer(ver,function (res) {
            var verfi=res.data;
            console.log(verfi)
            $(verfi).each(function (index,item) {
                //alert(item.content);
                var aa="<div class='zhanghao'>"+"<input type='text'class='dx' value='"+item.content+"' style='width: 30%'>"+
                    "</div>"
                $(".coll").append(aa);
            });
        });
        form.render();//重新绘制表单，让修改生效
    }
    init();
    /**
     * 页面初始化
     */
    $(document).ready(function () {
        $("#exportExcelStudent").on("click",exportExcelStudent); //导出Excel按钮绑定事件
    });
    /**
     * 导出Excel
     * @returns
     */
    function exportExcelStudent(){
        //定义URL为导出Excel的后台请求地址
        var url =  $tool.getContext()+"record/exportExcel";
        var ver={
            recordId:id
        }
        //将URL加至form的action中
        $api.GetExcel(ver,function (res) {
           var excel=res.data;
            layer.msg("导出成功！", {time: 1000}, function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });
        });
    }
});
