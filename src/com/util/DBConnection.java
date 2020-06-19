package com.util;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.exception.ApplicationException;
import org.apache.log4j.Logger;

import java.sql.Connection;

/**
 * 数据库连接
 * 从配置文件中读取相关数据库连接信息
 *
 *
 */
public class DBConnection {
	private static Logger logger = Logger.getLogger(DBConnection.class);

	static {
		final String driver = PropertyUtil.getProperty("driver");
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			logger.error("驱动加载失败:"+e.getMessage(),e);
			throw new ApplicationException("数据库驱动加载失败,请检查数据库配置!!!" + e.getMessage(), e);
		}
	}

	public static Connection getConnection() {
		Connection conn = null;
		try {
			final String url = PropertyUtil.getProperty("url");
			final String username = PropertyUtil.getProperty("username");
			final String password = PropertyUtil.getProperty("password");
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			throw new ApplicationException("数据库连接失败：" + e.getMessage(), e);
		}
		return conn;
	}

	public static void close(Connection connection, Statement statement, ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				logger.error("结果集关闭失败:" + e.getMessage(), e);
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				logger.error("Statement关闭失败:" + e.getMessage(), e);
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				logger.error("数据库连接关闭失败:" + e.getMessage(), e);
			}
		}
	}
}