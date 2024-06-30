package mr.diagspring.bootcamp2.mapper;

import java.util.List;

import mr.diagspring.bootcamp2.dto.UserDTO;
import mr.diagspring.bootcamp2.entites.AccountUserEntity;

public class AccountUserMapper {

	public static AccountUserEntity toUserAccountEntity(UserDTO userDTO) {

		AccountUserEntity userEntity = new AccountUserEntity();
		userEntity.setId(userDTO.getId());
		userEntity.setFirstName(userDTO.getFirstName());
		userEntity.setLastName(userDTO.getLastName());
		userEntity.setEmail(userDTO.getEmail());
		userEntity.setPassword(userDTO.getPassword());
		userEntity.setState(userDTO.isState());

		return userEntity;
	}

	public static UserDTO toUserAccountDto(AccountUserEntity userEntity) {

		UserDTO userDto = new UserDTO();
		userDto.setId((int) userEntity.getId());
		userDto.setFirstName(userEntity.getFirstName());
		userDto.setLastName(userEntity.getLastName());
		userDto.setEmail(userEntity.getEmail());
		userDto.setPassword(userEntity.getPassword());
		userDto.setState(userEntity.isState());

		return userDto;
	}

	public static List<UserDTO> toListUserAccountDto(List<AccountUserEntity> users) {
		/* java programmation object */
		return users.stream().map(AccountUserMapper::toUserAccountDto).toList();/* acces par recurence */

		/*
		 * return userEntities.stream() .map(userEntity -> toUserAccountDto(userEntity))
		 * .collect(Collectors.toList());
		 */

	}

}