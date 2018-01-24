package com.sql9.db;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

import com.sql9.connect.CommonDB;
import com.sql9.connect.DBConnection;
import com.sql9.enums.SqlType;

public class AccessMDBConnection extends DBConnection {
    public AccessMDBConnection(CommonDB db) throws Exception {
        super(db);
    }

    protected String _$1(boolean isAutoIncrement, boolean isSigned, int isNullable, boolean isAutoIdentity, int stdType, int length, int precision, int scale) {
        String t = "";
        if (!isAutoIncrement) {
            switch (stdType) {
                case -7:
                    t = "BIT";
                    break;
                case -6:
                    t = "SHORT";
                    break;
                case -5:
                	//int类型
                case 4:
//					t = isSigned ? "INT" : "INT";
                    t = isSigned ? "INTEGER" : "INTEGER";
                    break;
                case -4:
                case 2004:
                    t = "IMAGE";
                    break;
                case -3:
                    t = "VARBINARY(" + length + ")";
                    break;
                case -1:
                case 2005:
                    t = "TEXT";
                    break;
                case 1:
                    t = "CHAR(" + length + ")";
                    break;
                case 2:
                case 3:
                    t = "NUMERIC";
                    break;
                case 5:
                    t = isSigned ? "SHORT" : "SHORT";
                    break;
                case 6:
                    t = "FLOAT(" + precision + ")";
                    break;
                case 7:
                    t = "REAL";
                    break;
                case 8:
                    t = "DOUBLE";
                    break;
                case 12:
                    if (length < 255) {
                        t = "VARCHAR(" + length + ")";
                        break;
                    }
                    t = "TEXT";
                    break;
                case 16:
                    t = "BIT";
                    break;
                case 91:
                    t = "DATETIME";
                    break;
                case 92:
                    t = "DATETIME";
                    break;
                case 93:
                    t = "DATETIME";
                    break;
                default:
                    t = "VARCHAR(" + length + ")";
                    break;
            }
        }else{
        	t = t + " COUNTER PRIMARY KEY ";
        }
        if (isNullable == 1) {
            return t + " NULL";
        }
        return t + " NOT NULL";
    }

    public void setParam(PreparedStatement stmt, int i, Object obj) throws SQLException {
        if (obj instanceof SqlType) {
            stmt.setNull(i, ((SqlType) obj).getSqlType());
        } else if (obj instanceof String) {
            stmt.setString(i, (String) obj);
        } else if (obj instanceof Integer) {
            stmt.setInt(i, ((Integer) obj).intValue());
        } else if (obj instanceof Double) {
            stmt.setDouble(i, ((Double) obj).doubleValue());
        } else if (obj instanceof Boolean) {
            stmt.setBoolean(i, ((Boolean) obj).booleanValue());
        } else if (obj instanceof Byte) {
            stmt.setByte(i, ((Byte) obj).byteValue());
        } else if (obj instanceof ByteArrayOutputStream) {
            stmt.setBytes(i, ((ByteArrayOutputStream) obj).toByteArray());
        } else if (obj instanceof Time) {
            stmt.setTime(i, (Time) obj);
        } else if (obj instanceof Timestamp) {
            stmt.setTimestamp(i, (Timestamp) obj);
        } else if (obj instanceof InputStream) {
            try {
                stmt.setBinaryStream(i, (InputStream) obj, ((InputStream) obj).available());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (obj instanceof FileReader) {
            try {
                BufferedReader fr = new BufferedReader((FileReader) obj);
                char[] buf = new char[8192];
                int len = 0;
                fr.mark(Integer.MAX_VALUE);
                while (true) {
                    int tmp = fr.read(buf);
                    if (tmp > 0) {
                        len += tmp;
                    } else {
                        fr.reset();
                        stmt.setCharacterStream(i, fr, len);
                        return;
                    }
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        } else {
            stmt.setObject(i, obj);
        }
    }

    public void setParam(ResultSet srcRset, PreparedStatement pstmt, ResultSetMetaData meta, int i, Object obj) throws SQLException, IOException {
        int colType = meta.getColumnType(i);
        if (obj == null) {
            if (colType == -4 || colType == 2004) {
                pstmt.setBytes(i, new byte[0]);
            } else {
                pstmt.setNull(i, colType);
            }
        } else if (colType == -4 || colType == 2004 || (obj instanceof Blob)) {
            if (obj instanceof byte[]) {
                pstmt.setBytes(i, (byte[]) obj);
                return;
            }
            InputStream is = srcRset.getBinaryStream(i);
            pstmt.setBinaryStream(i, is, is.available());
        } else if (colType == -16 || colType == -1 || colType == 2005 || (obj instanceof Clob)) {
            InputStream as = srcRset.getAsciiStream(i);
            pstmt.setAsciiStream(i, as, as.available());
        } else if (obj.equals("")) {
            pstmt.setNull(i, colType);
        } else if (obj instanceof Timestamp) {
            pstmt.setTimestamp(i, new Timestamp(((Timestamp) obj).getTime()));
        } else if (obj instanceof BigDecimal) {
            pstmt.setString(i, obj.toString());
        } else if (obj instanceof BigInteger) {
            pstmt.setString(i, obj.toString());
        } else if (obj.getClass().getName().equals("oracle.sql.TIMESTAMP")) {
            pstmt.setTimestamp(i, srcRset.getTimestamp(i));
        } else if (obj instanceof String) {
            pstmt.setString(i, (String) obj);
        } else if (obj instanceof Integer) {
            pstmt.setInt(i, ((Integer) obj).intValue());
        } else if (obj instanceof Double) {
            pstmt.setDouble(i, ((Double) obj).doubleValue());
        } else if (obj instanceof Boolean) {
            pstmt.setBoolean(i, ((Boolean) obj).booleanValue());
        } else {
            pstmt.setObject(i, obj);
        }
    }

    public String getPKString(String t) throws SQLException {
        StringBuilder sb = new StringBuilder();
//        TreeMap<Integer, String> pksMap = new TreeMap();
        ResultSet rset = getMetaData().getIndexInfo(null, null, t, true, true);
        StringBuilder uniqueSb = new StringBuilder();
        String previousIdx = null;
        while (rset.next()) {
            String idx = rset.getString(6);
            if (idx != null) {
                if (idx.equalsIgnoreCase("PrimaryKey")) {
                    sb.append("\"" + rset.getString(9) + "\",");
                } else {
                    if (previousIdx == null || previousIdx.equals(idx)) {
                        uniqueSb.append("\"" + rset.getString(9) + "\",");
                    }
                    previousIdx = idx;
                }
            }
        }
        if (sb.length() > 0) {
            return " primary key (" + sb.substring(0, sb.length() - 1) + ")";
        }
        if (uniqueSb.length() > 0) {
            return " primary key (" + uniqueSb.substring(0, uniqueSb.length() - 1) + ")";
        }
        return null;
    }

    protected int _$1(String schema, String tname, String col) throws SQLException {
        ResultSet rset = getMetaData().getColumns(null, schema, tname, col);
        int isNullable = 1;
        if (rset.next()) {
            isNullable = rset.getInt("NULLABLE");
        }
        rset.close();
        if (isNullable != 1) {
            return isNullable;
        }
        String pk = getPKString(tname);
        if (pk == null || !pk.contains(col)) {
            return isNullable;
        }
        return 0;
    }
}
