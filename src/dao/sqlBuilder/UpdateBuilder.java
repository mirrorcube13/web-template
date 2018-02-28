package dao.sqlBuilder;

/**
 * Created by Andrey on 12.01.2017.
 */
public class UpdateBuilder implements SqlBuilder {

    private String update;
    private String set;
    private String where;

    public UpdateBuilder update(String update) {
        this.update = update;
        return this;
    }

    public UpdateBuilder set(String set) {
        this.set = set;
        return this;
    }

    public UpdateBuilder where(String where) {
        this.where = where;
        return this;
    }

    @Override
    public String build() {
        String result = "UPDATE " + update + " SET " + set;
        result += where == null ? ";" : " WHERE " + where + ";";
        return result;
    }
}
