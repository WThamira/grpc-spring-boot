package org.mygrpc.controller;

import java.util.List;

import org.mygrpc.grpc.services.Gender;
import org.mygrpc.grpc.services.UserDetail;
import org.mygrpc.grpc.services.UserServiceGrpc;
import org.mygrpc.grpc.services.UserServiceGrpc.UserServiceBlockingStub;
import org.mygrpc.grpc.services.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@RestController
@RequestMapping("/grcp")
public class UserSender {
	
	@Autowired
    private DiscoveryClient discoveryClient;
 
    @RequestMapping(method = RequestMethod.GET, value = "/senduser")
    public ResponseEntity<?> api() {

    	ManagedChannel channel=ManagedChannelBuilder.forAddress("localhost",7565).usePlaintext(true).build();
        UserServiceBlockingStub stub=UserServiceGrpc.newBlockingStub(channel);
        
        UserDetail user=UserDetail.newBuilder()
        			.setName("Thamira")
        			.setEmail("Thamira1005@gmail.com")
        			.setAge(24).setGender(Gender.Male)
        			.setPassword("password").build();
        
        user u=stub.createUser(user);
        return ResponseEntity.ok(u.toString());
    }
	
	
}
