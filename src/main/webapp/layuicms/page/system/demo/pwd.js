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
    function init() {
        var zhanghao = window.sessionStorage.getItem("zhang");
        var zh = JSON.parse(zhanghao);
        for (var j = 0; j < zh.coll.length; j++) {
            var aa = ' <div class="zhanghao">\n' +
                '        <input type="text"  class="dx" value="' + zh.coll[j] + '">\n' +
                '\n' +
                '    </div>'
            $(".coll").append(aa);
        }
        form.on("submit(daochu)", function (data) {
            const zhanghao = [];
            //遍历生成的账号
            for (var i = 0; i < zh.coll.length; i++) {
                const json =
                    {
                        num:''+(i+1)+'',
                        content:''+zh.coll[i]+''
                    }
                zhanghao.push(json);
            }

            //列标题，逗号隔开，每一个逗号就是隔开一个单元格
            let str = `序号,账号\n`;
            //增加\t为了不让表格显示科学计数法或者其他格式
            for(let i = 0 ; i < zhanghao.length ; i++ ){
                for(let item in zhanghao[i]){
                    str+=`${zhanghao[i][item] + '\t'},`;
                }
                str+='\n';
            }
            //encodeURIComponent解决中文乱码
            let uri = 'data:text/csv;charset=utf-8,\ufeff' + encodeURIComponent(str);
            //通过创建a标签实现
            let link = document.createElement("a");
            link.href = uri;
            //对下载的文件命名
            link.download =  "账号表.csv";
            document.body.appendChild(link);
            link.click();
            document.body.removeChild(link);

            return false;
        })


        form.render();//重新绘制表单，让修改生效
        // });

    }
    init();
});






