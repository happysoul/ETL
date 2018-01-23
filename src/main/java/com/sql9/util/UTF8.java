package com.sql9.util;

import org.omg.CORBA.MARSHAL;

public abstract class UTF8
{
  public static byte[] fromString(String value)
  {
    int n = value.length();int u = 0;
    for (int i = 0; i < n; i++)
    {
      int c = value.charAt(i);
      if ((c >= 1) && (c <= 127)) {
        u++;
      } else if (c > 2047) {
        u += 3;
      } else {
        u += 2;
      }
    }
    byte[] bytes = new byte[u];
    int i = 0;
    for (int j = 0; i < n; i++)
    {
      int c = value.charAt(i);
      if ((c >= 1) && (c <= 127))
      {
        bytes[(j++)] = ((byte)c);
      }
      else if (c > 2047)
      {
        bytes[(j++)] = ((byte)(0xE0 | c >> 12 & 0xF));
        bytes[(j++)] = ((byte)(0x80 | c >> 6 & 0x3F));
        bytes[(j++)] = ((byte)(0x80 | c & 0x3F));
      }
      else
      {
        bytes[(j++)] = ((byte)(0xC0 | c >> 6 & 0x1F));
        bytes[(j++)] = ((byte)(0x80 | c & 0x3F));
      }
    }
    return bytes;
  }
  
  public static int fromString(String value, byte[] buffer, int offset, int length)
  {
    int n = value.length();int j = offset;
    for (int i = 0; i < n; i++)
    {
      if (j + 3 > length) {
        return -1;
      }
      int c = value.charAt(i);
      if ((c >= 1) && (c <= 127))
      {
        buffer[(j++)] = ((byte)c);
      }
      else if (c > 2047)
      {
        buffer[(j++)] = ((byte)(0xE0 | c >> 12 & 0xF));
        buffer[(j++)] = ((byte)(0x80 | c >> 6 & 0x3F));
        buffer[(j++)] = ((byte)(0x80 | c & 0x3F));
      }
      else
      {
        buffer[(j++)] = ((byte)(0xC0 | c >> 6 & 0x1F));
        buffer[(j++)] = ((byte)(0x80 | c & 0x3F));
      }
    }
    return j - offset;
  }
  
  public static String toString(byte[] value)
  {
    return toString(value, 0, value.length, null);
  }
  
  public static String toString(byte[] value, int offset, int length)
  {
    return toString(value, offset, length, null);
  }
  
  public static String toString(byte[] value, int offset, int length, char[] temp)
  {
    int n = offset + length;int j = 0;
    char[] chars;
    if ((temp != null) && (length <= temp.length)) {
      chars = temp;
    } else {
      chars = new char[length];
    }
    for (int i = offset; i < n; i++)
    {
      int c = value[i] + 256 & 0xFF;
      int c2;
      switch (c >> 4)
      {
      case 0: 
      case 1: 
      case 2: 
      case 3: 
      case 4: 
      case 5: 
      case 6: 
      case 7: 
        chars[(j++)] = ((char)c);
        break;
      case 12: 
      case 13: 
        if (i + 1 >= n) {
          _$1();
        }
        c2 = value[(++i)] + 256 & 0xFF;
        if ((c2 & 0xC0) != 128) {
          _$1();
        }
        chars[(j++)] = ((char)((c & 0x1F) << 6 | c2 & 0x3F));
        break;
      case 14: 
        if (i + 2 >= n) {
          _$1();
        }
        c2 = value[(++i)] + 256 & 0xFF;
        int c3 = value[(++i)] + 256 & 0xFF;
        if (((c2 & 0xC0) != 128) || ((c3 & 0xC0) != 128)) {
          _$1();
        }
        chars[(j++)] = ((char)((c & 0xF) << 12 | (c2 & 0x3F) << 6 | c3 & 0x3F));
        break;
      case 8: 
      case 9: 
      case 10: 
      case 11: 
      default: 
        _$1();
      }
    }
    return new String(chars, 0, j);
  }
  
  private static void _$1()
  {
    throw new MARSHAL("bad UTF-8 data");
  }
}
