package com.microcontrollersystem.wirelessrfidbackend.models.orm;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @JoinColumn(name = "id_client", foreignKey = @ForeignKey(name = "fk_schedule_client"))
    private Client client;
    
    @Column(name = "start_date")
    private Date startDate;
    
    @Column(name = "end_date")
    private Date endDate;
    
    @Column(name = "start_hour")
    private Date startHour;
    
    @Column(name = "end_hour")
    private Date endHour;
    
    @JoinColumn(name = "id_space", foreignKey = @ForeignKey(name = "fk_schedule_space"))
    private Space space;
    
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
        Schedule schedule = (Schedule) o;
        return getId() != null && Objects.equals(getId(), schedule.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer()
                .getPersistentClass().hashCode() : getClass().hashCode();
    }
}
