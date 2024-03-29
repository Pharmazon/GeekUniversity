package com.flamexander.hibernate.annotations;


import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@javax.persistence.Entity
@javax.persistence.Table
@org.hibernate.annotations.DynamicInsert
@org.hibernate.annotations.DynamicUpdate
@org.hibernate.annotations.Immutable
public class AnnotatedEntity {
    public enum  EnumType {
        A, B, C;
    }

    @Id // нужен ли тут сеттер?
    Long id;

    @Enumerated(javax.persistence.EnumType.STRING) // javax.persistence.EnumType.ORDINAL
    EnumType eType;

    // Проверка самим Хибернейт перед сохранением, приводит к генерации НОТ НУЛЛ
    @javax.validation.constraints.NotNull
    @Column(nullable = false)
    Integer field1;

    transient Integer field2;

    @Transient
    Integer field3;

    // вычисляется только в момент вытаскивания
    @Formula("SELECT avg(b.AMOUNT) from BID b where b_ITEM_ID = ID")
    Integer filed4;

    @Column(name = "double_weight")
    @ColumnTransformer(read = "double_weight / 2.0", write = "? * 2.0")
    Integer metricWeight;

    @NotNull
    @Size(
            min = 2,
            max = 255,
            message = "Field5 is required, maximum 255 characters"
    )
    String field5;

    @Future
    LocalDateTime field6;

    @Column(columnDefinition = "varchar(15) not null unique")
    String field7;
}
