package net.eternalcode.eternalparkour.command.base;

import com.google.common.collect.ImmutableList;
import dev.rollczi.litecommands.argument.ArgumentName;
import dev.rollczi.litecommands.argument.simple.OneArgument;
import dev.rollczi.litecommands.command.LiteInvocation;
import dev.rollczi.litecommands.command.sugesstion.Suggestion;
import net.eternalcode.eternalparkour.message.MessageLanguage;
import panda.std.Option;
import panda.std.Result;
import panda.std.stream.PandaStream;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ArgumentName("language")
public class MessageLanguageArgument implements OneArgument<MessageLanguage> {

    @Override
    public Result<MessageLanguage, ?> parse(LiteInvocation invocation, String argument) {
        return Option.of(MessageLanguage.valueOf(argument))
                .toResult("&cLanguage support not found!");
    }

    @Override
    public List<Suggestion> suggest(LiteInvocation invocation) {
        return PandaStream.of(MessageLanguage.values())
                .map(MessageLanguage::name)
                .map(Suggestion::of)
                .collect(Collectors.toList());
    }
}
