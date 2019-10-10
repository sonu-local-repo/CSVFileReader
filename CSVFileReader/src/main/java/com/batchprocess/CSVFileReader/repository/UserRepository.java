package com.batchprocess.CSVFileReader.repository;

import com.batchprocess.CSVFileReader.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
