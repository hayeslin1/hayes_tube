package com.hayes.hayes_tube.utils;

import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.OperatingSystem;
import nl.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hayes on 4/23/19.
 */
public class GyUtils {
    private static Logger logger = LoggerFactory.getLogger(GyUtils.class);

    public static boolean isNull(String str) {

        return str == null || "".equals(str);
    }

    public static boolean isNotNull(String str) {
        return !isNull(str);
    }





    public static String dm2Colum(String colDm) {
        Map<String,String> columnMap = new HashMap<>();
        columnMap.put("dz","动作片");
        columnMap.put("xj","喜剧片");
        columnMap.put("jq","剧情片");
        columnMap.put("kb","恐怖片");
        columnMap.put("aq","爱情片");
        columnMap.put("kh","科幻片");
        columnMap.put("zz","战争片");

        columnMap.put("gc","国产剧");
        columnMap.put("xg","香港剧");
        columnMap.put("hg","韩国剧");
        columnMap.put("om","欧美剧");
        columnMap.put("tw","台湾剧");
        columnMap.put("rb","日本剧");
        columnMap.put("hw","海外剧");

        columnMap.put("ndzy","内地综艺");
        columnMap.put("gtzy","港台综艺");
        columnMap.put("rhzy","日韩综艺");
        columnMap.put("omzy","欧美综艺");

        columnMap.put("gcdm","国产动漫");
        columnMap.put("rhdm","日韩动漫");
        columnMap.put("omdm","欧美动漫");
        columnMap.put("gtdm","港台动漫");
        columnMap.put("hwdm","海外动漫");


        if (GyUtils.isNull(columnMap.get(colDm))){
            return "%%";
        }
        String columnName = "%"+columnMap.get(colDm)+"%";

        return columnName;

    }


    public static String getIpAddress(HttpServletRequest request) {
        String unknown = "unknown" ;
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


    public static String getNowDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String dateStr = sdf.format(date);
        return dateStr;
    }


    public static String getLog(HttpServletRequest request) {
        //获取浏览器信息
        String ua = request.getHeader("User-Agent");
        logger.info(ua);
        //转成UserAgent对象
        UserAgent userAgent = UserAgent.parseUserAgentString(ua);
//        logger.info(userAgent.toString());
        //获取浏览器信息
        Browser browser = userAgent.getBrowser();
//        logger.info(browser.toString());
        //获取系统信息
        OperatingSystem os = userAgent.getOperatingSystem();
//        logger.info(os.toString());
        //系统名称
        String system = os.getName();
//        logger.info(system);
        //浏览器名称
        String browserName = browser.getName();
//        logger.info(browserName);
        return getIpAddress(request);
    }

    /**
     * 读取服务ip地址
     * @return
     */
    public static String getHostIp(){
        try{
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()){
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()){
                    InetAddress ip = (InetAddress) addresses.nextElement();
                    if (ip != null
                            && ip instanceof Inet4Address
                            && !ip.isLoopbackAddress() //loopback地址即本机地址，IPv4的loopback范围是127.0.0.0 ~ 127.255.255.255
                            && ip.getHostAddress().indexOf(":")==-1){
                        System.out.println("本机的IP = " + ip.getHostAddress());
                        return ip.getHostAddress();
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
