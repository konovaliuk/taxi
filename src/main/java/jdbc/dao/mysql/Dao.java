package jdbc.dao.mysql;

import jdbc.connection.DataSource;
import jdbc.dto.Dto;
import logging.LoggerLoader;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Dao<T extends Dto>  {

    private static final Logger log = LoggerLoader.getLogger(Dao.class);

    public class SelectOptions {
        private static final String ORDER_BY = " ORDER BY ";
        private String[][] orderByColumns = null;

        private Integer limit;
        private Integer offset;

        public SelectOptions(String[][] orderByColumns, Integer limit, Integer offset) {
            this.orderByColumns = orderByColumns;
            this.limit = limit;
            this.offset = offset;
        }

        public void setOrderByColumn(String column) {
            String[][] columns = new String[1][2];
            columns[0][0] = column;
            columns[0][1] = " ASC ";
            setOrderByColumns(columns);
        }

        public void setOrderByColumn(String column, boolean descending) {
            String[][] columns = new String[1][2];
            columns[0][0] = column;
            columns[0][1] = (descending) ? " DESC " : " ASC ";
            setOrderByColumns(columns);
        }

        public void setOrderByColumns(String[][] columns) {
            this.orderByColumns = columns;
        }

        public String[][] getOrderByColumns() {
            return this.orderByColumns;
        }

        public String getOrderByClause() {
            if (orderByColumns == null || orderByColumns.length <= 0)
                return "";
            StringBuilder sb = new StringBuilder(ORDER_BY);
            boolean first = true;

            for (String[] orderByColumn : orderByColumns) {
                if (!first) {
                    sb.append(", ");
                } else {
                    first = false;
                }
                sb.append(orderByColumn[0]);
                if (orderByColumn[1] != null) {
                    sb.append(" ");
                    sb.append(orderByColumn[1]);
                }
            }
            sb.append(" ");
            return sb.toString();
        }


        public void setLimit(Integer limit) {
            this.limit = limit;
        }

        public void setOffset(Integer offset) {
            this.offset = offset;
        }

        public Integer getLimit() {
            return limit;
        }

        public Integer getOffset() {
            return offset;
        }

        public String toString() {
            return getOrderByClause() +
                    ((limit != null && limit > 0) ? (" LIMIT " + limit) : "") +
                    ((offset != null && offset > 0) ? (" OFFSET " + offset) : "");
        }
    }

    protected Dao() {
    }

/////////////////////////////////////////////////////////////////////////////

    private DataSource dataSource;
    protected Dao(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    private Connection getConnection() throws SQLException {
        try {
            return dataSource.getConnection();
        } catch (Exception e) {
            throw new SQLException("Unable to get jdbc.connection");
        }
    }
/////////////////////////////////////////////////////////////////////////////



    protected int insert(T dto, String sql) throws SQLException {
        Integer generatedKey = -1;
        try (Connection con = getConnection()) {
            con.setAutoCommit(false);
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                fillPreparedStatement(dto, ps, false);

                int affectedRows = ps.executeUpdate();
                log.trace("SQL: " + sql);

                if (affectedRows > 0) {
                    try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            generatedKey = (int) generatedKeys.getLong(1);
                            con.commit();
                        } else {
                            con.rollback();
                            throw new SQLException("Creating failed, no ID obtained.");
                        }
                    }
                }
            }
            con.setAutoCommit(true);
        }
        return generatedKey;
    }

    protected int update(Integer pk, T dto, String sql) throws SQLException {
        try (Connection con = getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(sql)) {

                fillPreparedStatement(dto, ps, true);

                ps.setInt(4, pk);
                return ps.executeUpdate();
            }
        }
    }

    protected int delete(Integer pk, String sql) throws SQLException {
        int numRows;
        try (Connection con = getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, pk);
                numRows = ps.executeUpdate();
            }
        }
        return numRows;
    }


    protected T[] findByWhere(String sql, String where, Object[] sqlParams, SelectOptions opt) throws SQLException {
        if(opt != null) sql += opt;

        try(Connection con = getConnection()){
            if (where != null)
                sql += " WHERE " + where;
            try(PreparedStatement ps = con.prepareStatement(sql)){
                if (sqlParams != null) {
                    for (int i=0; i<sqlParams.length; i++) {
                        ps.setObject((i+1), sqlParams[i]);
                    }
                }
                log.trace("SQL: " + sql);
                try(ResultSet rs = ps.executeQuery()) {
                    return fetchMultipleResults(rs);
                }
            }
        }
    }

    protected int countByWhere(String sql, String where, Object[] sqlParams) throws SQLException {
        int count = 0;
        try(Connection con = getConnection()) {
            if (where != null)
                sql += " WHERE " + where;
            try(PreparedStatement ps = con.prepareStatement(sql)) {
                if (sqlParams != null) {
                    for (int i = 0; i < sqlParams.length; i++) {
                        ps.setObject((i + 1), sqlParams[i]);
                    }
                }
                try(ResultSet rs = ps.executeQuery()) {
                    while (rs.next())
                        count = rs.getInt(1);
                    return count;
                }
            }
        }
    }


    protected abstract T[] fetchMultipleResults(ResultSet rs) throws SQLException;

    protected abstract T fetchSingleResult(ResultSet rs) throws SQLException;

    protected abstract void fillPreparedStatement(T dto, PreparedStatement ps, boolean pkFill) throws SQLException;
}