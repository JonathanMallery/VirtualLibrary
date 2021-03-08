package com.cognixia.jump.maven.libraryproject.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.cognixia.jump.maven.libraryproject.model.BookCheckout;
import com.cognixia.jump.maven.libraryproject.model.Librarian;
import com.cognixia.jump.maven.libraryproject.model.Patron;

public class TestDaoRunner {
	public static void main(String[] args) {
		 LibrarianDao libDao = new LibrarianDao();
		 System.out.println(libDao.getByUsername("nerdlib"));
	}
}
