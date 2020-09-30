package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity // jpa에서 테이블 엔티티로 사용할 객체
public class Member {

    @Id
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
