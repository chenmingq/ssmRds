package com.mcin.dao;

import com.mcin.model.FileModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Mcin on 2017/3/25.
 * 上传文件
 */
@Repository
public interface UpDao {

    /**
     * 添加文件到数据
     * @param file
     */
    void insertFile (String file);

    /**
     * 查询出所有可以下载的文件
     * @return
     */
    List<FileModel> selectFile ();

}
