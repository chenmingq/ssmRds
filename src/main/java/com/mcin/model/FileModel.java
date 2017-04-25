package com.mcin.model;

import com.mcin.util.Constant;

/**
 * Created by Mcin on 2017/3/26.
 */
public class FileModel {

    /**
     * 图片文件
     */
    public String fileName;


    /**
     * 压缩文件
     */
    public String zipFile;

    /**
     * excel文件
     */
    public String xixFile;

    /**
     * 用于页面显示文件名
     */
    public String shouImgName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getZipFile() {
        return Constant.FILE_PATH + zipFile;
    }

    public void setZipFile(String zipFile) {
        this.zipFile = zipFile;
    }

    public String getXixFile() {
        return xixFile;
    }

    public void setXixFile(String xixFile) {
        this.xixFile = xixFile;
    }

    public String getShouImgName() {
        return shouImgName;
    }

    public void setShouImgName(String shouImgName) {
        this.shouImgName = shouImgName.substring(shouImgName.indexOf("_") + 1);
    }
}
