package com.transfet.auth;

import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;

/**
 * It is a utility class provides functionality to handle date/time
 */
@Service
public class DateTimeService {

    public LocalDateTime currentUTCLocalDateTime() {
        return LocalDateTime.now(Clock.systemUTC());
    }

}
