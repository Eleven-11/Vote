/**
 * api接口列表
 * Created by gameloft9 on 2018/7/19.
 */
layui.define(['$tool','jquery'], function (exports) {

    var $tool = layui.$tool,
        $ = layui.jquery;// 拿到模块变量

    /**
     * 封装一个post
     * */
    function doPost(url,req,successCallback,errorCallback) {
        $.ajax({
            url:url,
            data:req,
            method:"post",
            success:function (data) {
                successCallback(data);
            },
            error:function (error) {
                errorCallback(error);
            }
        });
    }

    /**
     * 封装一个get
     * */
    function doGet(url,req,successCallback,errorCallback) {
        $.ajax({
            url:url,
            data:req,
            method:"get",
            success:function (data) {
                successCallback(data);
            },
            error:function (error) {
                errorCallback(error);
            }
        });
    }

    /**
     * 封装一个支持更多参数的post
     * */
    function doComplexPost(url,req,config,successCallback,errorCallback) {
        var defaultConfig = {
            url:url,
            data:req,
            method:"post",
            success:function (data) {
                successCallback(data);
            },
            error:function (error) {
                errorCallback(error);
            }
        };
        var ajaxConfig = $.extend({},config,defaultConfig); // 合并参数

        $.ajax(ajaxConfig);
    }

    // API列表,工程庞大臃肿后可以将API拆分到单独的模块中
    var API = {
        Login: function(req,successCallback,errorCallback){ // 登录
            doPost($tool.getContext() + "login",req,successCallback,errorCallback);
        },
        LogOut:function(req,successCallback,errorCallback){ // 登出
            doPost($tool.getContext() + 'logout.do',req,successCallback,errorCallback);
        },
        ChangePwd:function(req,successCallback,errorCallback){ // 更改密码
            doPost($tool.getContext() + 'personCenter/changePwd.do',req,successCallback,errorCallback);
        },
        GetRoleList:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'personCenter/roleList.do',req,successCallback,errorCallback);
        },
        Verification:function(req,successCallback,errorCallback){
            doComplexPost($tool.getContext() + 'photoVote/verification.do',req,successCallback,errorCallback);
        },
        GetPhotoUserList:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'photoUser/photoUserList.do',req,successCallback,errorCallback);
        },
        GetStudentList:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'student/studentList.do',req,successCallback,errorCallback);
        },
        DeleteLog:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'log/delete.do',req,successCallback,errorCallback);
        },
        BatchDeleteLog:function(req,config,successCallback,errorCallback){
            doComplexPost($tool.getContext() + 'log/batchDelete.do',req,config,successCallback,errorCallback);
        },
        GetFirstClassMenus:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'menu/firstClassMenus.do',req,successCallback,errorCallback);
        },
        GetFirstPhoto:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'photoUser/find.do',req,successCallback,errorCallback);
        },
        //有请求方式和请求路径
        AddMenu:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'menu/add.do',req,successCallback,errorCallback);
        },
        DeleteMenu:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'menu/delete.do',req,successCallback,errorCallback);
        },
        GetMenu:function(req,successCallback,errorCallback){
            doPost($tool.getContext()+'menu/get.do',req,successCallback,errorCallback);
        },
        GetStudent:function(req,successCallback,errorCallback){
            doPost($tool.getContext()+'student/get.do',req,successCallback,errorCallback);
        },
        GetPhotoUser:function(req,successCallback,errorCallback){
            doPost($tool.getContext()+'photoUser/get.do',req,successCallback,errorCallback);
        },
        GetPhotoVote:function(req,successCallback,errorCallback){
            doPost($tool.getContext()+'photoVote/get.do',req,successCallback,errorCallback);
        },
        GetPhotoModel:function(req,successCallback,errorCallback){
            doPost($tool.getContext()+'photoModel/get.do',req,successCallback,errorCallback);
        },
        UpdateMenu:function(req,config,successCallback,errorCallback){
            doComplexPost($tool.getContext()+'menu/update.do',req,config,successCallback,errorCallback);
        },
        UpdateStudent:function(req,config,successCallback,errorCallback){
            doComplexPost($tool.getContext()+'student/update.do',req,config,successCallback,errorCallback);
        },
        UpdatePhotoUser:function(req,config,successCallback,errorCallback){
            doComplexPost($tool.getContext()+'photoUser/update.do',req,config,successCallback,errorCallback);
        },
        GetAllOrg:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'org/getAll.do',req,successCallback,errorCallback);
        },
        GetallOrg:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'depart/All.do',req,successCallback,errorCallback);
        },
        GetOrg:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'org/get.do',req,successCallback,errorCallback);
        },
        Getorg:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'depart/get.do',req,successCallback,errorCallback);
        },
        AddOrg:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'org/add.do',req,successCallback,errorCallback);
        },
        Addorg:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'depart/add.do',req,successCallback,errorCallback);
        },
        UpdateOrg:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'org/update.do',req,successCallback,errorCallback);
        },
        Updateorg:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'depart/update.do',req,successCallback,errorCallback);
        },
        DeleteOrg:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'org/delete.do',req,successCallback,errorCallback);
        },
        Deleteorg:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'depart/delete.do',req,successCallback,errorCallback);
        },
        AddRole:function(req,successCallback,errorCallback){
            doPost($tool.getContext()+'role/add.do',req,successCallback,errorCallback);
        },
        DeleteRole:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'role/delete.do',req,successCallback,errorCallback);
        },
        GetRole:function(req,successCallback,errorCallback){
            doPost($tool.getContext()+'role/get.do',req,successCallback,errorCallback);
        },
        UpdateRole:function(req,successCallback,errorCallback){
            doPost($tool.getContext()+'role/update.do',req,successCallback,errorCallback);
        },
        AddDemo:function(req,config,successCallback,errorCallback){
            doComplexPost($tool.getContext() + 'record/add.do',req,config,successCallback,errorCallback);
        },
        Addtemp:function(req,config,successCallback,errorCallback){
            doComplexPost($tool.getContext() + 'template/add.do',req,config,successCallback,errorCallback);
        },
        Deletedemo:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'record/delete.do',req,successCallback,errorCallback);
        },
        Deletetemp:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'template/delete.do',req,successCallback,errorCallback);
        },
        qxzd:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'template/qxzd.do',req,successCallback,errorCallback);
        },
        zd:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'template/zd.do',req,successCallback,errorCallback);
        },
        Guanbidemo:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'record/guanbi.do',req,successCallback,errorCallback);
        },
        jiaoyan:function(req,config,successCallback,errorCallback){
            doComplexPost($tool.getContext() + 'record/jiaoyan.do',req,config,successCallback,errorCallback);
        },
        Gettemp:function(req,successCallback,errorCallback){
            doPost($tool.getContext()+'template/get.do',req,successCallback,errorCallback);
        },
        Getrecord:function(req,successCallback,errorCallback){
            doPost($tool.getContext()+'record/get.do',req,successCallback,errorCallback);
        },
        Updatedemo:function(req,successCallback,errorCallback){
            doPost($tool.getContext()+'record/update.do',req,successCallback,errorCallback);
        },
        TijiaoDemo:function(req,config,successCallback,errorCallback){
            doComplexPost($tool.getContext()+'record/submit.do',req,config,successCallback,errorCallback);
        },
        AddUser:function(req,config,successCallback,errorCallback){
            doComplexPost($tool.getContext() + 'sysUser/add.do',req,config,successCallback,errorCallback);
        },
        AddVote:function(req,config,successCallback,errorCallback){
            doComplexPost($tool.getContext() + 'photoVote/votePhoto.do',req,config,successCallback,errorCallback);
        },
        VoteSuccess:function(req,config,successCallback,errorCallback){
            doComplexPost($tool.getContext() + 'photoVote/voteSuccess.do',req,config,successCallback,errorCallback);
        },
        AddModelVote:function(req,config,successCallback,errorCallback){
            doComplexPost($tool.getContext() + 'photoModel/votePhoto.do',req,config,successCallback,errorCallback);
        },
        AddModel:function(req,config,successCallback,errorCallback){
            doComplexPost($tool.getContext() + 'photoModel/modelPhoto.do',req,config,successCallback,errorCallback);
        },
        AddStudent:function(req,config,successCallback,errorCallback){
            doComplexPost($tool.getContext() + 'student/add.do',req,config,successCallback,errorCallback);
        },
        AddPhotoUser:function(req,config,successCallback,errorCallback){
            doComplexPost($tool.getContext() + 'photoUser/add.do',req,config,successCallback,errorCallback);
        },
        DeleteUser:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'sysUser/delete.do',req,config,successCallback,errorCallback);
        },
        DeleteVote:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'photoVote/delete.do',req,config,successCallback,errorCallback);
        },
        DeleteModel:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'photoModel/delete.do',req,config,successCallback,errorCallback);
        },
        CancelVote:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'photoVote/cancel.do',req,config,successCallback,errorCallback);
        },
        CancelModel:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'photoModel/cancel.do',req,config,successCallback,errorCallback);
        },
        TopModel:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'photoModel/top.do',req,config,successCallback,errorCallback);
        },
        PhotoUser:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'photoUser/deleteUser.do',req,config,successCallback,errorCallback);
        },
        AddMoreUser:function(req,config,successCallback,errorCallback){
            doComplexPost($tool.getContext() + 'photoUser/fileUpload.do',req,config,successCallback,errorCallback);
        },
        DeleteStudent:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'student/delete.do',req,config,successCallback,errorCallback);
        },
        InitPwd:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'Template/update3',req,successCallback,errorCallback);
        },
        GetUser:function(req,successCallback,errorCallback){
            doPost($tool.getContext()+'sysUser/get.do',req,successCallback,errorCallback);
        },
        UpdateUser:function(req,config,successCallback,errorCallback){
            doComplexPost($tool.getContext() + 'sysUser/update.do',req,config,successCallback,errorCallback);
        },
        GetUserInfo:function(req,successCallback,errorCallback){
            doPost($tool.getContext()+'personCenter/get.do',req,successCallback,errorCallback);
        },
        UpdateUserInfo:function(req,config,successCallback,errorCallback){
            doComplexPost($tool.getContext() + 'personCenter/update.do',req,config,successCallback,errorCallback);
        },
        DeleteStatus:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'WJ/delete.do',req,config,successCallback,errorCallback);
        },
        UpdateStatus:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'WJ/update.do',req,config,successCallback,errorCallback);
        },

        Add:function(req,config,successCallback,errorCallback){
            doComplexPost($tool.getContext() + 'WJ/add.do',req,config,successCallback,errorCallback);
        },
        Add1:function(req,config,successCallback,errorCallback){
            doComplexPost($tool.getContext() + 'WJ/add1.do',req,config,successCallback,errorCallback);
        },
        Account:function(req,config,successCallback,errorCallback){
            doComplexPost($tool.getContext() + 'WJ/Account.do',req,config,successCallback,errorCallback);
        },
        CCC:function(req,config,successCallback,errorCallback){
            doComplexPost($tool.getContext() + 'WJ/Tijiao.do',req,config,successCallback,errorCallback);
        },
        UpdateVote:function(req,config,successCallback,errorCallback){
            doComplexPost($tool.getContext() + 'WJ/updateVote.do',req,config,successCallback,errorCallback);
        },
        Baocun:function(req,config,successCallback,errorCallback){
            doComplexPost($tool.getContext() + 'Template/add2.do',req,config,successCallback,errorCallback);
        },
        UpdateRoofPlacement:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'Template/update1.do',req,config,successCallback,errorCallback);
        },
        UpdateRoofPlacement1:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'Template/update2.do',req,config,successCallback,errorCallback);
        },
        DeleteT:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'Template/delete2.do',req,config,successCallback,errorCallback);
        },
        GetTitle:function(req,successCallback,errorCallback){
            doPost($tool.getContext()+'Template/getAll.do',req,successCallback,errorCallback);
        },
        GetAll:function(req,successCallback,errorCallback){
            doPost($tool.getContext()+'WJ/Get.do',req,successCallback,errorCallback);
        },
        Count:function(req,successCallback,errorCallback){
            doPost($tool.getContext()+'WJ/Count.do',req,successCallback,errorCallback);
        },
        UpdateTemplate:function(req,config,successCallback,errorCallback){
            doComplexPost($tool.getContext() + 'Template/update3.do',req,config,successCallback,errorCallback);
        },
    };




    //输出扩展模块
    exports('$api', API);
});


