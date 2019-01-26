package com.techieshah;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techieshah.domainmodel.BlogPost;

@RestController
@RequestMapping("/blogPosts")
public class BlogPostController {
    
    private static List<BlogPost> blogPosts;
    static {
        blogPosts = new ArrayList<BlogPost>();
        blogPosts.add( new BlogPost( 1, "spring framework", "ws", "first example of rest web services" ) );
        blogPosts.add( new BlogPost( 2, "spring framework", "ws", "second example of rest web services" ) );
    }
    
    @GetMapping("")
    public List<BlogPost> all(){
        return blogPosts;
    }
    
    @GetMapping("/{id}")
    public BlogPost get(@PathVariable Integer id){
        for(BlogPost blogPost : blogPosts) {
            if(blogPost.id == id) {
                return blogPost;
            }
        }
        return null;
    }
    
    @PostMapping("")
    public String post(@RequestBody BlogPost blogPost){
       blogPosts.add(blogPost);
        return "Blog Post saved successfully";
    }
    
    @PutMapping("")
    public String put(@RequestBody BlogPost updatedBlogPost) {
        for(BlogPost blogPost : blogPosts) {
            if(blogPost.id == updatedBlogPost.id) {
               blogPost.name = updatedBlogPost.name;
               blogPost.tag = updatedBlogPost.tag;
               blogPost.content = updatedBlogPost.content;
            }
        }
        return "Blog Post updated successfully";
    }
    
    @DeleteMapping("{id}")
    public String delete(@PathVariable Integer id){
        
        for(BlogPost blogPost : blogPosts) {
            if(blogPost.id == id) {
                blogPosts.remove( blogPost );
                break;
            }
        }
        return "Blog Post deleted successfully";
    }
}
