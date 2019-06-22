package im.tabis.prod.repository;

import im.tabis.prod.entity.Demo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface DemoRepository extends JpaRepository<Demo, BigInteger> ,JpaSpecificationExecutor<Demo> {

}
