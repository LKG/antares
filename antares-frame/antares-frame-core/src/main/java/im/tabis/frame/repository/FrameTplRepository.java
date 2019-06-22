package im.tabis.frame.repository;

import im.tabis.frame.entity.FrameTpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 * 
 * @author gg
 * @Desc : FrameTpl Crud接口
 */
@Repository
public interface FrameTplRepository extends JpaRepository<FrameTpl, BigInteger> ,JpaSpecificationExecutor<FrameTpl> {


}
