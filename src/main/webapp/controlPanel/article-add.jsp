<%--
  Created by IntelliJ IDEA.
  User: Mcin
  Date: 2017/4/13
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="/favicon.ico" >
<link rel="Shortcut Icon" href="/favicon.ico" />
<script type="text/javascript" src="${pageContext.request.contextPath}/controlPanel/lib/jquery/1.9.1/jquery.js"></script>
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<script type="text/javascript" src="${pageContext.request.contextPath}/controlPanel/static/myPackgre/js/manage.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/controlPanel/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/controlPanel/static/myPackgre/css/myStyle.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/controlPanel/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/controlPanel/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/controlPanel/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/controlPanel/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="${pageContext.request.contextPath}/controlPanel/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/controlPanel/static/myPackgre/js/manage.js"></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<!--/meta 作为公共模版分离出去-->
<title>个人信息管理</title>
</head>
<body>
<article class="page-container">
	<form class="form form-horizontal" id="form-article-add">

		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>姓名：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text-length input-text" value="" placeholder="" id="userName" name="articletitle"><%--id="articletitle"--%>
			</div>
		</div>

		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>学历：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<span class="input-text-length select-box">
					<select name="articlecolumn" id="education" class="select">
						<option value="大专">大专</option>
						<option value="本科">本科</option>
						<option value="研究生">研究生</option>
						<option value="其他">其他</option>
					</select>
				</span>
			</div>
		</div>

		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>籍贯：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<span class="input-text-length select-box">
					<select name="articletype"  id="place" class="select">
						<option value="0">全部类型</option>
						<option value="1">帮助说明</option>
						<option value="2">新闻资讯</option>
					</select>
				</span>
			</div>
		</div>

		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>身高：</label>
			<div class="formControls col-xs-8 col-sm-9" >
				<span class="input-text-length select-box">
					<select name="articletype" id="height" class="select">
						<option value="0">175</option>
					</select>
				</span>
			</div>
		</div>

		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>生日：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="date" id="birthday" name="commentdatemax" class="input-text-length input-text Wdate"> <%--id="commentdatemax"--%>
			</div>
		</div>

		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>邮箱：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text-length input-text" value="" placeholder="" id="email" name="keywords"><%--id="keywords" --%>
			</div>
		</div>

		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>薪资：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<span class="input-text-length select-box">
					<select name="articletype"  id="wages" class="select">
						<option value="1000">10k</option>
					</select>
				</span>
			</div>
		</div>

		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>个人网站：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text-length input-text" value="" placeholder="" id="personalWebsite" name="keywords"><%--id="keywords" --%>
			</div>
		</div>

		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>项目库存：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text-length input-text" value="" placeholder="" id="openName" name="keywords"><%--id="keywords" --%>
			</div>
		</div>

		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>项目地址：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text-length input-text" value="" placeholder="" id="openSource" name="keywords"><%--id="keywords" --%>
			</div>
		</div>

		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>现居住地：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text-length input-text" value="0" placeholder="" id="apartment" name="sources">
			</div>
		</div>

		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>手机号码：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="tel" class="input-text-length input-text" value="" placeholder="" id="phone" name="articletitle2"><%-- id="articletitle2" --%>
			</div>
		</div>

		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>毕业学校：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text-length input-text" value="0" placeholder="" id="school" name="sources">
			</div>
		</div>

		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>所学专业：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text-length input-text" value="0" placeholder="" id="major" name="sources">
			</div>
		</div>

		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>应聘岗位：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<span class="input-text-length select-box">
					<select name="articletype" id="jobPosition" class="select">
						<option value="0">全部类型</option>
						<option value="1">帮助说明</option>
						<option value="2">新闻资讯</option>
					</select>
				</span>
			</div>
		</div>

		<div class="row cl" >
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>毕业时间：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="date" id="graduationTime" name="commentdatemin" class="input-text-length input-text Wdate"><%--commentdatemin--%>
			</div>
		</div>

		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<%--<button onClick="article_save_submit();" class="btn btn-primary radius" type="submit"><i class="Hui-iconfont">&#xe632;</i> 保存信息</button>--%>
					<button id="saveSubmit" class="btn btn-primary radius" type="button"><i class="Hui-iconfont">&#xe632;</i> 保存信息</button>

				<%--<button onClick="article_save();" class="btn btn-secondary radius" type="button"><i class="Hui-iconfont">&#xe632;</i> 保存草稿</button>--%>
				<button onClick="removeIframe();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
		</div>
	</form>
</article>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${pageContext.request.contextPath}/controlPanel/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/controlPanel/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/controlPanel/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/controlPanel/static/h-ui.admin/js/H-ui.admin.js"></script>
<!--/_footer /作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<%--
<script type="text/javascript" src="lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript" src="lib/webuploader/0.1.5/webuploader.min.js"></script> 
<script type="text/javascript" src="lib/ueditor/1.4.3/ueditor.config.js"></script> 
<script type="text/javascript" src="lib/ueditor/1.4.3/ueditor.all.min.js"> </script> 
<script type="text/javascript" src="lib/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script>
--%>

</body>
</html>