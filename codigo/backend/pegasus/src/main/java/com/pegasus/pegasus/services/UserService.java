package com.pegasus.pegasus.services;

import java.util.ArrayList;
import java.util.List;

//importando ferramentas
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.pegasus.pegasus.DTOs.UserDTO;
import com.pegasus.pegasus.entities.User;
import com.pegasus.pegasus.entities.UserRole;
import com.pegasus.pegasus.repositories.UserRepository;
import javax.persistence.EntityNotFoundException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    // encontrando user pelo id e usando o tipo da função como um DTO para que o
    // usuario não tenha acesso a informações sensiveis
    public UserDTO findUserById(Long id) {
        try {
            User existingUser = userRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id : " + id));

            UserDTO userDTO = new UserDTO();
            userDTO.setId(existingUser.getId());
            userDTO.setName(existingUser.getName());
            userDTO.setUsername(existingUser.getUsername());
            userDTO.setEmail(existingUser.getEmail());
            return userDTO;
        } catch (Exception e) {
            throw new RuntimeException("User not found with id : " + id + ", erro: " + e.getMessage() + "", e);
        }
    }

    public UserDTO createUser(UserDTO userDTO) {
        try {
            if (userRepository.findByUsernameCustom(userDTO.getUsername()) != null) {
                throw new RuntimeException("Username already exists");
            }
            if (userRepository.findByEmailCustom(userDTO.getEmail()) != null) {
                throw new RuntimeException("Email already exists");
            }
            User newUser = new User();
            UserRole role = UserRole.USER;
            newUser.setName(userDTO.getName());
            newUser.setUsername(userDTO.getUsername());
            newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            newUser.setEmail(userDTO.getEmail());
            newUser.setRole(role);
            // usa a repository pra salvar no banco
            User createdUser = userRepository.save(newUser);

            // usa o DTO para transitar entre as camadas e mandar para o frontend
            UserDTO createdUserDTO = new UserDTO();
            createdUserDTO.setName(createdUser.getName());
            createdUserDTO.setUsername(createdUser.getUsername());
            createdUserDTO.setEmail(createdUser.getEmail());

            return createdUserDTO;
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível criar o usuário, erro: " + e.getMessage() + "");
        }
    }

    public UserDTO updateUser(Long id, UserDTO user) {
        try {
            UserDTO updatedUserDTO = new UserDTO();
            if(user.getUsername() != null && userRepository.findUsername(user.getUsername()) == user.getUsername() ){
                throw new RuntimeException("Username already exists");
            }
            if(user.getEmail() != null && userRepository.findEmail(user.getEmail()) == user.getEmail() ){
                throw new RuntimeException("Username already exists");
            }
            User existingUser = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with id : " + id));
            if(user.getName() != null){
                existingUser.setName(user.getName());
                updatedUserDTO.setName(user.getName());
            }
            if(user.getUsername() != null){
                existingUser.setUsername(user.getUsername());
                updatedUserDTO.setUsername(user.getUsername());
            }
            if(user.getEmail() != null){
                existingUser.setEmail(user.getEmail());
                updatedUserDTO.setEmail(user.getEmail());
            }
            if(user.getPassword() != null){
                existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
                updatedUserDTO.setPassword(user.getPassword());
            }

            //usa a repository pra salvar no banco
            User updatedUser = userRepository.save(existingUser);

            //e usa o DTO para transitar entre as camadas e mandar para o frontend

            return updatedUserDTO;
        } catch (Exception ex) {
            throw new RuntimeException("Error updating user: " + ex.getMessage(), ex);
        }
    }

    // deletando usuario pelo ID
    public UserDTO deleteUser(Long id) {
        try {
            User existingUser = userRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id : " + id));

            UserDTO userDTO = new UserDTO();
            userDTO.setId(existingUser.getId());
            userDTO.setUsername(existingUser.getUsername());
            userDTO.setEmail(existingUser.getEmail());

            userRepository.delete(existingUser);

            return userDTO;
        } catch (Exception ex) {
            throw new RuntimeException("Error deleting user: " + ex.getMessage(), ex);
        }
    }

    // função para encriptar a password, porém ainda não esta ativada!
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}