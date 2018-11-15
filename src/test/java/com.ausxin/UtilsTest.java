package com.ausxin;

import com.utils.Utils;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * User: brucegao
 * Date: 2018-08-14
 */
class UtilsTest {

    List mockList = mock(List.class);
    Utils utils = mock(Utils.class);

    @Test
    void test() {
        mockList.add("one");
        mockList.clear();

        verify(mockList).add("one");
        verify(mockList).clear();

    }

    @Test
    void SingleTonTest() {

    }

    @Test
    void gTest() {
        verify(utils).equals(Utils.g());
    }

    @Test
    void replaceMapKeyTest() {
        Map map = newHashMap();
        map.put("123", "ABC");
        map.put("456", "ZCD");

        assertEquals("ABCZCD", Utils.g().replaceMapKey(map, "123456"));
    }
}