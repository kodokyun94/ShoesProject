package com.busanit501.shoesproject.service.shop;

import com.busanit501.shoesproject.domain.CartItem;
import com.busanit501.shoesproject.domain.Item;
import com.busanit501.shoesproject.domain.ItemImg;
import com.busanit501.shoesproject.domain.OrderItem;
import com.busanit501.shoesproject.dto.shop.ItemFormDTO;
import com.busanit501.shoesproject.dto.shop.ItemImgDTO;
import com.busanit501.shoesproject.dto.shop.ItemSearchDTO;
import com.busanit501.shoesproject.dto.MainItemDto;
import com.busanit501.shoesproject.repository.shop.CartItemRepository;
import com.busanit501.shoesproject.repository.shop.ItemImgRepository;
import com.busanit501.shoesproject.repository.shop.ItemRepository;
import com.busanit501.shoesproject.repository.shop.OrderItemRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    private final ItemImgService itemImgService;

    private final ItemImgRepository itemImgRepository;

    private final CartItemRepository cartItemRepository;

    private final OrderItemRepository orderItemRepository;


    public Long saveItem(ItemFormDTO itemFormDto, List<MultipartFile> itemImgFileList) throws Exception{

        //상품 등록
        Item item = itemFormDto.createItem();
        itemRepository.save(item);

        //이미지 등록
        for(int i=0;i<itemImgFileList.size();i++){
            ItemImg itemImg = new ItemImg();
            itemImg.setItem(item);

            if(i == 0)
                itemImg.setRepimgYn("Y");
            else
                itemImg.setRepimgYn("N");

            itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
        }

        return item.getId();
    }

    @Transactional(readOnly = true)
    public ItemFormDTO getItemDtl(Long itemId){
        List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(itemId);
        List<ItemImgDTO> itemImgDtoList = new ArrayList<>();
        for (ItemImg itemImg : itemImgList) {
            ItemImgDTO itemImgDto = ItemImgDTO.of(itemImg);
            itemImgDtoList.add(itemImgDto);
        }

        Item item = itemRepository.findById(itemId)
                .orElseThrow(EntityNotFoundException::new);
        ItemFormDTO itemFormDto = ItemFormDTO.of(item);
        itemFormDto.setItemImgDTOList(itemImgDtoList);
        return itemFormDto;
    }

    public Long updateItem(ItemFormDTO itemFormDto, List<MultipartFile> itemImgFileList) throws Exception{
        //상품 수정
        Item item = itemRepository.findById(itemFormDto.getId())
                .orElseThrow(EntityNotFoundException::new);
        item.updateItem(itemFormDto);
        List<Long> itemImgIds = itemFormDto.getItemImgIds();

        //이미지 등록
        for(int i=0;i<itemImgFileList.size();i++){
            itemImgService.updateItemImg(itemImgIds.get(i),
                    itemImgFileList.get(i));
        }

        return item.getId();
    }

    //상품 삭제
    public void deleteItem(Long item_id) throws Exception {
        //카트에 담긴 상품 삭제
        Optional<CartItem> cartItemResult = cartItemRepository.findById(item_id);
        if(cartItemResult.isPresent()){
            cartItemRepository.deleteById(item_id);
        }
        //주문에 담긴 상품 삭제
        Optional<OrderItem> orderItemResult = orderItemRepository.findById(item_id);
        if (orderItemResult.isPresent()){
            orderItemRepository.deleteById(item_id);
        }
        // 상품의 이미지들 삭제
        itemImgService.deleteItemImg(item_id);

        // 상품 삭제
        itemRepository.deleteById(item_id);



    }

    @Transactional(readOnly = true)
    public Page<Item> getAdminItemPage(ItemSearchDTO itemSearchDto, Pageable pageable){
        return itemRepository.getAdminItemPage(itemSearchDto, pageable);
    }

    @Transactional(readOnly = true)
    public Page<MainItemDto> getMainItemPage(ItemSearchDTO itemSearchDto, Pageable pageable){
        return itemRepository.getMainItemPage(itemSearchDto, pageable);
    }

}