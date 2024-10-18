package com.hdbank.demo.delegate;

import com.hdbank.demo.api.V1ApiDelegate;
import com.hdbank.demo.exception.DemoException;
import com.hdbank.demo.model.Response;
import com.hdbank.demo.model.UserData;
import com.hdbank.demo.service.SocketService;
import com.hdbank.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class V1ApiDelegateImpl implements V1ApiDelegate {

    @Autowired
    private UserService userService;

    @Autowired
    private SocketService socketService;

    @Override
    public ResponseEntity<Response> createUser(UserData body) {
        return ResponseEntity.ok(userService.upsertUser(body));
    }

    @Override
    public ResponseEntity<Response> getUserByName(String username) throws DemoException {
        return ResponseEntity.ok(userService.getUserByName(username));
    }

    @Override
    public ResponseEntity<String> sendSocket(String body) {
        return ResponseEntity.ok(socketService.sendSocket(body));
    }
}
