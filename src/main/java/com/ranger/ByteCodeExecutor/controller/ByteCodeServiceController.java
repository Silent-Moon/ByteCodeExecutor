package com.ranger.ByteCodeExecutor.controller;

import com.ranger.ByteCodeExecutor.response.ResponseObject;
import com.ranger.ByteCodeExecutor.service.ByteCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ByteCodeServiceController {
    private final ByteCodeService byteCodeService;

    @Autowired
    public ByteCodeServiceController(ByteCodeService byteCodeService) {
        this.byteCodeService = byteCodeService;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/byteCodeService", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseObject> executeByteCode(@RequestBody(required = false) byte[] classBytes, @RequestParam boolean isExecute, @RequestParam String uuid) {
        return ResponseEntity.ok(byteCodeService.executeByteCode(classBytes, isExecute, uuid));
    }
}
