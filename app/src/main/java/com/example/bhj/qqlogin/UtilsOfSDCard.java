package com.example.bhj.qqlogin;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bhj on 2018/9/8.
 */

public class UtilsOfSDCard {


    /**
     * 保存用户信息到SD卡
     *
     * @param number   qq号
     * @param password 密码
     * @return true 成功
     */
    public static boolean saveUserInfo(Context context, String number, String password) {

        try {

            //判断有没sd卡
            String state = Environment.getExternalStorageState();

            if (!Environment.MEDIA_MOUNTED.equals(state)){
                //没有sd卡
                return false;
            }

            File sdCardFile = Environment.getExternalStorageDirectory();

            File f = new File(sdCardFile, "bhjqq.txt");

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


    /**
     * 到sd卡获取用户信息
     * @param context
     * @return
     */
    public static Map<String, String> getUserInfo(Context context) {

        try {

            //判断有没sd卡
            String state = Environment.getExternalStorageState();

            if (!Environment.MEDIA_MOUNTED.equals(state)){
                //没有sd卡
                return null;
            }

            File sdCardFile = Environment.getExternalStorageDirectory();

            File f = new File(sdCardFile, "bhjqq.txt");

            FileInputStream fis = new FileInputStream(f);
            //字符流对象
            BufferedReader buf = new BufferedReader(new InputStreamReader(fis));

            String text = buf.readLine();

            buf.close();

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

