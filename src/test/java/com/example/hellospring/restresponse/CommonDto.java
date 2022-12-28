package com.example.hellospring.restresponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonDto<T> {
    private T data;
}
