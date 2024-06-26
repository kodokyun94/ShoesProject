package com.busanit501.shoesproject.repository.kdkrepository;


import com.busanit501.shoesproject.domain.kdkdomain.Item;
import com.busanit501.shoesproject.domain.kdkdomain.Member;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class RepositoryTests {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void testInsertItem() {
        IntStream.rangeClosed(1, 50).forEach(i -> {
                    Item item = Item.builder()
                            .itemName("더미이름" +i)
                            .itemBrand("더미브랜드"+i)
                            .itemType("더미타입"+i)
                            .itemGender("더미성ㄴㅁㅇㄹ별"+i)
                            .itemPrice("100000" + i+"원" )
                            .stockNumber(1)
                            .build();
                    // 데이터베이스에 추가,
                    // save 없으면, 1)추가, 있으면, 2) 수정.
                    itemRepository.save(item);
                    log.info("추가한 BNO: " + itemRepository);
                }
        );
    } // insetItem test

    @Test
    public void testSelect() {
        Long item_id = 1L;
        Optional<Item> result = itemRepository.findById(item_id);
        Item item = result.orElseThrow();
        log.info("조회 결과 : " + item);
    }

    @Test
    public void testDelete() {
        Long bno = 100L;
        // 반영.
        itemRepository.deleteById(1L);
        log.info("조회 결과2 후: 디비상에서 삭제 여부 확인 하기.");
    }


    @Test
    public void testInsertMember() {
        IntStream.rangeClosed(1, 50).forEach(i -> {
                    Member member = Member.builder()
                            .memberAddress("부산진구" +i)
                            .memberEmail("dassf"+i+"@naver.com")
                            .memberName("유명가수"+i)
                            .memberPhone("0101111111"+i)
                            .memberPw("1234")
                            .build();
                    // 데이터베이스에 추가,
                    // save 없으면, 1)추가, 있으면, 2) 수정.
                     memberRepository.save(member);
                    log.info("추가한 BNO: " + memberRepository);
                }
        );
    } // insetMember test







}//class 끝
