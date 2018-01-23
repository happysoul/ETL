package com.sql9.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;

public class StringUtil
{
  private static final String _$1 = getOSName().toLowerCase();
  
  public static String getOSName()
  {
    return System.getProperty("os.name");
  }
  
  private static boolean _$3()
  {
    return _$1.startsWith("windows");
  }
  
  public static String getStackInfoFromException(Exception ex)
  {
    try
    {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      ex.printStackTrace(pw);
      return "\r\n" + sw.toString() + "\r\n";
    }
    catch (Exception e2) {}
    return "bad getStackInfoFromException";
  }
  
  public static String getTimeStamp()
  {
    long t = System.currentTimeMillis();
    Timestamp ts = new Timestamp(t);
    return ts.toString();
  }
  
  private static String _$2()
  {
    String mac = null;
    BufferedReader bufferedReader = null;
    Process process = null;
    try
    {
      process = Runtime.getRuntime().exec("ifconfig eth0");
      bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      String line = null;
      int index = -1;
      while ((line = bufferedReader.readLine()) != null)
      {
        index = line.toLowerCase().indexOf("hwaddr");
        if (index >= 0) {
          mac = line.substring(index + "hwaddr".length() + 1).trim();
        }
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    finally
    {
      try
      {
        if (bufferedReader != null) {
          bufferedReader.close();
        }
      }
      catch (IOException e1)
      {
        e1.printStackTrace();
      }
      bufferedReader = null;
      process = null;
    }
    return mac;
  }
  
  private static String _$1()
  {
    String mac = null;
    BufferedReader bufferedReader = null;
    Process process = null;
    try
    {
      process = Runtime.getRuntime().exec("ipconfig /all");
      bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      String line = null;
      int index = -1;
      while ((line = bufferedReader.readLine()) != null)
      {
        index = line.toLowerCase().indexOf("physical address");
        if (index >= 0)
        {
          index = line.indexOf(":");
          if (index >= 0) {
            mac = line.substring(index + 1).trim();
          }
        }
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    finally
    {
      try
      {
        if (bufferedReader != null) {
          bufferedReader.close();
        }
      }
      catch (IOException e1)
      {
        e1.printStackTrace();
      }
      bufferedReader = null;
      process = null;
    }
    return mac;
  }
  
  public static String getDeviceString()
  {
    if (_$3()) {
      return _$1();
    }
    return _$2();
  }
}
