package com.busanit501.shoesproject.repository;


import com.busanit501.shoesproject.domain.kdkdomain.Item;
import com.busanit501.shoesproject.domain.kdkdomain.Member;
import com.busanit501.shoesproject.repository.kdkrepository.CartItemRepository;
import com.busanit501.shoesproject.repository.kdkrepository.ItemRepository;
import com.busanit501.shoesproject.repository.kdkrepository.MemberRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class RepositoryTests {
    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void testInsertItem() {
        IntStream.rangeClosed(1, 100).forEach(i ->
                {
                    Item item = Item.builder()
                            .item_name("조던" +i)
                            .item_brand("나이키"+i)
                            .item_type("운동화"+i)
                            .item_gender("남"+i)
                            .item_price("100000" + i )
                            .build();
                    // 데이터베이스에 추가,
                    // save 없으면, 1)추가, 있으면, 2) 수정.
                    Item result = itemRepository.save(item);
                    log.info("추가한 BNO: " + result.getItem_id());

                }
        );

    } // insetItem test

    @Test
    public void testInsertMember() {
        IntStream.rangeClosed(1, 100).forEach(i ->
                {
                    Member member = Member.builder()
                            .member_address("부산진구" +i)
                            .member_email("dassf"+i+"@naver.com")
                            .member_name("유명가수"+i)
                            .member_phone("0101111111"+i)
                            .member_pw("asdf" + i )
                            .build();
                    // 데이터베이스에 추가,
                    // save 없으면, 1)추가, 있으면, 2) 수정.
                    Member result = memberRepository.save(member);
                    log.info("추가한 BNO: " + result.getMember_id());

                }
        );
    } // insetMember test







}//class 끝
