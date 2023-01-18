package com.jacaranda.MiTienda.service;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jacaranda.MiTienda.model.User;
import com.jacaranda.MiTienda.repository.UserRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import net.bytebuddy.utility.RandomString;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private JavaMailSender mailSender;

	public boolean checkExist(User s) {
		boolean resultado = false;
		// Comprueba que no existe el nombre del usuario
		User checkUser = repository.findById(s.getUsername()).orElse(null);
		// Comprueba que no existe el email en la base de datos
		List<User> checkEmail = repository.findByEmail(s.getEmail());
		if (checkUser == null && checkEmail.size() == 0) {
			resultado = true;
		}
		return resultado;
	}
	
	public boolean checkVerify(String code, String username) {
		boolean isVerify=false;
		User user= repository.findById(username).orElse(null);

		if( user.getVerificationCode().equals(code)) {

			isVerify=true;
			user.setEnabled(isVerify);
			repository.save(user);

		}else {
			return isVerify;
		}

		return isVerify;

		}

	public User getUser(String id) {
		return repository.findById(id).orElse(null);
	}

	public List<User> getUsers() {
		return repository.findAll();
	}

	public User addUser(User user) {
		user.setPass(getMD5(user.getPass()));
		user.setAdmin(false);

		return repository.save(user);
	}
	
	public void add(User s, String siteURL) throws UnsupportedEncodingException, MessagingException {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(s.getPassword());
		s.setPassword(encodedPassword);
		// Genera una cadena aleatoria que guarderemos en el código
		// de verificación y que le enviaremos al usuario para saber
		// que es él.
		String randomCode = RandomString.make(64);
		s.setVerificationCode(randomCode);
		s.setEnabled(false);
		s.setRole("USER");
		repository.save(s);
		sendVerificationEmail(s, siteURL);
	}
	
	private void sendVerificationEmail(User user, String siteURL)
			throws MessagingException, UnsupportedEncodingException {
		String toAddress = user.getEmail();
		String fromAddress = "ioliasa@gmail.com";
		String senderName = "Jacaranda";
		String subject = "Please verify your registration";
		String content = "Dear [[user]],<br>" + "Please click the link below to verify your registration:<br>"
				+ "<h3><a href=\"[[URL]]\"target=\"_self\">VERIFY</a></h3>" + "Thank you,<br>" + "Your company name.";
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setFrom(fromAddress, senderName);
		helper.setTo(toAddress);
		helper.setSubject(subject);
		content = content.replace("[[user]]", user.getUsername());
		String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode() + "&username=" + user.getUsername();
		content = content.replace("[[URL]]", verifyURL);
		helper.setText(content, true);
		mailSender.send(message);
	}



	public User updateUser(User modUser) {
		User user = this.getUser(modUser.getUsername());
		user.setPass(getMD5(modUser.getPass()));
		user.setName(modUser.getName());
		user.setEmail(modUser.getEmail());
		return repository.save(user);
	}

	public User updateAdmin(User modUser) {
		User user = this.getUser(modUser.getUsername());
		user.setAdmin(modUser.isAdmin());
		return repository.save(user);
	}

	public void deleteUser(String id) {
		repository.deleteById(id);
	}

	public static String getMD5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);

			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findById(username).orElse(null);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return user;
	}

}
