package uz.pdp.pcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.pcmarket.entity.MyTeam;
import uz.pdp.pcmarket.projection.CustomMyTeam;

@RepositoryRestResource(path = "myTeam",excerptProjection = CustomMyTeam.class)
public interface MyTeamRepository extends JpaRepository<MyTeam,Integer> {
}
