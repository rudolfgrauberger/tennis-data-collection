package xyz.grauberger.application.idmapping.core.entities;

import java.io.Serializable;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import xyz.grauberger.application.fdi.provider.DataProvider;
import xyz.grauberger.application.masterdata.entities.player.Player;

@Embeddable
public record PlayerMappingId(
    @ManyToOne @OnDelete(action = OnDeleteAction.CASCADE) Player player,
    @Enumerated(EnumType.STRING) @Column(columnDefinition = "TEXT") DataProvider provider) implements Serializable {

    public static PlayerMappingId of(Player player, DataProvider provider) {
        return new PlayerMappingId(player, provider);
    }
}
