/**
 * Created by Mcin on 2017/4/15.
 */


$(function(){
    // ar infoId ; //信息表id
    // var userId ; //用户id


    /**
     * 点击保存资料的按钮
     */
        $('#saveSubmit').click(function () {
alert(213);

        var protocol = window.location.protocol;//当前url协议
        var host = window.location.host; // 获取当前服务器和端口
        var pathname = window.location.pathname; //获取当前项目名
        var url =protocol+'//'+host+pathname;

        var  phone = $('#phone').val(); //手机号码
        var email = $('#email').val();// 邮箱地址
        var jobPosition = $('#jobPosition').val(); //应聘岗位
        var wages = $('#wages').val();//薪资
        var openSource = $('#openSource').val();// //项目地址
        var openName = $('#openName').val(); //项目存放的位置  github || osGtChina
        var userName = $('#userName').val();// 用户的姓名
        var birthday = $('#birthday').val(); //生日
        var education = $('#education').val(); //学历
        var height = $('#height').val(); //身高
        // var weight = $('').val(); //体重
        var apartment = $('#apartment').val() ;// 居住地
        var place = $('#place').val(); //籍贯
        var personalWebsite = $('#personalWebsite').val(); //个人网站
        var major = $('#major').val(); //专业
        var graduationTime = $('#graduationTime').val(); //毕业时间
        var school = $('#school').val(); //毕业学校

        $.ajax({
            type:'post',
            url: 'http://localhost:8000/mcinjob/info/updateInfo.json',
            dataType:"text",
            data:{'email':email,'phone':phone,'jobPosition':jobPosition,'wages':wages,'openSource':openSource,'openName':openName,
                'userName':userName,'birthday':birthday,'education':education,'height':height,'apartment':apartment,'place':place,
                'personalWebsite':personalWebsite,'major':major,'graduationTime':graduationTime,'school':school},



            success:function(data){
                console.log(data);
            }
        });









    });



});

/**
 * 获取服务器地址
 * @returns {string}
 */
function getUrl() {
    var protocol = window.location.protocol;//当前url协议
    var host = window.location.host; // 获取当前服务器和端口
    var pathname = window.location.pathname; //获取当前项目名
    var url =protocol+'//'+host+pathname;
    return url;
}