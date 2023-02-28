package com.fdmgroup.CharCloud.dataImport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fdmgroup.CharCloud.model.Collection;
import com.fdmgroup.CharCloud.model.Role;
import com.fdmgroup.CharCloud.model.User;
import com.fdmgroup.CharCloud.repository.CollectionRepository;
import com.fdmgroup.CharCloud.repository.UserRepository;
import com.fdmgroup.CharCloud.security.Roles;

@Component
public class DataImport implements ApplicationRunner {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CollectionRepository collectionRepo;
	@Autowired
	private PasswordEncoder encoder;
	
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		if(!userRepo.findByUsername("admin").isPresent()) {
			
			Role roleAdmin = new Role(Roles.Admin);
			Role roleUser = new Role (Roles.User);
					
			User admin = new User(roleAdmin, "admin", "Ad", "Min", encoder.encode("123"), "admin@aa.com");
			User user1 = new User(roleUser, "user1", "User", "One", encoder.encode("111"), "one@o.com");
			
			
			admin.setCharPoints(100000);
			user1.setCharPoints(3000);
			
			userRepo.save(admin);
			userRepo.save(user1);
			
			

			Collection coll1 = new Collection("Save Planet", (int) 20000.0, user1, "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex...", "https://media.istockphoto.com/id/1318389435/pl/wektor/r%C4%99ka-trzymaj%C4%85ca-planet%C4%99-ziemi-z-drzewami-rosn%C4%85cymi-w-p%C5%82askiej-konstrukcji-uratuj-koncepcj%C4%99.jpg?s=612x612&w=0&k=20&c=aeiNsZwyCXFDPBAggPw-v6yvv5gLqRmVs7vjD-m2-QU=");
			Collection coll2 = new Collection("Save Rainforest", (int) 6500.0, user1, "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex", "https://www.travelandleisure.com/thmb/R2kb6GuJwF4wVJhRVevV-FqOVao=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/el-yunque-national-rainforest-tropical-puerto-rico-TROPICALPLANTS0617-d3ccb18a16064e42bdd626cdf7a8cb68.jpg");
			Collection coll3 = new Collection("Save Pandas", (int) 1000.0, admin, "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex", "https://images.theconversation.com/files/430483/original/file-20211105-9897-18ahqx2.jpg?ixlib=rb-1.1.0&q=45&auto=format&w=1200&h=1200.0&fit=crop\"");
			Collection coll4 = new Collection("Save ", (int) 10000.0, admin, "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex","https://charitywatch-images.sfo2.digitaloceanspaces.com/images/Untit_70561.png");
			Collection coll5 = new Collection("Save ", (int) 5000.0, user1, "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRz_yCQnwLBWIKqEqg12NiLjhr55MWjaZBwUg&usqp=CAU");
			Collection coll6 = new Collection("Save ", (int) 2000.0, admin, "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex", "https://img.freepik.com/premium-vector/people-characters-trying-save-planet-earth-global-warming-climate-change-concept_250257-2184.jpg?w=2000");
			Collection coll7 = new Collection("Save ", (int) 12000.0, admin, "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex", "https://images.theconversation.com/files/430483/original/file-20211105-9897-18ahqx2.jpg?ixlib=rb-1.1.0&q=45&auto=format&w=1200&h=1200.0&fit=crop\"");
			Collection coll8 = new Collection("Doctors Without Borders", (int) 8000.0, user1, "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex", "https://charitywatch-images.sfo2.digitaloceanspaces.com/images/Untit_70561.png");

			collectionRepo.save(coll1);
			collectionRepo.save(coll2);
			collectionRepo.save(coll3);
			collectionRepo.save(coll4);
			collectionRepo.save(coll5);
			collectionRepo.save(coll6);
			collectionRepo.save(coll7);
			collectionRepo.save(coll8);
			
			
		}
		
		
	}

}
