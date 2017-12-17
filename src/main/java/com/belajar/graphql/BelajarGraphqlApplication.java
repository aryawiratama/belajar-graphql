package com.belajar.graphql;

import com.belajar.graphql.entity.BookCategory;
import com.belajar.graphql.repository.BookCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BelajarGraphqlApplication implements CommandLineRunner {

	@Autowired
	private BookCategoryRepository bookCategoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(BelajarGraphqlApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		bookCategoryRepository.deleteAll();
		BookCategory category = BookCategory.builder()
				.name("Komputer")
				.description("Buku seputar IT dan Komputer")
				.build();
		bookCategoryRepository.save(category);
		category = BookCategory.builder()
				.name("Novel")
				.description("Buku Cerita Novel")
				.build();
		bookCategoryRepository.save(category);
	}
}
