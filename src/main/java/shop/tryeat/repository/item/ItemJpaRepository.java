package shop.tryeat.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.tryeat.domain.item.entity.Item;

public interface ItemJpaRepository extends JpaRepository<Item, Long>, ItemCustom {

}
