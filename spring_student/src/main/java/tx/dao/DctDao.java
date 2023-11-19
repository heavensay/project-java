package tx.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import tx.entity.DctConstant;

public class DctDao {

    private JdbcTemplate jdbcTemplate;

    public void saveDctConstant(DctConstant constant) {
        jdbcTemplate.update("INSERT INTO tes_constant(typecode,typename) " +
                " values(?,?)", constant.getTypecode(), constant.getTypename());
    }

    public void saveBeThrowRuntimeException() {
        throw new RuntimeException("插入异常 RuntimeException  dct");
    }

    public void saveBeThrowException() throws Exception {
        throw new Exception("插入异常 Exception dct");
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

}
