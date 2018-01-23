package com.sql9.db;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DB2Connection extends DBConnection {
    public DB2Connection(CommonDB db) throws Exception {
        super(db);
    }

    protected String _$1(boolean isAutoIncrement, boolean isSigned, int isNullable, boolean isAutoIdentity, int stdType, int length, int precision, int scale) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setParam(PreparedStatement stmt, int i, Object obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setParam(ResultSet srcRset, PreparedStatement stmt, ResultSetMetaData meta, int i, Object obj) throws SQLException, IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
