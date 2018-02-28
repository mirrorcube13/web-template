package dao.sqlBuilder;

/**
 * Created by Andrey on 12.01.2017.
 */
public class DeleteBuilder implements SqlBuilder {

    private String delete;
    private String where;

    public DeleteBuilder delete(String delete) {
        this.delete = delete;
        return this;
    }

    public DeleteBuilder where(String where) {
        this.where = where;
        return this;
    }

    @Override
    public String build() {
        String result = "DELETE FROM " + delete + " WHERE " + where + ";";
        return result;
    }
}
