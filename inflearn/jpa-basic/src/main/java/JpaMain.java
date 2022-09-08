import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            // new / transient
//            Member member = new Member();
//            member.setId(101L);
//            member.setName("HellloA");
            // managed
//            em.persist(member);
            // db 에서 조회 -> persistence context에 올림.
//            Member findMember1 = em.find(Member.class, 101L);
//            //1차 캐시 조회 -> 반환
//            Member findMember2 = em.find(Member.class, 101L);
//            System.out.println("findMember.getId() = " + findMember1.getId());
//            System.out.println("findMember.getName() = " + findMember2.getName());
//            // 동일성 비교
//            System.out.println(findMember1==findMember2);

            //영속
//            System.out.println("1=============================");
//            Member member1 = new Member(12l, "AAAAAAAAAA");
//            Member member2 = new Member(13l, "BBBBBBBBBBB  ");
            // 영속성 컨텍스트에 쌓임
//            em.persist(member1);
//            em.persist(member2);
//            em.flush();
//            Member member = em.find(Member.class, 12L);
//            System.out.println("1.5========================");
////            em.detach(member);
//            System.out.println("1.8========================");
//            Member member3 = em.find(Member.class, 12l);
//            member3.setName("TTTTTTTTTTTT");
//            System.out.println("2=============================");
//            tx.commit();
//            System.out.println("3=============================");
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }

    }
}
