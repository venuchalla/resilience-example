package com.example.resilience.dto;

import java.util.List;

public record ApiError(int statusCode, String message, List<String> errors) {}
