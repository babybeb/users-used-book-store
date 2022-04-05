package babybeb.usersusedbookstore.domain;

public enum ItemCondition {
    MINT, VERY_GOOD, GOOD, NOT_GOOD, BAD
    // MINT: 미개봉 혹은 책이 새것같은 상태
    // VERY_GOOD: 사용감은 있지만 구성품이 모두 있고 책 상태가 매우 좋음
    // GOOD: 사용감이 있고 구성품이 전부 있진 않지만 책 상태가 좋음
    // NOT_GOOD: 사용감이 있고 책 상태가 좋지 않음. 또는 책에 낙서가 있음.
    // BAD: 사용감이 있고 책 상태가 나쁨. 단, 책을 읽는 데 문제가 없으며 상품으로서의 가치가 남아있어야 함.
}