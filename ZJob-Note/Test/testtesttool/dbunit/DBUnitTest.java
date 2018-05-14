package testtesttool.dbunit;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DefaultDataSet;
import org.dbunit.dataset.DefaultTable;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;

public class DBUnitTest {

    /**
     * 删除t_user表中的数据
     * DatabaseOperation.DELETE ：这个操作删除表数据，只是删除存在于数据集中的数据而非表中的所有数据。
     * @throws Exception
     */
    @Test
    public void testDelete() throws Exception{
        DatabaseOperation.DELETE.execute(DBUnitTool.getPostgresqlDBConn(),DBUnitTool.getDataSet());
    }

    /**
     *
     * 删除t_user表中的数据
     *  DatabaseOperation.DELETE_ALL ：这个操作删除表中所有数据，无论数据集中的数据是否在目标数据库表中。
     * @throws Exception
     */
    @Test
    public void testDeleteAll() throws Exception{
        DatabaseOperation.DELETE_ALL.execute(new DatabaseConnection(DBUnitTool.getConnection()),DBUnitTool.getDataSet());
    }

    @Test
    public void testInsert() throws Exception{
        DatabaseOperation.INSERT.execute(new DatabaseConnection(DBUnitTool.getConnection()),DBUnitTool.getDataSet());
    }

    /**
     * 先delete_all，在insert;慎用
     * @throws Exception
     */
    @Test
    public void testCleanInsert() throws Exception{
        DatabaseOperation.CLEAN_INSERT.execute(new DatabaseConnection(DBUnitTool.getConnection()),DBUnitTool.getDataSet());
    }

    /**
     * 使用xml中的数据更应数据库中对应的数据
     * @throws Exception
     */
    @Test
    public void testUpdate() throws Exception{
        DatabaseOperation.UPDATE.execute(new DatabaseConnection(DBUnitTool.getConnection()),DBUnitTool.getDataSet());
    }

    /**
     * 备份整个数据库
     */
    @Test
    public void testBackupAll() throws Exception {
        // create DataSet from database.
        IDataSet ds = DBUnitTool.getPostgresqlDBConn().createDataSet();
        // create temp file
        File tempFile = File.createTempFile("tb-all-temp", "xml",new File("D:/temp"));
        // write the content of database to temp file
        FlatXmlDataSet.write(ds, new FileWriter(tempFile), "UTF-8");
    }

    /**
     * 备份制定表的数据
     *
     * @Title: backupCustom
     * @throws Exception
     */
    @Test
    public void backupCustom() throws Exception {
        String[] tablenames = new String[]{"t_user"};
        // back up specific files
        QueryDataSet qds = new QueryDataSet(DBUnitTool.getPostgresqlDBConn());
        for (String str : tablenames) {
            qds.addTable(str);
        }
        File tempFile = File.createTempFile("tb-temp", "xml",new File("D:/temp"));
        FlatXmlDataSet.write(qds, new FileWriter(tempFile), "UTF-8");

    }
}
