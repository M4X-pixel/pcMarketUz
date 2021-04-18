package uz.pdp.pcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.pcmarket.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment,Integer> {
    boolean existsByOriginalName(String originalName);
}
