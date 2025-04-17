package ru.gitcoder.telegram.api.model.reply;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ru.gitcoder.telegram.api.model.chat.Chat;
import ru.gitcoder.telegram.api.model.animation.Animation;
import ru.gitcoder.telegram.api.model.audio.Audio;
import ru.gitcoder.telegram.api.model.contact.Contact;
import ru.gitcoder.telegram.api.model.dice.Dice;
import ru.gitcoder.telegram.api.model.document.Document;
import ru.gitcoder.telegram.api.model.game.Game;
import ru.gitcoder.telegram.api.model.giveaway.Giveaway;
import ru.gitcoder.telegram.api.model.giveaway.GiveawayWinners;
import ru.gitcoder.telegram.api.model.invoice.Invoice;
import ru.gitcoder.telegram.api.model.link.LinkPreviewOptions;
import ru.gitcoder.telegram.api.model.location.Location;
import ru.gitcoder.telegram.api.model.media.Media;
import ru.gitcoder.telegram.api.model.message.forward.ForwardInfo;
import ru.gitcoder.telegram.api.model.paid.PaidMediaInfo;
import ru.gitcoder.telegram.api.model.poll.Poll;
import ru.gitcoder.telegram.api.model.sticker.Sticker;
import ru.gitcoder.telegram.api.model.story.Story;
import ru.gitcoder.telegram.api.model.venue.Venue;
import ru.gitcoder.telegram.api.model.video.Video;
import ru.gitcoder.telegram.api.model.video.VideoNote;
import ru.gitcoder.telegram.api.model.voice.Voice;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExternalReplyInfo {
    /**
     * Origin of the message replied to by the given message
     */
    @JsonProperty("origin")
    private ForwardInfo originMessage;

    /**
     * Optional. Chat the original message belongs to. Available only if the chat is a supergroup or a channel.
     */
    @JsonProperty("chat")
    private Chat chat;

    /**
     * Optional. Unique message identifier inside the original chat. Available only if the original chat is a
     * supergroup or a channel.
     */
    @JsonProperty("message_id")
    private int messageId;

    /**
     * Optional. Options used for link preview generation for the original message, if it is a text message
     */
    @JsonProperty("link_preview_options")
    private LinkPreviewOptions linkPreviewOptions;

    /**
     * Optional. Message is an animation, information about the animation
     */
    @JsonProperty("animation")
    private Animation animation;

    /**
     * Optional. Message is an audio file, information about the file
     */
    @JsonProperty("audio")
    private Audio audio;

    /**
     * Optional. Message is a general file, information about the file
     */
    @JsonProperty("document")
    private Document document;

    /**
     * Optional. Message contains paid media; information about the paid media
     */
    @JsonProperty("paid_media")
    private PaidMediaInfo paidMediaInfo;

    /**
     * Optional. Message is a photo, available sizes of the photo
     */
    @JsonProperty("photo")
    private List<Media> photos = new ArrayList<>();

    /**
     * 	Optional. Message is a sticker, information about the sticker
     */
    @JsonProperty("sticker")
    private Sticker sticker;

    /**
     * Optional. Message is a forwarded story
     */
    @JsonProperty("story")
    private Story story;

    /**
     * Optional. Message is a video, information about the video
     */
    @JsonProperty("video")
    private Video video;

    /**
     * Optional. Message is a video note, information about the video message
     */
    @JsonProperty("video_note")
    private VideoNote videoNote;

    /**
     * Optional. Message is a voice message, information about the file
     */
    @JsonProperty("voice")
    private Voice voice;

    /**
     * Optional. True, if the message media is covered by a spoiler animation
     */
    @Getter(AccessLevel.NONE)
    @JsonProperty("has_media_spoiler")
    private boolean mediaSpoiler;

    /**
     * Optional. Message is a shared contact, information about the contact
     */
    @JsonProperty("contact")
    private Contact contact;

    /**
     * Optional. Message is a dice with random value
     */
    @JsonProperty("dice")
    private Dice dice;

    /**
     * Optional. Message is a game, information about the game
     */
    @JsonProperty("game")
    private Game game;

    /**
     * 	Optional. Message is a scheduled giveaway, information about the giveaway
     */
    @JsonProperty("giveaway")
    private Giveaway giveaway;

    /**
     * Optional. A giveaway with public winners was completed
     */
    @JsonProperty("giveaway_winners")
    private GiveawayWinners giveawayWinners;

    /**
     * Optional. Message is an invoice for a payment, information about the invoice.
     */
    @JsonProperty("invoice")
    private Invoice invoice;

    /**
     * Optional. Message is a shared location, information about the location
     */
    @JsonProperty("location")
    private Location location;

    /**
     * Optional. Message is a native poll, information about the poll
     */
    @JsonProperty("poll")
    private Poll poll;

    /**
     * Optional. Message is a venue, information about the venue
     */
    @JsonProperty("venue")
    private Venue venue;

    public boolean hasMediaSpoiler() {
        return mediaSpoiler;
    }
}
