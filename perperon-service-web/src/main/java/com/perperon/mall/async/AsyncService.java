package com.perperon.mall.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author perperon
 * @date 2024/6/13
 * @apiNote
 */
@Service
public class AsyncService {

    // 执行耗时操作...
    @Async
    public void asyncMethod() {
        for (int i = 0; i < 10000; i++){
            System.out.println("asyncMethod:" + i);
        }
    }
}
