package com.cheng.networkframestudy.diy;

import java.io.File;
import java.util.Map;

/**
 * 自定义网络任务 JavaBean
 * 李旺成
 * 2016年2月21日18:54:12
 */
public class DIYHttpTask {

    private int taskId;                                             // 任务编号
    private String taskUrl;                                         // 请求 url
    private Map<String, String> taskParams;                         // 请求参数
    private Map<String, String> tastHeaders;                        // Http 请求 Headers
    private File file;                                              // 上传单个文件时使用
    private File[] fileArr;                                         // 上传多个文件时使用
    private String uploadReceiveName;                               // 上传文件时，服务器端接收文件的参数名称

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskUrl() {
        return taskUrl;
    }

    public void setTaskUrl(String taskUrl) {
        this.taskUrl = taskUrl;
    }

    public Map<String, String> getTaskParams() {
        return taskParams;
    }

    public void setTaskParams(Map<String, String> taskParams) {
        this.taskParams = taskParams;
    }

    public Map<String, String> getTastHeaders() {
        return tastHeaders;
    }

    public void setTastHeaders(Map<String, String> tastHeaders) {
        this.tastHeaders = tastHeaders;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public File[] getFileArr() {
        return fileArr;
    }

    public void setFileArr(File[] fileArr) {
        this.fileArr = fileArr;
    }

    public String getUploadReceiveName() {
        return uploadReceiveName;
    }

    public void setUploadReceiveName(String uploadReceiveName) {
        this.uploadReceiveName = uploadReceiveName;
    }
}
