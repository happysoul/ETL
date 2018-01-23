package com.sql9.db;

import com.sql9.db.CommonDB.DBType;

/* compiled from: Unknown Source */
public class ConnectionFactory {
    public static DBConnection createConnection(CommonDB db) throws Exception {
        DBType dbtype = db.getDbType();
        if (dbtype.equals(DBType.ASA)) {
            return new ASAConnection(db);
        }
        if (dbtype.equals(DBType.ASE)) {
            return new ASEConnection(db);
        }
        if (dbtype.equals(DBType.Access)) {
            return new AccessMDBConnection(db);
        }
        if (dbtype.equals(DBType.DB2)) {
            return new DB2Connection(db);
        }
        if (dbtype.equals(DBType.MySQL)) {
            return new XxConnection(db);
        }
        if (dbtype.equals(DBType.Oracle)) {
            return new OracleConnection(db);
        }
        if (dbtype.equals(DBType.PostgreSQL)) {
            return new PostgreSQLConnection(db);
        }
        if (dbtype.equals(DBType.SQLServer)) {
            return new SQLServerConnection(db);
        }
        if (dbtype.equals(DBType.SQLite)) {
            return new SQLiteConnection(db);
        }
        if (dbtype.equals(DBType.CUBRID)) {
            return new CubridConnection(db);
        }
        return null;
    }
}
