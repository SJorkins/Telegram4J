package ru.gitcoder.telegram.api.payload.request.text;

import lombok.Getter;

@Getter
public enum OptionType {
    // Общие параметры (применимы ко всем типам сообщений)
    DISABLE_NOTIFICATION("disable_notification"),
    REPLY_TO_MESSAGE_ID("reply_to_message_id"),

    // Параметры для текстовых сообщений (sendMessage)
    SPOILER("has_spoiler"),
    PARSE_MODE("parse_mode"),

    // Параметры для медиа-сообщений (sendPhoto, sendVideo, sendAudio, sendDocument, etc.)
    CAPTION("caption"),

    // Параметры для опросов (sendPoll)
    IS_ANONYMOUS("is_anonymous"),
    TYPE("type"),
    ALLOWS_MULTIPLE_ANSWERS("allows_multiple_answers"),
    CORRECT_OPTION_ID("correct_option_id"),
    EXPLANATION("explanation"),
    EXPLANATION_PARSE_MODE("explanation_parse_mode"),
    OPEN_PERIOD("open_period"),
    CLOSE_DATE("close_date"),

    // Параметры для локаций (sendLocation)
    LIVE_PERIOD("live_period"),
    HORIZONTAL_ACCURACY("horizontal_accuracy"),
    HEADING("heading"),
    PROXIMITY_ALERT_RADIUS("proximity_alert_radius"),

    // Параметры для мест (sendVenue)
    FOURSQUARE_ID("foursquare_id"),
    FOURSQUARE_TYPE("foursquare_type"),
    GOOGLE_PLACE_ID("google_place_id"),
    GOOGLE_PLACE_TYPE("google_place_type"),

    // Параметры для контактов (sendContact)
    LAST_NAME("last_name"),
    VCARD("vcard");

    private final String jsonKey;

    OptionType(String jsonKey) {
        this.jsonKey = jsonKey;
    }
}