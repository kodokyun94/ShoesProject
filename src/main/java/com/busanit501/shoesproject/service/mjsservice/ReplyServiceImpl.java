package com.busanit501.shoesproject.service.mjsservice;


import com.busanit501.shoesproject.domain.mjsdomain.Reply;
import com.busanit501.shoesproject.domain.mjsdomain.Shoes;
import com.busanit501.shoesproject.dto.mjsdto.PageRequestDTO;
import com.busanit501.shoesproject.dto.mjsdto.PageResponseDTO;
import com.busanit501.shoesproject.dto.mjsdto.ReplyDTO;
import com.busanit501.shoesproject.repository.mjsrepository.ShoesReplyRepository;
import com.busanit501.shoesproject.repository.mjsrepository.ShoesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReplyServiceImpl implements ReplyService {

    private final ShoesRepository shoesRepository;
    private final ShoesService shoesService;
    private final ModelMapper modelMapper;
    private final ShoesReplyRepository shoesReplyRepository;

    @Override
    public Long register(ReplyDTO replyDTO) {
        Reply reply = modelMapper.map(replyDTO, Reply.class);
        // replyDTO 들어 있는 게시글 번호로, 해당 엔티티 객체를 가지고 오기.
        Optional<Shoes> result = shoesRepository.findById(replyDTO.getItemId());
        Shoes shoes = result.orElseThrow();
        reply.setShoes(shoes);
        Long rno = shoesReplyRepository.save(reply).getRno();

        return rno;
    }

    @Override
    public ReplyDTO read(Long rno) {
        Optional<Reply> replyOptional = shoesReplyRepository.findById(rno);
        Reply reply = replyOptional.orElseThrow();
        log.info("확인1 Read reply: " + reply);
        ReplyDTO replyDTO = modelMapper.map(reply, ReplyDTO.class);
        log.info("확인2 Read replyDTO: " + replyDTO);
        replyDTO.setItemId(reply.getShoes().getItemId());
        log.info("확인3 Read replyDTO: " + replyDTO);
        return replyDTO;
    }

    @Override
    public void update(ReplyDTO replyDTO) {
        Optional<Reply> replyOptional = shoesReplyRepository.findById(replyDTO.getRno());
        Reply reply = replyOptional.orElseThrow();
        reply.chageText(replyDTO.getContent());
        shoesReplyRepository.save(reply);
    }

    @Override
    public void delete(Long rno) {
        //유효성 체크.
        Optional<Reply> replyOptional = shoesReplyRepository.findById(rno);
        Reply reply = replyOptional.orElseThrow();
        shoesReplyRepository.deleteById(rno);
    }

    @Override
    public PageResponseDTO<ReplyDTO> getListOfBoard
            (Long itemId, PageRequestDTO pageRequestDTO) {

        //오름차순
        Pageable pageable = PageRequest.of(pageRequestDTO.getPage()-1 <=0 ? 0 :pageRequestDTO.getPage()-1, pageRequestDTO.getSize(), Sort.by("rno").ascending());
        Page<Reply> result = shoesReplyRepository.listOfBoard(itemId,pageable);

        List<ReplyDTO> dtoList = result.getContent().stream()
                .map(reply ->{
                        ReplyDTO replyDTO = modelMapper.map(reply,ReplyDTO.class);
                        replyDTO.setItemId(reply.getShoes().getItemId());
                        return replyDTO;
                })
                .collect(Collectors.toList());

        PageResponseDTO<ReplyDTO> pageResponseDTO = PageResponseDTO.<ReplyDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int) result.getTotalElements())
                .build();

        return pageResponseDTO;
    }
}
