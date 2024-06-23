package com.license.studentscenespring.repository;

import com.license.studentscenespring.model.Tag;
import com.license.studentscenespring.util.TagType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findAllByTagType(TagType tagType);
}
