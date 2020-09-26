package com.assignment.filesharing.repository;


import com.assignment.filesharing.model.DBFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface FileSharingRepository extends JpaRepository<DBFile, String> {

        @Query(value = "select * from files where file_name = ?1 and user_id = ?2",nativeQuery = true)
        DBFile findDBFilesByName(String fileName, int userId);

}
