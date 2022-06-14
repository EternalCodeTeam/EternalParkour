package net.eternalcode.eternalparkour.configuration.model;

import net.dzikoysk.cdn.entity.Contextual;
import net.dzikoysk.cdn.entity.Description;
import net.eternalcode.eternalparkour.feature.message.MessageLanguage;
import panda.std.Pair;

import java.util.Set;

@Contextual
public class MessageConfigurationModel {

    @Description("Language messages configuration model")
    public Set<Pair<MessageLanguage, Set<Pair<String, String>>>> messages =
            Set.of(
                    new Pair<>(
                            MessageLanguage.PL,
                            Set.of(
                                    new Pair<>("abc", "abc"),
                                    new Pair<>("def", "def"),
                                    new Pair<>("abc", "def"),
                                    new Pair<>("abc", "def")
                            )),
                    new Pair<>(
                            MessageLanguage.EN,
                            Set.of(
                                    new Pair<>("abc", "abc"),
                                    new Pair<>("def", "def"),
                                    new Pair<>("abc", "def"),
                                    new Pair<>("abc", "def")))
            );


}
