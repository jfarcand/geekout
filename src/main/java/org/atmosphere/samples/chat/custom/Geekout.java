package org.atmosphere.samples.chat.custom;

import org.atmosphere.config.service.Disconnect;
import org.atmosphere.config.service.ManagedService;
import org.atmosphere.config.service.Message;
import org.atmosphere.config.service.PathParam;
import org.atmosphere.config.service.Ready;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceEvent;
import org.atmosphere.samples.chat.ChatMessage;
import org.atmosphere.samples.chat.Reply;
import org.atmosphere.samples.chat.jackson.JacksonDecoder;
import org.atmosphere.samples.chat.jackson.JacksonEncoder;

//@ManagedService(path="/{roomChat}")
public class Geekout {

    @PathParam("roomChat")
    public String roomName;

    @Ready
    public void ready(AtmosphereResource r) {
        System.out.println(String.format("%s is connected to room %s", r.uuid(), roomName));
    }

    @Message(encoders= JacksonEncoder.class, decoders = JacksonDecoder.class)
    public Reply message(ChatMessage m) {
        return m.asReply();
    }

    @Disconnect
    public void disconnect(AtmosphereResourceEvent event) {
        if (event.isClosedByClient()) {
            System.out.println(String.format("Bye my friend %s", event.getResource().uuid()));
        } else {
            System.out.println(String.format("Where are you buddy %s", event.getResource().uuid()));
        }
    }
}
