package com.liftdevelops.homeitems.storage;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "containers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContainerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 50)
    private String color;

    @Column(length = 100)
    private String location;
}