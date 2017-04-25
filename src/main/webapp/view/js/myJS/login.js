/**
 * 登录模块使用的脚本
 * Created by Mcin on 2017/3/10.
 * @type {RegExp}
 */

var emailReg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/; // 邮箱正则
var phoneReg = /^1[3|4|5|7|8][0-9]\d{4,8}$/; // 正则验证手机号码
var strReg = /(^\s*)|(\s*$)/g; // 验证空格


$(function(){
    // strings.replace(/(^s*)|(s*$)/g, "").length ==0

    /**
     * 注册的方法
     */
    $('#registerBtn').click(function () {

        var protocol = window.location.protocol;//当前url协议
        var host = window.location.host; // 获取当前服务器和端口
        var pathname = window.location.pathname; //获取当前项目名
        var url =protocol+'//'+host+pathname;
        // var host = "http://localhost:8080/ssmr/home/register.json"; // 要访问的url地址

        var email = $('#registerEmail').val();//邮箱地址
        var passWord = $('#registerPassWord').val();//密码
        var isEmailOk = emailReg.test(email); // 邮箱匹配
        if (!isEmailOk){
            alert("邮箱不对劲");
            return false;
        } else if (passWord == ''){
            alert("输入密码");
            return false;
        }

        $.ajax({
            type:'post',
            url:url+'home/register.json',
            dataType:"text",
            data:{'email':email,'passWord':passWord},
            success:function(data){
                console.log(data);
                if (data == 'errorEmail'){
                    alert("账号或密码格式不正确");
                } else if (data == 'exist'){
                    alert("该邮箱已被注册");
                } else if (data == 'registerOk'){
                    alert("注册成功，请登录邮箱 : "+email+" 进行激活吧");
                } else {
                    alert("注册失败");
                }
            }
        });
    });

    /**
     * 登录
     */
    $('#userLogin').click(function () {

        var protOcol = window.location.protocol;//当前url协议
        var host = window.location.host; // 获取当前服务器和端口
        var pathName = window.location.pathname; //获取当前项目名
        var url =protOcol+'//'+host+pathName;

        var loginUrl = "home/login.json"; // 登录要跳转的连接

        // var host = "http://localhost:8080/ssmr/home/register.json"; // 要访问的url地址



        var loginName = $('.loginName').attr("id");
        var loginPwd = $('.loginPwd').attr("id");
        alert(loginName);

        // 企业登录
        if ( loginName == "username" && loginPwd == "account") {
            var username = $('#username').val();//邮箱地址
            var account = $('#account').val();//密码

            if (username.replace(strReg,"").length != 0 && account.replace(strReg,"").length != 0){
                var radioName = "loginType";
                var loginType = radioSelect(radioName);
                $.ajax({
                    type:'post',
                    url:url+loginUrl,
                    dataType:"text",
                    data:{'loginType':loginType,"comPany":username,"account":account},
                    success:function(data){
                        var data_ =  JSON.parse(data);
                        if (data_.sessus == 'comOk'){
                            post(url+data_.url, {'loginType':loginType,"comPany":username,"account":account,"loginId":data_.resultStr,"login":data_.login});
                        } else if(data_.sessus == 'comPanyErrorAccount'){
                            alert(username + "__" + loginType+" 您好：登录的手机或者邮件输入有误");
                        }
                    }
                });
            } else {
                alert("请正确填写登录数据！");
                return false;
            }

            // 个人登录
        } else if ( loginName == "meLoginName" && loginPwd == "meLoginPassWord"){
            var email = $('#meLoginName').val();
            var passWord = $('#meLoginPassWord').val();
            if (email.replace(strReg,"").length != 0 && passWord.replace(strReg,"").length != 0){
                $.ajax({
                    type:'post',
                    url:url+loginUrl,
                    dataType:"text",
                    data:{"email":email,"passWord":passWord},
                    success:function(data){
                        var data_ = JSON.parse(data);
                        if (data_.sessus == 'meOk'){
                            if (data_.firstLogin == 1){
                                alert("邮箱为"+email+" 用户您好，感谢您注册， 这是您第一次登陆");
                            }
                            post(url+data_.url, {"email":email,"passWord":passWord,"loginId":data_.resultStr,"login":data_.login,"firstLogin":data_.firstLogin});
                        } else if(data_.sessus == 'errorAccount'){
                            alert("输入的账号密码有误,每天只能登录错误密码五次");
                        } else if (data_.sessus == 'errorPassWordCount'){
                            alert("该账号密码错误次数已经达到五次了");
                        }
                    }
                });
            } else {
                alert("请正确填写登录数据！");
                return false;
            }
        } else {
            alert("请正确填写登录数据！");
            return false;
        }
    });


});

/**
 *  验证input 去除空格后是否为空
 * @param str
 * @returns {number}
 */
function strSpacing(str) {
    // var strReg = /(^\s*)|(\s*$)/g; // 验证空格
    var result = 0;
    if (str.replace(strReg,"").length != 0){
        result = 1;
        return result;
    }
    return result;
}


/**
 * radio 选择 value
 * @author mcin
 * @Date 2017年4月6日10:24:42
 * @param radioName
 * @returns {*}
 */
function radioSelect (radioName) {
    var radioV;
    var radio = document.getElementsByName(radioName);
    if (radio.length > 0){
        for (i=0; i<radio.length; i++) {
            if (radio[i].checked) {
                // alert(radio[i].value);
                radioV = radio[i].value;
            }
        }
    } else {
        return null;
    }
    return radioV;
}


/**
 * js post提交
 * @author mcin
 * @Date 2017年4月6日10:24:42
 * @param url
 * @param params
 * @returns {Element}
 */
function post(url, params) {
    var postData = document.createElement("form");
    postData.action = url;
    postData.method = "post";
    postData.style.display = "none";
    for (var x in params) {
        var opt = document.createElement("input");
        opt.name = x;
        opt.value = params[x];
        postData.appendChild(opt);
    }
    document.body.appendChild(postData);
    postData.submit();
    return postData;
}

// //调用方法 如
// post('pages/statisticsJsp/excel.action', {html :prnhtml,cm1:'sdsddsd',cm2:'haha'});