package net.eternalcode.eternalparkour.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Panda;
import panda.std.stream.PandaStream;

import java.util.List;

public final class ChatUtils {

    private static final LegacyComponentSerializer SERIALIZER = LegacyComponentSerializer.legacyAmpersand();

    private ChatUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static Component component(String text) {
        return SERIALIZER.deserialize(text);
    }

    public static List<Component> component(Iterable<String> texts) {
        return PandaStream.of(texts).map(ChatUtils::component).toList();
    }

    public static String decode(Component component){
        return SERIALIZER.serialize(component);
    }

    public static List<String> decode(List<Component> components){
        return PandaStream.of(components).map(ChatUtils::decode).toList();
    }
}
