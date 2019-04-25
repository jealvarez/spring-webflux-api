package com.spring.example.payments.repositories;

import com.spring.example.payments.models.PaymentActivity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentActivityRepository {

    private final SqlSession sqlSession;

    public PaymentActivityRepository(final SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<PaymentActivity> findAll() {
        return sqlSession.selectList(this.getClass().getPackageName() + ".PaymentActivity.findAll");
    }

    public int insert(final PaymentActivity paymentActivity) {
        return sqlSession.insert(this.getClass().getPackageName() + ".PaymentActivity.insert", paymentActivity);
    }

}
