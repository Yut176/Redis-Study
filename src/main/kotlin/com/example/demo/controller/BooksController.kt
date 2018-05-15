package com.example.demo.controller

import com.example.demo.Book
import com.example.demo.response.BooksResponse
import com.example.demo.service.BooksServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Controller
import org.springframework.transaction.annotation.Transactional
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import redis.clients.jedis.Jedis

@Controller
@Suppress("unused")
class BooksController {
    val jedis = Jedis("localhost", 6379)

    data class ISBN(var code: String = "")

//    @GetMapping()
//    fun getBooks(isbnCode: String): BooksResponse {
//        return BooksResponse(BooksServiceImpl().getBooks(isbnCode))
//    }

    @GetMapping("/")
    fun index(model: Model): String {
        model["indexForm"] = Book()
        return "index"
    }

    @GetMapping("/search")
    fun search(model: Model): String {
        model["isbn"] = ISBN()
//        model["indexForm"] = Book()
        return "search"
    }

    @RequestMapping("/search/result")
    fun showData(@ModelAttribute isbn: ISBN, model: Model): String {
        print("search method ${isbn.code}")
        val bk = jedis.hgetAll(isbn.code)
        print(bk)
        val book = Book(isbn.code,
                bk["title"]!!,
                bk["price"]!!,
                bk["publisher"]!!,
                bk["publishYear"]!!,
                bk["writer"]!!,
                bk["translator"]!!,
                bk["imgUrl"]!!)
        model["indexForm"] = book
        return "search/result"
    }

    @RequestMapping(value = ["/result"])
    fun indexFormSubmit(@ModelAttribute book: Book, model: Model): String {
        model["indexForm"] = book
        jedis.hmset(book.isbnCode, hashMapBookData(book))
        return "result"
    }

    private fun hashMapBookData(book: Book): HashMap<String, String> {
        val hm = HashMap<String, String>()
        hm["title"] = book.title
        hm["price"] = book.price
        hm["publisher"] = book.publisher
        hm["publishYear"] = book.publishYear
        hm["writer"] = book.writer
        hm["imgUrl"] = book.imgUrl
        hm["translator"] = book.translator
        return hm
    }

}