package com.nmzl.pigpriceplatform.service.impl;

import com.nmzl.pigpriceplatform.entity.AreaCode;
import com.nmzl.pigpriceplatform.entity.AvgPrice;
import com.nmzl.pigpriceplatform.entity.Price;
import com.nmzl.pigpriceplatform.pojo.AllTypePrice;
import com.nmzl.pigpriceplatform.repository.AreaCodeRepository;
import com.nmzl.pigpriceplatform.repository.AvgPriceRepository;
import com.nmzl.pigpriceplatform.repository.PriceRepository;
import com.nmzl.pigpriceplatform.service.PriceService;
import com.nmzl.pigpriceplatform.util.Constants;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.fusesource.leveldbjni.All;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.sql.Date;
import java.util.*;

/**
 * @author : zxy
 * @date : 2020/4/3 14:29
 */
@Service
public class PriceServiceImpl implements PriceService {
    private final AvgPriceRepository avgPriceRepository;

    private final PriceRepository priceRepository;

    private final AreaCodeRepository areaCodeRepository;

    public PriceServiceImpl(AvgPriceRepository avgPriceRepository, PriceRepository priceRepository, AreaCodeRepository areaCodeRepository) {
        this.avgPriceRepository = avgPriceRepository;
        this.priceRepository = priceRepository;
        this.areaCodeRepository = areaCodeRepository;
    }

    @Override
    public List<AvgPrice> listAvgPrice() {
        return avgPriceRepository.findAll();
    }

    @Override
    public List<AllTypePrice> listAllPrice() {
        FSDataInputStream fsr;
        BufferedReader bufferedReader = null;
        List<AllTypePrice> list = new ArrayList<>();
        String line;
        HashMap<String, AllTypePrice> map = new HashMap<>();
        try {
            FileSystem fs = FileSystem.get(URI.create(Constants.URL_HDFS), new Configuration());
            fsr = fs.open(new Path(Constants.URL_HDFS));
            bufferedReader = new BufferedReader(new InputStreamReader(fsr));
            while ((line = bufferedReader.readLine()) != null) {
                if("湖北省".equals(line.split(",")[0])){
                    byte type = Byte.parseByte(line.split(",")[2]);
                    float p = Float.parseFloat(line.split(",")[1]);
                    if (map.containsKey(line.split(",")[3])) {
                        AllTypePrice allTypePrice = map.get(line.split(",")[3]);
                        if (type == 1) {
                            allTypePrice.setPrice1(p);
                        } else if (type == 2) {
                            allTypePrice.setPrice2(p);
                        } else if (type == 3) {
                            allTypePrice.setPrice3(p);
                        }
                    }else {
                        AllTypePrice allTypePrice = new AllTypePrice();
                        if (type == 1) {
                            allTypePrice.setPrice1(p);
                        } else if (type == 2) {
                            allTypePrice.setPrice2(p);
                        } else if (type == 3) {
                            allTypePrice.setPrice3(p);
                        }
                        allTypePrice.setDate(Date.valueOf(line.split(",")[3]));
                        map.put(line.split(",")[3], allTypePrice);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        ArrayList<AllTypePrice> resultList = new ArrayList<AllTypePrice>(map.values());
        resultList.sort(new Comparator<AllTypePrice>() {
            @Override
            public int compare(AllTypePrice o1, AllTypePrice o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        return resultList;
    }

    @Override
    public List<Price> insertAllPrice() {
        FSDataInputStream fsr;
        BufferedReader bufferedReader = null;
        List<Price> list = new ArrayList<>();
        String line;
        HashMap<String, Integer> map = new HashMap<>();
        List<AreaCode> listCode = areaCodeRepository.findAll();
        for (AreaCode code : listCode) {
            map.put(code.getName(), code.getCode());
        }
        try {
            FileSystem fs = FileSystem.get(URI.create(Constants.URL_HDFS), new Configuration());
            fsr = fs.open(new Path(Constants.URL_HDFS));
            bufferedReader = new BufferedReader(new InputStreamReader(fsr));
            while ((line = bufferedReader.readLine()) != null) {
                Price price = new Price();
                String[] itemList = line.split(",");
                price.setAreaCode(map.get(itemList[0]));
                price.setPrice(Float.parseFloat(itemList[1]));
                price.setType(Byte.parseByte(itemList[2]));
                price.setDate(Date.valueOf(itemList[3]));
                list.add(price);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return priceRepository.saveAll(list);
    }

    @Override
    public List<AllTypePrice> listAllPrice(int code) {
        List<Price> priceList = priceRepository.getByAreaCode(code);
        HashMap<String, AllTypePrice> hashMap = new HashMap<>();
        for (Price price : priceList) {
            if (hashMap.containsKey(price.getDate().toString())) {
                AllTypePrice allTypePrice = hashMap.get(price.getDate().toString());
                switch (price.getType()) {
                    case 1:
                        allTypePrice.setPrice1(price.getPrice());
                        break;
                    case 2:
                        allTypePrice.setPrice2(price.getPrice());
                        break;
                    case 3:
                        allTypePrice.setPrice3(price.getPrice());
                }
            } else {
                AllTypePrice allTypePrice = new AllTypePrice();
                allTypePrice.setDate(price.getDate());
                switch (price.getType()) {
                    case 1:
                        allTypePrice.setPrice1(price.getPrice());
                        break;
                    case 2:
                        allTypePrice.setPrice2(price.getPrice());
                        break;
                    case 3:
                        allTypePrice.setPrice3(price.getPrice());
                        break;
                }
                hashMap.put(price.getDate().toString() , allTypePrice);
            }
        }
        List<AllTypePrice> resultList = new ArrayList<AllTypePrice>(hashMap.values());
        resultList.sort(Comparator.comparing(AllTypePrice::getDate));
        return resultList;
    }
}
