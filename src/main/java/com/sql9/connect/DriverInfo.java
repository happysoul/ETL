package com.sql9.connect;

import java.util.HashMap;
import java.util.Map;

public class DriverInfo {
	
	String url;
	String driver;
	
    static final Map<String, DriverInfo> driverMap = new HashMap<String, DriverInfo>();
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

    static {
    	driverMap.put("ase", _$14);
    	driverMap.put("jdbc:sybase", _$14);
    	driverMap.put("asa", _$13);
    	driverMap.put("jdbc:jtds:sybase", _$12);
    	driverMap.put("ads", _$11);
    	driverMap.put("oracle", _$10);
    	driverMap.put("jdbc:oracle", _$10);
    	driverMap.put("pgsql", _$9);
    	driverMap.put("jdbc:postgresql", _$9);
    	driverMap.put("mysql", _$8);
    	driverMap.put("jdbc:mysql", _$8);
    	driverMap.put("db2", _$7);
    	driverMap.put("jdbc:db2", _$7);
    	driverMap.put("mssql", _$6);
    	driverMap.put("jdbc:sqlserver", _$6);
    	driverMap.put("jdbc:jtds:sqlserver", _$5);
    	driverMap.put("sqlite", _$4);
    	driverMap.put("jdbc:sqlite", _$4);
    	driverMap.put("jdbc:odbc:driver={Microsoft Access Driver (*.mdb)}", _$3);
    	driverMap.put("jdbc:cubrid", _$2);
    	driverMap.put("cubrid", _$2);
    }

    public DriverInfo(String driver, String url) {
        this.driver = driver;
        this.url = url;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DriverInfo)) {
            return false;
        }
        DriverInfo t = (DriverInfo) o;
        if (t.driver.equals(this.driver) && t.url.equals(this.url)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this.driver != null) {
            hashCode = this.driver.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + 329) * 47;
        if (this.url != null) {
            i = this.url.hashCode();
        }
        return hashCode + i;
    }


    public static DriverInfo searchFromUrl(String url) {
        for (String s : driverMap.keySet()) {
            if (url.startsWith(s)) {
                return (DriverInfo) driverMap.get(s);
            }
        }
        return null;
    }
}
