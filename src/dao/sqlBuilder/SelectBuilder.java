package dao.sqlBuilder;

/**
 * Created by Andrey on 12.01.2017.
 */
public class SelectBuilder implements SqlBuilder {

    private String select;
    private String from;
    private String join;
    private String on;
    private String where;
    private String groupBy;
    private String having;
    private String orderBy;
    private int limit = 0;

    public SelectBuilder select(String select) {
        this.select = select;
        return this;
    }

    public SelectBuilder from(String from) {
        this.from = from;
        return this;
    }

    public SelectBuilder join(String join) {
        this.join = join;
        return this;
    }

    public SelectBuilder on(String on) {
        this.on = on;
        return this;
    }

    public SelectBuilder where(String where) {
        this.where = where;
        return this;
    }

    public SelectBuilder groupBy(String groupBy) {
        this.groupBy = groupBy;
        return this;
    }

    public SelectBuilder having(String having) {
        this.having = having;
        return this;
    }

    public SelectBuilder orderBy(String orderBy) {
        this.orderBy = orderBy;
        return this;
    }

    public SelectBuilder limit(int limit) {
        this.limit = limit;
        return this;
    }

    @Override
    public String build() {
        String result = "SELECT " + select + " FROM " + from;
        result += join == null ? "" : " JOIN " + join + " ON " + on;
        result += where == null ? "" : " WHERE " + where;
        result += groupBy == null ? "" : " GROUP BY " + groupBy;
        result += having == null ? "" : " HAVING " + having;
        result += orderBy == null ? "" : " ORDER BY " + orderBy ;
        result += limit == 0 ? ";" : +limit + ";";
        return result;
    }
}
