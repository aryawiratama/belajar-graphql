package com.belajar.graphql;

import com.belajar.graphql.entity.Book;
import com.belajar.graphql.entity.BookCategory;
import com.belajar.graphql.repository.BookCategoryRepository;
import com.belajar.graphql.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BelajarGraphqlApplication implements CommandLineRunner {

	@Autowired
	private BookCategoryRepository bookCategoryRepository;
	@Autowired
	private BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(BelajarGraphqlApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		bookRepository.deleteAll();
		bookCategoryRepository.deleteAll();
		BookCategory category = BookCategory.builder()
				.name("Komputer")
				.description("Buku seputar IT dan Komputer")
				.build();
		bookCategoryRepository.save(category);

		Book book = Book.builder()
				.title("Clean Code")
				.author("Robert C. Martins")
				.isbn("ISBN-1234555")
				.description("How to write code clean")
				.bookCategory(category)
				.build();
		bookRepository.save(book);

		book = Book.builder()
				.title("Cloud Native java")
				.author("Josh Long")
				.isbn("ISBN-1234585475")
				.description("Cloud Java")
				.bookCategory(category)
				.build();
		bookRepository.save(book);

		book = Book.builder()
				.title("Reactive Design Pattern")
				.author("Rolland Kuhn")
				.isbn("ISBN-544545")
				.description("Reactive Pattern Java")
				.bookCategory(category)
				.build();
		bookRepository.save(book);

		category = BookCategory.builder()
				.name("Novel")
				.description("Buku Cerita Novel")
				.build();
		bookCategoryRepository.save(category);
		book = Book.builder()
				.title("Sherlock Holmes")
				.author("Conan Doyle")
				.isbn("ISBN-65484254")
				.description("Detective")
				.bookCategory(category)
				.build();
		bookRepository.save(book);

	}
}
