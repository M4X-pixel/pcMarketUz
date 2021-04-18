package uz.pdp.pcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.pcmarket.projection.CustomArticle;

@RepositoryRestResource(path = "article", excerptProjection = CustomArticle.class)
public interface ArticleRepository extends JpaRepository<uz.pdp.pcmarket.entity.Article,Integer> {
}
