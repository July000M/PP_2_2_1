package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private static final String GET_USER_BY_CAR_HQL =
            "FROM User user WHERE user.car.model=:model AND user.car.series=:series";

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public User getUserByCar(String model, int series) {
        TypedQuery<User> query2 = sessionFactory.getCurrentSession().createQuery(GET_USER_BY_CAR_HQL)
                .setParameter("model", model)
                .setParameter("series", series);
        return query2.getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsersByCar(String model, int series) {
        TypedQuery<User> query2 = sessionFactory.getCurrentSession().createQuery(GET_USER_BY_CAR_HQL)
                .setParameter("model", model)
                .setParameter("series", series);
        return query2.getResultList();
    }
}
