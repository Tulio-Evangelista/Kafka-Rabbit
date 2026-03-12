package dev.java10x.email.dto;

import java.util.UUID;

public record EmailDto(
        UUID id,
        String emailFrom,
        String emailTo,
        String emailSubject,
        String body

) {
}
