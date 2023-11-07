import java.util.List;

public interface PasswordDAO {
    void insert(PasswordInfo p);
    List<PasswordInfo> findAll();
    PasswordInfo findByKey(String url);
    void update(PasswordInfo p);
    void delete(String url);
    void delete(PasswordInfo p);
}
