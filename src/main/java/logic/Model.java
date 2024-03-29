package logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable {
    private static final Model instance = new Model();
    private final Map<Integer, User> model;

    public static Model getInstance() {
        return instance;
    }

    private Model() {
        model = new HashMap<>();

        model.put(1, new User("Marfa", "Nikitina", 10000));
        model.put(2, new User("Anton", "Nikitin", 15000));
        model.put(3, new User("Zoya", "Nikitina", 20000));
    }

    public void Add(User user, int id) {
        model.put(id, user);
    }

    public User getUser(int id) {
        return model.get(id);
    }

    public void delete(int id) {
        model.remove(id);
    }

    public void put(int id, User user) {
        model.put(id, user);
    }

    public Map<Integer, User> getFromList() {
        return model;
    }
}
