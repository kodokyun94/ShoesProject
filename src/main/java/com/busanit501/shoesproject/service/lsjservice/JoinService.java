package com.busanit501.shoesproject.service.lsjservice;


import com.busanit501.shoesproject.domain.lsjdomain.LsjJoin;
import com.busanit501.shoesproject.repository.lsjrepository.JoinRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class JoinService {

    private final JoinRepository joinRepository;

    public LsjJoin saveMember(LsjJoin lsjJoin){
        validateDuplicateMember(lsjJoin);
        return joinRepository.save(lsjJoin);
    }

    private void validateDuplicateMember(LsjJoin lsjJoin) {
        LsjJoin findMember = joinRepository.findByEmail(lsjJoin.getMember_email());
         if (findMember != null) {
             throw new IllegalStateException("이미 가입된 회원입니다.");
         }
    }


}
