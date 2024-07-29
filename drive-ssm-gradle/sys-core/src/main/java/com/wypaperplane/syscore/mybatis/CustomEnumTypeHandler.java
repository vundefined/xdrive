package com.wypaperplane.syscore.mybatis;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
mybatis 有两个枚举类型处理器: (默认的是2)
1. EnumOrdinalTypeHandler // Enum.ordinal() 存枚举的下标，如: 0
2. EnumTypeHandler // Enum.name() 存枚举字符串，如: "PENDING"
 */

public class CustomEnumTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E> {

    private final Logger logger = LoggerFactory.getLogger(CustomEnumTypeHandler.class);

    private final Class<E> type;
    private final E[] enums;

    public CustomEnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;

        this.enums = type.getEnumConstants();
        if (this.enums == null) {
            throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type.");
        }
    }

    // index 表示 column 下标
    // 定义设置参数时，该如何把 Java 类型的参数转换为对应的数据库类型
    @Override
    public void setNonNullParameter(PreparedStatement ps, int index, E parameter, JdbcType jdbcType) throws SQLException {
        if (jdbcType == null) {
            ps.setString(index, parameter.name());
        } else {
            ps.setObject(index, parameter.name(), jdbcType.TYPE_CODE); // see r3589
        }
    }

    // 定义通过字段名称获取字段数据时，如何把数据库类型转换为对应的 Java 类型
    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String s = rs.getString(columnName);
        return s == null ? null : Enum.valueOf(type, s);
    }

    // 定义通过字段索引获取字段数据时，如何把数据库类型转换为对应的 Java 类型
    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String s = rs.getString(columnIndex);
        return s == null ? null : Enum.valueOf(type, s);
    }

    // 定义调用存储过程后，如何把数据库类型转换为对应的 Java 类型
    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String s = cs.getString(columnIndex);
        return s == null ? null : Enum.valueOf(type, s);
    }

    private E toOrdinalEnum(int ordinal) {
        try {
            // Method method = type.getMethod("getEnum", int.class);
            // (E) method.invoke(type, ordinal);
            return enums[ordinal];
        } catch (Exception ex) {
            throw new IllegalArgumentException("Cannot convert " + ordinal + " to " + type.getSimpleName() + " by ordinal value.", ex);
        }
    }
}
