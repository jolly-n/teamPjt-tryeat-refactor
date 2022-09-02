package shop.tryeat.repository.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.tryeat.domain.item.dto.ItemSearchCondition;
import shop.tryeat.domain.item.dto.ItemSearchDto;

public interface ItemCustom {

    Page<ItemSearchDto> searchItems(ItemSearchCondition condition, Pageable pageable);

    ItemSearchDto findItemDtoById(Long orderDetailItemId);

}
