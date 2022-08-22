package com.nasr.supportingsystemproject.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTemplate<T> {

    private boolean success;
    private T data;
    private String message;
}
