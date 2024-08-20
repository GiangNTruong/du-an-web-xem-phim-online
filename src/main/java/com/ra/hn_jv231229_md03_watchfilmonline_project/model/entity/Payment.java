package com.ra.hn_jv231229_md03_watchfilmonline_project.model.entity;

import com.ra.hn_jv231229_md03_watchfilmonline_project.model.constant.SubscriptionType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "payment")
public class Payment
{
    @Id
    @Column(name = "payment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    @Column(name = "payment_date")
    private Date paymentDate;
    @Column(name = "subscription_type")
    private SubscriptionType subscriptionType;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    public Payment()
    {
    }

    public Payment(Date paymentDate, Long paymentId, SubscriptionType subscriptionType, User user)
    {
        this.paymentDate = paymentDate;
        this.paymentId = paymentId;
        this.subscriptionType = subscriptionType;
        this.user = user;
    }

    public Date getPaymentDate()
    {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate)
    {
        this.paymentDate = paymentDate;
    }

    public Long getPaymentId()
    {
        return paymentId;
    }

    public void setPaymentId(Long paymentId)
    {
        this.paymentId = paymentId;
    }

    public SubscriptionType getSubscriptionType()
    {
        return subscriptionType;
    }

    public void setSubscriptionType(SubscriptionType subscriptionType)
    {
        this.subscriptionType = subscriptionType;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}
