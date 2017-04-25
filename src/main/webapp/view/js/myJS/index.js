/**
 * Created by Mcin on 2017/4/16.
 */
$(function(){
    // ar infoId ; //信息表id
    // var userId ; //用户id


    /**
     * 个人登录进入控制面板
     */
    $('#toManage').click(function () {
        alert(123)
        var userId = "";

        $.ajax({
            type:'post',
            url: 'http://localhost:8000/mcinjob/info/toManage.json',
            dataType:"text",
            data:{'userId':userId},
            success:function(data){
                console.log(data);
            }
        });
    });



});