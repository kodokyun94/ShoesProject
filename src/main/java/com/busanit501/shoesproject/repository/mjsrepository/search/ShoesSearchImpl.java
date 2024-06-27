package com.busanit501.shoesproject.repository.mjsrepository.search;


import com.busanit501.shoesproject.domain.mjsdomain.QReply;
import com.busanit501.shoesproject.domain.mjsdomain.QShoes;
import com.busanit501.shoesproject.domain.mjsdomain.Shoes;
import com.busanit501.shoesproject.dto.mjsdto.ShoesImageDTO;
import com.busanit501.shoesproject.dto.mjsdto.ShoesListAllDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

// Querydsl 사용하기 위한 조건
// 인터페이스 이름 + Impl
// 상속 : QuerydslRepositorySupport,
// BoardSearch 구현을 해야합니다.
@Log4j2
public class ShoesSearchImpl extends QuerydslRepositorySupport implements ShoesSearch {

  public ShoesSearchImpl() {
    super(Shoes.class);
  }

  @Override
  public Page<Shoes> search(Pageable pageable) {
    // Querydsl 이용하는 기본 문법
    QShoes shoes = QShoes.shoes;
    // select from board 하는 것과 비슷한 기능.
    // query -> sql 관련 문장을, 자바 문법으로 표현하고 있다.
    JPQLQuery<Shoes> query = from(shoes);

    //BooleanBuilder를 이용한 조건절 추가 해보기.
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    booleanBuilder.or(shoes.itemName.contains("1"));
    booleanBuilder.or(shoes.itemBrand.contains("1"));
    booleanBuilder.or(shoes.itemType.contains("1"));
    booleanBuilder.or(shoes.itemPrice.eq (1));
    booleanBuilder.or(shoes.itemReviewRankAvg.contains("1"));
    booleanBuilder.or(shoes.itemGender.contains("1"));

    // where 조건절  where title like
//    query.where(board.title.contains("1"));
    // BooleanBuilder를 적용하기.
    query.where(booleanBuilder);
    // bno > 0 보다 큰 조건 넣을 경우
    query.where(shoes.itemId.gt(0L));


    // 페이징 처리 적용하기.
    this.getQuerydsl().applyPagination(pageable, query);

    // 해당 조건에 맞는 데이터 가져오기
    List<Shoes> list = query.fetch();
    // 해당 조건에 맞는 데이터 갯수
    long count = query.fetchCount();
    return null;
  }

  @Override
  public Page<ShoesListAllDTO> searchWithAll(String[] types, String keyword, Pageable pageable) {
    // Querydsl 버전에 조인하는 방법.
    QShoes shoes = QShoes.shoes;
    QReply reply = QReply.reply;

    //Left Join(Outer join)
    //추가
    JPQLQuery<Shoes> query = from(shoes);
    query.leftJoin(reply).on(reply.shoes.eq(shoes));

    // 검색 조건, 위에서 , 위의 조건을 재사용.
    if ((types != null && types.length > 0) && keyword != null) {
      // BooleanBuilder , 조건절의 옵션을 추가하기 쉽게하는 도구.
      log.info("조건절 실행여부 확인 1 ");
      BooleanBuilder   booleanBuilder = new BooleanBuilder();
      //String[] types = {"t","w" }
      for (String type : types) {
        switch (type) {
          case "n":
            log.info("조건절 실행여부 확인 2 :  itemName");
            booleanBuilder.or(shoes.itemName.contains(keyword));
            break;
          case "t":
            log.info("조건절 실행여부 확인 2 :  itemType");
            booleanBuilder.or(shoes.itemType.contains(keyword));
            break;
          case "b":
            log.info("조건절 실행여부 확인 2 :  itemBrand");
            booleanBuilder.or(shoes.itemBrand.contains(keyword));
            break;
          case "p":
            log.info("조건절 실행여부 확인 2 :  itemPrice");
            booleanBuilder.or(shoes.itemPrice.eq(Integer.valueOf(keyword)));
            break;
          case "r":
            log.info("조건절 실행여부 확인 2 :  itemReviewRankAvg");
            booleanBuilder.or(shoes.itemReviewRankAvg.contains(keyword));
            break;
          case "g":
            log.info("조건절 실행여부 확인 2 :  itemGender");
            booleanBuilder.or(shoes.itemGender.contains(keyword));
            break;
        } //switch
      } // end for
      // BooleanBuilder를 적용하기.
      query.where(booleanBuilder);
    } // end if


    // bno >0 보다 큰 조건.
    query.where(shoes.itemId.gt(0L));

    // 그룹은 board 로 지정해서.
    query.groupBy(shoes);

    // 페이징 조건 적용
    getQuerydsl().applyPagination(pageable,query);

    // 엔티티 -> dto 모델 변환하는 방법, Tuple 타입으로 컬렉션으로 반환해서 이용하기.
    JPQLQuery<Tuple> tupleListQuery = query.select(shoes,reply.countDistinct());

    List<Tuple> tupleList = tupleListQuery.fetch();

    // 병렬 처리해서, 변환하기.
    List<ShoesListAllDTO> dtoList = tupleList.stream().map(tuple -> {
      Shoes shoes1 = tuple.get(shoes);
      long replyCount = tuple.get(1,Long.class);

      // 1번, 게시글
      ShoesListAllDTO shoesListAllDTO = ShoesListAllDTO.builder()
              .itemId(shoes1.getItemId())
              .itemName(shoes1.getItemName())
              .itemType(shoes1.getItemType())
              .itemBrand(shoes1.getItemBrand())
              .itemPrice(shoes1.getItemPrice())
              .itemReviewRankAvg(shoes1.getItemReviewRankAvg())
              .itemGender(shoes1.getItemGender())
              .replyCount(replyCount)
              .build();

      // 2번,  첨부 이미지 추가하는 작업.
      // entity -> dto
     List<ShoesImageDTO> imageDTOS = shoes1.getImageSet().stream().sorted().map(
              shoesImage -> ShoesImageDTO.builder()
                      .uuid(shoesImage.getUuid())
                      .fileName(shoesImage.getFileName())
                      .ord(shoesImage.getOrd())
                      .build()
      ).collect(Collectors.toList());
      shoesListAllDTO.setShoesImages(imageDTOS);

      return shoesListAllDTO;

    }).collect(Collectors.toList());


    // entity -> dto 변환 했고,
     long totalCount = query.fetchCount();


    return new PageImpl<>(dtoList,pageable,totalCount);
  }
}







