package com.example.hellospring.converttoqueryparam;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ZonedDto {

    private String name;
    private StatusType statusType;
//    private LocalDateTime startDate;
//    private LocalDateTime endDate;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private List<UserType> userTypeList;
    private Set<ProductType> productTypeSet;
    private Integer maxRating;

    public ZonedDto() {
    }

    @Builder
    public ZonedDto(String name, StatusType statusType, ZonedDateTime startDate, ZonedDateTime endDate, List<UserType> userTypeList,
        Set<ProductType> productTypeSet, Integer maxRating) {
        this.name = name;
        this.statusType = statusType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userTypeList = userTypeList;
        this.productTypeSet = productTypeSet;
        this.maxRating = maxRating;
    }
}