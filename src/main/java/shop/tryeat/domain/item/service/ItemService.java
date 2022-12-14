package shop.tryeat.domain.item.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import shop.tryeat.domain.item.dto.ItemSearchCondition;
import shop.tryeat.domain.item.dto.ItemSearchDto;
import shop.tryeat.domain.item.entity.Item;
import shop.tryeat.domain.item.exception.ItemNotFoundException;
import shop.tryeat.domain.item.repository.ItemRepository;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    /**
     * 상품 등록
     */
    public Long register(Item item) {
        return itemRepository.save(item);
    }

    /**
     * 상품 수정
     */
    public Long update(Item findItem, Item newItem) {
        findItem.update(newItem.getName(), newItem.getPrice(), newItem.getStockQuantity(), newItem.getCategory());
        return findItem.getId();
    }

    /**
     * 상품 목록
     */
    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    /**
     * 특정 상품 조회
     */
    public Item findItem(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException(String.format("ID[%s] 상품을 찾을 수 없습니다.", itemId)));
    }

    /**
     * 상품 삭제
     */
    public void delete(Long itemId) {
        Item item = findItem(itemId);
        itemRepository.delete(item);
    }

    /**
     * 상품 검색
     */
    public Page<ItemSearchDto> searchItem(ItemSearchCondition condition, Pageable pageable) {
        return itemRepository.searchItems(condition, pageable);
    }

}
