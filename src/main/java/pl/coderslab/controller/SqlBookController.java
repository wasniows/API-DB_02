package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.coderslab.app.Book;
import pl.coderslab.app.SqlBookService;

import java.util.List;

@RestController
@RequestMapping("/mysql")
public class SqlBookController {

    private SqlBookService sqlBookService;
    @Autowired
    public SqlBookController(SqlBookService sqlBookService) {
        this.sqlBookService = sqlBookService;
    }

    @GetMapping("")
    public @ResponseBody
    List<Book> sqlGetList() {
        return sqlBookService.getBooks();
    }

    @PostMapping("")
    public void sqlAddBook(@RequestBody Book book){
        sqlBookService.add(book);
    }

    @GetMapping("/{id}")
    public Book sqlGetBook(@PathVariable Long id){
        return this.sqlBookService.get(id).orElseThrow(()->{
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        });
    }

    @DeleteMapping("/{id}")
    public void sqlDeleteBook(@PathVariable Long id){
        sqlBookService.delete(id);
    }

    @PutMapping("")
    @ResponseBody
    public void sqlUpdateBook(@RequestBody Book book){
        sqlBookService.update(book);
    }
}