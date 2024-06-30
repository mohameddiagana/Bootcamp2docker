package mr.diagspring.bootcamp2.service;

import java.util.List;
import java.util.Optional;

import mr.diagspring.bootcamp2.dto.UserDTO;

public interface IUserService {

	public List<UserDTO> getAll();

	Optional<UserDTO> save(UserDTO userDto);

	Optional<UserDTO> login(String email, String password);
}
