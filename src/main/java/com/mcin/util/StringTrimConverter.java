package com.mcin.util;

import org.springframework.core.convert.converter.Converter;
/**
 * @project 项目:<meike-sister>
 * @class: 类名:<StringTrimConverter.java>
 * @author 创建人:<Licn>
 * @date 创建时间:<2016年11月14日 下午3:21:56>
 * @comments: 说明:<日期工具类>
 */
public class StringTrimConverter implements Converter<String, String> {

	public String convert(String source) {
		//如果源字符串不为空则进行转换
		if(source != null){
			//去除源字符串前后空格
			source = source.trim();
			if(source.equals("")){ 
				source = null;
			}
		}
		return source;
	}
}
