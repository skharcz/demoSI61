package pe.edu.upc.demosi61.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.demosi61.entities.Role;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
}
