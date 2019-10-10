package com.batchprocess.CSVFileReader.processor;

import com.batchprocess.CSVFileReader.entity.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class BatchProcessor implements ItemProcessor <User, User> {

    private static final Map<String, String> CODE_SET = new HashMap<>();

    public BatchProcessor() {
        this.CODE_SET.put("001", "Accounts");
        this.CODE_SET.put("002", "Audit");
        this.CODE_SET.put("003", "Customer Service");
    }

    @Override
    public User process(User user) throws Exception {
        user.setDept(this.CODE_SET.get(user.getDept()));
        return new User(user.getId(), user.getName(),user.getDept());

    }
}
