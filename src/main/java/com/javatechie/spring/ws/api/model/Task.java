package com.javatechie.spring.ws.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Created by Win8.1 on 04/06/2018.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Table(name = "task")
@EntityListeners(AuditingEntityListener.class)

public class Task {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long ak;
    @Column(length = 10000000)
    private String data;
    private String messageType;
    private long posName;

    private long companyId;
   @Column( columnDefinition = "INT(11) default 0")
   @JsonIgnore
    private Status status;

    @Column(nullable = false, columnDefinition = "bigint(20) default 0")
    @JsonIgnore
    private long trackingId ;
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    @JsonIgnore
    private Timestamp createdAt;
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @UpdateTimestamp
    @JsonIgnore
    private Timestamp updatedAt;
    @Column(columnDefinition="BIGINT DEFAULT 0")
    private int branchId;

    public enum Status{
        SYNC ("sync "),INPROGRESS("inProgress");

        private final String status;

        Status(String status)
        {
            this.status = status;
        }

        public String getStatus() {
            return this.status;
        }
    }


}
