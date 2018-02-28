package dao.sqlBuilder;

/**
 * Created by Andrey on 12.01.2017.
 */
public class InsertBuilder implements SqlBuilder {

    private String insertInto;
    private String values;

    public InsertBuilder insertInto(String tableName, String columns) {
        this.insertInto = tableName + " (" + columns + ")";
        return this;
    }

    public InsertBuilder values(String values) {
        this.values = values;
        return this;
    }

    @Override
    public String build() {
        String result = "INSERT INTO " + insertInto + " VALUES(" + values + ");";
        return result;
    }
}
