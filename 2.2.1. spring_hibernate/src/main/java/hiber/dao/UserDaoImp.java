package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   @SuppressWarnings("unchecked")
   public User getUserCar(String model, int series) {
      TypedQuery<User> userQuery = sessionFactory.getCurrentSession().createQuery("FROM User " +
              "WHERE userCar.model =: model AND userCar.series =: series");
      userQuery.setParameter("model", model);
      userQuery.setParameter("series", series);
      return userQuery.getSingleResult();
   }

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

}
