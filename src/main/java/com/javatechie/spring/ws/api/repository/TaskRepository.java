package com.javatechie.spring.ws.api.repository;

import com.javatechie.spring.ws.api.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TaskRepository extends JpaRepository<Task,Long> {
    @Query(value = "SELECT * FROM task where  company_id= :companyId and pos_name= :deviceId and tracking_id=:trackingId and (branch_id=:branchId or branch_id=0)  order by ak ASC limit :offset, :count " , nativeQuery = true)
    List<Task> getUserId(@Param("companyId") long companyId, @Param("deviceId") long deviceId, @Param("trackingId") long trackingId, @Param("branchId") long branchId, @Param("offset") long offset, @Param("count") long count);


    @Query(value = "SELECT * FROM task where  company_id= :companyId and pos_name= :deviceId and tracking_id=:trackingId  and (branch_id=:branchId or branch_id=0) and message_type='AddProduct' order by ak DESC limit :offset, :count " , nativeQuery = true)
    List<Task> getSyncProduct(@Param("companyId") long companyId, @Param("deviceId") long deviceId, @Param("trackingId") long trackingId, @Param("branchId") long branchId, @Param("offset") long offset, @Param("count") long count);

    @Query(value = "SELECT * FROM task where  company_id= :companyId and pos_name= :deviceId and tracking_id=:trackingId  and (branch_id=:branchId or branch_id=0) and message_type in('AddScheduleWorker','UpdateScheduleWorker') order by ak DESC limit :offset, :count " , nativeQuery = true)
    List<Task> getSyncScheduleWorker(@Param("companyId") long companyId, @Param("deviceId") long deviceId, @Param("trackingId") long trackingId, @Param("branchId") long branchId, @Param("offset") long offset, @Param("count") long count);



    @Query(value = "SELECT count(*) FROM task where  company_id= :companyId and pos_name= :deviceId and message_type='AddProduct' and (branch_id=:branchId or branch_id=0) " , nativeQuery = true)
    long getCountOfProductSync(@Param("companyId") long companyId, @Param("deviceId") long deviceId, @Param("branchId") long branchId);
    @Modifying
    @Query(value = "delete from task where  company_id= :companyId and pos_name= :deviceId and message_type='AddProduct' and (branch_id=:branchId or branch_id=0) " , nativeQuery = true)
    int deleteProductSync(@Param("companyId") long companyId, @Param("deviceId") long deviceId, @Param("branchId") long branchId);


    @Query(value = "SELECT ak FROM task where  company_id= :companyId and pos_name= :deviceId and status=:status and (branch_id=:branchId or branch_id=0)  and tracking_id NOT IN ( 0 )   " , nativeQuery = true)
    List<Long> getErorrAck(@Param("companyId") long companyId, @Param("deviceId") long deviceId, @Param("status") long status, @Param("branchId") long branchId);

    @Transactional
    @Modifying
    @Query(value = "insert into task  (company_id,data,message_type,pos_name,branch_id) VALUES (?,?,?,?,?)", nativeQuery = true)
    void post(long companyId, String data, String message, long syncNumber, long branchId);
    @Transactional
    @Modifying(clearAutomatically = true)
    // @Query(value ="UPDATE task SET status=:statuse WHERE /*ak =:ak  AND*/ device_id=:posNum  ",nativeQuery = true)
    @Query(value = "UPDATE task set status=:statuse , tracking_id=:tracking  where ak =:ak AND pos_name=:posNum AND company_id=:companyId and (branch_id=:branchId or branch_id=0) ", nativeQuery = true)
    int updateStatus(@Param("statuse") String statuse, @Param("tracking") long tracking, @Param("ak") long ak, @Param("posNum") long posNum, @Param("companyId") long companyId, @Param("branchId") long branchId);

    @Modifying
    @Transactional
    @Query(value ="delete from task  where  company_id=? and pos_name=? and tracking_id=? and status=1 and (branch_id=? or branch_id=0) ",nativeQuery = true)
    int deleteByTaskId(long companyId, long posNum, long taskId, long branchId);
}