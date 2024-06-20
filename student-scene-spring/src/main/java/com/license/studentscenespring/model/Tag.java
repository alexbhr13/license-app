package com.license.studentscenespring.model;

import com.license.studentscenespring.util.TagType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="_tag")
public class Tag {
    @Id
    private String name;
    private TagType tagType;
}