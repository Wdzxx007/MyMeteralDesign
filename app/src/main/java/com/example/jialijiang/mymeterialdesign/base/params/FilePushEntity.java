package com.example.jialijiang.mymeterialdesign.base.params;

import java.util.List;

/**
 * Created by sean on 16/11/17.
 */
public class FilePushEntity {
    private String fileName;

    private List<String> fileList;

    public String getFileName() {
        return fileName;
    }

    public void setFileName( String fileName ) {
        this.fileName = fileName;
    }

    public List<String> getFileList() {
        return fileList;
    }

    public void setFileList( List<String> fileList ) {
        this.fileList = fileList;
    }
}
