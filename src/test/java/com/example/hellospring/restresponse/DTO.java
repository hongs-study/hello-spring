package com.example.hellospring.restresponse;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class DTO {

    private ReviewDto review;
    private OrderDto order;

    public DTO() {
        this.review = new ReviewDto();
        this.order = new OrderDto();
    }

    @Getter
    @Setter
    private class ReviewDto {
        private String title;
        private String content;
    }

    @Getter
    @Setter
    private class OrderDto {
        private Integer orderNumber;
    }
}
