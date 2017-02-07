package com.example.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Friendship")
public class FriendshipBean {
	
	public enum FriendshipStatus {
        PENDING,
        ACCEPTED,
        DECLINED
    }
	
	@Id
	@GeneratedValue
	private Long id;

    @Enumerated(EnumType.STRING)
    private FriendshipStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "originator_id", nullable = false)
    private UserBean originator;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recipient_id", nullable = false)
    private UserBean recipient;

    public FriendshipBean() {
    }

    public FriendshipBean(UserBean originator, UserBean recipient) {
        this.status = FriendshipStatus.PENDING;
        this.originator = originator;
        this.recipient = recipient;
    }
    
    public Long getId() {
        return id;
    }

    public FriendshipStatus getStatus() {
        return status;
    }

    public void setStatus(FriendshipStatus status) {
        this.status = status;
    }

    public UserBean getOriginator() {
        return originator;
    }

    public void setOriginator(UserBean originator) {
        this.originator = originator;
    }

    public UserBean getRecipient() {
        return recipient;
    }

    public void setRecipient(UserBean recipient) {
        this.recipient = recipient;
    }

    @Override
    public String toString() {
        return "Friendship{" +
                "status=" + status +
                ", originator=" + originator +
                ", recipient=" + recipient +
                '}';
    }
}
