package com.sql9.connect;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.TreeMap;

import javax.sql.rowset.CachedRowSet;

import com.sql9.enums.DBType;
import com.sql9.util.StringUtil;
//import com.sun.rowset.CachedRowSetImpl;
import com.sun.rowset.CachedRowSetImpl;

public abstract class DBConnection {
    public static final int MAX_BATCH_COMMIT = 2000;
    DBType dbType;
    CommonDB db;

    protected abstract String _$1(boolean z, boolean z2, int i, boolean z3, int i2, int i3, int i4, int i5);

    public abstract void setParam(PreparedStatement preparedStatement, int i, Object obj) throws SQLException;

    public abstract void setParam(ResultSet resultSet, PreparedStatement preparedStatement, ResultSetMetaData resultSetMetaData, int i, Object obj) throws SQLException, IOException;

    protected void _$1(DBConnection dbconn, String t, String pk, TextWriter tw) {
        if (!dbconn.dbType.equals(DBType.SQLite)) {
            if (pk == null) {
                tw.println("       No PK exists in table: " + t);
                return;
            }
            String sql = "ALTER TABLE \"" + t + "\" add " + pk;
            try {
                tw.println("       pk definition: " + pk);
                dbconn.executeUpdate(sql, new Object[0]);
                tw.println("       table: " + t + "  PK created ...");
            } catch (SQLException ex) {
                tw.println("       table: " + t + "  PK create FAILED ...");
                tw.println(ex.toString());
            }
        }
    }

    /**
     * 执行拼接创建DDL和插入数据
     * @param dbconn
     * @param meta
     * @param schema
     * @param t
     * @param pk
     * @param tw
     * @return
     * @throws Exception
     */
    protected String createTableAndInsert(DBConnection dbconn, ResultSetMetaData meta, String schema, String t, String pk, TextWriter tw) throws Exception {
        String insert = "insert into \"" + t + "\"(";
        String sql = "create table \"" + t + "\"(";
        try {
            int i;
            int count = meta.getColumnCount();
            for (i = 1; i <= count; i++) {
                String column = meta.getColumnName(i);
                sql = sql + "\"" + column + "\" ";
                insert = insert + "\"" + column + "\" ";
                int isNullable = meta.isNullable(i);
                if (isNullable == 1) {
                    isNullable = _$1(schema, t, column);
                }
                StringBuilder append = new StringBuilder().append(sql);
                StringBuilder stringBuilder = append;
                sql = stringBuilder.append(dbconn._$1(meta.isAutoIncrement(i), meta.isSigned(i), isNullable, meta.isAutoIncrement(i), meta.getColumnType(i), meta.getColumnDisplaySize(i), meta.getPrecision(i), meta.getScale(i))).toString();
                if (i < count) {
                    sql = sql + ", ";
                    insert = insert + ", ";
                }
            }
            if (pk != null && dbconn.dbType.equals(DBType.SQLite)) {
                sql = sql + "," + pk;
            }
            sql = sql + ")";
            
            insert = insert + ") values (";
            for (i = 1; i <= count; i++) {
                insert = insert + "?";
                if (i < count) {
                    insert = insert + ", ";
                }
            }
            insert = insert + ")";
            tw.println("       Table ["+t+"] DDL: " + sql);
            Statement stmt = dbconn.getConnection().createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            return insert;
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    public DBConnection(CommonDB db) throws Exception {
        this.db = db;
        this.dbType = db.getDbType();
    }

    public DatabaseMetaData getMetaData() {
        return this.db.metaData;
    }

    public String getUserName() {
        return this.db.getUserName();
    }

    protected int _$1(String schema, String tname, String col) throws SQLException {
        return 1;
    }

    public String getColumnDefinition(String schema, String tname, String col) throws SQLException {
        String t = null;
        ResultSet rset = getMetaData().getColumns(null, schema, tname, col);
        boolean isAutoIncrement = false;
        boolean hasAutoIncrementColumn = rset.getMetaData().getColumnCount() >= 23;
        if (rset.next()) {
            int stdType = rset.getInt("DATA_TYPE");
//            String type_name = rset.getString("TYPE_NAME");
            int length = rset.getInt("COLUMN_SIZE");
//            int scale = rset.getInt("DECIMAL_DIGITS");
            boolean isNullable = rset.getInt("NULLABLE") == 1;
            if (hasAutoIncrementColumn) {
                isAutoIncrement = "YES".equalsIgnoreCase(rset.getString("IS_AUTOINCREMENT"));
            }
            if (!isAutoIncrement) {
                switch (stdType) {
                    case -7:
                        t = "BIT";
                        break;
                    case -6:
                        t = "SHORT";
                        break;
                    case -5:
                    case 4:
                        t = "INT";
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
                        t = "SHORT";
                        break;
                    case 6:
                        t = "FLOAT(" + 0 + ")";
                        break;
                    case 7:
                        t = "REAL";
                        break;
                    case 8:
                        t = "DOUBLE";
                        break;
                    case 12:
                        t = "VARCHAR(" + length + ")";
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
            }
            t = null + " AUTOINCREMENT ";
            if (isNullable) {
                t = t + " NULL";
            } else {
                t = t + " NOT NULL";
            }
        }
        rset.close();
        return t;
    }

    /**
     * 执行迁移数据
     * @param dbconn
     * @param tables
     * @param tw
     * @throws Exception
     */
    public void importTo(DBConnection dbconn, List<String> tables, TextWriter tw) throws Exception {
        List<String> targetList = dbconn.db.getTablesWithoutPrefix();
        for (String sName : tables) {
            String o = checkTableNameAndSubString46(sName);
            String schema = checkTableNameReturnNull(sName);
            tw.println("\r\n--- 开始处理 Table : " + o + " ---");
            if (targetList.contains(o)) {
                tw.println("       Table : " + o + " 目标库已经存在");
                tw.println("--- 结束处理 Table : " + o + " ---");
            } else {
                ResultSet rset;
                int i;
                String sql ="";
                String pk = getPKString(o);
                Statement srcStmt = this.db.getConnection().createStatement();
                try {
                    rset = srcStmt.executeQuery("select * from \"" + o + "\"");
                } catch (SQLException e) {
                    rset = srcStmt.executeQuery("select * from " + o);
                }
                ResultSetMetaData meta = rset.getMetaData();
                boolean hasIdentity = false;
                String identityCol = null;
                int identColNum = 0;
                for (i = 1; i <= meta.getColumnCount(); i++) {
                    if (meta.isAutoIncrement(i)) {
                        hasIdentity = true;
                        identityCol = meta.getColumnName(i);
                        identColNum = i;
                        break;
                    }
                }
                try {
                    if (!dbconn.dbType.equals(DBType.Access)) {
                        dbconn.setAutoCommit(true);
                    }
                } catch (SQLException e2) {
                    tw.println("       autocommit on setting warning...., ignored.");
                }
                try {
                    sql = createTableAndInsert(dbconn, meta, schema, o, pk, tw);
                    if (hasIdentity) {
                        tw.println("       table: " + o + " has identity column. ");
                        dbconn.setIdentityInsertEnabled(o, identityCol, true, tw);
                    }
                } catch (Exception e22) {
                    tw.println(StringUtil.getStackInfoFromException(e22));
                }
                try {
                    ResultSet rsetTS;
                    try {
                        rsetTS = dbconn.executeQuery("select * from \"" + o + "\" where 0 = 1", new Object[0]);
                    } catch (Exception e3) {
                        rsetTS = dbconn.executeQuery("select * from " + o + " where 0 = 1", new Object[0]);
                    }
                    ResultSetMetaData desMeta = rsetTS.getMetaData();
                    rsetTS.close();
                    if (!dbconn.dbType.equals(DBType.Access)) {
                        dbconn.setAutoCommit(false);
                    }
                    PreparedStatement pstmt = dbconn.db.getConnection().prepareStatement(sql);
                    int total_rows = 0;
                    while (rset.next()) {
                        i = 1;
                        while (i <= meta.getColumnCount()) {
                            Object obj = rset.getObject(i);
                            dbconn.setParam(rset, pstmt, desMeta, i, obj);
                            if (i == identColNum) {
//                                Object obj2 = obj;
                            }
                            i++;
                        }
                        try {
                            pstmt.execute();
                            total_rows++;
                            if (total_rows % MAX_BATCH_COMMIT == 0) {
                                dbconn.commit();
                            }
                        } catch (Exception e4) {
                            tw.println("       table:  " + o + " insert error: , column type: " + 0 + ", column i=" + i + ", caused by: ");
                            tw.println(StringUtil.getStackInfoFromException(e4));
                        }
                    }
                    if (total_rows % MAX_BATCH_COMMIT > 0) {
                        dbconn.commit();
                    }
                    if (!dbconn.dbType.equals(DBType.Access)) {
                        dbconn.setAutoCommit(true);
                    }
                    tw.println("       table:  " + o + " transfered : [" + total_rows + "] rows. ");
                    rset.close();
                    pstmt.close();
                    _$1(dbconn, o, pk, tw);
                    if (hasIdentity) {
                        dbconn.setIdentityInsertEnabled(o, identityCol, false, tw);
                    }
                    tw.println("       table:  " + o + " transfered finished.");
                    tw.println("--- end transfer table : " + o + " ---");
                } catch (SQLException ex) {
                    tw.println(StringUtil.getStackInfoFromException(ex));
                }
            }
        }
    }

    public void setParams(PreparedStatement stmt, Object[] params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            setParam(stmt, i + 1, params[i]);
        }
    }

    public Connection getConnection() {
        return this.db.getConnection();
    }

    public ResultSet executeQuery(String sql, Object[] params) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        setParams(stmt, params);
        ResultSet rset = stmt.executeQuery();
        CachedRowSet crset = new CachedRowSetImpl();
        crset.populate(rset);
        rset.close();
        stmt.close();
        return crset;
    }

    public int executeUpdate(String sql, Object[] params) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        setParams(stmt, params);
        int affected = stmt.executeUpdate();
        stmt.close();
        return affected;
    }

    public int[] executeUpdate(String sql, List<Object[]> paramsList) throws SQLException {
        int[] res = new int[paramsList.size()];
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        int i = 0;
        for (Object[] params : paramsList) {
            setParams(stmt, params);
            int i2 = i + 1;
            res[i] = stmt.executeUpdate();
            i = i2;
        }
        stmt.close();
        return res;
    }

    public void setIdentityInsertEnabled(String t, String identityCol, boolean enabled, TextWriter tw) {
        String sql = "set identity_insert \"" + t + "\"" + (enabled ? " ON " : " OFF ");
        Statement stmt;
        try {
            stmt = getConnection().createStatement();
//            int affected_count = 
            		stmt.executeUpdate(sql);
            getConnection().commit();
            stmt.close();
        } catch (SQLException e) {
            tw.println("       identity_insert ON/OFF setting is not supported");
        }
        if (this.dbType == DBType.Oracle && !enabled) {
            long maxIdent = 1;
            if (identityCol != null) {
                try {
                    ResultSet rset = executeQuery("SELECT max(\"" + identityCol + "\") FROM \"" + t + "\"", new Object[0]);
                    if (rset.next()) {
                        maxIdent = rset.getLong(1) + 1;
                    }
                    rset.close();
                    sql = "CREATE sequence \"iiseq_" + t + "\" start with " + maxIdent + " increment by 1";
                    tw.println("       SEQ: " + sql);
                    executeUpdate(sql, new Object[0]);
                    sql = "create or replace trigger \"trig_iiseq_" + t + "\" " + "before insert on \"" + t + "\" for each row " + "begin " + "if :new.\"" + identityCol + "\" is null then " + "select \"iiseq_" + t + "\".NEXTVAL into :new.\"" + identityCol + "\" from dual; " + "end if; " + "end;\n";
                    tw.println("       TRIGGER: " + sql);
                    stmt = getConnection().createStatement();
                    stmt.executeUpdate(sql);
                    getConnection().commit();
                    stmt.close();
                } catch (SQLException ex) {
                    tw.println("       Exception:   " + ex.toString());
                    tw.println("       Oracle sequence mapping failed during creation.");
                }
            }
        }
    }

    public void setAutoCommit(boolean enabled) throws SQLException {
        this.db.getConnection().setAutoCommit(enabled);
    }

    public boolean getAutoCommit() throws SQLException {
        return this.db.getConnection().getAutoCommit();
    }

    public void commit() throws SQLException {
        this.db.getConnection().commit();
    }

    public void rollback() throws SQLException {
        this.db.getConnection().rollback();
    }

    public String getPKString(String t) throws SQLException {
        String pk = null;
        ResultSet rs = this.db.metaData.getPrimaryKeys(null, null, t);
        StringBuilder sb = new StringBuilder();
        TreeMap<Integer, String> pksMap = new TreeMap<Integer, String>();
        while (rs.next()) {
            pksMap.put(new Integer(rs.getInt("KEY_SEQ")), rs.getString("COLUMN_NAME"));
        }
        for (Integer seq : pksMap.keySet()) {
            sb.append("\"" + ((String) pksMap.get(seq)) + "\",");
        }
        if (sb.length() > 0) {
            pk = " primary key (" + sb.substring(0, sb.length() - 1) + ")";
        }
        if (rs != null) {
            rs.close();
        }
        return pk;
    }

    protected String checkTableNameAndSubString46(String fullTableName) {
        if (fullTableName.indexOf(46) >= 0) {
            return fullTableName.substring(fullTableName.indexOf(46) + 1);
        }
        return fullTableName;
    }

    protected String checkTableNameReturnNull(String fullTableName) {
        if (fullTableName.indexOf(46) >= 0) {
            return fullTableName.substring(0, fullTableName.indexOf(46));
        }
        return null;
    }
}
