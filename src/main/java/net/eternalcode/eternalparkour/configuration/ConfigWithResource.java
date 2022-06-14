package net.eternalcode.eternalparkour.configuration;

import net.dzikoysk.cdn.source.Resource;

public interface ConfigWithResource {

    Resource getResource();

    void setResource(Resource resource);

}
