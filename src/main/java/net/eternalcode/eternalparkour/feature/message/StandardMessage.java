package net.eternalcode.eternalparkour.feature.message;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class StandardMessage {

    private MessageLanguage messageLanguage;
    private MessageType messageType;
    private String context;
    private String key;

    public StandardMessage(MessageLanguage messageLanguage, MessageType messageType){
        this.messageLanguage = messageLanguage;
        this.messageType = messageType;

    }

    public StandardMessage(String key, MessageLanguage messageLanguage, MessageType messageType, String context) {
        this.key = key;
        this.messageLanguage = messageLanguage;
        this.messageType = messageType;
        this.context = context;
    }

    public void of(String context){
        this.context = context;
    }

    public void of(String context, String key){
        this.context = context;
        this.key = key;
    }
}
