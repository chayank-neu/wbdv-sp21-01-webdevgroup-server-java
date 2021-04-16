package com.webdevgroup.sp2101webdevegroupserverjava.controllers;

import com.webdevgroup.sp2101webdevegroupserverjava.models.Comment;
import com.webdevgroup.sp2101webdevegroupserverjava.models.Events;
import com.webdevgroup.sp2101webdevegroupserverjava.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
public class CommentController {

    @Autowired
    CommentService service;


    @GetMapping("/event/{eid}/comments")
    public List<Comment>  getAllEvents(
            @PathVariable("eid") Long eid){
        return service.findCommentsForEvent(eid);
    }


    @PostMapping("/event/comment")
    public List<Comment> addComment(@RequestBody Comment comment)
    {

        return service.addComment(comment);
    }

    @PutMapping("/event/comment")
    public boolean UpdateComment(@RequestBody Comment comment)
    {

        return service.updateComment(comment);
    }
}