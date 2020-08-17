package com.hello.hellospring;

import java.util.HashMap;
import java.util.Map;

public class PracticeClass {
    public static Map<Integer, String> testMap = new HashMap<>();
    public static void main(String[] args) {
        testMap.put(1, "철수");
        testMap.put(2, "동철");
        testMap.put(3, "철진");

        System.out.println(findByName("동철"));

    }

    public static int findByName(String name) {
        return testMap.entrySet().stream() // testMap의 모든 key value(items)로 stream 생성
                .filter(ele -> name.equals(ele.getValue())) // 각 요소들을 순회하는데, 주어진 name과 요소에서 getValues()한 값이 일치하는지 확인
                .map(Map.Entry::getKey) // stream 안의 요소를 하나씩 특정값으로 바꿔준다. Map의 Entry를 참조해 getKey 메서드를 실행시킨다.
                .findFirst().get(); // 이렇게 찾은 값 중 가장 첫번째 값을 찾은 뒤 가져온다.
    }
    
    
}
