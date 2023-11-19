package mybatis;

public class MysqlDialect {

    /**
     * 获取Mysql数据库的分页查询语句
     *
     * @param page      分页对象
     * @param sqlBuffer 包含原sql语句的StringBuffer对象
     * @return Mysql数据库分页语句
     */
    public String getMysqlPageSql(Page<?> page, StringBuffer sqlBuffer) {
        //计算第一条记录的位置，Mysql中记录的位置是从0开始的。
        int offset = (page.getPageNo() - 1) * page.getPageSize();
        sqlBuffer.append(" limit ").append(offset).append(",").append(page.getPageSize());
        return sqlBuffer.toString();
    }

    /**
     * 获取Mysql数据库的分页查询语句
     *
     * @param page      分页对象
     * @param sqlBuffer 包含原sql语句的StringBuffer对象
     * @return Mysql数据库分页语句
     */
    public static String getMysqlPageSql(StringBuffer sqlBuffer, int offset, int limit) {
        //计算第一条记录的位置，Mysql中记录的位置是从0开始的。
        sqlBuffer.append(" limit ").append(offset).append(",").append(limit);
        return sqlBuffer.toString();
    }

    /**
     * 获取Mysql数据库的分页查询语句
     *
     * @param page      分页对象
     * @param sqlBuffer 包含原sql语句的StringBuffer对象
     * @return Mysql数据库分页语句
     */
    public static String getMysqlPageSql(String sql, int offset, int limit) {
        //计算第一条记录的位置，Mysql中记录的位置是从0开始的。
        sql = sql + " limit " + offset + "," + limit;
        return sql;
    }
}
