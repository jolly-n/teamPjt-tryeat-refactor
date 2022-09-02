package shop.tryeat.web.item;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.net.URI;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import shop.tryeat.domain.item.dto.ItemRequestDto;
import shop.tryeat.domain.item.dto.ItemResponseDto;
import shop.tryeat.domain.item.dto.ItemSearchCondition;
import shop.tryeat.domain.item.dto.ItemSearchDto;
import shop.tryeat.domain.item.service.ItemFacade;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/items")
public class ItemApiController {

    private final ItemFacade itemFacade;

    @PostMapping
    public ResponseEntity<String> newItem(@Valid @ModelAttribute ItemRequestDto itemRequestDto, BindingResult bindingResult) {
        // 이미지 검증
        if (itemRequestDto.getMainImage().isEmpty() || itemRequestDto.getDetailImage().isEmpty()) {
            bindingResult.rejectValue("mainImage", "ImageError", "메인이미지와 상세이미지는 필수값입니다.");
            bindingResult.rejectValue("detailImage", "ImageError", "메인이미지와 상세이미지는 필수값입니다.");
        }

        // 검증 실패시 400
        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();

            for (FieldError error : bindingResult.getFieldErrors()) {
                sb.append(error.getDefaultMessage() + "\n");
            }

            return new ResponseEntity<>(sb.toString(), BAD_REQUEST);
        }

        // 검증 성공시 상품 등록 + 201
        Long itemId = itemFacade.register(itemRequestDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(itemId)
                .toUri();

        return ResponseEntity.created(location).body("상품 등록 성공");
    }

    @GetMapping
    public ResponseEntity<Page<ItemSearchDto>> list(@ModelAttribute ItemSearchCondition itemSearchCondition,
                                                    Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), 12);
        return ResponseEntity.ok(itemFacade.findItems(itemSearchCondition, pageRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponseDto> detail(@PathVariable long id) {
        return ResponseEntity.ok(itemFacade.findItem(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        itemFacade.delete(id);
        return ResponseEntity.ok("상품 삭제 성공");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id,
                                    @Valid @ModelAttribute ItemRequestDto itemRequestDto,
                                    BindingResult bindingResult) {
        // 검증 실패시 400
        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();

            for (FieldError error : bindingResult.getFieldErrors()) {
                sb.append(error.getDefaultMessage());
            }

            return new ResponseEntity<>(sb.toString(), BAD_REQUEST);
        }

        // 검증 성공시 상품 수정 + 200
        Long itemId = itemFacade.update(id, itemRequestDto);
        return ResponseEntity.ok(itemFacade.findItem(itemId));
    }

}
