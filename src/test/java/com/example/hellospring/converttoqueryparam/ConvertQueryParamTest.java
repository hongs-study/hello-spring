package com.example.hellospring.converttoqueryparam;

import static com.example.hellospring.converttoqueryparam.StatusType.NORMAL;
import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class ConvertQueryParamTest {

    private MultiValueMapConverter multiValueMapConverter = new MultiValueMapConverter();

    @DisplayName("String을 Map으로 변환해보면???")
    @Test
    void test1() {
        //given
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        String name = "홍길동";
        LocalDateTime startDate = LocalDateTime.of(2023, 1, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2023, 12, 31, 23, 59, 59);

        Dto dto = Dto.builder()
            .name(name)
            .statusType(StatusType.NORMAL)
            .startDate(startDate)
            .endDate(endDate)
            .userTypeList(List.of(UserType.USER, UserType.ADMIN))
            .productTypeSet(Set.of(ProductType.ALBUM, ProductType.CLOCK, ProductType.MONITOR))
            .maxRating(10)
            .build();

        //when
        MultiValueMap<String, String> parameter = new LinkedMultiValueMap<>();
        Map<String, String> params = objectMapper.convertValue(dto, Map.class);
        parameter.setAll(params);

        //then
        for (String key : parameter.keySet()) {
            System.out.println(key + ": " + parameter.get(key));
        }
    }

    @DisplayName("DTO를 MultiValueMap로 변환하자!")
    @Test
    void test2() {
        //given
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        String name = "홍길동";
        LocalDateTime startDate = LocalDateTime.of(2023, 1, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2023, 12, 31, 23, 59, 59);

        Dto dto = Dto.builder()
            .name(name)
            .statusType(StatusType.NORMAL)
            .startDate(startDate)
            .endDate(endDate)
            .userTypeList(List.of(UserType.USER, UserType.ADMIN))
            .productTypeSet(Set.of(ProductType.ALBUM, ProductType.CLOCK, ProductType.MONITOR))
            .maxRating(10)
            .build();

        //when : DTO
        MultiValueMap<String, String> parameter = new LinkedMultiValueMap<>();
        Map<String, Object> map = objectMapper.convertValue(dto, Map.class);
        for (String key : map.keySet()) {
            System.out.println(key + ": " + map.get(key));
            if (map.get(key) instanceof Collection<?>) {
                MultiValueMap<String, String> listMap = new LinkedMultiValueMap<>();
                listMap.addAll(key, (List) map.get(key));
                parameter.addAll(listMap);
            } else {
                parameter.set(key, map.get(key).toString());
            }
        }

        //then
        DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        assertThat((List) parameter.get("name")).containsOnly(name);
        assertThat((List) parameter.get("statusType")).containsOnly(NORMAL.name());
        assertThat(LocalDateTime.parse(((List) parameter.get("startDate")).get(0).toString(), DATETIME_FORMATTER)).isEqualTo(startDate);
        assertThat(LocalDateTime.parse(((List) parameter.get("endDate")).get(0).toString(), DATETIME_FORMATTER)).isEqualTo(endDate);
        assertThat((List) parameter.get("productTypeSet")).contains(ProductType.ALBUM.name(), ProductType.CLOCK.name(), ProductType.MONITOR.name());
        assertThat((List) parameter.get("userTypeList")).contains(UserType.USER.name(), UserType.ADMIN.name());
        assertThat(parameter.get("maxRating")).containsOnly(String.valueOf(10));
    }

    @DisplayName("DTO를 MultiValueMap로 변환하자! - 정리")
    @Test
    void test3() {
        //given
        String name = "홍길동";
        LocalDateTime startDate = LocalDateTime.of(2023, 1, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2023, 12, 31, 23, 59, 59);

        Dto dto = Dto.builder()
            .name(name)
            .statusType(StatusType.NORMAL)
            .startDate(startDate)
            .endDate(endDate)
            .userTypeList(List.of(UserType.USER, UserType.ADMIN))
            .productTypeSet(Set.of(ProductType.ALBUM, ProductType.CLOCK, ProductType.MONITOR))
            .maxRating(10)
            .build();

        //when : DTO
        MultiValueMap<String, String> parameter = multiValueMapConverter.convertFrom(dto);

        //then
        DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        assertThat((List) parameter.get("name")).containsOnly(name);
        assertThat((List) parameter.get("statusType")).containsOnly(NORMAL.name());
        assertThat(LocalDateTime.parse(((List) parameter.get("startDate")).get(0).toString(), DATETIME_FORMATTER)).isEqualTo(startDate);
        assertThat(LocalDateTime.parse(((List) parameter.get("endDate")).get(0).toString(), DATETIME_FORMATTER)).isEqualTo(endDate);
        assertThat((List) parameter.get("productTypeSet")).contains(ProductType.ALBUM.name(), ProductType.CLOCK.name(), ProductType.MONITOR.name());
        assertThat((List) parameter.get("userTypeList")).contains(UserType.USER.name(), UserType.ADMIN.name());
        assertThat(parameter.get("maxRating")).containsOnly(String.valueOf(10));
    }



    @DisplayName("ObjectMapper 객체 생성하는데 원하는 날짜 포맷으로 테스트하기")
    @Test
    void test4() {
        ZoneId KST = ZoneId.of("Asia/Seoul");

        //given
        String name = "홍길동";
        ZonedDateTime startDate = ZonedDateTime.of(LocalDateTime.of(2023, 1, 1, 0, 0), KST);
        ZonedDateTime endDate = ZonedDateTime.of(LocalDateTime.of(2023, 12, 31, 23, 59, 59), KST);

        ZonedDto dto = ZonedDto.builder()
            .name(name)
            .statusType(StatusType.NORMAL)
            .startDate(startDate)
            .endDate(endDate)
            .userTypeList(List.of(UserType.USER, UserType.ADMIN))
            .productTypeSet(Set.of(ProductType.ALBUM, ProductType.CLOCK, ProductType.MONITOR))
            .maxRating(10)
            .build();

        //given
        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule module = new JavaTimeModule();
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        objectMapper.registerModule(module);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);


        //when : DTO
        MultiValueMap<String, String> parameter = new LinkedMultiValueMap<>();
        Map<String, Object> map = objectMapper.convertValue(dto, Map.class);
        for (String key : map.keySet()) {
            System.out.println(key + ": " + map.get(key));
            if (map.get(key) instanceof Collection<?>) {
                MultiValueMap<String, String> listMap = new LinkedMultiValueMap<>();
                listMap.addAll(key, (List) map.get(key));
                parameter.addAll(listMap);
            } else {
                parameter.set(key, map.get(key).toString());
            }
        }

        //then
        DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        assertThat((List) parameter.get("name")).containsOnly(name);
        assertThat((List) parameter.get("statusType")).containsOnly(NORMAL.name());
//        assertThat(LocalDateTime.parse(((List) parameter.get("startDate")).get(0).toString(), DATETIME_FORMATTER)).isEqualTo(startDate);
//        assertThat(LocalDateTime.parse(((List) parameter.get("endDate")).get(0).toString(), DATETIME_FORMATTER)).isEqualTo(endDate);
        assertThat((List) parameter.get("productTypeSet")).contains(ProductType.ALBUM.name(), ProductType.CLOCK.name(), ProductType.MONITOR.name());
        assertThat((List) parameter.get("userTypeList")).contains(UserType.USER.name(), UserType.ADMIN.name());
        assertThat(parameter.get("maxRating")).containsOnly(String.valueOf(10));
    }


}