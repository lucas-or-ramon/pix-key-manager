package dev.canoa.pixkeymanager.domain.model;

import lombok.Builder;

@Builder
public record PixKey(
        String id,
        Key key,
        Account account,
        String inclusionDateTime
) {
}
