package com.busanit501.shoesproject.service.kdkservice;


import com.busanit501.shoesproject.domain.*;
import com.busanit501.shoesproject.dto.OrderDTO;
import com.busanit501.shoesproject.dto.OrderHistDTO;
import com.busanit501.shoesproject.dto.OrderItemDTO;
import com.busanit501.shoesproject.repository.ItemImgRepository;
import com.busanit501.shoesproject.repository.ItemRepository;
import com.busanit501.shoesproject.repository.OrderRepository;
import com.busanit501.shoesproject.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final ItemRepository itemRepository;

    // 합치기 수정
    private final MemberRepository memberRepository;

    private final OrderRepository orderRepository;

    private final ItemImgRepository itemImgRepository;

    public Long order(OrderDTO orderDto, String memberId){

        Item item = itemRepository.findById(orderDto.getItemId())
                .orElseThrow(EntityNotFoundException::new);

        // 합치기 수정

//        ShopMember shopMember = memberRepository.findByEmail(email);
        Optional<Member> result = memberRepository.findByMid(memberId);
        Member Member = result.orElseThrow();

        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem orderItem = OrderItem.createOrderItem(item, orderDto.getCount());
        orderItemList.add(orderItem);
        // 합치기 수정
        Order order = Order.createOrder(Member, orderItemList);
        orderRepository.save(order);

        return order.getId();
    }

    @Transactional(readOnly = true)
    public Page<OrderHistDTO> getOrderList(String mid, Pageable pageable) {

        List<Order> orders = orderRepository.findOrders(mid, pageable);
        Long totalCount = orderRepository.countOrder(mid);

        List<OrderHistDTO> orderHistDtos = new ArrayList<>();

        for (Order order : orders) {
            OrderHistDTO orderHistDto = new OrderHistDTO(order);
            List<OrderItem> orderItems = order.getOrderItems();
            for (OrderItem orderItem : orderItems) {
                ItemImg itemImg = itemImgRepository.findByItemIdAndRepimgYn
                        (orderItem.getItem().getId(), "Y");
                OrderItemDTO orderItemDto =
                        new OrderItemDTO(orderItem, itemImg.getImgUrl());
                orderHistDto.addOrderItemDto(orderItemDto);
            }

            orderHistDtos.add(orderHistDto);
        }

        return new PageImpl<OrderHistDTO>(orderHistDtos, pageable, totalCount);
    }

    @Transactional(readOnly = true)
    public boolean validateOrder(Long orderId, String memberEmail){
        // 합치기 수정
//        ShopMember curShopMember = memberRepository.findByEmail(email);
        Optional<Member> result = memberRepository.findByEmail(memberEmail);
        Member curMember = result.orElseThrow();

        Order order = orderRepository.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);
        // 합치기 수정
        Member savedMember = order.getMember();

        if(!StringUtils.equals(curMember.getEmail(), savedMember.getEmail())){
            return false;
        }

        return true;
    }

    public void cancelOrder(Long orderId){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);
        order.cancelOrder();
    }

    public Long orders(List<OrderDTO> orderDtoList, String memberId){
// 합치기 수정
//        ShopMember shopMember = memberRepository.findByEmail(email);
        Optional<Member> result = memberRepository.findByMid(memberId);
        Member Member = result.orElseThrow();

        List<OrderItem> orderItemList = new ArrayList<>();

        for (OrderDTO orderDto : orderDtoList) {
            Item item = itemRepository.findById(orderDto.getItemId())
                    .orElseThrow(EntityNotFoundException::new);

            OrderItem orderItem = OrderItem.createOrderItem(item, orderDto.getCount());
            orderItemList.add(orderItem);
        }

        Order order = Order.createOrder(Member, orderItemList);
        orderRepository.save(order);

        return order.getId();
    }

}