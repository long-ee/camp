package com.campgem.common.typehandler;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JsonListTypeHandler<T extends Object> extends BaseTypeHandler<List<T>> {
	
	private static final ObjectMapper mapper = new ObjectMapper();
	private Class<T> clazz;
	
	public JsonListTypeHandler(Class<T> clazz) {
		if (clazz == null) {
			throw new IllegalArgumentException("Type argument cannot be null");
		}
		this.clazz = clazz;
	}
	
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, List<T> parameter, JdbcType jdbcType) throws SQLException {
		ps.setString(i, this.toJson(parameter));
	}
	
	@Override
	public List<T> getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return this.toObject(rs.getString(columnName), clazz);
	}
	
	@Override
	public List<T> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return this.toObject(rs.getString(columnIndex), clazz);
	}
	
	@Override
	public List<T> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return this.toObject(cs.getString(columnIndex), clazz);
	}
	
	private String toJson(List<T> object) {
		try {
			return mapper.writeValueAsString(object);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private List<T> toObject(String content, Class<?> clazz) {
		if (content != null && !content.isEmpty()) {
			try {
				return (List<T>)JSON.parseArray(content, clazz);
//				return (T) mapper.readValue(content, clazz);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		} else {
			return null;
		}
	}
	
}