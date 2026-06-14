package com.hibernate.dto;

import com.hibernate.entity.Reader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReaderDto {

    private Long id;
    private String fullName;

    public static ReaderDto from(Reader reader) {
        return ReaderDto.builder()
                .id(reader.getId())
                .fullName(reader.getFullName())
                .build();
    }
}
