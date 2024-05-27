package dev.canoa.pixkeymanager.domain.ports.outbound;

public interface ExistsPixKeyPort {
    boolean exists(String key);
}
