/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.aliyun.api.gateway.demo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.aliyun.api.gateway.demo.util.CommonUtil;

/**
 * 调用示例
 * 请替换APP_KEY,APP_SECRET,HOST,CUSTOM_HEADERS_TO_SIGN_PREFIX为真实配置
 */
public class Demo1_4 {
    //APP KEY
    private final static String APP_KEY = "23551757";
    // APP密钥
    private final static String APP_SECRET = "5c7f24ade21f9a4b70830c3906e69959";
    //API域名
    private final static String HOST = "dm-51.data.aliyun.com";
    //url
    private final static String URL = "/rest/160601/ocr/ocr_idcard.json";
    //自定义参与签名Header前缀（可选,默认只有"X-Ca-"开头的参与到Header签名）
    private final static List<String> CUSTOM_HEADERS_TO_SIGN_PREFIX = new ArrayList<String>();
    private static ArrayList<String> filelist = new ArrayList<String>();
    static {
        CUSTOM_HEADERS_TO_SIGN_PREFIX.add("Custom");
    }
    
    public static void main(String[] args) {
    	getFiles("/Users/qianjianlei/Desktop/work/idcard/佛山经纪人身份证原件+照片/洛溪B片区-经纪人申请虚拟手机号码/珠江花园身份证");
    	
    	System.out.println("====================demo1-4 over==========");
	}
    
    
    /*
     * 通过递归得到某一路径下所有的目录及其文件
     */
    static void getFiles(String filePath){
    	int index = 1;
    	File root = new File(filePath);
    	File[] files = root.listFiles();
    	for(File file:files){    
    		if(file.isDirectory()){
		         /*
		          * 递归调用
		          */

		         System.out.println("目录及其文件"+file.getAbsolutePath());
		         try {
					getFiles(file.getAbsolutePath());
					 filelist.add(file.getAbsolutePath());
				} catch (Exception e) {
					e.printStackTrace();
				}
    		}else{
    			try {
					String path = file.getAbsolutePath();
					System.out.println("处理："+path);
					String p = path.substring(path.lastIndexOf(".")-3);
//					if(p.indexOf("2.") == -1 && p.indexOf("3.") == -1 
//							&& p.indexOf("反面") == -1 
//							&& p.indexOf("照片") == -1 
//							&& p.indexOf(".xlsx") == -1){
					////if(!p.equals("2") && !p.equals("3") && !p.equals("反") && !p.equals("片")){
						try {
							CommonUtil commonUtil = new CommonUtil(HOST,URL,APP_KEY,APP_SECRET,CUSTOM_HEADERS_TO_SIGN_PREFIX,file.getAbsolutePath());
							String msg = commonUtil.sendPostRequestWithBody();
							System.out.println(msg+" : "+path);
							if(msg == null || msg.equals("")){
								System.out.println("====================文件处理失败=========="+file.getAbsolutePath());
								method2("/Users/qianjianlei/Desktop/work/idcard/佛山经纪人身份证原件+照片/佛山虚拟小号码资料收集/佛山A区/珠江花园身份证_fail.txt", path);
							}else{
								System.out.println("====================success=========");
								method2("/Users/qianjianlei/Desktop/work/idcard/佛山经纪人身份证原件+照片/佛山虚拟小号码资料收集/佛山A区/珠江花园身份证_succ.txt", (index++)+" : "+msg+" : "+path);
							}
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("====================文件处理失败=========="+file.getAbsolutePath());
							method2("/Users/qianjianlei/Desktop/work/idcard/佛山经纪人身份证原件+照片/佛山虚拟小号码资料收集/佛山A区/珠江花园身份证_fail.txt", path);
						}
//					}
				} catch (Exception e) {
					e.printStackTrace();
				}
    		}    
       }
    }
    
    public static void method2(String file, String conent) {
    	BufferedWriter out = null;
    	try {
	    	out = new BufferedWriter(new OutputStreamWriter(
	    	new FileOutputStream(file, true)));
	    	out.write(conent+"\r\n");
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
	    	try {
	    		out.close();
	    	} catch (IOException e) {
	    		e.printStackTrace();
	    	}
	    }
    }
   
}
