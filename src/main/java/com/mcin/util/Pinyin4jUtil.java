package com.mcin.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.*;

/**
 * @project 项目:<meike-web>
 * @class 类名:<Pingyin4Util>
 * @author 创建人:<Chenmq>
 * @date 创建时间:<2017上午10:21:36>
 * @comments: 说明:<TODO>
 */
public class Pinyin4jUtil {
	/** 
	 * 汉字转换位汉语拼音首字母，英文字符不变，特殊字符丢失 支持多音字，生成方式如（长沙市长:cssc,zssz,zssc,cssz） 
	 *  
	 * @param chines 
	 *            汉字 
	 * @return 拼音 
	 */  
	public static String converterToFirstSpell(String chines) {  
		StringBuffer pinyinName = new StringBuffer();  
		char[] nameChar = chines.toCharArray();  
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();  
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
		for (int i = 0; i < nameChar.length; i++) {  
			if (nameChar[i] > 128) {  
				try {  
					// 取得当前汉字的所有全拼  
					String[] strs = PinyinHelper.toHanyuPinyinStringArray(  
							nameChar[i], defaultFormat);  
					if (strs != null) {  
						for (int j = 0; j < strs.length; j++) {  
							// 取首字母  
							pinyinName.append(strs[j].charAt(0));  
							if (j != strs.length - 1) {  
								pinyinName.append(",");  
							}  
						}  
					}  
					// else {  
					// pinyinName.append(nameChar[i]);  
					// }  
				} catch (BadHanyuPinyinOutputFormatCombination e) {  
					e.printStackTrace();  
				}  
			} else {  
				pinyinName.append(nameChar[i]);  
			}  
			pinyinName.append(" ");  
		}  
		// return pinyinName.toString();  
		return parseTheChineseByObject(discountTheChinese(pinyinName.toString()));  
	}  

	/** 
	 * 汉字转换位汉语全拼，英文字符不变，特殊字符丢失 
	 * 支持多音字，生成方式如（重当参:zhongdangcen,zhongdangcan,chongdangcen 
	 * ,chongdangshen,zhongdangshen,chongdangcan） 
	 *  
	 * @param chines 
	 *            汉字 
	 * @return 拼音 
	 */  
	public static String converterToSpell(String chines) { 
		if (chines.replace(" ", "").length() > 0) {
			StringBuffer pinyinName = new StringBuffer();  
			char[] nameChar = chines.toCharArray();  
			HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();  
			defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
			defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
			for (int i = 0; i < nameChar.length; i++) {  
				if (nameChar[i] > 128) {  
					try {  
						// 取得当前汉字的所有全拼  
						String[] strs = PinyinHelper.toHanyuPinyinStringArray(  
								nameChar[i], defaultFormat);  
						if (strs != null) {  
							for (int j = 0; j < strs.length; j++) {  
								pinyinName.append(strs[j]);  
								if (j != strs.length - 1) {  
									pinyinName.append(",");  
									System.out.println(pinyinName+""+j);
								}  
							}  
						}  
					} catch (BadHanyuPinyinOutputFormatCombination e) {  
						e.printStackTrace();  
					}  
				} else {  
					pinyinName.append(nameChar[i]);  
				}  
				pinyinName.append(" ");  
				pinyinName.append("_");  
//				System.out.println("pinyinName"+pinyinName);
			}  
			// return pinyinName.toString();  
			List<Map<String, Integer>> list = discountTheChinese(pinyinName.toString().substring(0, pinyinName.toString().length() - 1));
			String s = parseTheChineseByObject(list);
			return s;
			//            return parseTheChineseByObject(discountTheChinese(pinyinName.toString());  

		} else {
			return "";
		}


	}  

	/** 
	 * 去除多音字重复数据 
	 *  
	 * @param theStr 
	 * @return 
	 */  
	private static List<Map<String, Integer>> discountTheChinese(String theStr) {  
		// 去除重复拼音后的拼音列表  
		List<Map<String, Integer>> mapList = new ArrayList<Map<String, Integer>>();  
		// 用于处理每个字的多音字，去掉重复  
		Map<String, Integer> onlyOne = null;  
		String[] firsts = theStr.split(" ");  
		// 读出每个汉字的拼音  
		for (String str : firsts) {  
			onlyOne = new Hashtable<String, Integer>();  
			String[] china = str.split(",");  
			// 多音字处理  
			for (String s : china) {  
				Integer count = onlyOne.get(s);  
				if (count == null) {  
					onlyOne.put(s, new Integer(1));  
				} else {  
					onlyOne.remove(s);  
					count++;  
					onlyOne.put(s, count);  
				}  
			}  
			mapList.add(onlyOne);  
		}  
		return mapList;  
	}  

	/** 
	 * 解析并组合拼音，对象合并方案
	 *  
	 * @return 
	 */  
	private static String parseTheChineseByObject(  
			List<Map<String, Integer>> list) {  
		Map<String, Integer> first = null; // 用于统计每一次,集合组合数据  
		// 遍历每一组集合  
		for (int i = 0; i < list.size(); i++) {  
			// 每一组集合与上一次组合的Map  
			Map<String, Integer> temp = new Hashtable<String, Integer>();  
			// 第一次循环，first为空  
			if (first != null) {  
				// 取出上次组合与此次集合的字符，并保存  
				for (String s : first.keySet()) {  
					for (String s1 : list.get(i).keySet()) {
						String str = s + s1;  
						temp.put(str, 1);  
					}  
				}  
				// 清理上一次组合数据  
				if (temp != null && temp.size() > 0) {  
					first.clear();  
				}  
			} else {  
				for (String s : list.get(i).keySet()) {  
					String str = s;  
					temp.put(str, 1);  
				}  
			}  
			// 保存组合数据以便下次循环使用  
			if (temp != null && temp.size() > 0) {  
				first = temp;  
			}  
		}  
		String returnStr = "";  
		if (first != null) {  
			// 遍历取出组合字符串  
//			for (String str : first.keySet()) {
//				returnStr += (str + ",");
//
//			}
			List listKey = new ArrayList();
			List listValue = new ArrayList();
			Iterator it = first.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next().toString();
				listKey.add(key);
				listValue.add(first.get(key));
			}

//			for (int ii=0,ii<listKey.size(),ii++){
//
//			}
			returnStr +=  listKey.get(1);
//			for (int i = 0; i < listKey.size(); i++) {
//				if (i==1){
//					returnStr +=  listKey.get(1);
//				}
//			}


		}  
		if (returnStr.length() > 0) {  
			returnStr = returnStr.substring(0, returnStr.length() - 1);  
		}  
		return returnStr;  
	}

	
	public static void main(String[] args) {
		String str = "🍦你好你好🍦🍦";
//		System.out.println(getPingYin(str));
		String pinyin = Pinyin4jUtil.converterToSpell(str.trim());  
		System.out.println(str+" pin yin ："+pinyin.toUpperCase());
//		pinyin = Pinyin4jUtil.converterToFirstSpell(str);
//		System.out.println(str+" short pin yin ："+pinyin);
	}
}
