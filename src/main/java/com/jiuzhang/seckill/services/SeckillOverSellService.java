package com.jiuzhang.seckill.services;

import com.jiuzhang.seckill.db.dao.SeckillActivityDao;
import com.jiuzhang.seckill.db.po.SeckillActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeckillOverSellService {

    @Autowired
    private SeckillActivityDao seckillActivityDao;

    /** 库存扣减的简单处理办法 **/
    public String processSeckill(long activityId) {
        SeckillActivity seckillActivity = seckillActivityDao.querySeckillActivityById(activityId);
        int availableStock = seckillActivity.getAvailableStock();
        String result;

        if (availableStock > 0) {
            result = "恭喜，抢购成功";
            System.out.println(result);
            availableStock -= 1;
            seckillActivity.setAvailableStock(availableStock);
            seckillActivityDao.updateSeckillActivity(seckillActivity);
        } else {
            result = "抱歉，抢购失败，商品被抢完了";
            System.out.println(result);
        }

        return result;

    }
}
