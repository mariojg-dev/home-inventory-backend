package com.liftdevelops.homeitems.item;

import com.liftdevelops.homeitems.common.BaseEntity;
import com.liftdevelops.homeitems.storage.ContainerEntity;
import com.liftdevelops.homeitems.tag.TagEntity;
import com.liftdevelops.homeitems.user.model.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "inventory_item")
@Builder
public class ItemEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name must be filled up")
    @Size(max = 250, message = "Name can't exceed 250 characters")
    @Column(nullable = false, length = 250)
    private String name;

    @Size(max = 500, message = "Description can't exceed 500 characters")
    @Column(name = "description")
    private String description;

    @Min(value = 0, message = "Quantity must be greater than 0")
    @Column()
    private Integer quantity;

    @Size(max = 50, message = "Color can't exceed 50 characters")
    private String color;

    @DecimalMin(value = "0.00", message = "Price can't be negative")
    @Column(precision = 8, scale = 2)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private UserEntity owner;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "container_id")
    private ContainerEntity container;

    @Size(max = 50, message = "Category username can't exceed 50 characters")
    private String category;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
//    @JoinTable(username = "item_tags",
//            joinColumns = @JoinColumn(username = "item_id"),
//            inverseJoinColumns = @JoinColumn(username = "tag_id")
//    )
//    @Builder.Default
//    private Set<TagEntity> tags = new HashSet<>();

}
