package com.fazliddin.appgatewayservice;

import com.fazliddin.library.payload.ApiResult;
import com.fazliddin.library.payload.ErrorData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Fazliddin Xamdamov
 * @date 19.04.2022  09:55
 * @project app-fast-food
 */


@RestController
@RequestMapping("/fallBack")
@RequiredArgsConstructor
public class    FallBackController {

    String ERROR_MESSAGE = "There was a problem connecting to the Service";
    HttpStatus HTTP_STATUS = HttpStatus.BAD_REQUEST;

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS, RequestMethod.PATCH},
            path = "/fallback")
    public ResponseEntity<?> fallbackController() {
        return ResponseEntity
                .status(HTTP_STATUS)
                .body(new ApiResult<>(false, List.of(new ErrorData(ERROR_MESSAGE, HTTP_STATUS.value()))));
    }
}
