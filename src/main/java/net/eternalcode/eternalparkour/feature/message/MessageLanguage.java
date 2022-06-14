package net.eternalcode.eternalparkour.feature.message;

import lombok.Getter;

@Getter
public enum MessageLanguage {
    EN("en-file.yml"),
    PL("pl-file.yml");

    private final String fileName;

    MessageLanguage(String fileName) {
        this.fileName = fileName;
    }
}
