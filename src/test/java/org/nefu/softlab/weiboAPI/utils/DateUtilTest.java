package org.nefu.softlab.weiboAPI.utils;

import org.junit.jupiter.api.Test;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class DateUtilTest {
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Test
    public void testTransferMillsToTimestamp() {
        long time = 1304438400000L;
        String expected = "2011-05-04 00:00:00";
        assertEquals(expected, DateUtil.transferMillsToTimestamp(time));
    }

    @Test
    public void testGetCurTimeStamp() {
        String timestamp = DateUtil.getCurTimeStamp();
        assertNotNull(timestamp);
        assertTrue(timestamp.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"));
    }

    @Test
    public void testGetCurDayInWeek() {
        int day = DateUtil.getCurDayInWeek();
        assertTrue(day >= 1 && day <= 7);
    }

    @Test
    public void testParseTimestamp() {
        String timestamp = "2024-03-27 12:00:00";
        Date date = DateUtil.parseTimestamp(timestamp);
        assertNotNull(date);
        assertEquals(timestamp, formatter.format(date));
    }

    @Test
    public void testGetTimeInterval() {
        String oldTimestamp = "2024-03-27 12:00:00";
        String newTimestamp = "2024-03-27 13:00:00";
        long interval = DateUtil.getTimeInterval(oldTimestamp, newTimestamp);
        assertEquals(3600000, interval); // 1小时 = 3600000毫秒
    }
} 