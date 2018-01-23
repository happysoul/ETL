package com.sql9.db;

import java.util.HashMap;
import java.util.Map;

public class DriverInfo {
    static final Map<String, DriverInfo> _$1 = new HashMap<String, DriverInfo>();
   static final DriverInfo _$2 = new DriverInfo("cubrid.jdbc.driver.CUBRIDDriver", "jdbc:cubrid:{host}:{port}:{database}:::");
    static final DriverInfo _$3 = new DriverInfo("sun.jdbc.odbc.JdbcOdbcDriver", "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ={database}");
    static final DriverInfo _$4 = new DriverInfo("org.sqlite.JDBC", "jdbc:sqlite:{database}");
    static final DriverInfo _$5 = new DriverInfo("net.sourceforge.jtds.jdbc.Driver", "jdbc:jtds:sqlserver://{host}:{port}/{database}");
    static final DriverInfo _$6 = new DriverInfo("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://{host}:{port};DatabaseName={database}");
    static final DriverInfo _$7 = new DriverInfo("com.ibm.db2.jcc.DB2Driver", "jdbc:db2://{host}:{port}/{database}");
    static final DriverInfo _$8 = new DriverInfo("com.mysql.jdbc.Driver", "jdbc:mysql://{host}:{port}/{database}");
    static final DriverInfo _$9 = new DriverInfo("org.postgresql.Driver", "jdbc:postgresql://{host}:{port}/{database}");
    static final DriverInfo _$10 = new DriverInfo("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@{host}:{port}:{database}");
    static final DriverInfo _$11 = new DriverInfo("com.extendedsystems.jdbc.advantage.ADSDriver", "jdbc:extendedsystems:advantage://{host}:{port};catalog={database}");
    static final DriverInfo _$12 = new DriverInfo("net.sourceforge.jtds.jdbc.Driver", "jdbc:jtds:sybase://{host}:{port}/{database}");
    static final DriverInfo _$13 = new DriverInfo("com.sybase.jdbc3.jdbc.SybDriver", "jdbc:sybase:Tds:{host}:{port}/{database}");
    static final DriverInfo _$14 = new DriverInfo("com.sybase.jdbc3.jdbc.SybDriver", "jdbc:sybase:Tds:{host}:{port}/{database}");
    String _$15;
    String _$16;

    public DriverInfo(String driver, String url) {
        this._$16 = driver;
        this._$15 = url;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DriverInfo)) {
            return false;
        }
        DriverInfo t = (DriverInfo) o;
        if (t._$16.equals(this._$16) && t._$15.equals(this._$15)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this._$16 != null) {
            hashCode = this._$16.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + 329) * 47;
        if (this._$15 != null) {
            i = this._$15.hashCode();
        }
        return hashCode + i;
    }

    static {
        _$1.put("ase", _$14);
        _$1.put("jdbc:sybase", _$14);
        _$1.put("asa", _$13);
        _$1.put("jdbc:jtds:sybase", _$12);
        _$1.put("ads", _$11);
        _$1.put("oracle", _$10);
        _$1.put("jdbc:oracle", _$10);
        _$1.put("pgsql", _$9);
        _$1.put("jdbc:postgresql", _$9);
        _$1.put("mysql", _$8);
        _$1.put("jdbc:mysql", _$8);
        _$1.put("db2", _$7);
        _$1.put("jdbc:db2", _$7);
        _$1.put("mssql", _$6);
        _$1.put("jdbc:sqlserver", _$6);
        _$1.put("jdbc:jtds:sqlserver", _$5);
        _$1.put("sqlite", _$4);
        _$1.put("jdbc:sqlite", _$4);
        _$1.put("jdbc:odbc:driver={Microsoft Access Driver (*.mdb)}", _$3);
        _$1.put("jdbc:cubrid", _$2);
        _$1.put("cubrid", _$2);
    }

    public static DriverInfo searchFromUrl(String url) {
        for (String s : _$1.keySet()) {
            if (url.startsWith(s)) {
                return (DriverInfo) _$1.get(s);
            }
        }
        return null;
    }
}
