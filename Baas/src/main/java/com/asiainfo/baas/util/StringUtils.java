/*     */ package com.asiainfo.baas.util;
/*     */ 
/*     */ import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/*     */ 
/*     */ public class StringUtils
/*     */ {
/*     */   public static boolean equals(String s1, String s2)
/*     */   {
/*  14 */     return org.apache.commons.lang.StringUtils.equals(s1, s2);
/*     */   }
/*     */   public static boolean isEmptyString(String aStr) {
/*  17 */     return (aStr == null) || (aStr.length() == 0);
/*     */   }
/*     */ 
/*     */   public static int indexOfArray(String[] list, String str)
/*     */   {
/*  25 */     if (list == null)
/*  26 */       return -1;
/*  27 */     for (int i = 0; i < list.length; i++)
/*  28 */       if (str.equals(list[i]))
/*  29 */         return i;
/*  30 */     return -1;
/*     */   }
/*     */ 
/*     */   public static final boolean emptyString(Object o) {
/*  34 */     boolean expr = (o == null) || (((o instanceof String)) && (((String)o).length() == 0));
/*     */ 
/*  36 */     return expr;
/*     */   }
/*     */ 
/*     */   public static final String nullToEmptyString(String s) {
/*  40 */     return s == null ? "" : s;
/*     */   }
/*     */ 
/*     */   public static final String quoteHtml(String s) {
/*  44 */     if (s != null) {
/*  45 */       StringBuilder result = new StringBuilder(s.length() + 10);
/*  46 */       for (int i = 0; i < s.length(); i++) {
/*  47 */         char ch = s.charAt(i);
/*  48 */         switch (ch) {
/*     */         case '&':
/*  50 */           result.append("&amp;");
/*  51 */           break;
/*     */         case '"':
/*  53 */           result.append("&quot;");
/*  54 */           break;
/*     */         case '<':
/*  56 */           result.append("&lt;");
/*  57 */           break;
/*     */         case '>':
/*  59 */           result.append("&gt;");
/*  60 */           break;
/*     */         default:
/*  62 */           result.append(ch);
/*     */         }
/*     */       }
/*  65 */       return result.toString();
/*     */     }
/*  67 */     return "";
/*     */   }
/*     */ 
/*     */   public static String[] splitString(String aSource, String aSplitPoint)
/*     */   {
/*  72 */     if (org.apache.commons.lang.StringUtils.isEmpty(aSource))
/*  73 */       return null;
/*  74 */     return org.apache.commons.lang.StringUtils.split(aSource, aSplitPoint);
/*     */   }
/*     */   public static String[] split(String s, char sep) {
/*  77 */     if ((s == null) || (s.length() == 0))
/*  78 */       return new String[0];
/*  79 */     ArrayList al = new ArrayList();
/*     */ 
/*  81 */     int start_pos = 0;
/*  82 */     while (start_pos < s.length()) {
/*  83 */       int end_pos = s.indexOf(sep, start_pos);
/*  84 */       if (end_pos == -1) {
/*  85 */         end_pos = s.length();
/*     */       }
/*  87 */       String found_item = s.substring(start_pos, end_pos);
/*  88 */       al.add(found_item);
/*  89 */       start_pos = end_pos + 1;
/*     */     }
/*  91 */     if ((s.length() > 0) && (s.charAt(s.length() - 1) == sep)) {
/*  92 */       al.add("");
/*     */     }
/*  94 */     String[] returned_array = new String[al.size()];
/*  95 */     al.toArray(returned_array);
/*  96 */     return returned_array;
/*     */   }
/*     */ 
/*     */   public static String join(String[] strings, char joinChar) {
/* 100 */     StringBuilder result = new StringBuilder();
/* 101 */     int lastIdx = strings.length - 1;
/* 102 */     for (int idx = 0; idx < strings.length; idx++) {
/* 103 */       result.append(strings[idx]);
/* 104 */       if (idx < lastIdx) {
/* 105 */         result.append(joinChar);
/*     */       }
/*     */     }
/*     */ 
/* 109 */     return result.toString();
/*     */   }
/*     */ 
/*     */   public static String stripWhiteSpace(String s) {
/* 113 */     StringBuilder to = new StringBuilder();
/* 114 */     boolean inSpace = true;
/*     */ 
/* 117 */     for (int i = 0; i < s.length(); i++) {
/* 118 */       char c = s.charAt(i);
/* 119 */       boolean isSpace = Character.isWhitespace(c);
/* 120 */       if (!isSpace) {
/* 121 */         to.append(c);
/* 122 */         inSpace = false;
/* 123 */       } else if (!inSpace) {
/* 124 */         to.append(' ');
/* 125 */         inSpace = true;
/*     */       }
/*     */     }
/* 128 */     return to.toString().trim();
/*     */   }
/*     */ 
/*     */   public static String stripNewLines(String str)
/*     */   {
/* 142 */     int len = str.length();
/* 143 */     StringBuilder sb = new StringBuilder(len);
/* 144 */     for (int i = 0; i < len; i++) {
/* 145 */       char ch = str.charAt(i);
/* 146 */       if ((ch != '\r') && (ch != '\n')) {
/* 147 */         sb.append(ch);
/*     */       }
/*     */     }
/* 150 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public static String addNewline(String s)
/*     */   {
/* 167 */     int n = s.length() - 1;
/* 168 */     if (n == -1)
/* 169 */       return s;
/* 170 */     if (Character.isWhitespace(s.charAt(n))) {
/* 171 */       return s;
/*     */     }
/* 173 */     return s.concat(System.getProperty("line.separator"));
/*     */   }
/*     */ 
/*     */   public static String join(List elements, String sep)
/*     */   {
/* 189 */     StringBuilder sb = new StringBuilder();
/* 190 */     boolean first = true;
/* 191 */     Iterator iter = elements.iterator();
/*     */ 
/* 193 */     while (iter.hasNext()) {
/* 194 */       String element = (String)iter.next();
/*     */ 
/* 196 */       if (!first)
/* 197 */         sb.append(sep);
/*     */       else {
/* 199 */         first = false;
/*     */       }
/*     */ 
/* 202 */       sb.append(element);
/*     */     }
/*     */ 
/* 205 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public static String[] getParamFromString(String aSourceString, String aStartStr, String aEndStr) {
/* 209 */     aSourceString = aSourceString + aEndStr;
/* 210 */     String strSource = aSourceString;
/* 211 */     ArrayList strKey = new ArrayList();
/* 212 */     int iStartIndex = strSource.indexOf(aStartStr);
/* 213 */     int iStartLength = aStartStr.length();
/* 214 */     int iEndLength = aEndStr.length();
/* 215 */     String strTemp = "";
/* 216 */     strTemp = strSource.substring(iStartIndex + iStartLength, strSource.length());
/* 217 */     int iEndIndex = strTemp.indexOf(aEndStr) + strSource.substring(0, iStartIndex + iStartLength).length();
/* 218 */     if (iEndIndex == iStartIndex) {
/* 219 */       strKey.add(strTemp);
/*     */     }
/* 221 */     while ((iStartIndex != -1) && (iEndIndex != -1) && (iStartIndex < iEndIndex)) {
/* 222 */       strTemp = strSource.substring(iStartIndex + iStartLength, iEndIndex);
/* 223 */       strKey.add(strTemp);
/* 224 */       strSource = strSource.substring(iEndIndex + iEndLength, strSource.length());
/* 225 */       iStartIndex = strSource.indexOf(aStartStr);
/* 226 */       strTemp = strSource.substring(iStartIndex + iStartLength, strSource.length());
/* 227 */       iEndIndex = strTemp.indexOf(aEndStr) + strSource.substring(0, iStartIndex + iStartLength).length();
/*     */     }
/* 229 */     return (String[])strKey.toArray(new String[0]);
/*     */   }
/*     */   public static String replaceParamString(String source, String s1, String s2) {
/* 232 */     int index = source.indexOf(s1);
/* 233 */     if (index == 0)
/* 234 */       return s2 + source.substring(s1.length());
/* 235 */     if (index > 0) {
/* 236 */       return source.substring(0, index) + s2 + source.substring(index + s1.length());
/*     */     }
/* 238 */     return source;
/*     */   }
/*     */   public static String replaceParamString(String source, String[] l, String aCode, String aStartStr, String aEndStr) {
/* 241 */     for (int i = 0; i < l.length; i++) {
/* 242 */       source = replaceParamString(source, aStartStr + l[i] + aEndStr, aCode);
/*     */     }
/* 244 */     return source;
/*     */   }
/*     */   public static String replace(String s, String source, String dest) {
/* 247 */     return org.apache.commons.lang.StringUtils.replace(s, source, dest);
/*     */   }
/*     */   public static String replaceFirst(String s, String source, String dest) {
/* 250 */     return org.apache.commons.lang.StringUtils.replaceOnce(s, source, dest);
/*     */   }
/*     */ 
/*     */   public static void main2(String[] args) {
/* 254 */     System.out.println(replace(":abc 123:abc adaa :abc ", ":abc ", "789 "));
/*     */   }
/*     */   public static void main(String[] args) {
/* 257 */     String str = "abc = :bb and deg = :aa and dd = :bb";
/* 258 */     String[] list = getParamFromString(str, ":", " ");
/* 259 */     String result = replaceParamString(str, list, " ? ", ":", " ");
/* 260 */     System.out.println(str);
/* 261 */     for (int i = 0; i < list.length; i++)
/* 262 */       System.out.println(list[i].toString());
/* 263 */     System.out.println(result);
/*     */   }
/*     */ }

/* Location:           D:\workspace\AIBCOSS_Common_DEV\lib\appframe.jar
 * Qualified Name:     com.ai.appframe2.util.StringUtils
 * JD-Core Version:    0.6.2
 */