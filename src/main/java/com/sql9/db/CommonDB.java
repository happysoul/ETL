package com.sql9.db;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.myetl.MDBUtil;

public class CommonDB {
    protected DriverInfo _$1;
    protected DatabaseMetaData _$2;
    private Connection _$3;
    protected Properties _$4;
    private String _$5;
    private String _$6;
    private String _$7;

    /* compiled from: Unknown Source */
    public enum DBType {
        ASE,
        ASA,
        PostgreSQL,
        DB2,
        Oracle,
        SQLServer,
        ADS,
        MySQL,
        SQLite,
        Access,
        CUBRID
    }

    public String getUserName() {
        return this._$6;
    }

    public CommonDB(String url, String username, String password, Properties props) throws Exception {
        this._$7 = url;
        this._$6 = username;
        this._$5 = password;
        this._$4 = props;
        DriverInfo di = DriverInfo.searchFromUrl(this._$7);
        Class.forName(di._$16);
        if (di._$15.startsWith("jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=")) {
            String dbfile = this._$7.substring("jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=".length());
            if (!new File(dbfile).exists()) {
                MDBUtil.createMDB(dbfile);
            }
        }
        if (di.equals(DriverInfo._$14) || di.equals(DriverInfo._$13)) {
            this._$7 += "?DYNAMIC_PREPARE=true";
        } else if (di.equals(DriverInfo._$8)) {
            this._$7 += "?useunicode=true&characterEncoding=utf8";
        } else if (di.equals(DriverInfo._$2)) {
            this._$7 += "?charset=utf-8";
        }
        this._$3 = DriverManager.getConnection(this._$7, this._$6, this._$5);
        this._$2 = this._$3.getMetaData();
        this._$1 = di;
    }

    public Connection getConnection() {
        return this._$3;
    }

    public void close() throws SQLException {
        if (this._$3 != null) {
            if (!this._$3.getAutoCommit()) {
                this._$3.commit();
            }
            this._$3.close();
            this._$3 = null;
        }
    }

    public List<String> getTables() throws SQLException {
        List<String> res = new ArrayList<String>();
        ResultSet rset = this._$2.getTables(null, null, null, new String[]{"TABLE"});
        while (rset.next()) {
            String schema = rset.getString("TABLE_SCHEM");
            if (schema == null) {
                res.add(rset.getString("TABLE_NAME"));
            } else {
                res.add(schema + "." + rset.getString("TABLE_NAME"));
            }
        }
        rset.close();
        return res;
    }

    public List<String> getTablesWithoutPrefix() throws SQLException {
        List<String> res = new ArrayList<String>();
        ResultSet rset = this._$2.getTables(null, null, null, new String[]{"TABLE"});
        while (rset.next()) {
            res.add(rset.getString("TABLE_NAME"));
        }
        rset.close();
        return res;
    }

    public String getDetails() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(this._$2.getDatabaseProductName() + " [version: ");
        sb.append(this._$2.getDatabaseProductVersion() + "] ");
        sb.append(" [Driver:   " + this._$2.getDriverName() + " [version: ");
        sb.append(this._$2.getDriverVersion() + "]]");
        return sb.toString();
    }

    public String getDbProductName() throws Exception {
        return this._$2.getDatabaseProductName();
    }

    public DBType getDbType() throws Exception {
        if (this._$1.equals(DriverInfo._$11)) {
            return DBType.ADS;
        }
        if (this._$1.equals(DriverInfo._$6)) {
            return DBType.SQLServer;
        }
        if (this._$1.equals(DriverInfo._$8)) {
            return DBType.MySQL;
        }
        if (this._$1.equals(DriverInfo._$10)) {
            return DBType.Oracle;
        }
        if (this._$1.equals(DriverInfo._$9)) {
            return DBType.PostgreSQL;
        }
        if (this._$1.equals(DriverInfo._$7)) {
            return DBType.DB2;
        }
        if (this._$1.equals(DriverInfo._$4)) {
            return DBType.SQLite;
        }
        if (this._$1.equals(DriverInfo._$3)) {
            return DBType.Access;
        }
        if (this._$1.equals(DriverInfo._$14)) {
            if (getDbProductName().equals("Adaptive Server Enterprise")) {
                return DBType.ASE;
            }
            return DBType.ASA;
        } else if (this._$1.equals(DriverInfo._$2)) {
            return DBType.CUBRID;
        } else {
            return null;
        }
    }

    public void importDataToTargetDB(CommonDB targetDB, List<String> tables, TextWriter tw) throws Exception {
        if (this._$3.isClosed()) {
            tw.println("The connection of the source db is closed.");
        } else if (targetDB._$3.isClosed()) {
            tw.println("The connection of the target db is closed.");
        } else {
            ConnectionFactory.createConnection(this).importTo(ConnectionFactory.createConnection(targetDB), tables, tw);
        }
    }

    public void importDataToTargetDB(CommonDB targetDB, String table, TextWriter tw) throws Exception {
        if (this._$3.isClosed()) {
            tw.println("The connection of the source db is closed.");
        } else if (targetDB._$3.isClosed()) {
            tw.println("The connection of the target db is closed.");
        } else {
            DBConnection conn = ConnectionFactory.createConnection(this);
            DBConnection desConn = ConnectionFactory.createConnection(targetDB);
            List<String> tables = new ArrayList<String>();
            tables.add(table);
            conn.importTo(desConn, tables, tw);
        }
    }
}
