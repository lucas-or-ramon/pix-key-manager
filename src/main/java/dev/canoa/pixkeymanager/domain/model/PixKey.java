package dev.canoa.pixkeymanager.domain.model;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PixKey(
        String id,
        Key key,
        Account account,
        LocalDateTime inclusionDateTime,
        LocalDateTime deactivationDateTime
) {
}
