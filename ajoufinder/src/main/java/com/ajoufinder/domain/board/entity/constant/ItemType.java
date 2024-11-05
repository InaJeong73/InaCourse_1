package com.ajoufinder.domain.board.entity.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ItemType {
  ELECTRONICS("전자기기"),
  WALLET("지갑"),
  BAG("가방"),
  CLOTHING("의류"),
  BOOK("책"),
  DOCUMENT("서류"),
  ACCESSORY("액세서리"),
  JEWELRY("귀금속"),
  KEY("열쇠"),
  UMBRELLA("우산"),
  OTHER("기타");

  private final String text;
}
