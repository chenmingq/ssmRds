package com.mcin.controller;

import com.mcin.dao.UpDao;
import com.mcin.model.FileModel;
import com.mcin.util.Constant;
import com.mcin.util.Log;
import com.mcin.util.ToUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by Mcin on 2017/3/25.
 */


@Controller
@RequestMapping("/up")
public class UpController {


    @Resource
    private UpDao upDao;


    @RequestMapping("/toFile2")
    public String toFileUpload2() {
        return "fileUpload2";
    }


    /**
     * 一次上传多张图片
     */
//    @ResponseBody
    @RequestMapping("/threeFile")
    public String threeFileUpload(
            @RequestParam("file") CommonsMultipartFile files[],
            HttpServletRequest request, ModelMap model) {

        String hostPath = ToUtil.getHost(request);
        List<String> list = new ArrayList<String>();
        // 获得项目的路径
        ServletContext sc = ToUtil.getProjects(request);
        // 上传位置
        String path = sc.getRealPath(Constant.FILE_PATH) + Constant.FILE_$; // 设定文件保存的目录
        Map map = new HashMap();
        map = ToUtil.uploadFile(request,files,path,hostPath);
        String savePath = (String) map.get("saveFilePath");
        String showFilePath = (String) map.get("showFilePath");
        list.add(showFilePath);
        upDao.insertFile(savePath);
        // 保存文件地址，用于JSP页面回显
        model.addAttribute("fileList", list);
        return "/fileUpload2";
    }

    /**
     * 查询出数据库的文件
     * @param model
     * @return
     */
    @RequestMapping("/showImg")
    public ModelAndView showImg (HttpServletRequest request,Model model){
        ModelAndView mav = new ModelAndView();
        List<String> list = new ArrayList<String>();
        String hostPath = ToUtil.getHost(request);
        String fileName = "";
        List<FileModel> fileModelList = upDao.selectFile();
        if (fileModelList.size() > 0){
            for (int i = 0 ;i<fileModelList.size();i++){
                fileModelList.get(i).setFileName( fileModelList.get(i).getFileName());
                fileModelList.get(i).setShouImgName(fileModelList.get(i).getFileName());
            }
        }

        String realname = fileName.substring(fileName.indexOf("_") + 1);
        list.add(fileName);
        model.addAttribute("fileModelList",fileModelList);
        mav.setViewName("/dowm");
        return mav;
    }


    /**
     * 跳转到下载的页面
     * @return
     */
    @RequestMapping("dowm")
    public ModelAndView down(){
        ModelAndView mav = new ModelAndView();
        List<String> list = new ArrayList<String>();
//        list = upDao.selectFile();


        for (int i =0;i<list.size();i++){
            mav.addObject("i",i);
        }
        mav.addObject("list",list);
        mav.setViewName("/dowm");
        return mav;
    }


    /**
     * 文件下载
     * @param request
     * @param response
     */
    @RequestMapping("/downFile")
    public void downFile(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("1");

        String filePath = "";

       Integer result = ToUtil.downFile(request,response,filePath);
    if (request.equals(0)){
        Log.info("下载成功");
    } else if (result.equals(1)){
        Log.info("下在失败，发生异常");
    } else if (result == 2){
        Log.info("文件不存在");
    }
        // 得到要下载的文件名
//        String fileName = request.getParameter("filename");
//        System.out.println("2");
//        try {
//            fileName = new String(fileName.getBytes("iso8859-1"), "UTF-8");
//            System.out.println("3");
//            // 获取上传文件的目录
//            ServletContext sc = request.getSession().getServletContext();
//            System.out.println("4");
//            // 上传位置
//            String fileSaveRootPath = sc.getRealPath("");
//
//            System.out.println(fileSaveRootPath + "\\" + fileName);
//            String hostPath = ToUtil.getHost(request);
//            // 得到要下载的文件
//            File file = new File(fileSaveRootPath+"\\" + fileName);
//            Log.info("http://localhost:8000/mcinjob" + fileName);
//            Log.info(file+"-----------------------------");
//            // 如果文件不存在
//            if (!file.exists()) {
//                request.setAttribute("message", "您要下载的资源已被删除！！");
//                System.out.println("您要下载的资源已被删除！！");
//                return;
//            }
//            // 处理文件名
//            String realname = fileName.substring(fileName.indexOf("_") + 1);
//            // 设置响应头，控制浏览器下载该文件
//            response.setHeader("content-disposition", "attachment;filename="
//                    + URLEncoder.encode(realname, "UTF-8"));
//            // 读取要下载的文件，保存到文件输入流
//            FileInputStream in = new FileInputStream(fileSaveRootPath + "\\" + fileName);
//            // 创建输出流
//            OutputStream out = response.getOutputStream();
//            // 创建缓冲区
//            byte buffer[] = new byte[1024];
//            int len = 0;
//            // 循环将输入流中的内容读取到缓冲区当中
//            while ((len = in.read(buffer)) > 0) {
//                // 输出缓冲区的内容到浏览器，实现文件下载
//                out.write(buffer, 0, len);
//            }
//            // 关闭文件输入流
//            in.close();
//            // 关闭输出流
//            out.close();
//        } catch (Exception e) {
//
//        }
    }








    /**
     * 列出所有的图片
     */
    @RequestMapping("/listFile")
    public String listFile(HttpServletRequest request,  HttpServletResponse response) {
        // 获取上传文件的目录
//        ServletContext sc = request.getSession().getServletContext();
//        // 上传位置
//        String uploadFilePath = sc.getRealPath("/img") + "/"; // 设定文件保存的目录
        List<String> list = new ArrayList<String>();

//        StringBuilder sb = new StringBuilder();
//        for (int i=0;i<list.size();i++){
//            sb.append(i);
//        }


        // 存储要下载的文件名
        Map<String, String> fileNameMap = new HashMap<String, String>();
        // 递归遍历filepath目录下的所有文件和目录，将文件的文件名存储到map集合中
//        listfile(new File(uploadFilePath), fileNameMap);// File既可以代表一个文件也可以代表一个目录
        // 将Map集合发送到listfile.jsp页面进行显示
//        List<Map> map = upDao.selectFile();
//        request.setAttribute("fileNameMap", map);
        return "/dowm";
    }

    /**
     * @Method: listfile
     * @Description: 递归遍历指定目录下的所有文件
     * @param file 即代表一个文件，也代表一个文件目录
     * @param map 存储文件名的Map集合
     */
    public void listfile(File file,Map<String,String> map){
        //如果file代表的不是一个文件，而是一个目录
        if(!file.isFile()){
            //列出该目录下的所有文件和目录
            File files[] = file.listFiles();
            //遍历files[]数组
            for(File f : files){
                //递归
                listfile(f,map);
            }
        }else{
            /**
             * 处理文件名，上传后的文件是以uuid_文件名的形式去重新命名的，去除文件名的uuid_部分
             file.getName().indexOf("_")检索字符串中第一次出现"_"字符的位置，如果文件名类似于：9349249849-88343-8344_阿_凡_达.avi
             那么file.getName().substring(file.getName().indexOf("_")+1)处理之后就可以得到阿_凡_达.avi部分
             */
            String realName = file.getName().substring(file.getName().indexOf("_")+1);
            //file.getName()得到的是文件的原始名称，这个名称是唯一的，因此可以作为key，realName是处理过后的名称，有可能会重复
            map.put(file.getName(), realName);
        }
    }

}
