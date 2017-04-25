package com.mcin.util;

import java.util.Properties;

/**
 * Created by Mcin on 2017/4/17.
 *
 * 系统修改工具
 *
 */
public class OsUtil {

   private static Properties props = System.getProperties();

    /**
     * Java的运行环境版本
     * @return
     */
    public static String javaVersion (){
        return props.getProperty("java.version");
    }

    /**
     * Java的运行环境供应商
     * @return
     */
    public static String javaVendor (){
        return props.getProperty("java.vendor");
    }

    /**
     * Java供应商的URL
     * @return
     */
    public static String javaVendorUrl (){
        return props.getProperty("java.vendor.url");
    }

    /**
     * java安装路径
     * @return
     */
    public static String javaHome(){
        return props.getProperty("java.home");
    }

    /**
     * Java的虚拟机规范版本
     * @return
     */
    public static String specificationVersion (){
        return props.getProperty("java.vm.specification.version");
    }

    /**
     * Java的虚拟机规范供应商
     * @return
     */
    public static String javaSpecificationVendor(){
        return props.getProperty("java.vm.specification.vendor");
    }

    /**
     * Java的虚拟机规范名称
     * @return
     */
    public static String javaSpecificationName(){
        return props.getProperty("java.vm.specification.name");
    }

    /**
     * java虚拟机实现版本
     * @return
     */
    public static String javaVmVersion(){
        return props.getProperty("java.vm.version");
    }

    /**
     * Java的虚拟机实现供应商
     * @return
     */
    public static String javaVmVendor(){
        return props.getProperty("java.vm.vendor");
    }

    /**
     * Java的虚拟机实现名称
     * @return
     */
    public static String javaVmName(){
        return props.getProperty("java.vm.name");
    }

    /**
     * Java运行时环境规范版本
     * @return
     */
    public static String javaSpecificationVersion(){
        return props.getProperty("java.specification.version");
    }

    /**
     * Java运行时环境规范供应商
     * @return
     */
    public static String javaSpecificationVender(){
        return props.getProperty("java.specification.vender");
    }

    /**
     * 获取操作系统名称
     * @return
     */
    public static String osName(){
        return props.getProperty("os.name");
    }

    /**
     * 操作系统的版本
     * @return
     */
    public static String osVersion(){
        return props.getProperty("os.version");
    }


    //java环境
    public static void all(){
        System.out.println("Java的运行环境版本："+props.getProperty("java.version"));
        System.out.println("Java的运行环境供应商："+props.getProperty("java.vendor"));
        System.out.println("Java供应商的URL："+props.getProperty("java.vendor.url"));
        System.out.println("Java的安装路径："+props.getProperty("java.home"));
        System.out.println("Java的虚拟机规范版本："+props.getProperty("java.vm.specification.version"));
        System.out.println("Java的虚拟机规范供应商："+props.getProperty("java.vm.specification.vendor"));
        System.out.println("Java的虚拟机规范名称："+props.getProperty("java.vm.specification.name"));
        System.out.println("Java的虚拟机实现版本："+props.getProperty("java.vm.version"));
        System.out.println("Java的虚拟机实现供应商："+props.getProperty("java.vm.vendor"));
        System.out.println("Java的虚拟机实现名称："+props.getProperty("java.vm.name"));
        System.out.println("Java运行时环境规范版本："+props.getProperty("java.specification.version"));
        System.out.println("Java运行时环境规范供应商："+props.getProperty("java.specification.vender"));

        System.out.println("Java运行时环境规范名称："+props.getProperty("java.specification.name"));
        System.out.println("Java的类格式版本号："+props.getProperty("java.class.version"));
        System.out.println("Java的类路径："+props.getProperty("java.class.path"));
        System.out.println("加载库时搜索的路径列表："+props.getProperty("java.library.path"));
        System.out.println("默认的临时文件路径："+props.getProperty("java.io.tmpdir"));
        System.out.println("一个或多个扩展目录的路径："+props.getProperty("java.ext.dirs"));
        System.out.println("操作系统的名称："+props.getProperty("os.name"));
        System.out.println("操作系统的构架："+props.getProperty("os.arch"));
        System.out.println("操作系统的版本："+props.getProperty("os.version"));
        System.out.println("文件分隔符："+props.getProperty("file.separator"));//在 unix 系统中是＂／＂ System.out.println("路径分隔符："+props.getProperty("path.separator"));//在 unix 系统中是＂:＂ System.out.println("行分隔符："+props.getProperty("line.separator"));//在 unix 系统中是＂/n＂ System.out.println("用户的账户名称："+props.getProperty("user.name"));
        System.out.println("用户的主目录："+props.getProperty("user.home"));
        System.out.println("用户的当前工作目录："+props.getProperty("user.dir"));
    }

public static void main(String aggs[]){
        all();
}

}
