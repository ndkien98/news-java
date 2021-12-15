package com.t3h.news.controller;

import com.t3h.news.model.NewsModel;
import com.t3h.news.service.INewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {

    private INewsService iNewsService;

    public NewsController(INewsService iNewsService) {
        this.iNewsService = iNewsService;
    }

    @GetMapping()
    public ResponseEntity<List<NewsModel>> getList(){
        return ResponseEntity.ok(iNewsService.getList());
    }

}