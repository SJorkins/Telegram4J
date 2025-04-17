package ru.gitcoder.telegram.api.model.color;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AccentColor {

    _7(7, "E15052 F9AE63", "FF9380 992F37"),
    _8(8, "E0802B FAC534", "ECB04E C35714"),
    _9(9, "A05FF3 F48FFF", "C697FF 5E31C8"),
    _10(10, "27A910 A7DC57", "A7EB6E 167E2D"),
    _11(11, "27ACCE 82E8D6", "40D8D0 045C7F"),
    _12(12, "3391D4 7DD3F0", "52BFFF 0B5494"),
    _13(13, "DD4371 FFBE9F", "FF86A6 8E366E"),
    _14(14, "247BED F04856 FFFFFF", "3FA2FE E5424F FFFFFF"),
    _15(15, "D67722 1EA011 FFFFFF", "FF905E 32A527 FFFFFF"),
    _16(16, "179E42 E84A3F FFFFFF", "66D364 D5444F FFFFFF"),
    _17(17, "2894AF 6FC456 FFFFFF", "22BCE2 3DA240 FFFFFF"),
    _18(18, "0C9AB3 FFAD95 FFE6B5", "22BCE2 FF9778 FFDA6B"),
    _19(19, "7757D6 F79610 FFDE8E", "9791FF F2731D FFDB59"),
    _20(20, "1585CF F2AB1D FFFFFF", "3DA6EB EEA51D FFFFFF"),
    UNKNOWN(-1, "", "");

    private final int id;

    private final String lightColors;
    private final String darkColors;

    @JsonCreator
    public static AccentColor fromId(int id) {
        for (AccentColor accentColor : values()) {
            if (accentColor.id == id) {
                return accentColor;
            }
        }
        return UNKNOWN;
    }
}
