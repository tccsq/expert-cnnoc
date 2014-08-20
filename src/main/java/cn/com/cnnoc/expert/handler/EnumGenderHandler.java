package cn.com.cnnoc.expert.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import cn.com.cnnoc.expert.model.Gender;

public class EnumGenderHandler extends BaseTypeHandler<Gender> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i,
			Gender parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(i, parameter.getValue());
	}

	@Override
	public Gender getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		int i = rs.getInt(columnName);
		if (rs.wasNull()) {
			return null;
		} else {
			return Gender.getGender(i);
		}
	}

	@Override
	public Gender getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		int i = rs.getInt(columnIndex);
		if (rs.wasNull()) {
			return null;
		} else {
			return Gender.getGender(i);
		}
	}

	@Override
	public Gender getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		int i = cs.getInt(columnIndex);
		if (cs.wasNull()) {
			return null;
		} else {
			return Gender.getGender(i);
		}
	}

}
