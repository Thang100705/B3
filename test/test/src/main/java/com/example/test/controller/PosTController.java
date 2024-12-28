package com.example.test.controller;

import com.example.test.models.Posts;
import com.example.test.models.Users;
import com.example.test.services.CommentService;
import com.example.test.services.ImageService;
import com.example.test.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3001") // Cho phép frontend truy cập
@RequestMapping("/api")
public class PosTController {
    @Autowired
    private PostService postService;
    private CommentService commentService;
    private ImageService imageService;

    public PosTController(PostService postService, CommentService commentService, ImageService imageService) {
        this.postService = postService;
        this.commentService = commentService;
        this.imageService = imageService;
    }

    //hiển thị toàn bộ bài viết đã duyệt
    @GetMapping("/postApprove")
    public ResponseEntity<List<Posts>>getAllPost(){
        List<Posts>postsApproved=postService.getAllPostApproved();
        return ResponseEntity.ok(postsApproved);
    }


    //tìm kiếm toàn bộ theo tiêu đề
    @GetMapping("/search")
    public ResponseEntity<List<Posts>> searchPostsByTitle(@RequestParam String title) {
        List<Posts> posts = postService.searchPostByTile(title);
        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    //Tạo bài viết
    @PostMapping("/posts")
    public ResponseEntity<Posts> CreateBlog(
            @Valid @RequestBody Posts post
//            ,
//            @RequestParam("file") MultipartFile file
    ) {
        try {
            // Lưu bài viết
            Posts create = postService.save(post);
//            Posts createdPost = postService.save(post);
//
//
////            imageService.saveImage(file, postId);
//            imageService.saveImage(file,createdPost.getPost_id());

            // Trả về phản hồi với bài viết đã tạo
            return new ResponseEntity<>(create, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    //cập nhật bài viết
    @PutMapping("/posts/{id}")
    public ResponseEntity<Posts>UpdateBlog(@Valid @PathVariable Long id, @RequestBody Posts post) {
        try {
            Posts update = postService.update(id,post);
            return ResponseEntity.ok(update);
        } catch (Exception e) {
            return ResponseEntity.status((HttpStatus.INTERNAL_SERVER_ERROR)).build();
        }
    }

    //hiển thị toàn bộ bài viết duyệt và chưa duyệt
    @GetMapping("/posts")
    public List<Posts> showPosts() {
        return postService.getAll();
    }

    //hiển thị bài viết theo id
    @GetMapping("/posts/{id}")
    public ResponseEntity<Posts> getPost( @PathVariable(required = false) Long id) {
        Optional<Posts> post = postService.getById(id);
        if (post.isPresent()) {
            return new ResponseEntity<>(post.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //xóa bài viết theo id
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> deletePostById(@PathVariable Long id) {
        Optional<Posts> post = postService.getById(id);
        if (post.isPresent()) {
            postService.deleteById(id);
            //chu y fix
            commentService.deleteCommentsByPostId(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //lấy bài viết theo user id
    @GetMapping("/posts/user/{id}")
    public ResponseEntity<List<Posts>>getPostByUserId(@PathVariable Long id){
        List<Posts>posts=postService.getPostByUserId(id);
        return ResponseEntity.ok(posts);
    }









//    @GetMapping("/post")
//    public ResponseEntity<Posts> getPostByTitle(@RequestParam String postName) {
//        Optional<Posts> post = postService.getPostByTitle(postName);
//        if (post.isPresent()) {
//            return new ResponseEntity<>(post.get(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//
//    }
}