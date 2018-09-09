package com.example.bhj.qqlogin;

import android.content.Context;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bhj on 2018/9/6.
 */


public class Utils {


    /**
     * 保存用户信息
     *
     * @param number   qq号
     * @param password 密码
     * @return true 成功
     */
    public static boolean saveUserInfo(String number, String password) {

        try {
            String path = "/data/data/com.example.bhj.myapplication/bhjqq.txt";

            FileOutputStream fos = new FileOutputStream(path);

            //3966554#12154684
            String data = number + "##" + password;
            fos.write(data.getBytes());
            fos.flush();
            fos.close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }


        return false;
    }


    /**
     * 保存用户信息到本地
     *
     * @param number   qq号
     * @param password 密码
     * @return true 成功
     */
    public static boolean saveUserInfo(Context context, String number, String password) {

        try {
            //String path = "/data/data/com.example.bhj.myapplication/bhjqq.txt"

            File filesDir = context.getFilesDir();

            File f = new File(filesDir, "bhjqq.txt");

            FileOutputStream fos = new FileOutputStream(f);

            //3966554#12154684
            String data = number + "##" + password;
            fos.write(data.getBytes());
            fos.flush();
            fos.close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }


        return false;
    }


    public static Map<String, String> getUserInfo() {

        try {
            String path = "/data/data/com.example.bhj.myapplication/bhjqq.txt";

            FileInputStream fis = new FileInputStream(path);
            //字符流对象
            BufferedReader buf = new BufferedReader(new InputStreamReader(fis));

            String text = buf.readLine();

            if (!TextUtils.isEmpty(text)) {
                String[] split = text.split("##");

                Map<String, String> userInfoMap = new HashMap<String, String>();

                userInfoMap.put("number", split[0]);
                userInfoMap.put("password", split[1]);
                return userInfoMap;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public static Map<String, String> getUserInfo(Context context) {

        try {
            //String path = "/data/data/com.example.bhj.myapplication/bhjqq.txt";

            File filesDir = context.getFilesDir();

            File f = new File(filesDir, "bhjqq.txt");

            FileInputStream fis = new FileInputStream(f);
            //字符流对象
            BufferedReader buf = new BufferedReader(new InputStreamReader(fis));

            String text = buf.readLine();

            if (!TextUtils.isEmpty(text)) {
                String[] split = text.split("##");

                Map<String, String> userInfoMap = new HashMap<String, String>();

                userInfoMap.put("number", split[0]);
                userInfoMap.put("password", split[1]);
                return userInfoMap;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
