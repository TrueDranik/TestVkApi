package api.vk.service.vk;

import api.vk.model.entity.Post;

import java.util.List;

public interface PostService {
    void saveAll(List<Post> post);
}
