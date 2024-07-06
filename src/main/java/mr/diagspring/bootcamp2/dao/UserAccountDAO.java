package mr.diagspring.bootcamp2.dao;

import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transaction;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mr.diagspring.bootcamp2.config.HibernateUtil;
import mr.diagspring.bootcamp2.entites.AccountUserEntity;

public class UserAccountDAO extends RepositoryImpl<AccountUserEntity> implements IUserDAO {
	private Logger logger = LoggerFactory.getLogger(UserAccountDAO.class);

	// API critria
	@Override
	public Optional<AccountUserEntity> login(String email, String password) {
		AccountUserEntity result = null;
		logger.info("Email doa : {}", email);
		CriteriaBuilder cb = this.session.getSession().getCriteriaBuilder();
		CriteriaQuery<AccountUserEntity> cr = cb.createQuery(AccountUserEntity.class);
		Root<AccountUserEntity> user = cr.from(AccountUserEntity.class);
		// Predicate pour la clause where
		Predicate predicateEmail = cb.equal(user.get("email"), email);
		Predicate predicatePwd = cb.equal(user.get("password"), password);
		Predicate finalPredicate = cb.and(predicateEmail, predicatePwd);

		cr.select(user);
		cr.where(finalPredicate);
		try {
			result = session.createQuery(cr).getSingleResult();
			logger.info("Connexion ok");
		} catch (Exception e) {
			logger.error("Error :", e);
		} finally {
			session.close();
		}

		return Optional.ofNullable(this.session.getSession().createQuery(cr).getSingleResult());
	}

	public Optional<AccountUserEntity> logException(String email, String pwd) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<AccountUserEntity> cr = cb.createQuery(AccountUserEntity.class);
		Root<AccountUserEntity> user = cr.from(AccountUserEntity.class);
		// Predicate pour la clause where
		Predicate predicateEmail = cb.equal(user.get("email"), email);
		Predicate predicatePwd = cb.equal(user.get("password"), pwd);
		Predicate finalPredicate = cb.and(predicateEmail, predicatePwd);

		cr.select(user);
		cr.where(finalPredicate);

		return Optional.ofNullable(session.createQuery(cr).getSingleResult());
//		return Optional.ofNullable(result);
	}

	public Optional<AccountUserEntity> loginUser(String email, String pwd) {
		AccountUserEntity userEntity = null;
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = (Transaction) session.beginTransaction();
			userEntity = (AccountUserEntity) session
					.createQuery("SELECT u FROM AccountUserEntity u WHERE u.email = :email")
					.setParameter("email", email).uniqueResult();
			if (userEntity != null && userEntity.getPassword().equals(pwd)) {
				return Optional.ofNullable(userEntity);
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				// transaction.rollback();
			}
			logger.error("Error ", e);
		}
		return Optional.ofNullable(userEntity);
	}

	public Optional<AccountUserEntity> log(String email, String password) {

		if (email.equals("mohameddiagana807@gmail.com") && password.equals("passer123")) {
			AccountUserEntity user = new AccountUserEntity();
			user.setEmail(email);
			user.setPassword(password);

			return Optional.of(user);
		} else {
			return Optional.ofNullable(null);
		}

	}

}