package com.sql9.enums;

public enum SqlType {
	VARCHAR(12),
    INTEGER(4),
    DOUBLE(8),
    BOOLEAN(-7),
    BYTE(-6),
    VARBINARY(-3),
    LONGVARBINARY(-4),
    TIME(92),
    TIMESTAMP(93),
    DATE(91),
    BIGINT(-5),
    BINARY(-2),
    FLOAT(6),
    DECIMAL(3);
    
    private int sqlType;
    private SqlType(int sqlType) {
        this.sqlType = sqlType;
    }
    public int getSqlType() {
        return this.sqlType;
    }
}
