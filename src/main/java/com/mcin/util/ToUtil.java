package com.mcin.util;

import com.mcin.handler.MultipartListenerResolver;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import redis.clients.jedis.Jedis;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Mcin on 2017/3/8.
 * 需要操作的工具
 */
public class ToUtil {

    public static final String EMPTY_STRING = "";

    /**
     * 身高
     * @return
     */
    public static List<Integer> heightList(){
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 130; i <= 190; i++) {
            list.add(i);
        }
        return list;
    }

    /**
     * 体重
     * @return
     */
    public static List<Integer> weightList(){
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 20; i <= 90; i++) {
            list.add(i);
        }
        return list;
    }

    /**
     * 56个民族
     * @return
     */
    public static List<String> nationArr(){
        String[] nationArr={"汉族","蒙古族","回族","藏族","维吾尔族","苗族","彝族","壮族","布依族","朝鲜族","满族","侗族","瑶族","白族","土家族",
                "哈尼族","哈萨克族","傣族","黎族","傈僳族","佤族","畲族","高山族","拉祜族","水族","东乡族","纳西族","景颇族","柯尔克孜族",
                "土族","达斡尔族","仫佬族","羌族","布朗族","撒拉族","毛南族","仡佬族","锡伯族","阿昌族","普米族","塔吉克族","怒族", "乌孜别克族",
                "俄罗斯族","鄂温克族","德昂族","保安族","裕固族","京族","塔塔尔族","独龙族","鄂伦春族","赫哲族","门巴族","珞巴族","基诺族"};

        List<String> list = Arrays.asList(nationArr);
        return list;
    }

    /**
     * 学历选择
     * @return
     */
    public static List<String> educationArr(){
        String[] educationArr = {"中专","大专","本科","双学位","硕士","博士","博士后","其他"};
        List<String> list = Arrays.asList(educationArr);
        return list;
    }

    /**
     * 血型
     * @return
     */
    public static List<String> boolArr(){
        String[] boolArr = {"A型","B型","AB型","O型","Rh阴性型","Rh阳性型"};
        List<String> list = Arrays.asList(boolArr);
        return list;
    }

    /**
     * 12星座
     * @return
     */
    public static List<String> signArr(){
        String[] signArr = {"魔羯座","水瓶座","双鱼座","牡羊座","金牛座","双子座","巨蟹座","狮子座","处女座","天秤座","天蝎座","射手座"};
        List<String> list = Arrays.asList(signArr);
        return list;
    }

    /**
     * 职业
     * @return
     */
    public static List<String> professionArr(){
        String[] professionArr = {"在校学生","计算机/互联网/IT","电子/半导体/仪表仪器","通信技术","销售","市场拓展 ","公关/商务 ","采购/贸易",
                "客户服务/技术支持 ","人力资源/行政/后勤","高级管理","生产/加工/制造","质控/安检","工程机械","技工","财会/审计/统计","金融/证券/投资/保险",
                "房地产/装修/物业","仓储/物流","普通劳动力/家政服务","普通服务行业","航空服务业","教育/培训","咨询/顾问","学术/科研","法律","设计/创意",
                "文学/传媒/影视","餐饮/旅游","化工","能源/地质勘查","医疗/护理","保健/美容","生物/制药/医疗器械","体育工作者","翻译","公务员/国家干部",
                "私营业主","农/林/牧/渔业","警察/其它","自由职业者","其它"};
        List<String> list = Arrays.asList(professionArr);
        return list;
    }

    /**
     * 发型
     * @return
     */
    public static List<String> hairdArr(){
        String[] hairdArr = {"顺直长发","卷曲长发","中等长度","短发","很短","光头","其他"};
        List<String> list = Arrays.asList(hairdArr);
        return list;
    }

    /**
     * 自评类型
     * @return
     */
    public static List<String> selfRateArr(){
        String[] selfRate = {"秀外慧中","眉清目秀","明眸善睐","娇小依人","青春活泼","成熟魅力","雍容华贵","淡雅如菊"};
        List<String> list = Arrays.asList(selfRate);
        return list;
    }

    /**
     * 魅力部位
     * @return
     */
    public static List<String> charmArr(){
        String[] charmArr = {"手","腿","脚","笑容","眉毛","眼睛","头发","鼻梁","嘴唇","牙齿","颈部","耳朵","胳膊","胸部","臀部","腰部","没有太特别的"};
        List<String> list = Arrays.asList(charmArr);
        return list;
    }

    /**
     * 脸型
     * @return
     */
    public static List<String> faceArr(){
        String[] faceArr = {"圆脸型","方脸型","长脸型","瓜子脸型","鸭蛋脸型","国字脸型","三角脸型","菱形脸型","保密"};
        List<String> list = Arrays.asList(faceArr);
        return list;
    }

    /**
     * 体型
     * @return
     */
    public static List<String> shapeArr(){
        String[] shapeArr = {"瘦","胖","较瘦","匀称","苗条","高挑","丰满","健壮","魁梧","较胖"};
        List<String> list = Arrays.asList(shapeArr);
        return list;
    }

    /**
     * 籍贯
     * @return
     */
    public static List<String> origoArr(){
        String[] origoArr = {};
        List<String> list = Arrays.asList(origoArr);
        return list;
    }

    /**
     * 胸围
     * @return
     */
    public static List<Integer> bustList(){
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 80; i <= 97; i++) {
            list.add(i);
        }
        return list;
    }

    /**
     * 腰围
     * @return
     */
    public static List<Integer> waistList(){
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 54; i <= 67; i++) {
            list.add(i);
        }
        return list;
    }

    /**
     * 腰围
     * @return
     */
    public static List<Integer> hipList(){
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 85; i <= 101; i++) {
            list.add(i);
        }
        return list;
    }

    /**
     * 支付类型提现类型
     * @return
     */
    public static List<String> accountType(){
        String[] accountTypeArr = {"支付宝","招商银行","中国银行","中信银行","交通银行","中国工商银行","中国建设银行 ","中国农业银行","中国民生银行","上海浦东发展银行"};
        List<String> list = Arrays.asList(accountTypeArr);
        return list;
    }

    /**
     * 拆分字符串
     * @param str 需要截取的字符串
     * @param arrLength 要截取字符串的长度
     * @return
     */
    public static String strSplit(String str,int arrLength){
        String[] resources=str.split(",");
        StringBuffer sb = new StringBuffer();
        if (resources.length > arrLength) {
            for (int i = 0; i < resources.length; i++) {
                sb.append(resources[i]+",");
                if (i == arrLength) {
                    break;
                }
            }
        }else{
            return str;
        }

        return sb.toString().substring(0,sb.toString().length()-1);
    }

    /**
     * ip 获取
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 得到类中所有集合的属性
     * @param obj
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @return map
     */
    public static Map getProperty (Object obj) throws InvocationTargetException, IllegalAccessException {

        //得到类对象
        Class<?> c = obj.getClass();
        Map map = new HashMap();
		/*
		 * 得到类中的所有属性集合
		 */
        Field[] fs = c.getDeclaredFields();
        for(int i = 0 ; i < fs.length; i++){
            Field f = fs[i];
            f.setAccessible(true); //设置些属性是可以访问的
            Object val = f.get(obj);//得到此属性的值

            //   System.out.println("name:"+f.getName()+"\t value = "+val);

            //			String type = f.getType().toString();//得到此属性的类型
            //			if (type.endsWith("String")) {
            //				System.out.println(f.getType()+"\t是String");
            // 				f.set(bean,"12") ;        //给属性设值
            //			}else if(type.endsWith("int") || type.endsWith("Integer")){
            //				System.out.println(f.getType()+"\t是int");
            // 				f.set(bean,12) ;       //给属性设值
            //			}else{
            //				System.out.println(f.getType()+"\t");
            //			}
            map.put(f.getName(), val);
        }
        LogInfo.info("类中属性和值 ： -- " + map);
        return map;
    }


    /**
     * 修改实体类熟悉 返回map 类型
     * @param obj
     * @param str
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static Map setModelName (Object obj,String str) throws InvocationTargetException, IllegalAccessException {

        //得到类对象
        Class<?> c = obj.getClass();
        Map map = new HashMap();
        Field[] fs = c.getDeclaredFields();
        for(int i = 0 ; i < fs.length; i++){
            Field f = fs[i];
            f.setAccessible(true); //设置些属性是可以访问的
            Object val = f.get(obj);//得到此属性的值
            map.put(str+"_"+f.getName(), val);
        }
        LogInfo.info("类中属性和值 ： -- " + map);
        return map;
    }


    /**
     * 修改实体类中属性 返回list类型
     * @param obj
     * @param str
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static List<?> setBeanName (Object obj,String str) throws InvocationTargetException, IllegalAccessException {
        //得到类对象
        Class<?> c = obj.getClass();
        List list = new ArrayList();

        //得到类中的所有属性集合
        Field[] fs = c.getDeclaredFields();
        for(int i = 0 ; i < fs.length; i++){
            Field f = fs[i];
            f.setAccessible(true); //设置些属性是可以访问的
            Object val = f.get(obj);//得到此属性的值
            list.add(str+"_"+f.getName());
        }
        return list;
    }



    /**
     * set对应属性的值
     * @param obj
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static Map setProperty (Object obj,Object value) throws InvocationTargetException, IllegalAccessException {

        //得到类对象
        Class<?> c = obj.getClass();
        Map map = new HashMap();
		/*
		 * 得到类中的所有属性集合
		 */
        Field[] fs = c.getDeclaredFields();
        for(int i = 0 ; i < fs.length; i++) {
            Field f = fs[i];
            f.setAccessible(true); //设置些属性是可以访问的
            Object val = f.get(obj);//得到此属性的值

            System.out.println("name:" + f.getName() + "\t value = " + val);

            String type = f.getType().toString();//得到此属性的类型
            f.set(c, value);
            if (type.endsWith("String")) {
                System.out.println(f.getType() + "\t是String");
                f.set(c, value);        //给属性设值
            } else if (type.endsWith("int") || type.endsWith("Integer")) {
                System.out.println(f.getType() + "\t是int");
                f.set(c, value);       //给属性设值
            } else if (type.endsWith("Date")) {
                System.out.println(f.getType() + "\tDate");
            }
            if (val != null) {
                map.put(f.getName(), val);
                LogInfo.info("set对应属性的值 ： -- " + map);
//            }
            }
        }

        return map;
    }

    /**
     * 得到类中的方法
     * @param obj
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @return map
     */
    public static Map getMothod (Object obj) throws InvocationTargetException, IllegalAccessException {
        //得到类对象
        Class<?> c = obj.getClass();
        Map map = new HashMap();
		/*
		 * 得到类中的方法
		 */
        Method[] methods = c.getMethods();
        for(int i = 0; i < methods.length; i++){
            Method method = methods[i];
            if(method.getName().startsWith("get")){
                //                    System.out.print("methodName:"+method.getName()+"\t");
                //                    System.out.println("value:"+method.invoke(obj));//得到get 方法的值
                map.put(method.getName(),method.invoke(obj));
            }
        }
        LogInfo.info("得到类中的方法 ： -- " + map);
        return map;
    }

    /**
     * 利用EnterySet迭代 遍历map
     * @param map
     */
    public void addRed(Map map){

        Iterator i=map.entrySet().iterator();
        //        System.out.println( map.entrySet().size());
        String key;
        String value;
        while(i.hasNext()){
            Map.Entry entry = (Map.Entry)i.next();
            key=entry.getKey().toString();
            value=entry.getValue().toString();
            //          System.out.println(key+"===="+value);
        }
    }
    /**
     * 获取key参数
     * @param param_map
     * @param name
     * @return
     */
    public static final String getParameter(Map<String, Object> param_map,
                                            String name) {
        String stringValue = EMPTY_STRING;
        if (null == param_map.get(name) || param_map.get(name).equals("")) {
            return stringValue.toString();
        }
        return param_map.get(name).toString();
    }


    /**
     * map 转 实体对象
     * @param map
     * @param obj
     * @return
     * @throws Exception
     */
    public static <T> T mapToBean(Map<String, Object> map, Class<T> obj) throws Exception {
        if (map == null) {
            return null;
        }
        Set<Entry<String, Object>> sets = map.entrySet();
        T t = obj.newInstance();
        Method[] methods = obj.getDeclaredMethods();
        for (Entry<String, Object> entry : sets) {
            String str = entry.getKey();
            String setMethod = "set" + str.substring(0, 1).toUpperCase() + str.substring(1);
            for (Method method : methods) {
                System.out.println("method.getName() == "+method.getName());
                if (method.getName().equals(setMethod)) {
                    if (null != entry.getValue() && !"".equals(entry.getValue())) {
                        method.invoke(t, entry.getValue());
                    }
                }
            }
        }
        return t;
    }

    /**
     * 反射获取属性类型
     * @param object
     * @return
     */
    public static Map getClassType (Object object){
        Class cls = null;  //com.geocompass.model.STSTBPRPModel

        Map<String ,Object> map = new HashMap<String ,Object>();

        try {
            cls = Class.forName(object.getClass().getName());
            Field[] fieldlist = cls.getDeclaredFields();
            for (int i = 0; i < fieldlist.length; i++) {
                Field fld = fieldlist[i];
//                            fieldHashMap.put(fld.getName(), fld.getType());
//                System.out.println("name = " + fld.getName());
//                System.out.println("decl class = " + fld.getDeclaringClass());
//                System.out.println("type = " + fld.getType().getName());
//                System.out.println("-----");
                map.put(fld.getName(),fld.getType().getName());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 将fromObject对象转换成toObjectClass类型的对象
     *
     * @param fromObject
     *            源对象
     * @param toObjectClass
     *            目标对象的类型
     * @return T 目标对象
     *
     */
    public static <T> T convert(Object fromObject, Class<T> toObjectClass) {

        ObjectMapper objectMapper = new ObjectMapper();
        // String json = objectMapper.writeValueAsString(fromObject);
        // obj = objectMapper.readValue(json, toObjectClass);

        return objectMapper.convertValue(fromObject, toObjectClass);
    }

//    /**
//     * 数据保存redis
//     * @param object
//     * @return
//     */
////    public static Integer setCache(Object object){
////
////        Integer result = 0;
////
////        if (object != null){
////            Map map = new HashMap();
////            Jedis jedis = jedisPool.getResource();
////            try {
////                map = ToUtil.getProperty(object);
////
////                Iterator it = map.entrySet().iterator();
////                String key = "";
////                String value = "";
////
////                while (it.hasNext()){
////                    Map.Entry entry = (Map.Entry)it.next();
////                    if (entry.getValue() !=null){
////                        key = entry.getKey().toString();
////                        value = entry.getValue().toString();
////                        jedis.set(key, value);
////                    }
////                }
////                result = EMUtil.USER_INFO.INSERT_OK.KEY;
////            } catch (InvocationTargetException e) {
////                e.printStackTrace();
////                result = EMUtil.USER_INFO.INSERT_DEFEATED.KEY;
////            } catch (IllegalAccessException e) {
////                e.printStackTrace();
////                result = EMUtil.USER_INFO.INSERT_DEFEATED.KEY;
////            } catch (Exception e){
////                e.printStackTrace();
////                result = EMUtil.USER_INFO.INSERT_DEFEATED.KEY;
////            } finally {
////                closeRedis(jedis);
////            }
////        }
////        return result;
////    }
//
//    public  Integer setCache(Object object,String str){
//
//        Integer result = 0;
//
//        if (object != null){
//            Map map = new HashMap();
//            Jedis jedis = jedisPool.getResource();
//            try {
//                map = ToUtil.setModelName(object,str);
//
//                Iterator it = map.entrySet().iterator();
//                String key = "";
//                String value = "";
//
//                while (it.hasNext()){
//                    Map.Entry entry = (Map.Entry)it.next();
//                    if (entry.getValue() != null) {
//                        key = entry.getKey().toString();
//                        value = entry.getValue().toString();
//                        jedis.set(key, value);
//                    }
//                }
//                result = EMUtil.USER_INFO.INSERT_OK.KEY;
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//                result = EMUtil.USER_INFO.INSERT_DEFEATED.KEY;
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//                result = EMUtil.USER_INFO.INSERT_DEFEATED.KEY;
//            } catch (Exception e){
//                e.printStackTrace();
//                result = EMUtil.USER_INFO.INSERT_DEFEATED.KEY;
//            } finally {
//                closeRedis(jedis);
//            }
//        }
//        return result;
//    }
//
//
//    /**
//     *  查询出redis的数据
//     * @return
//     */
////    public static Map getCache(Object object){
////        Map map = new HashMap();
////        Map mm = new HashMap();
////        Jedis jedis = jedisPool.getResource();
////        try {
////            map = ToUtil.getProperty(object);
////            //利用迭代 （Iterator）
////            Set set=map.entrySet();
////            Iterator iterator=set.iterator();
////            while(iterator.hasNext()){
////                Map.Entry<String, Object> enter=(Map.Entry<String, Object>) iterator.next();
////                LogInfo.info(jedis.get(enter.getKey()));
////                if (jedis.get(enter.getKey()) != null){
////                    mm.put(enter.getKey(),jedis.get(enter.getKey()));
////                    LogInfo.info(enter.getKey()+"\t"+enter.getValue());
////                }
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////            mm = null;
////        }finally{
////            closeRedis(jedis);
////        }
////        return mm;
////    }
//
//
//    public  Map getCache(Object object,String str){
//        Map map = new HashMap();
//        Map mm = new HashMap();
//        Jedis jedis = null;
//        jedis = jedisPool.getResource();
//        if (jedis != null){
//            try {
//                map = ToUtil.setModelName(object,str);
//                //利用迭代 （Iterator）
//                Set set = map.entrySet();
//                Iterator iterator = set.iterator();
//                while(iterator.hasNext()){
//                    Map.Entry<String, Object> enter = (Map.Entry<String, Object>) iterator.next();
//                    LogInfo.info(jedis.get(enter.getKey()));
//                    if (jedis.get(enter.getKey()) != null){
//                        String beanKey = sub_str(enter.getKey());
//                        mm.put(beanKey,jedis.get(enter.getKey()));
//                        LogInfo.info(enter.getKey()+"\t"+enter.getValue());
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                mm = null;
//            }finally{
//                closeRedis(jedis);
//            }
//        }
//        return mm;
//    }


    /**
     * 关闭redis
     * @param jedis
     */
    public static void closeRedis (Jedis jedis){
        if (jedis != null ){
            jedis.quit();
        }
    }

    /**
     * 发送邮件信息的内容
     * @param host
     * @param email
     * @param activeCode
     * @param ch
     * @return
     */
    public static String sendEmailcentot(String host,String email,String activeCode,char ch) {
        StringBuffer sb = new StringBuffer("点击下面链接激活账号，48小时内有效，否则重新注册账号，链接只能使用一次，请尽快激活！</br>");
        sb.append("<a href=");
        sb.append(ch);
        sb.append(host);
        sb.append("/home/activeEmail.json?action=activate&email=");
        sb.append(email);
        sb.append("&activeCode=");
        sb.append(activeCode);
        sb.append("\">"+host+"/user/register.json?action=activate&email=");
        sb.append(email);
        sb.append("&activeCode=");
        sb.append(activeCode);
        sb.append("</a>");
        return sb.toString();
    }

    /**
     * 发送邮件
     * @param toEmail
     * @param host
     * @return
     */
    public static Integer sendEmail (String toEmail,String host,String activeCode){
        Integer sendResulr = 0;
        char ch = '"';
        String content = sendEmailcentot(host,toEmail,activeCode,ch);
        Session session = SendEmail.getSession();
        try {
            LogInfo.info("--开始发送邮件 --"+"邮箱 == "+toEmail+" : "+content);
            // Instantiate a message
            Message msg = new MimeMessage(session);
            //Set message attributes
            msg.setFrom(new InternetAddress(SendEmail.EMAIL_FROM));
            InternetAddress[] address = {new InternetAddress(toEmail)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject("账号激活邮件");
            msg.setSentDate(new Date());
            msg.setContent(content , "text/html;charset=utf-8");
            //发送信息
            Transport.send(msg);
            sendResulr = 1 ; //邮件发送成功
        } catch (MessagingException e) {
            LogInfo.error("邮件发送出现异常 ----------- ：",e.getMessage());
            e.printStackTrace();
        } finally {
            if(session != null){
                session = null;
            }
        }
        return sendResulr;
    }


    /**
     * 获取当前服务器的路径
     * @param request
     * @return
     */
    public static String getHost (HttpServletRequest request){
        String requestUrl = request.getScheme() //当前链接使用的协议
                +"://" + request.getServerName()//服务器地址
                + ":" + request.getServerPort() //端口号
                + request.getContextPath(); //应用名称，如果应用名称为
               /* + request.getServletPath() //请求的相对url
                + "?" + request.getQueryString(); //请求参数*/
        return requestUrl;
    }

    /**
     * 文件上传
     * @param files
     * @param path
     * @return
     */
    public static Map uploadFile (HttpServletRequest request,CommonsMultipartFile files[],String path,String hostPath) {

        Map map = new HashMap();
        //读取上传文件的地址
        File f = new File(path);
        if (!f.exists()){
            f.mkdirs();
        }

        //图片保存的地址
        String showFilePath = "";
        String saveFilePath = "";
        String filePath = "";
        FileOutputStream fos = null;
        InputStream in = null;

        for (int i = 0; i < files.length; i++) {
            // 获得原始文件名
            String fileName = files[i].getOriginalFilename();
            Log.info("原始文件名:" + fileName);

            // 新文件名 加上下划线可以下载时截取原文件名称
            String newFileName = UUID.randomUUID() + "_" + fileName;

            if (!files[i].isEmpty()) {
                try {
                    filePath = path + newFileName;
                    fos = new FileOutputStream(filePath);
                    in = files[i].getInputStream();
                    //创建缓冲区
                    byte buffer[] = new byte[1024];
                    int b = 0;
                    new MultipartListenerResolver().parseRequest(request);
                    while ((b = in.read(buffer)) > 0) {
                        fos.write(buffer, 0, b);
                    }
                    saveFilePath = Constant.FILE_PATH + Constant.FILE_$ + newFileName; //保存数据的文件 目录家文件名
                    showFilePath = hostPath + Constant.FILE_PATH + Constant.FILE_$ + newFileName; // 用于显示的文件 完整的图片路径
                    // 此处是不是可以添加数据库保存
                    map.put("saveFilePath",saveFilePath);
                    map.put("showFilePath",showFilePath);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    closeIO(fos,in);
                }
            }
        }
        return map;
    }

    /**
     * 文件下载
     * @param request
     * @param response
     * @return
     */
    public static Integer downFile(HttpServletRequest request, HttpServletResponse response,String filePath){

        String fileName = request.getParameter("filename");
        String downFileFath = "";
        FileInputStream in = null;
        OutputStream out = null;
        Integer downResult = 0;

        try {
            fileName = new String(fileName.getBytes("iso8859-1"), "UTF-8");
            // 获取上传文件的目录
            ServletContext sc = request.getSession().getServletContext();
            // 上传位置 // 决定路径 ， 否则会出现找不文件的
            String fileSaveRootPath = sc.getRealPath(filePath);
            downFileFath = fileSaveRootPath + "\\" + fileName;
            // 得到要下载的文件
            File file = new File(downFileFath);
            // 如果文件不存在
            if (!file.exists()) {
                request.setAttribute("message", "您要下载的资源已被删除！！");
                Log.info("您要下载的资源已被删除！！");
                downResult = 2;
                return downResult;
            }
            // 处理文件名
            String realname = fileName.substring(fileName.indexOf("_") + 1);
            // 设置响应头，控制浏览器下载该文件
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
            // 读取要下载的文件，保存到文件输入流
            in = new FileInputStream(downFileFath);
            // 创建输出流
            out = response.getOutputStream();
            // 创建缓冲区
            byte buffer[] = new byte[1024];
            int len = 0;
            // 循环将输入流中的内容读取到缓冲区当中
            while ((len = in.read(buffer)) > 0) {
                // 输出缓冲区的内容到浏览器，实现文件下载
                out.write(buffer, 0, len);
            }

        } catch (Exception e) {
            Log.error("下载文件出现异常 : "+e.getMessage());
            downResult = 1;
        } finally {
//            closeIO(out,in);
        }
        return downResult;
    }


    /**
     * 关闭流
     * @param fos
     * @param in
     */
    public static void closeIO(FileOutputStream fos,InputStream in){

        if (fos != null){
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取项目路径
     * @param request
     * @return
     */
    public static ServletContext getProjects (HttpServletRequest request){
        ServletContext sc = request.getSession().getServletContext();
        return sc;
    }

    /**
     * 下划线拆分字符串
     * @param subString
     * @return
     */
    public static String sub_str(String subString){
        if (StringUtils.isNotBlank(subString)){
            String subStr = subString.substring(subString.indexOf("_") + 1);
            return subStr;
        }
        return subString;
    }

    /**
     * 正则验证邮箱
     * @param str
     * @return
     */
    public static boolean regEmail (String str){
        boolean flage = false;
        if (StringUtils.isNotBlank(str)) {
            String emailReg = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
            Pattern regex = Pattern.compile(emailReg);
            Matcher matcher = regex.matcher(str);
            flage = matcher.matches();
        }
        return flage;
    }

    /**
     * 正则验证手机 11位的数字
     * @param str
     * @return
     */
    public static boolean regPhone (String str){
        boolean flage = false;
        if (StringUtils.isNotBlank(str)) {
            String phoneReg = "\\d{11}";
            Pattern regex = Pattern.compile(phoneReg);
            Matcher matcher = regex.matcher(str);
            flage = matcher.matches();
        }
        return flage;
    }

}
