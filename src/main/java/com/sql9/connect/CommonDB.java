package com.sql9.connect;

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
import com.sql9.enums.DBType;

public class CommonDB {
    protected DriverInfo driverInfo;
    protected DatabaseMetaData metaData;
    private Connection connection;
    protected Properties props;
    private String password;
    private String username;
    private String url;

    public String getUserName() {
        return this.username;
    }

    public CommonDB(String url, String username, String password, Properties props) throws Exception {
        this.url = url;
        this.username = username;
        this.password = password;
        this.props = props;
        DriverInfo di = DriverInfo.searchFromUrl(this.url);
        Class.forName(di.driver);
        if (di.url.startsWith("jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=")) {
            String dbfile = this.url.substring("jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=".length());
            if (!new File(dbfile).exists()) {
                MDBUtil.createMDB(dbfile);
            }
        }
        if (di.equals(DriverInfo._$14) || di.equals(DriverInfo._$13)) {
            this.url += "?DYNAMIC_PREPARE=true";
        } else if (di.equals(DriverInfo._$8)) {
            this.url += "?useunicode=true&characterEncoding=utf8";
        } else if (di.equals(DriverInfo._$2)) {
            this.url += "?charset=utf-8";
        }
        this.connection = DriverManager.getConnection(this.url, this.username, this.password);
        this.metaData = this.connection.getMetaData();
        this.driverInfo = di;
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void close() throws SQLException {
        if (this.connection != null) {
            if (!this.connection.getAutoCommit()) {
                this.connection.commit();
            }
            this.connection.close();
            this.connection = null;
        }
    }

    public List<String> getTables() throws SQLException {
        List<String> res = new ArrayList<String>();
        ResultSet rset = this.metaData.getTables(null, null, null, new String[]{"TABLE"});
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
        ResultSet rset = this.metaData.getTables(null, null, null, new String[]{"TABLE"});
        while (rset.next()) {
            res.add(rset.getString("TABLE_NAME"));
        }
        rset.close();
        return res;
    }

    public String getDetails() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(this.metaData.getDatabaseProductName() + " [version: ");
        sb.append(this.metaData.getDatabaseProductVersion() + "] ");
        sb.append(" [Driver:   " + this.metaData.getDriverName() + " [version: ");
        sb.append(this.metaData.getDriverVersion() + "]]");
        return sb.toString();
    }

    public String getDbProductName() throws Exception {
        return this.metaData.getDatabaseProductName();
    }

    public DBType getDbType() throws Exception {
        if (this.driverInfo.equals(DriverInfo._$11)) {
            return DBType.ADS;
        }
        if (this.driverInfo.equals(DriverInfo._$6)) {
            return DBType.SQLServer;
        }
        if (this.driverInfo.equals(DriverInfo._$8)) {
            return DBType.MySQL;
        }
        if (this.driverInfo.equals(DriverInfo._$10)) {
            return DBType.Oracle;
        }
        if (this.driverInfo.equals(DriverInfo._$9)) {
            return DBType.PostgreSQL;
        }
        if (this.driverInfo.equals(DriverInfo._$7)) {
            return DBType.DB2;
        }
        if (this.driverInfo.equals(DriverInfo._$4)) {
            return DBType.SQLite;
        }
        if (this.driverInfo.equals(DriverInfo._$3)) {
            return DBType.Access;
        }
        if (this.driverInfo.equals(DriverInfo._$14)) {
            if (getDbProductName().equals("Adaptive Server Enterprise")) {
                return DBType.ASE;
            }
            return DBType.ASA;
        } else if (this.driverInfo.equals(DriverInfo._$2)) {
            return DBType.CUBRID;
        } else {
            return null;
        }
    }

    public void importDataToTargetDB(CommonDB targetDB, List<String> tables, TextWriter tw) throws Exception {
        if (this.connection.isClosed()) {
            tw.println("The connection of the source db is closed.");
        } else if (targetDB.connection.isClosed()) {
            tw.println("The connection of the target db is closed.");
        } else {
            ConnectionFactory.createConnection(this).importTo(ConnectionFactory.createConnection(targetDB), tables, tw);
        }
    }

    public void importDataToTargetDB(CommonDB targetDB, String table, TextWriter tw) throws Exception {
        if (this.connection.isClosed()) {
            tw.println("The connection of the source db is closed.");
        } else if (targetDB.connection.isClosed()) {
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
