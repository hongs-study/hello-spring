package com.example.hellospring;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DateTimeFormatTest {

    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    //private final String startDate = LocalDate.now().format(dateFormatter);

    @DisplayName("현재날짜 기준으로 1년 전 yyyy-MM-dd 문자열을 구한다.")
    @Test
    void getOneYearAgoFromNow() {
        String nowDate = LocalDate.now().format(dateFormatter);
        System.out.println(" 현재날짜 : " + nowDate);

        String oneYearAgo = LocalDate.now().minusYears(1L).format(dateFormatter);
        System.out.println(" 1년전 날짜 : " + oneYearAgo);
    }

    @DisplayName("에러메시지를 체크한다.")
    @Test
    void testErrorMessage() {
        //given
        String text = "Hello World!";

        //then
        assertThat(text).isEqualTo("Hello!").as("1입력값과 결과값은 같아야한다.");
        //assertThat(text).as("2입력값과 결과값은 같아야한다.").isEqualTo("Hello!");
    }

    @DisplayName("Iterator 기능을 체크한다")
    @Test
    void testIterator() {
        //given
        //Iterator<String> iterator = (Iterator<String>) List.of("111", "222", "333", "444", "555");
        LinkedList<String> list = new LinkedList<>(List.of("111", "222", "333", "444", "555"));
        Iterator<String> iterator = list.iterator();

        //then
        for (int i=0; i<20; i++) {
            if (!iterator.hasNext()) iterator = list.iterator();
            System.out.println(iterator.next());
        }
    }
}
