package uz.pdp.pcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.pcmarket.entity.Comments;
import uz.pdp.pcmarket.projection.CustomComments;

@RepositoryRestResource(path = "comments",excerptProjection = CustomComments.class)
public interface CommentsRepository extends JpaRepository<Comments,Integer> {
}
