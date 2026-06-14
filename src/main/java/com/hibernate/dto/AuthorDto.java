package com.hibernate.dto;

import com.hibernate.entity.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {

    private Long id;
    private String fullName;

    public static AuthorDto from(Author author) {
        return AuthorDto.builder()
                .id(author.getId())
                .fullName(author.getFullName())
                .build();
    }
}
