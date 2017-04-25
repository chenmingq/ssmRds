package com.mcin.handler;

import org.apache.commons.fileupload.ProgressListener;
import sun.net.ProgressEvent;

import javax.servlet.http.HttpSession;

/**
 * Created by Mcin on 2017/3/26.
 */
public class FileUploadProgressListener implements ProgressListener {

    private HttpSession session;

    public FileUploadProgressListener(HttpSession session) {
        this.session = session;
    }

    //pBytesRead  已经上传的大小
    //pContentLength   文件总大小
    public void update(long pBytesRead, long pContentLength, int pItems) {
        System.out.println((double)pBytesRead/pContentLength);
    }
}
