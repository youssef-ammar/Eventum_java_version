package Services;

import Modules.Forum;

import java.sql.SQLException;
import java.util.List;

public interface IServices<T> {
    public void add(T t);
    public void addF(T t);
    public boolean delete(T t);

    public boolean update(T t);

    public List<T> getList();
}
