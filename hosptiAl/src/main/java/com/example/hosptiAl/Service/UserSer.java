package com.example.hosptiAl.Service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.hosptiAl.Dto.AuthRequest;
import com.example.hosptiAl.Dto.DoctorDao;
import com.example.hosptiAl.Dto.PatientDao;
import com.example.hosptiAl.Model.Doctor;
import com.example.hosptiAl.Model.Patient;
import com.example.hosptiAl.Model.User;
import com.example.hosptiAl.Repository.UserRepo;

@Service
public class UserSer {
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private PatientService patientService;
	int otp;
	@Autowired
	private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepo userRepo;
	public void addUser(User user) {
		System.out.println(user.toString());
		userRepo.save(user);
	}
	public boolean geDta(long uid,String pass) {
		return passwordEncoder.matches(pass, userRepo.findById(uid).orElse(new User()).getPassword());
		
	}
	public Iterable<User> getallData() {
		return userRepo.findAll();
	}
	 public void forGot(String name) {
		 if(userRepo.findByUsername(name)==null) throw new IllegalArgumentException("Invalid not Avaliable");
		Random re=new Random();
		int min = 1000;
        int max = 9999;
        otp = re.nextInt(max - min + 1) + min;
        System.out.println(otp);
	 }
	 
	 public String veOtp(int num,String pass, String usrNm) {
		 
		 if(num!=otp) {
			 throw new IllegalArgumentException("Invalid Otp");
		 }
		 User user=userRepo.findByUsername(usrNm).orElse(null);
		 if(user!=null) {
			 user.setPassword(passwordEncoder.encode(pass));
			 userRepo.save(user);
		 }
		 return "Success";
	 }
	 
	 public void addDoctor( DoctorDao doctorDao){
		 User user=new User();
			user.setUsername(doctorDao.getUserName());
			user.setPassword(passwordEncoder.encode( doctorDao.getPassWord()));
			user.setRole(doctorDao.getRole());
			Doctor doctor=new Doctor();
			doctor.setAvailabilitySchedule(doctorDao.getAvailabilitySchedule());
			doctor.setContactNumber(doctorDao.getContactNumber());
			doctor.setName(doctorDao.getUserName());
			doctor.setSpecialization(doctorDao.getSpecialization());
			doctorService.addDoc(doctor);
			addUser(user);
	 }
	 public void addPatient( PatientDao patientDao){
			User user=new User();
			Patient patient=new Patient();
			user.setUsername(patientDao.getUserName());
			user.setPassword(passwordEncoder.encode( patientDao.getPassWord()));
			user.setRole(patientDao.getRole());
			patient.setAddress(patientDao.getAddress());
			patient.setDateOfBirth(patientDao.getDateOfBirth());
			patient.setContactNumber(patientDao.getContactNumber());
			patient.setGender(patientDao.getGender());
			patient.setMedicalHistory(patientDao.getMedicalHistory());
			patient.setName(patientDao.getUserName());
			patientService.addPatient(patient);
			addUser(user);
			
		}
	 public String authenticateAndGetToken(AuthRequest authRequest) {
//	        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
//	        
//	        if (authentication.isAuthenticated() && authRequest.getRole()==userRepo.findByUsername(authRequest.getUsername()).orElse(new User()).getRole()) {
//	            return jwtService.generateToken(authRequest.getUsername());
//	        } else {
//	            throw new UsernameNotFoundException("invalid user request !");
//	        }
		 Optional<User> user = userRepo.findByUsername(authRequest.getUsername());

		 if (user.isPresent()) {
		     User usr = user.get();
		     
		     // Use the matches() method to securely compare the passwords
		     boolean passwordMatches = passwordEncoder.matches(authRequest.getPassword(), usr.getPassword());
		     
		     // Compare the role only if the password is correct
		     if (passwordMatches && authRequest.getRole().equals(usr.getRole()) && usr.getUsername().equals(authRequest.getUsername())) {
		         return "Success";
		     } else {
		         throw new UsernameNotFoundException("Invalid credentials or role!");
		     }
		 } else {
		     // Handle the case where the user is not found in the database
		     throw new UsernameNotFoundException("Invalid user request!");
		 }
	    }

}
