package com.example.hellospring.converttoqueryparam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Dto {

    private String name;
    private StatusType statusType;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<UserType> userTypeList;
    private Set<ProductType> productTypeSet;
    private Integer maxRating;

    public Dto() {
    }

    @Builder
    public Dto(String name, StatusType statusType, LocalDateTime startDate, LocalDateTime endDate, List<UserType> userTypeList,
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