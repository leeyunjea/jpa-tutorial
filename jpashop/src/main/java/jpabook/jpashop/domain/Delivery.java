package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) // ORDINAL 절대 쓰면 안됨. 1,2,3 식으로 증가하는 형식인데 중간에 enum이 추가되면 밀리게 됨. 오류소지 다분.
    private DeliveryStatus status; // READY, COMP
}
