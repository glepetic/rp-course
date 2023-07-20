package com.rp.scratch.course.sec09.helper.bookstore;

import java.time.LocalDateTime;
import java.util.Map;

public record RevenueReport(LocalDateTime dateTime, Map<String, Double> revenue) {}
