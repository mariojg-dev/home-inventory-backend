package com.liftdevelops.homeitems.tag;


import com.liftdevelops.homeitems.common.BaseEntity;
import com.liftdevelops.homeitems.item.ItemEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tags")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TagEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

//    @ManyToMany(mappedBy = "tags")
//    @Builder.Default
//    private Set<ItemEntity> items = new HashSet<>();
}
