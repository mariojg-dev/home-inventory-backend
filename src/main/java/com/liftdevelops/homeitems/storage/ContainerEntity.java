package com.liftdevelops.homeitems.storage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.liftdevelops.homeitems.common.BaseEntity;
import com.liftdevelops.homeitems.item.ItemEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "containers")
@Getter
@Setter
@NoArgsConstructor
public class ContainerEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name must be filled up")
    @Column(nullable = false, length = 250)
    private String name;

    @Column(length = 50)
    private String color;

    @Column(length = 100)
    private String location;

    @OneToMany(mappedBy = "container", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ItemEntity> items = new ArrayList<>();
}