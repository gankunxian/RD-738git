/**
 * Program  : IpAddressUtils.java
 * Author   : misery
 * Create   : 2013-4-10 下午2:10:46
 *
 */

package com.hotshare.util;

import java.util.ArrayList;
import java.util.List;

/**
* 
* @author   misery
* @version  1.0.0
* @2013-4-10 下午2:10:46
*/
@SuppressWarnings("rawtypes")
public class IPAddress implements Comparable {  
    
    private int[] part = new int[4];  
      
    /**
     * 四段式IP字符�?
     * @author misery
     * @create 2013-4-10 下午2:15:58
     * @since 
     * @param str
     * @throws Exception
     */
    public IPAddress(String str) throws Exception {  
        String[] array = str.split("[.]");  
        if (array.length != 4) {  
            throw(new Exception("不正确的IP地址"));  
        } else {  
            try {  
                for (int i=0;i<4;i++) {  
                    part[i] = Integer.parseInt(array[i]);  
                    if (part[i] > 255 || part[i] < 0) {  
                        throw(new Exception("不正确的IP地址"));  
                    }  
                }  
            } catch (NumberFormatException ex) {  
                throw(new Exception("不正确的IP地址"));  
            } 
        } 
    }  
      
    /**
     * 比较�?
     * @author misery
     * @create 2013-4-10 下午2:16:05
     * @since 
     * @param ipo
     * @return
     */
    public int compareTo(Object ipo) {  
        IPAddress ip = (IPAddress) ipo;  
          
        if (this.part[0] < ip.part[0]) {  
            return -1;  
        } else if (this.part[0] > ip.part[0]) {  
            return 1;  
        } else {  
            if (this.part[1] < ip.part[1]) {  
                return -1;  
            } else if (this.part[1] > ip.part[1]) {  
                return 1;  
            } else {  
                if (this.part[2] < ip.part[2]) {  
                    return -1;  
                } else if (this.part[2] > ip.part[2]) {  
                    return 1;  
                } else {  
                    if (this.part[3] < ip.part[3]) {  
                        return -1;  
                    } else if (this.part[3] > ip.part[3]) {  
                        return 1;  
                    } else {  
                        return 0;  
                    }  
                }  
            }  
        }  
    } 
      
    /**
     * ip地址加一
     * @author misery
     * @create 2013-4-10 下午2:16:16
     * @since
     */
    public void add() {  
        if (this.part[3] < 255) {  
            this.part[3]++;  
        } else {  
            if (this.part[2] < 255) {  
                this.part[2]++;  
                this.part[3] = 0;  
            } else {  
                if (this.part[1] < 255) {  
                    this.part[1]++;  
                    this.part[2] = 0;  
                    this.part[3] = 0;  
                } else {  
                    if (this.part[0] < 255) {  
                        this.part[0]++;  
                        this.part[1] = 0;  
                        this.part[2] = 0;  
                        this.part[3] = 0;  
                    }  
                }  
            }  
        }  
    }
      /**
       * ip地址增加num
       * @author misery
       * @create 2013-4-10 下午2:16:56
       * @since 
       * @param num
       */
    public void add(int num) {  
        for (int i = 0; i < num; i++) {  
            this.add();  
        }
    }
      /**
       * 输出字符�?
       * @author misery
       * @create 2013-4-10 下午2:17:23
       * @since 
       * @return
       */
    public String toString() {  
        return part[0] + "." + part[1] + "." + part[2] + "." + part[3];  
    }  
      
   /**
    * 获取子网的网络地�?
    * @author misery
    * @create 2013-4-10 下午2:17:46
    * @since 
    * @param ip
    * @param mask
    * @return
    * @throws Exception
    */
    public static String parseSubNetIP(String ip, int mask) throws Exception {  
        if (!isIP(ip)) {  
            throw(new Exception("IP地址不合法！"));  
        }  
        String s1 = toBinaryString(ip);  
        String s2 = maskToBinaryString(mask);  
        return toIPString(Long.toBinaryString(Long.parseLong(s1,2)&Long.parseLong(s2,2)));  
    }  
      
    /**
     * 将整型的掩码转换成IPv4�?30 -> 255.255.255.252).
     * @author misery
     * @create 2013-4-10 下午2:18:13
     * @since 
     * @param mask
     * @return
     * @throws Exception
     */
    public static String convertMask(int mask) throws Exception {  
        if (mask > 32 || mask < 8) {  
            throw(new Exception("掩码错误！应�?-32的整数�?"));  
        }  
        return toIPString(maskToBinaryString(mask));  
    } 
    /**
     * 计算子网掩码整数
     * @author misery
     * @create 2013-4-10 下午2:20:13
     * @since 
     * @param ip
     * @return
     * @throws Exception
     */
    public static int convertMask(String ip) throws Exception {  
        String str = toBinaryString(ip);  
        int counter = 0;  
        for (int i = 0; i < str.length(); i++) {  
            if (str.charAt(i) == '1') {  
                counter++;  
            } else {  
                break;  
            }  
        }  
        return counter;  
    } 
      
    /**
     * 将整型掩码转换成IPv4型的反码(30 -> 0.0.0.3)  
     * @author misery
     * @create 2013-4-10 下午2:31:23
     * @since 
     * @param mask
     * @return
     * @throws Exception
     */
    public static String reverseMask(int mask) throws Exception {  
        if (mask > 32 || mask < 8) {  
            throw(new Exception("掩码错误！应�?-32的整数�?"));  
        }  
        String str = maskToBinaryString(mask);  
        str = str.replace('0','2');  
        str = str.replace('1','0');  
        str = str.replace('2','1');  
        return toIPString(str);  
    } 
      
    /**
     * 判断IP是否合法  
     * @author misery
     * @create 2013-4-10 下午2:31:37
     * @since 
     * @param ip
     * @return
     */
    public static boolean isIP(String ip) {  
        String[] array = ip.split("[.]");  
        if (array.length != 4) {  
            return false;  
        } else {  
            try {  
                for (int i=0;i<4;i++) {  
                    int num = Integer.parseInt(array[i]);  
                    if (num > 255 || num < 0) {  
                        return false;  
                    }  
                } 
                return true;  
            } catch (NumberFormatException ex) {  
                return false;  
            }  
        }  
    }
      
   /**
    * 将整型掩码转换成二进制字符串  
    * @author misery
    * @create 2013-4-10 下午2:31:56
    * @since 
    * @param mask
    * @return
    */
    public static String maskToBinaryString(int mask) {  
        StringBuffer str = new StringBuffer();  
        for(int i=1;i<=32;i++) {  
            if (i <= mask) {  
                str.append('1');  
            } else {  
                str.append('0');  
            }  
        }  
        return str.toString();  
    } 
      
    /**
     * 将二进制字符串转换成IPv4型字符串   
     * @author misery
     * @create 2013-4-10 下午2:32:10
     * @since 
     * @param binary
     * @return
     */
    public static String toIPString(String binary) {  
        if (binary.length() < 32) {  
            for (int i=binary.length();i<32;i++) {  
                binary = "0" + binary;  
            }  
        }  
          
        String part1 = binary.substring(0,8);  
        String part2 = binary.substring(8,16);  
        String part3 = binary.substring(16,24);  
        String part4 = binary.substring(24);  
        return Integer.parseInt(part1,2) + "."  
             + Integer.parseInt(part2,2) + "."  
             + Integer.parseInt(part3,2) + "."  
             + Integer.parseInt(part4,2);  
    }
      
    /**
     * 将IPv4型字符串转换成长度为32的二进制字符�? 
     * @author misery
     * @create 2013-4-10 下午2:32:24
     * @since 
     * @param ip
     * @return
     */
    public static String toBinaryString(String ip) {  
        String[] array = ip.split("[.]");  
        String str = "";  
        for (int i=0; i<array.length; i++) {  
            String s = Integer.toBinaryString(Integer.parseInt(array[i]));  
            if (s.length() < 8) {  
                for (int j=s.length();j<8;j++) {  
                    s = "0" + s;  
                }  
            }  
            str += s;  
        }  
        return str;  
    }  
      
    /**
     * 判断�?��IP地址是否属于某一个子�? 
     * @author misery
     * @create 2013-4-10 下午2:32:42
     * @since 
     * @param ip
     * @param prefix
     * @param prefixLen
     * @return
     */
    public static boolean inPrefix(String ip, String prefix, int prefixLen) {  
        String binPrefix = toBinaryString(prefix);  
        if (binPrefix.length() > prefixLen) {  
            binPrefix = binPrefix.substring(0,prefixLen);  
        }  
          
        String binIP = toBinaryString(ip);  
        binIP = binIP.substring(0,prefixLen);  
        if (binIP.equals(binPrefix)) {  
            return true;  
        } else {  
            return false;  
        }  
    } 
      
   /**
    * 获得两个IP间的范围，如192.168.0.128-192.168.2.256 
    * @author misery
    * @create 2013-4-10 下午2:33:00
    * @since 
    * @param ip1
    * @param ip2
    * @return
    */
    @SuppressWarnings("unchecked")
	public static String[] getIPRange(String ip1, String ip2) {  
        //System.out.println("GET IPAddress from " + ip1 + " to " + ip2);  
          
        String[] ips = null;  
        try{  
            List list = new ArrayList();  
              
            long value1 = GetIPValue(ip1);  
            long value2 = GetIPValue(ip2);  
              
            for (long i = value1; i <= value2; i++) {  
                list.add(GetStringbyValue(i));  
            }  
              
            /*try { 
                IPAddress addr1 = new IPAddress(ip1); 
                IPAddress addr2 = new IPAddress(ip2); 
                 
                list.add(ip1); 
                while (addr1.compareTo(addr2) < 0) { 
                    addr1.add(); 
                    list.add(addr1.toString()); 
                } 
            } catch (Exception e) { 
                System.out.println(e); 
                e.printStackTrace(); 
            }*/  
              
            ips = new String[list.size()];  
            list.toArray(ips);  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
          
          
        return ips;  
    }
      
   /**
    * 获得子网的所有IP地址；如�?92.168.0/24�? 
    * @author misery
    * @create 2013-4-10 下午2:33:52
    * @since 
    * @param prefix
    * @param prefixLen
    * @return
    */
    public static String[] getIPRange(String prefix, int prefixLen) {  
        String binPrefix = toBinaryString(prefix);  
        if (binPrefix.length() > prefixLen) {  
            binPrefix = binPrefix.substring(0,prefixLen);  
        }  
        StringBuffer ip1 = new StringBuffer(binPrefix);  
        StringBuffer ip2 = new StringBuffer(binPrefix);  
        for (int i = ip1.length(); i < 32; i++) {  
            ip1.append('0');  
        }
        for (int i = ip2.length(); i < 32; i++) {  
            ip2.append('1');  
        }  
          
        return getIPRange(toIPString(ip1.toString()),toIPString(ip2.toString()));  
    }  
      
    /**
     * �?段式IP地址字符串转为对应长整数，如192.168.0.128 -> 3232235648
     * @author misery
     * @create 2013-4-10 下午2:34:32
     * @since 
     * @param IPAddress
     * @return
     */
    public static long GetIPValue(String IPAddress){  
        if (IPAddress == null){  
            return -1;  
        }  
        //System.out.println("IPAddress:GetIPValue:IPAddress:"+IPAddress);  
        String[] array = IPAddress.split("[.]");  
        long value = 0;  
        try{  
            for (int i=0; i<array.length; i++) {  
                ////System.out.println("IPAddress:GetIPValue:array["+i+"]"+array[i]);  
                long v = Long.parseLong(array[i]);  
                ////System.out.println("IPAddress:GetIPValue:v"+v);  
                if (v< 0 || v > 255){  
                    return -2;  
                }  
                value = value*256 + v;  
            }  
        }catch(Exception ex){  
            return -1;  
        }  
        //System.out.println("IPAddress:GetIPValue:value"+value);  
        return value;  
      }  
      
    /**
     * 将长整数转为对应4段式IP地址字符串，�?92.168.0.128 <- 3232235648
     * @author misery
     * @create 2013-4-10 下午2:34:42
     * @since 
     * @param value
     * @return
     */
    public static String GetStringbyValue(long value){  
        if (value < 0){  
            return null;  
        }  
        //System.out.println("IPAddress:GetStringbyValue:value"+value);  
  
        int vip[] = new int[4];  
        long v = value;  
        for (int i = 3;i>=0;i--){  
            vip[i] = (int)(v%256);  
            //System.out.println("IPAddress:GetStringbyValue:vip["+i+"]"+vip[i]);  
            v = v/256;  
            //System.out.println("IPAddress:GetStringbyValue:v"+v);  
        }  
          
        return (""+vip[0]+"."+vip[1]+"."+vip[2]+"."+vip[3]);   
      }  
      
    /**
     *  �?段式IP地址字符串转为对应长整数字符串，�?92.168.0.128 -> "3232235648"   
     * @author misery
     * @create 2013-4-10 下午2:34:53
     * @since 
     * @param IPAddress
     * @return
     */
    public static String GetIPValueStr(String IPAddress) {  
        Long V = new Long (GetIPValue(IPAddress));  
        return V.toString();  
    }  
      
    /**
     *  计算地址段包含地�?��，如192.168.0.1~192.168.0.254 (254)  
     * @author misery
     * @create 2013-4-10 下午2:35:20
     * @since 
     * @param startip
     * @param endip
     * @return
     */
    public static long GetIPCount(String startip,String endip){  
        return GetIPValue(endip)-GetIPValue(startip)+1;  
      }  
  
    /**
     * 计算地址段包含地�?��，如192.168.0.1~192.168.0.254 (254)  
     * @author misery
     * @create 2013-4-10 下午2:35:28
     * @since 
     * @param maskLenth
     * @return
     */
    public static long GetIPCount(int maskLenth){  
        double ipnum;  
        long ipCount;  
        // IP个数�? ^ (32 - 掩码长度) �?3  
        if (32 - maskLenth <= 0) {  
            ipCount = -1;  
        }else{  
            ipnum = Math.pow(2,32 - maskLenth) -3;  
            ipCount = new Double(ipnum).longValue();  
        }  
        return ipCount;  
    }  
  
      /**
       * 获得当子网掩码长度（根据子网子符串）
       * @author misery
       * @create 2013-4-10 下午2:35:45
       * @since 
       * @param mask
       * @return
       */
      public static int getMaskLen(String mask) {  
            try {  
                //后台对IPv6的支�?子网掩码在数据库中填写的就是数字.  
                if (mask.indexOf('.') == -1) {  
                    return Integer.parseInt(mask);  
                }  
                  
                int p1 = 0, p2 = mask.indexOf('.');  
                int b1 = Integer.parseInt(mask.substring(p1, p2));  
                p1 = p2 + 1;  
                p2 = mask.indexOf('.', p1);  
                int b2 = Integer.parseInt(mask.substring(p1, p2));  
                p1 = p2 + 1;  
                p2 = mask.indexOf('.', p1);  
                int b3 = Integer.parseInt(mask.substring(p1, p2));  
                p1 = p2 + 1;  
                int b4 = Integer.parseInt(mask.substring(p1));  
                return IPAddress.getMaskLen(b1 * 256 * 256 * 256 + b2 * 256  
                        * 256 + b3 * 256 + b4);  
            } catch (Throwable e1) {  
                System.out.println("无效的子网掩�? " + mask);  
                e1.printStackTrace();  
                return -1;  
            }  
        }  
  
  /**
   * 得当子网掩码长度（根据整型数�? 
   * @author misery
   * @create 2013-4-10 下午2:35:56
   * @since 
   * @param mask
   * @return
   */
      public static  int getMaskLen(long  mask)  
      {  
        int l=32;  
        while(l>0)  
        {  
          if((mask&(1<<(l-1)))==0)  
          {  
            break;  
          }  
          l--;  
        }  
        return 32-l;  
      }  
  
    /**
     * 两地�?���? 
     * @author misery
     * @create 2013-4-10 下午2:36:05
     * @since 
     * @param ip1
     * @param ip2
     * @return
     */
    public static String AddressAND(String ip1,String ip2){  
        char str[] = new char[32];    
        ip1 = toBinaryString(ip1);  
        ip2 = toBinaryString(ip2);  
        if (ip1==null || ip1.length()<32 || ip2 == null || ip2.length()< 32 ){  
            return null;  
        }  
        for (int i = 0;i<32;i++){  
            if (ip1.charAt(i) == '1' && ip2.charAt(i) == '1' ){  
                str[i] = '1';  
            }else{  
                str[i] = '0';  
            }  
        }  
        String resultIp = toIPString(new String (str));   
        return resultIp ;  
      }  
       
    /**
     * 两地�?���? 
     * @author misery
     * @create 2013-4-10 下午2:36:23
     * @since 
     * @param ip1
     * @param ip2
     * @return
     */
    public static String AddressOR(String ip1,String ip2){  
        char str[] = new char[32];  
        ip1 = toBinaryString(ip1);  
        ip2 = toBinaryString(ip2);  
        if (ip1==null || ip1.length()<32 || ip2 == null || ip2.length()< 32 ){  
            return null;  
        }  
        for (int i = 0;i<32;i++){  
            if (ip1.charAt(i) == '1' || ip2.charAt(i) == '1' ){  
                str[i] = '1';  
            }else{  
                str[i] = '0';  
            }  
        }  
        String resultIp = toIPString(new String (str));   
        return resultIp ;  
      }  
      
    /** 
     * 将地�?��反码
     * @author misery
     * @create 2013-4-10 下午2:36:34
     * @since 
     * @param ip
     * @return
     */
    public static String AddressReverse(String ip) {  
        if (ip == null || ip.length() < 32) {  
            return null;  
        }  
        String str = new String (ip);  
        str = str.replace('0','2');  
        str = str.replace('1','0');  
        str = str.replace('2','1');  
        return str;  
    }  
  
   /**
    * 求网络地�?(netAddress = ip&Mask)   
    * @author misery
    * @create 2013-4-10 下午2:36:49
    * @since 
    * @param ip
    * @param mask
    * @return
    */
    public static String GetNetAddress(String ip,String mask){  
        return toIPString(AddressAND(toBinaryString(ip.trim()),toBinaryString(mask.trim())));  
    }  
      
    /**
     * 求广播地�?( = ip|~Mask)  
     * @author misery
     * @create 2013-4-10 下午2:37:04
     * @since 
     * @param ip
     * @param mask
     * @return
     */
    public static String GetBroadcastAddress(String ip,String mask){      
        return toIPString(AddressOR(toBinaryString(ip.trim()),AddressReverse(toBinaryString(mask.trim()))));  
    }  
      
    public static void main(String[] args) throws Exception { 
        boolean result =  inPrefix("10.8.0.1","10.0.0.0" ,13);
        System.out.println(result);
        
    }
  
}

