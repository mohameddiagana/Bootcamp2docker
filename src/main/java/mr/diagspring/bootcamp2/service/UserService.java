package mr.diagspring.bootcamp2.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mr.diagspring.bootcamp2.dao.IUserDAO;
import mr.diagspring.bootcamp2.dao.UserAccountDAO;
import mr.diagspring.bootcamp2.dto.UserDTO;
import mr.diagspring.bootcamp2.entites.AccountUserEntity;
import mr.diagspring.bootcamp2.mapper.AccountUserMapper;

public class UserService implements IUserService {

	private static Logger logger = LoggerFactory.getLogger(UserService.class);

	private IUserDAO userDao = new UserAccountDAO();

	@Override
	public Optional<UserDTO> login(String email, String password) {

		logger.info("Tentative email : {} ", email);

		return userDao.login(email, password).map(user -> {
			UserDTO userDto = AccountUserMapper.toUserAccountDto(user);

			return Optional.of(userDto);
		}).orElseThrow(() -> new EntityNotFoundException("email ou mot de passe incorrect !"));
	}

	public void setUserAccountDao(IUserDAO accountDao) {
		this.userDao = accountDao;
	}

	@Override
	public List<UserDTO> getAll() {
		return AccountUserMapper.toListUserAccountDto(userDao.list(new AccountUserEntity()));

	}

	@Override
	public Optional<UserDTO> save(UserDTO userDto) {
		boolean result = userDao.Ajout(AccountUserMapper.toUserAccountEntity(userDto));

		return (result) ? Optional.of(userDto) : Optional.empty();
	}

}
