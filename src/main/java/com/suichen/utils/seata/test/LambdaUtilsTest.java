package com.suichen.utils.seata.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LambdaUtilsTest {
    public static void main(String[] args) {
        List<LockDO> lockDOs = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            LockDO lock = new LockDO();
            lock.setResourceId("abc");
            lock.setXid("abc-123:123");
            lock.setTransactionId(123L);
            lock.setBranchId((long) i);
            lock.setRowKey("abc-"+6);
            lock.setPk(String.valueOf(i));
            lock.setTableName("t");
            lockDOs.add(lock);
        }

        lockDOs = lockDOs.stream().filter(LambdaUtils.distinctByKey(LockDO::getRowKey))
                .collect(Collectors.toList());

        System.out.println();
    }
}
