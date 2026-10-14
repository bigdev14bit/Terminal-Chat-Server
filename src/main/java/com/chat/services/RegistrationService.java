package com.chat.services;

import com.chat.models.dto.Packet;
import com.chat.models.enums.CommandType;
import com.chat.models.enums.MessageType;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RegistrationService {
    private static final Random random = new Random();
    // Tracks registered handles in memory to prevent collisions
    private static final Set<String> registeredHandles = Collections.synchronizedSet(new HashSet<>());

    public Packet processRegistration(Packet incomingPacket) {
        String baseUsername = incomingPacket.getSenderId().toLowerCase().trim();
        String assignedHandle;

        // Loop until a non-colliding tag is generated
        do {
            String tag = String.format("%04d", random.nextInt(10000));
            assignedHandle = baseUsername + "#" + tag;
        } while (registeredHandles.contains(assignedHandle));

        registeredHandles.add(assignedHandle);

        System.out.println("✅ Successfully registered handle: " + assignedHandle);

        String responsePayload = String.format("{\"assignedHandle\":\"%s\",\"status\":\"SUCCESS\"}", assignedHandle);

        return new Packet(
                "p_resp_" + System.currentTimeMillis(),
                CommandType.REGISTER,
                MessageType.TEXT,
                "SERVER",
                assignedHandle,
                responsePayload,
                null
        );
    }
}
