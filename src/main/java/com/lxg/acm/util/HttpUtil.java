package com.lxg.acm.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by 刘雪岗 on 2017/2/23.
 */
public class HttpUtil {
    public static String doGet(String urlStr) {

        StringBuffer st = new StringBuffer("");

        try {
            if (urlStr != null) {
                URL url = new URL(urlStr);// 生成url对象
                URLConnection urlConnection = url.openConnection();// 打开url连接
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        urlConnection.getInputStream(),"utf-8"));
                String line = null;

                while ((line = br.readLine()) != null) {
                    // line.getBytes("UTF-8");
                    // System.out.println(line);
                    st.append(line);
                }
                br.close();

            }

        } catch (MalformedURLException e) {
            System.out.println("连接到URL抛出异常信息：" + e);

        } catch (Exception e) {
            System.out.println("连接到URL抛出异常信息：" + e);
        } finally {
			/*
			 * String s=""; try { s = new
			 * String(st.toString().getBytes("UTF-8"),"UTF-8"); } catch
			 * (UnsupportedEncodingException e) { // TODO Auto-generated catch
			 * block e.printStackTrace(); }finally{ return s; }
			 */
            // System.out.println(st.toString());
            return st.toString();
        }

    }

    public static String doPost(String urlStr, String param) {

        StringBuffer st = new StringBuffer("");

        try {
            if (urlStr != null) {
                URL url = new URL(urlStr);// 生成url对象
                URLConnection  conn = (URLConnection )url.openConnection();
                conn.setDoInput(true);
                conn.setDoOutput(true);
//				conn.setRequestProperty("Content-Type", "application/octet-stream");
                conn.setRequestProperty("accept", "*/*");
                conn.setRequestProperty("connection", "Keep-Alive");
                conn.setRequestProperty("user-agent",
                        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
                conn.setUseCaches(false);
                OutputStream outStream = conn.getOutputStream();
                byte[] resultArray = param.getBytes();
                outStream.write(resultArray);
                outStream.flush();
                outStream.close();
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        conn.getInputStream(),"utf-8"));
                String line = null;

                while ((line = br.readLine()) != null) {
                    // line.getBytes("UTF-8");
                    // System.out.println(line);
                    st.append(line);
                }
                br.close();

            }

        } catch (MalformedURLException e) {
            System.out.println("连接到URL抛出异常信息：" + e);

        } catch (Exception e) {
            System.out.println("连接到URL抛出异常信息：" + e);
        } finally {
			/*
			 * String s=""; try { s = new
			 * String(st.toString().getBytes("UTF-8"),"UTF-8"); } catch
			 * (UnsupportedEncodingException e) { // TODO Auto-generated catch
			 * block e.printStackTrace(); }finally{ return s; }
			 */
            // System.out.println(st.toString());
            return st.toString();
        }

    }
}