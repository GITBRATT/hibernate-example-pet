package com.hibernate.dto;

import com.hibernate.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestBookDto {
    private String title;
    private Long authorId;
}
