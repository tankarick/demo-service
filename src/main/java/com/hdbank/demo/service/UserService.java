package com.hdbank.demo.service;

import com.hdbank.demo.exception.DemoException;
import com.hdbank.demo.exception.DemoResult;
import com.hdbank.demo.model.Response;
import com.hdbank.demo.model.User;
import com.hdbank.demo.model.UserData;
import com.hdbank.demo.repository.UserRepository;
import com.hdbank.demo.utils.JsonService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class UserService {
    private static final Logger logger = LogManager.getLogger(UserService.class);

    private static Map<String, String> userMap = new ConcurrentHashMap<>();

    @Autowired
    private JsonService jsonService;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        userMap.put("admin", "admin");
        userMap.put("user", "user");
        userMap.put("user2", "user2");
        userMap.put("user3", "user3");
        userMap.put("user4", "user4");
    }

    @Transactional
    public Response upsertUser(UserData userData) {
        User user = new User();
        user.setUserName(userData.getUserName());
        user.setPassword(userData.getPassword());
        user.setFirstName(userData.getFirstName());
        user.setLastName(userData.getLastName());
        user.setEmail(userData.getEmail());
        user.setPhoneNumber(userData.getPhoneNumber());
        user.setStatus(0);
        userRepository.save(user);
        return new Response();
    }

    public Response getUserByName(String userId) throws DemoException {

        String requestId = MDC.get("requestId");
        logger.info("getUserByName userId={}{}",requestId, userId);
        try {
            for (String userName : userMap.keySet()) {
                System.out.println(userName);
                userMap.put(UUID.randomUUID().toString().replace("-", "_"), userName);
            }
            User user = userRepository.findByUserName(userId);
            User dc = jsonService.read("{\"id\":null, \"internalId\":\"hfjkk\"}", User.class);
            if (user == null) {
                throw new DemoException(DemoResult.NOT_FOUND);
            }
            return new Response().data(user);
        } catch (Exception e) {
            return null;
        }
    }
}