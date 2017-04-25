/**
 * Created by Mcin on 2017/3/9.
 */
$(function(){


    var emailReg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/; // 邮箱正则
    var phoneReg = /^1[3|4|5|7|8][0-9]\d{4,8}$/; // 正则验证手机号码
    /**
     * 注册的方法
     */
    $('#actionBtn').click(function () {
        var host = "http://localhost:8080/mcinjob/info/register.json"; // 要访问的url地址
        
        var email = $('#email').val();//邮箱地址
        var passWord = $('#passWord').val();//邮箱地址
        var userName = $('#userName').val();//填写姓名
        var birthday = $('#birthday').val();//生日
        var phone = $('#phone').val();//手机号码
        var education = $('#education').val();//学历
        var height = $('#height').val();//身高
        var weight = $('#weight').val();//体重
        var apartment = $('#apartment').val();//居住地
        var place = $('#place').val();//籍贯
        var personalWebsite = $('#personalWebsite').val();//个人网站
        var major = $('#major').val();//专业
        var graduationTime = $('#graduationTime').val(); //毕业时间
        var school = $('#school').val(); //毕业学校

        var isEmailOk= emailReg.test(email); // 邮箱匹配

        if (!isEmailOk){
            alert("邮箱不对劲");
            return false;
        } else if (passWord == ''){
            alert("输入密码");
            return false;
        }

        // else if (userName == ''){
        //     alert("填写姓名");
        //     return false;
        // } else if(birthday == ''){
        //     alert("填写生日");
        //     return false;
        // } else if (phone == ''){
        //     alert("填写手机号码") ;
        //     return false;
        // } else if(education == ''){
        //     alert("填写学历");
        //     return false;
        // }



            $.ajax({
                type:'post',
                url:host,
                dataType:"text",
                data:{'email':email,'passWord':passWord/*,'userName':userName,'birthday':birthday,'phone':phone,'education':education,'height':height,'weight':weight,
                    'apartment':apartment,'place':place,'personalWebsite':personalWebsite,'major':major,'graduationTime':graduationTime,'school':school*/},
                success:function(data){
                    alert(data);
                }
            });
    })
});