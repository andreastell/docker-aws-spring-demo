package se.callistaenterprise.demo.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by atell on 2017-05-18.
 */
@Api(value = "A dummy endpoint", tags = "DEMO",
        description = "")
@RestController
@RequestMapping("/greetings")
@Slf4j
public class DemoController {

    @Value("${info.app.version}")
    private String version;

    @ApiOperation(value = "Say hello.")
    @RequestMapping(method = GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Greet> getGreeting(@RequestParam(value = "name", required = false) final String type) {

        final String greetings = "Howdy ".concat(Optional.ofNullable(type).orElse("someone")).concat(" !");

        return ResponseEntity.ok(Greet.of(version, new Date(), greetings));
    }

    @Data
    @RequiredArgsConstructor(staticName = "of")
    static class Greet {
        final String version;
        final Date when;
        final String message;
    }

}
