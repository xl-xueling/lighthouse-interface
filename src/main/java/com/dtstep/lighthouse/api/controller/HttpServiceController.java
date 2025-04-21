package com.dtstep.lighthouse.api.controller;

import com.dtstep.lighthouse.api.common.ResultCode;
import com.dtstep.lighthouse.api.common.ResultData;
import com.dtstep.lighthouse.api.common.StatParams;
import com.dtstep.lighthouse.client.LightHouse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

@RestController
@ControllerAdvice
public class HttpServiceController {

    private static final Logger logger = LoggerFactory.getLogger(HttpServiceController.class);

    @RequestMapping(value = "/reader", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.HEAD})
    public String read(HttpServletRequest request) throws IOException {
        StringWriter stringWriter = new StringWriter();
        try (BufferedReader bufferedReader = request.getReader()) {
            char[] charBuffer = new char[8192];
            int bytesRead;
            while ((bytesRead = bufferedReader.read(charBuffer)) != -1) {
                stringWriter.write(charBuffer, 0, bytesRead);
            }
        } catch (IOException ex) {
            logger.error("Error reading request body", ex);
            throw ex;
        }
        String str = stringWriter.toString();
        logger.info("Received request body: {}", str);
        return str;
    }

    /**
     *
     *
     * @param statParams
     * @return
     * @throws Exception
     */
    @PostMapping("/stat")
    public ResultData stat(@Validated @RequestBody StatParams statParams) throws Exception {
        String token = statParams.getToken();
        String secretKey = statParams.getSecretKey();
        Map<String,Object> paramMap = statParams.getParams();
        try{
            LightHouse.stat(token,secretKey,paramMap, statParams.getRepeat(), statParams.getTimestamp());
        }catch (Exception ex){
            logger.error("Message sending exception!",ex);
            return ResultData.result(ResultCode.SystemError);
        }
        return ResultData.result(ResultCode.Success);
    }
}
