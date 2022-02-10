package com.jingbo.kongzhong.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jingbo.kongzhong.entity.User;
import com.jingbo.kongzhong.exception.StringException;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wangqiang
 * @Date: 2022/2/8 10:01
 */
public class Utils {
    //默认格式
    public static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String STANDARD_FORMAT1 = "ss mm HH dd MM ? yyyy";
    //1时间转字符串
    public static String dateToStr(Date date){
        if(date == null){
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(STANDARD_FORMAT);
    }
    //2字符串转时间
    public static Date strToDate(String dateTimeStr){
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(STANDARD_FORMAT);
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }
    //3字符串转时间
    public static Date cornStrToDate(String dateTimeStr){
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(STANDARD_FORMAT1);
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }

    public static boolean isNumStr(String zwstr){
        boolean zwa=false,zwb=false;
        for (int i=0;i<zwstr.length();i++) {
            char zwch=zwstr.charAt(i);
            if (zwch >='0' && zwch<= '9'){
                zwa=true;
            } else{
                if ((zwch>='a' && zwch<= 'z')||(zwch>='A' && zwch<='Z')){
                    zwb=true;
                } else {
                    return false;
                }
            }
        }
        return (zwa&&zwb);
    }

    public static String RandomCode() {
        StringBuilder sb=new StringBuilder();
        Random random=new Random();
        Integer number=random.nextInt(900000)+100000;
        sb.append(dateToStr(new Date(),"yyyyMMddHHmmss"));
        sb.append(number);
        sb.append(getStringId());
        return sb.toString();
    }
    private static String dateToStr(Date date,String formatStr){
        if(date == null){
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(formatStr);
    }

    //5 uuidfactary
    public static String getStringId(){
        Long random = UUID.randomUUID().getMostSignificantBits();
        long ane=random+System.currentTimeMillis();
        Long id = Math.abs(ane);
        return id.toString();
    }
    //判断是否是合法的url
    private static String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]" ;
    public static boolean UrlPattern(String url){
        Pattern patt = Pattern. compile(regex);
        Matcher matcher = patt.matcher(url);
        return matcher.matches();
    }

    /***
     *  功能描述：日期转换cron表达式
     * @param date
     * @param dateFormat : e.g:yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String formatDateByPattern(Date date,String dateFormat){
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String formatTimeStr = null;
        if (date != null) {
            formatTimeStr = sdf.format(date);
        }
        return formatTimeStr;
    }
    /***
     * convert Date to cron ,eg.  "0 07 10 15 1 ? 2016"
     * @param date  : 时间点
     */
    public static String getCron(Date  date){
        String dateFormat="ss mm HH dd MM ? yyyy";
        return formatDateByPattern(date, dateFormat);
    }

//    /***
//     * convert Date to cron ,eg.  "0 07 10 15 1 ? 2016"
//     * @param corn  : 时间点表达式
//     */
//    public static String getDate(String  corn){
//        String dateFormat="ss mm HH dd MM ? yyyy";
//        return formatDateByPattern(corn, dateFormat);
//    }

    //计算差多少分钟
    public static Long dateToMinus(Date startdate, Date enddate) {
        if(startdate==null) {
            throw new StringException("定时任务开始时间错误");
        }
        if(enddate==null) {
            throw new RuntimeException("定时任务开始时间错误");
        }
        long nh = 1000 * 60;
        // 获得两个时间的毫秒时间差异
        Long time=enddate.getTime()-startdate.getTime();
        // 计算差多少分钟
        return time / nh;
    }

    //生成token
    public static String CreateToken(String username){
        //生成token
        return JWT.create().withClaim("username",username).sign(Algorithm.HMAC256("F0Jbv4SICxVxFxar-gEnDpVgA"));
    }


    //解析token
    public static String ParaseToken(String token){
        JWTVerifier jwtVerifier=JWT.require(Algorithm.HMAC256("F0Jbv4SICxVxFxar-gEnDpVgA")).build();
        DecodedJWT verify = jwtVerifier.verify(token);
        return verify.getClaim("username").asString();
    }

    //读取html
    public static void readHtml(String inputHtml,String userId,String outPutHtml,String http){
        InputStream inputStream= null;
        OutputStream outStream=null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(inputHtml));
            outStream=new BufferedOutputStream(new FileOutputStream(outPutHtml));
            int i;
            byte[] buffer = new byte[1024];
            StringBuilder sb1=new StringBuilder();
            while((i = inputStream.read(buffer))!=-1){
                //读取全量空间 只有0-i剩余
                String str=new String(buffer,0,i);
                sb1.append(str);
            }
            String user1=sb1.toString().split("2672861012")[0];
            String user2=sb1.toString().split("2672861012")[1];
            String click1=user2.split("lanhu-edit.html")[0];
            String click2=user2.split("lanhu-edit.html")[1];
            StringBuilder sb2=new StringBuilder();
            sb2.append(user1).append(userId).append(click1).append(http).append(click2);
            outStream.write(sb2.toString().getBytes(StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                outStream.flush();
                outStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 把oldHtml的内容 写入到newHtml中
     * @param newHtml
     * @param oldHtml
     */
    public static void rebackHtml(String oldHtml,String newHtml){
        OutputStream outStream=null;
        try {
            outStream=new BufferedOutputStream(new FileOutputStream(newHtml));
            outStream.write(oldHtml.getBytes(StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                outStream.flush();
                outStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 解析Html模版
     */
    public static Document paraHtml(String html) throws IOException {
        File file=new File(html);
        Document document = Jsoup.parse(file,"utf-8");
        return document;
        /**
         * 像js一样，通过标签获取title
         *  System.out.println(document.getElementsByTag("title").first());
         *  System.out.println(document.getElementById("879").getElementsByTag("a").attr("href","123"));
         */
    }

    //linux获取ip
    public static String getLinuxHostIp(){
        String hostIP = null;
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) networkInterfaces.nextElement();
                Enumeration<InetAddress> nias = ni.getInetAddresses();
                while (nias.hasMoreElements()) {
                    InetAddress ia = (InetAddress) nias.nextElement();
                    if (!ia.isLinkLocalAddress() && !ia.isLoopbackAddress() && ia instanceof Inet4Address) {
                        hostIP=ia.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return hostIP;
    }
    //修改个人中心信息--刷新token并更改账户信息
    public static void setUser(User userInfo) {
        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection principalCollection = subject.getPrincipals();
        String realmName = principalCollection.getRealmNames().iterator().next();
        PrincipalCollection newPrincipalCollection =
                new SimplePrincipalCollection(userInfo, realmName);
        subject.runAs(newPrincipalCollection);
    }

    // 1MD5加密+盐（用户名）+散列次数
    public static String addMd5Hash(String sercrty,int randoms,String salt) {
        return new Md5Hash(sercrty, salt, randoms).toString();
    }

    public static User login() {
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()) {
            return (User)subject.getPrincipal();
        }else {
            throw new StringException("温馨提示:请使用对应的账号登陆");
        }
    }
    /** 全字母规则 正整数规则*/
    public static final String STR_ENG_PATTERN="^[a-z0-9A-Z]+$";
    public static boolean validateStrEnglish(final String str){

        if(StringUtils.isEmpty(str)){
            return Boolean.FALSE ;
        }
        if(StringUtils.isEmpty(str)){
            return Boolean.FALSE ;
        }
        boolean matches = str.matches(STR_ENG_PATTERN);
        if(str.length() < 6 || str.length() > 20) {
            return  Boolean.FALSE;
        }
        if (matches){
            return  true;
        }else{
            return  false;
        }
    }
}
