package com.amazon.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * <p>
 * Represents the DATABASE Connection
 * </p>
 *
 * @author Roshan B
 * @version 1.0
 */

public class DBConnection {

    private static DBConnection DB_CONNECTION;
    private static final Integer MAX_SIZE = 15;
    private static BlockingQueue<Connection> pool;


    private DBConnection() {
        pool = new ArrayBlockingQueue<>(MAX_SIZE);

        initialize();
    }

    /**
     * <p>
     * Represents the instance of DBConnection class can be created for only once
     * </p>
     *
     * @return Represents {@link DBConnection }
     */
    public static DBConnection getInstance() {
        if (null == DB_CONNECTION) {
            DB_CONNECTION = new DBConnection();
        }
        return DB_CONNECTION;
    }

    private void initialize() {
        try {
            for (int i = 0; i < MAX_SIZE; i++) {
                final Connection connection = getConnection();

                if (null != connection) {
                    pool.offer(connection);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Represents the database connection
     *
     * @return Connection object
     * @throws SQLException if any error occur in connection it throws a sql exception
     */
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/Amazon", "postgres", "roshan");
    }

    public Connection get() throws InterruptedException {
        return pool.take();
    }

    public void close() throws SQLException {
        for (final Connection connection : pool) {
            connection.close();
        }
    }

    public void release(final Connection connection) {
        if (null != connection) {
            pool.offer(connection);
        }
    }
}
