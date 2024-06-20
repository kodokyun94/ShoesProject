package com.busanit501.shoesproject.service;

import com.busanit501.shoesproject.service.kdkservice.CartItemService;
import com.busanit501.shoesproject.service.kdkservice.ItemService;
import com.busanit501.shoesproject.service.kdkservice.MemberService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class ServiceTests {
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private MemberService memberService;


}
