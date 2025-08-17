package com.agripulse.agripulse.services;

import com.agripulse.agripulse.dto.PaginatedResponse;
import com.agripulse.agripulse.dto.PostResponseDto;
import com.agripulse.agripulse.exceptions.NoPostsFoundException;
import com.agripulse.agripulse.exceptions.PostNotCreatedException;
import com.agripulse.agripulse.exceptions.PostNotFoundException;
import com.agripulse.agripulse.mapper.PostMapper;
import com.agripulse.agripulse.models.Post;
import com.agripulse.agripulse.repositories.PostRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @Override
    public Post createPost(Post post) throws PostNotCreatedException {

        try{

            return postRepository.save(post);
        }catch (DataIntegrityViolationException e){
            // invalid data in post like nulls
            throw new PostNotCreatedException("Could not create or update the post due to invalid data. Please try again!");
        }catch (DataAccessException e){
            // not able to access the database
            throw new PostNotCreatedException("Could not create or update the post due to system unavailability. Please try after some time!");
        }catch (Exception e){
            throw new PostNotCreatedException("Could not create or update the post. Please try again after sometime!");
        }
    }

    @Override
    public PaginatedResponse<PostResponseDto> getAllPosts(int pageNumber, int pageSize) throws NoPostsFoundException {

        Page<Post> postPage = postRepository.findAll(PageRequest.of(pageNumber, pageSize));;

        // throwing exception for first page
        if (pageNumber == 0 && postPage.isEmpty()) {

            throw new NoPostsFoundException("No posts found.");
        }

        List<PostResponseDto> postDtos = postPage.getContent().stream()
                .map(PostMapper::toResponseDto)
                .toList();

        return new PaginatedResponse<PostResponseDto>(
                postDtos,
                postPage.getNumber(),
                postPage.getTotalPages(),
                postPage.getTotalElements()
        );
    }

    @Override
    public Post getPostById(UUID postId) throws PostNotFoundException {
        Optional<Post>  optionalPost = postRepository.findById(postId);

        if(optionalPost.isEmpty()){
            throw new PostNotFoundException(postId, "Post not found for mentioned id");
        }
            return optionalPost.get();
    }

    @Override
    public Post updatePost(UUID postId, Post post) throws PostNotFoundException, PostNotCreatedException {

        Post oldPost = getPostById(postId);

        oldPost.setContent(post.getContent());
        oldPost.setCtaType(post.getCtaType());
        oldPost.setType(post.getType());

      return createPost(oldPost);

    }

    @Override
    public void deletePost(UUID postId) throws PostNotFoundException {
        try{
        postRepository.deleteById(postId);
        }catch (EmptyResultDataAccessException e){
            throw new PostNotFoundException(postId, "Post not found");
        }
    }

    @Override
    public void validatePostExists(UUID postId) throws PostNotFoundException {
        if (!postRepository.existsById(postId)) {
            throw new PostNotFoundException(postId, "Post not found");
        }
    }

    @Override
    @Transactional
    public long incrementLikeCount(UUID postId) throws PostNotFoundException {

        long row = postRepository.incrementLikeCount(postId);

        if(row == 0){
            throw new PostNotFoundException(postId, "Post Not Found");
        }
        return postRepository.findLikeCountById(postId);
    }

    @Override
    @Transactional
    public long decrementLikeCount(UUID postId) throws PostNotFoundException {
        long row = postRepository.decrementLikeCount(postId);

        if( row == 0){
            throw new PostNotFoundException(postId, "Post Not Found");
        }

        return postRepository.findLikeCountById(postId);
    }


}
