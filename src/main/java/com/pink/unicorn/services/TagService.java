package com.pink.unicorn.services;

import com.pink.unicorn.domain.PlainObjects.PlainTag;
import com.pink.unicorn.domain.Tag;
import com.pink.unicorn.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    @Transactional
    public List<Tag> findOrCreate(Collection<PlainTag> plainTagNames) {
        return plainTagNames.stream().map(plainTagName -> {

            List<Tag> foundTags = tagRepository.findByName(plainTagName.getName());

            if (foundTags.size() == 1) {
                return foundTags.get(0);
            } else {
                Tag tag = new Tag(plainTagName.getName());
                tagRepository.save(tag);
                return tag;
            }
        }).collect(Collectors.toList());
    }
}
