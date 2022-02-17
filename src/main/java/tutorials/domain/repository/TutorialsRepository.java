package tutorials.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tutorials.domain.model.Tutorials;

@Repository
public interface TutorialsRepository extends JpaRepository<Tutorials, Long> {

	List<Tutorials> findByPublished(Boolean published);
	List<Tutorials> findByTitle(String title);
}
