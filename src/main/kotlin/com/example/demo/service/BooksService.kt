package com.example.demo.service

import com.example.demo.Book

interface BooksService {
    fun getBooks(isbnCode:String): List<Book>
}