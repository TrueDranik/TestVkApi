package api.vk.service.vk.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import api.vk.model.entity.Post;
import api.vk.repository.PostRepository;
import api.vk.service.vk.PostService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Transactional
    @Override
    public void saveAll(List<Post> post) {
        postRepository.saveAll(post);
    }
}
