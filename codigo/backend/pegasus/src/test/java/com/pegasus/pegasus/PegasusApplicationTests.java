package com.pegasus.pegasus;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.pegasus.pegasus.entities.User;
import com.pegasus.pegasus.repositories.UserRepository;

@Configuration
class PegasusApplicationTests implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		// User u1 = new User(null, "Maria Brown", "maria@gmail.com", "123456");
		// User u2 = new User(null, "Alex Green", "alex@gmail.com", "123456");
		

		// userRepository.saveAll(Arrays.asList(u1, u2));
	}

}
