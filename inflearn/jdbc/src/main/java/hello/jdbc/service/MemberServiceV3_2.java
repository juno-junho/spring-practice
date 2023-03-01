package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 트랜젝션 - 트랜잭션 template
 */
@Slf4j
public class MemberServiceV3_2 {

    //    private final DataSource dataSource;
//    private final PlatformTransactionManager transactionManager;
    private final MemberRepositoryV3 memberRepository;
    private final TransactionTemplate txTemplate;

    public MemberServiceV3_2(MemberRepositoryV3 memberRepository, PlatformTransactionManager transactionManager) {
        this.txTemplate = new TransactionTemplate(transactionManager);    // template를 쓰려면 TransactionManager가 필요함.
        this.memberRepository = memberRepository;
    }

    public void accountTransfer(String fromId, String toId, int money) throws SQLException {

        txTemplate.executeWithoutResult((status) -> {   //응답값이 없을 때 사용한다.
            try {
                bizLogic(fromId, toId, money);
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        });
     /*   // 트랜잭션 시작 // 트랜잭션 관련 속성들을 넣어줘야함.
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
//            con.setAutoCommit(false); // tx 시작
            // 비즈니스 로직
            bizLogic(fromId, toId, money);
            transactionManager.commit(status);  // 성공 시 커밋
        } catch (Exception e) {
            transactionManager.rollback(status); // 실패 시 롤백
            throw new IllegalStateException(e);
        }*/
    }

    private void bizLogic(String fromId, String toId, int money) throws SQLException {
        Member fromMember = memberRepository.findById(fromId);
        Member toMember = memberRepository.findById(toId);

        memberRepository.update(fromId, fromMember.getMoney() - money);
        // 오류케이스
        validation(toMember);
        memberRepository.update(toId, toMember.getMoney() + money);
    }

    private void validation(Member toMember) {
        if (toMember.getMemberId().equals("ex")) {
            throw new IllegalStateException("이체 중 예외 발생");
        }
    }
}
