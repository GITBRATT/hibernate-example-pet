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
public class BookDto {

    private Long id;
    private String title;
    private boolean available;

    // Вместо вложенного AuthorDto — только id и имя,
    // чтобы не получить Book → Author → List<Book> → ...
    private Long authorId;
    private String authorName;

    public static BookDto from(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .available(book.isAvailable())
                .authorId(book.getAuthor() != null ? book.getAuthor().getId() : null)
                .authorName(book.getAuthor() != null ? book.getAuthor().getFullName() : null)
                .build();
    }
}
