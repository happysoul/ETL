package com.sql9.connect;

import java.util.HashMap;
import java.util.Map;

public class DriverInfo {
	
	String url;
	String driver;
	
    static final Map<String, DriverInfo> driverMap = new HashMap<String, DriverInfo>();
    static final DriverInfo cubrid 		= new DriverInfo("cubrid.jdbc.driver.CUBRIDDriver", "jdbc:cubrid:{host}:{port}:{database}:::");
//    static final DriverInfo mdb 		= new DriverInfo("sun.jdbc.odbc.JdbcOdbcDriver", "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ={database}");
    static final DriverInfo mdb 		= new DriverInfo("net.ucanaccess.jdbc.UcanaccessDriver", "jdbc:ucanaccess://{database}");
    static final DriverInfo sqlite 		= new DriverInfo("org.sqlite.JDBC", "jdbc:sqlite:{database}");
    static final DriverInfo jtds_sqlserver = new DriverInfo("net.sourceforge.jtds.jdbc.Driver", "jdbc:jtds:sqlserver://{host}:{port}/{database}");
    static final DriverInfo mssql 		= new DriverInfo("com.microsoft.sqlserver.jdbc.SQLServerDriver", "jdbc:sqlserver://{host}:{port};DatabaseName={database}");
    static final DriverInfo db2 		= new DriverInfo("com.ibm.db2.jcc.DB2Driver", "jdbc:db2://{host}:{port}/{database}");
    static final DriverInfo mysql 		= new DriverInfo("com.mysql.jdbc.Driver", "jdbc:mysql://{host}:{port}/{database}");
    static final DriverInfo postgresql	= new DriverInfo("org.postgresql.Driver", "jdbc:postgresql://{host}:{port}/{database}");
    static final DriverInfo oracle 		= new DriverInfo("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@{host}:{port}:{database}");
    static final DriverInfo ads 		= new DriverInfo("com.extendedsystems.jdbc.advantage.ADSDriver", "jdbc:extendedsystems:advantage://{host}:{port};catalog={database}");
    static final DriverInfo jtds_sybase = new DriverInfo("net.sourceforge.jtds.jdbc.Driver", "jdbc:jtds:sybase://{host}:{port}/{database}");
    static final DriverInfo asa 		= new DriverInfo("com.sybase.jdbc3.jdbc.SybDriver", "jdbc:sybase:Tds:{host}:{port}/{database}");
    static final DriverInfo ase 		= new DriverInfo("com.sybase.jdbc3.jdbc.SybDriver", "jdbc:sybase:Tds:{host}:{port}/{database}");

    static {
    	driverMap.put("ase", ase);
    	driverMap.put("jdbc:sybase", ase);
    	driverMap.put("asa", asa);
    	driverMap.put("jdbc:jtds:sybase", jtds_sybase);
    	driverMap.put("ads", ads);
    	driverMap.put("oracle", oracle);
    	driverMap.put("jdbc:oracle", oracle);
    	driverMap.put("pgsql", postgresql);
    	driverMap.put("jdbc:postgresql", postgresql);
    	driverMap.put("mysql", mysql);
    	driverMap.put("jdbc:mysql", mysql);
    	driverMap.put("db2", db2);
    	driverMap.put("jdbc:db2", db2);
    	driverMap.put("mssql", mssql);
    	driverMap.put("jdbc:sqlserver", mssql);
    	driverMap.put("jdbc:jtds:sqlserver", jtds_sqlserver);
    	driverMap.put("sqlite", sqlite);
    	driverMap.put("jdbc:sqlite", sqlite);
//    	driverMap.put("jdbc:odbc:driver={Microsoft Access Driver (*.mdb)}", mdb);
    	driverMap.put("jdbc:ucanaccess", mdb);
    	driverMap.put("jdbc:cubrid", cubrid);
    	driverMap.put("cubrid", cubrid);
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
