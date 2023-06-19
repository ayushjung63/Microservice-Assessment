package com.assessment.gateway.fallback;

import com.assessment.gateway.response.FallbackResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallBackController {

    /**
     * BLOG Service Fallback
     *
     * @return
     */
    @RequestMapping(path = "/blog", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<FallbackResponse> blogServiceFallBack() {
        return new ResponseEntity<>(new FallbackResponse( "Blog Service Instance Currently Not Available.", null), HttpStatus.SERVICE_UNAVAILABLE);
    }

    /**
     * AUTH Service Fallback
     *
     * @return
     */
    @RequestMapping(path = "/auth", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<FallbackResponse> authServiceFallBack() {
        return new ResponseEntity<>(new FallbackResponse( "Auth Service Instance Currently Not Available.", null), HttpStatus.SERVICE_UNAVAILABLE);
    }
}
