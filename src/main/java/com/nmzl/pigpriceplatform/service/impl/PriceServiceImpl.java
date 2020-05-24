package com.nmzl.pigpriceplatform.service.impl;

import com.nmzl.pigpriceplatform.entity.*;
import com.nmzl.pigpriceplatform.pojo.AllTypePrice;
import com.nmzl.pigpriceplatform.pojo.ExtremePriceChart;
import com.nmzl.pigpriceplatform.pojo.PigAndChicken;
import com.nmzl.pigpriceplatform.repository.*;
import com.nmzl.pigpriceplatform.service.PriceService;
import com.nmzl.pigpriceplatform.util.Constants;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.sql.Date;
import java.text.DecimalFormat;
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

    private final ExtremePriceRepository extremePriceRepository;

    private final ChickenPriceRepository chickenPriceRepository;

    private final AllAvgPriceRepository allAvgPriceRepository;

    private final ResultRepository resultRepository;

    public PriceServiceImpl(AvgPriceRepository avgPriceRepository, PriceRepository priceRepository, AreaCodeRepository areaCodeRepository, ExtremePriceRepository extremePriceRepository, ChickenPriceRepository chickenPriceRepository, AllAvgPriceRepository allAvgPriceRepository, ResultRepository resultRepository) {
        this.avgPriceRepository = avgPriceRepository;
        this.priceRepository = priceRepository;
        this.areaCodeRepository = areaCodeRepository;
        this.extremePriceRepository = extremePriceRepository;
        this.chickenPriceRepository = chickenPriceRepository;
        this.allAvgPriceRepository = allAvgPriceRepository;
        this.resultRepository = resultRepository;
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

    @Override
    public HashMap listExtremePrice(int type) {
        /*List<ExtremePrice> list = extremePriceRepository.findAll();
        List<ExtremePriceChart> charts = new ArrayList<>();
        HashMap<String, ExtremePriceChart> map = new HashMap<>();
        for (ExtremePrice ep: list){
            if(! map.containsKey(ep.getMaxPriceDate().toString())){
                ExtremePriceChart max = new ExtremePriceChart();
                max.setDate(ep.getMaxPriceDate());
                max.setMaxPrice(ep.getMaxPrice());
                map.put(ep.getMaxPriceDate().toString(), max);
            }else {
                map.get(ep.getMaxPriceDate().toString()).setMaxPrice(ep.getMaxPrice());
            }
            if(! map.containsKey(ep.getMinPriceDate().toString())){
                ExtremePriceChart min = new ExtremePriceChart();
                min.setDate(ep.getMinPriceDate());
                min.setMinPrice(ep.getMinPrice());
                map.put(ep.getMinPriceDate().toString(), min);
            }else {
                map.get(ep.getMinPriceDate().toString()).setMinPrice(ep.getMinPrice());
            }
        }
        List<ExtremePriceChart> result = Arrays.asList((map.values().toArray(new ExtremePriceChart[0])));
        result.sort(Comparator.comparing(ExtremePriceChart::getDate));*/

        List<ExtremePrice> list = extremePriceRepository.findByType(type);
        List<ExtremePriceChart> maxCharts = new ArrayList<>();
        List<ExtremePriceChart> minCharts = new ArrayList<>();
        HashMap<String, List<ExtremePriceChart>> map = new HashMap<>();
        for (ExtremePrice ep: list){
            float maxPrice = ep.getMaxPrice();
            float minPrice = ep.getMinPrice();
            Date maxDate = ep.getMaxPriceDate();
            Date minDate = ep.getMinPriceDate();
            ExtremePriceChart c1 = new ExtremePriceChart();
            ExtremePriceChart c2 = new ExtremePriceChart();

            c1.setDate(maxDate);
            c2.setDate(minDate);
            c1.setArea(ep.getArea());
            c2.setArea(ep.getArea());
            c1.setPrice(maxPrice);
            c2.setPrice(minPrice);
            maxCharts.add(c1);
            minCharts.add(c2);
        }
        maxCharts.sort(Comparator.comparing(ExtremePriceChart::getDate));
        minCharts.sort(Comparator.comparing(ExtremePriceChart::getDate));
        map.put("最高价", maxCharts);
        map.put("最低价", minCharts);
        return map;
    }

    @Override
    public List<ChickenPrice> listChickenPrice() {
        return chickenPriceRepository.findAll();
    }

    @Override
    public List<AllAvgPrice> listAllAvgPrice() {
        List<AllAvgPrice> list = allAvgPriceRepository.findAll();
        list.sort(Comparator.comparing(AllAvgPrice::getDate));
        return list;
    }

    @Override
    public List<PigAndChicken> listPigAndChicken() {
        List<AllAvgPrice> pigList = allAvgPriceRepository.findAll();
        Date dateAfter = Date.valueOf("2019-4-14");
        Date dateBefore = Date.valueOf("2020-4-14");
        List<ChickenPrice> chickenList = chickenPriceRepository.findAllByDateAfterAndDateBefore(dateAfter, dateBefore);
        HashMap<String, PigAndChicken> map = new HashMap<>();
        for (AllAvgPrice price : pigList) {
            PigAndChicken pdc = new PigAndChicken();
            pdc.setPig(price.getPrice());
            pdc.setDate(price.getDate());
            map.put(price.getDate().toString(), pdc);
        }
        for (ChickenPrice price : chickenList) {
            if (map.containsKey(price.getDate().toString())) {
                map.get(price.getDate().toString()).setChicken(price.getPrice());
            } else {
                PigAndChicken pdc = new PigAndChicken();
                pdc.setChicken(price.getPrice());
                pdc.setDate(price.getDate());
                map.put(price.getDate().toString(), pdc);
            }
        }
        List<PigAndChicken> result = Arrays.asList(map.values().toArray(new PigAndChicken[0]));
        result.sort(Comparator.comparing(PigAndChicken::getDate));
        return result;
    }

    @Override
    public List<Result> getPigAndChickenCovariance() {
        List<Result> list = resultRepository.findAll();
        Result r = list.get(0);
        DecimalFormat df = new DecimalFormat("#.00");
        r.setValue(Double.parseDouble(df.format(r.getValue())));
        return list;
    }

}
