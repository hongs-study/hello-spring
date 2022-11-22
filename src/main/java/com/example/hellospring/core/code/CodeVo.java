package com.example.hellospring.core.code;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class CodeVo {
    private final CodeRule code;
    private final String codeName;

    CodeVo(CodeRule code) {
        this.code = code;
        this.codeName = code.getCodeName();
    }
}