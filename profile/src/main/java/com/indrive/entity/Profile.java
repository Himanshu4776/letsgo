package com.indrive.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Profile {
    @Id
    @Column(unique = true)
    private String emailId;
    private String name;
    private String password;
    private String type;
    private float rating;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "location_id",
            referencedColumnName = "locationId"
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Location location;
}
