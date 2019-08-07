layui.config({
    base: $config.resUrl+'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention: 'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool: 'tool',
    $api:'api'
}).use(['form', 'layer', 'tree','$api', 'jquery', 'ajaxExtention', '$tool'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery,
        $tool = layui.$tool,
        $api = layui.$api;

    var orgId;
    var orgName;
    var roleIdList = new Array();//所有的角色id列表

    /**
     * 页面初始化
     * */
    function init() {
        //初始化机构树
        initOrgTree();
        //加载角色列表
        loadRoleList();
    }

    init();

    /**
     * 初始化组织机构树
     * */
    function initOrgTree() {
        //获取所有组织机构树

        $api.GetAllOrg(null,function (res) {
            renderTree('#org-tree', res.data);
        });

    }

    /**
     * 绘制树
     * @param id dom id
     * @param nodes 树节点数据
     * */
    function renderTree(id, nodes) {
        //绘制前先清空
        $(id).empty();

        //绘制
        layui.tree({
            elem: id
            , nodes: nodes
            , click: function (node) {//显示组织机构数据
                console.log(node); //node即为当前点击的节点数据
                orgId = node.id;//保存机构id
                orgName = node.name;
            }
        });
    }

    /**
     * 加载角色列表
     * */
    function loadRoleList() {
        var req = {
            page: 1,
            limit: 999
        };


        $api.GetRoleList(req,function (res) {
            var data = res.data;
            if (data.length > 0) {
                var roleHtml = "";
                for (var i = 0; i < data.length; i++) {
                    roleHtml += '<input type="checkbox" name="' + data[i].id + '" title="' + data[i].roleName + '">';
                    roleIdList.push(data[i].id);//保存id列表
                }

                $('.role-check-list').append($(roleHtml));
                form.render();//重新绘制表单，让修改生效
            }
        });
    }

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
        var aa={
            sum:count
        }
        var bb =   JSON.stringify(aa);
        window.sessionStorage.setItem("bb", bb);
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
                    content: "Account.html",
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
                //刷新父页面
                // parent.location.reload();
            });
        });

        return false;
    })




    /**
     * 存为模板
     * */
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

    /**
     * 预览
     * */
    form.on("submit(yul)", function (data) {



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
            questionList:list,

        };
        var aa =   JSON.stringify(req);
        window.sessionStorage.setItem("aa", aa);
        var index = layui.layer.open({
            title: "预览",
            type: 2,
            content: "addCheck.html",
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


