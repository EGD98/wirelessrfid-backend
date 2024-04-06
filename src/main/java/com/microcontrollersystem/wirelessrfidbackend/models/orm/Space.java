package com.microcontrollersystem.wirelessrfidbackend.models.orm;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Date;
import java.util.Objects;

/**
 * creator by eligio glez
 * 09/03/2024
 * copyrigth
 */
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "space")
public class Space {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "room_name")
    private String roomName;
    
    @Column(name = "registration_date")
    private Date registrationDate;
    
    private Integer places;
    
    @Column(name = "creation_date")
    private Date creationDate;
    @Column(name = "edit_date")
    private Date editDate;
    
    private Boolean status = true;
    
    @Column(name = "creator_user")
    private Integer creatorUser;
    @Column(name = "editor_user")
    private Integer editorUser;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer()
                .getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this)
                .getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Space space = (Space) o;
        return getId() != null && Objects.equals(getId(), space.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer()
                .getPersistentClass().hashCode() : getClass().hashCode();
    }
}
