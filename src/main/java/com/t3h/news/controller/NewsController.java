package com.t3h.news.controller;

import com.t3h.news.model.NewsModel;
import com.t3h.news.model.request.NewsRequest;
import com.t3h.news.service.INewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody NewsRequest newsRequest){
        return ResponseEntity.ok(iNewsService.insert(newsRequest));
    }

    @GetMapping("/properties")
    public ResponseEntity<?> findByProperties(@RequestParam int numberAccess,@RequestParam int censor){
        return ResponseEntity.ok(iNewsService.findByProperties(numberAccess,censor));
    }

}
