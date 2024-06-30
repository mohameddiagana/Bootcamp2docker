package mr.diagspring.bootcamp2.dao;

import java.util.Optional;

import mr.diagspring.bootcamp2.entites.AccountUserEntity;

public interface IUserDAO extends Repository<AccountUserEntity> {

	Optional<AccountUserEntity> login(String email, String password);
}
