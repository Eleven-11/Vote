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

    var a =[];
    function init() {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var statusId = queryArgs['statusId'];
         var req ={
            statusId:statusId
        }
        $api.CheckAccount(req,function (data) {
            a= data.data;
            for (var i = 0; i < data.data.length ; i++) {
                    var aa="<div class=\"aa\" style='width: 15%;display: inline-block'>\n" +
                        "            <input type=\"text\" class=\"layui-input \" value='"+data.data[i].content+"' readonly=\"true\" style='border: none' >\n" +
                        "        </div>"
                    $(".count").append(aa);

            }
        });




        //导出
        form.on("submit(daochu)", function (data) {
            const zh = [];
            //遍历生成的账号
            for (var i = 0; i < a.length; i++) {
                const json =
                    {
                        num:''+(i+1)+'',
                        content:''+a[i].content+''
                    }
                zh.push(json);
            }
            //列标题，逗号隔开，每一个逗号就是隔开一个单元格
            let str = `,账号\n`;
            //增加\t为了不让表格显示科学计数法或者其他格式
            for(let i = 0 ; i < zh.length ; i++ ){
                for(let item in zh[i]){
                    str+=`${zh[i][item] + '\t'},`;
                }
                str+='\n';
            }
            //encodeURIComponent解决中文乱码
            let uri = 'data:text/csv;charset=utf-8,\ufeff' + encodeURIComponent(str);
            //通过创建a标签实现
            let link = document.createElement("a");
            link.href = uri;
            //对下载的文件命名
            link.download =  "账号.xls";
            document.body.appendChild(link);
            link.click();
            document.body.removeChild(link);

            return false;
        })
    }
    init();



});


