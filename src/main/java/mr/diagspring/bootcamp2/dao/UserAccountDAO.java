package mr.diagspring.bootcamp2.dao;

import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mr.diagspring.bootcamp2.entites.AccountUserEntity;

public class UserAccountDAO extends RepositoryImpl<AccountUserEntity> implements IUserDAO {
	private Logger logger = LoggerFactory.getLogger(UserAccountDAO.class);

	// API critria
	@Override
	public Optional<AccountUserEntity> login(String email, String pwd) {
		logger.info("Email doa : {}", email);
		CriteriaBuilder cb = this.session.getSession().getCriteriaBuilder();
		CriteriaQuery<AccountUserEntity> cr = cb.createQuery(AccountUserEntity.class);
		Root<AccountUserEntity> user = cr.from(AccountUserEntity.class);
		// Predicate pour la clause where
		Predicate predicateEmail = cb.equal(user.get("email"), email);
		Predicate predicatePwd = cb.equal(user.get("password"), pwd);
		Predicate finalPredicate = cb.and(predicateEmail, predicatePwd);

		cr.select(user);
		cr.where(finalPredicate);

		return Optional.ofNullable(this.session.getSession().createQuery(cr).getSingleResult());
	}

}