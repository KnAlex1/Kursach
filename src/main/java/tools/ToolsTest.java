package tools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToolsTest {
    @Test
    void lengthToStringSeconds() {
        assertEquals("00:00:22", Tools.lengthToString(22));
    }
    @Test
    void lengthToStringMinutes() {
        assertEquals("00:12:44", Tools.lengthToString(764));
    }
    @Test
    void lengthToStringHours() {
        assertEquals("04:17:02", Tools.lengthToString(15422));
    }
}