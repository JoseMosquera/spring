package com.game.JoseMosquera.converter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Crypt {

	public static void main(String[] args) {
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		System.out.print(pe.encode("1234"));
	}

}
