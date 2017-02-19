package com.mcin.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**
 * @project 项目:<meike-sister>
 * @class: 类名:<DateConverter.java>
 * @author 创建人:<Licn>
 * @date 创建时间:<2016年11月14日 下午3:21:56>
 * @comments: 说明:<日期工具类>
 */
public class DateConverter implements WebBindingInitializer {

	public void initBinder(WebDataBinder binder, WebRequest request) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		dateFormat.setLenient(false);

		SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		datetimeFormat.setLenient(false);

		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));

		//binder.registerCustomEditor(java.sql.Timestamp.class,new CustomTimestampEditor(datetimeFormat, true));

		binder.registerCustomEditor(java.sql.Timestamp.class, new CustomDateEditor(datetimeFormat, true));
	}

}
