package com.hotel.service.util;

import com.hotel.service.entity.Hotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.core.io.ClassPathResource;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vinil jose on 21/05/16.
 * This class Loads the DataBase in CSV to Java HashMap.
 */
public class CsvLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static Map<Long,Hotel> hotelsMap = new HashMap<Long, Hotel>();
    private static Map<String,List<Hotel>> cityMap = new HashMap<String, List<Hotel>>();

    public static void loadData() throws Exception {
        LOGGER.info("-----Loading CSV Start----");
        FlatFileItemReader<Hotel> reader = new FlatFileItemReader<Hotel>();
        reader.setResource(new ClassPathResource("hoteldb.csv"));
        reader.setLineMapper(new DefaultLineMapper<Hotel>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "city", "hotelId","room", "price" });
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Hotel>() {{
                setTargetType(Hotel.class);
            }});
        }});

        reader.open(MetaDataInstanceFactory.createStepExecution().getExecutionContext());
        Hotel hotel;
        while ((hotel = reader.read()) != null) {
            LOGGER.info("Loading CSV---->"+hotel);
            hotelsMap.put(hotel.getHotelId(),hotel);
            if(cityMap.containsKey( hotel.getCity())){
                cityMap.get(hotel.getCity()).add(hotel);
            } else {
                List<Hotel> list =new ArrayList<Hotel>();
                list.add(hotel);
                cityMap.put(hotel.getCity(),list);
            }

        }
        LOGGER.info("HotelsMap---->"+hotelsMap);
        LOGGER.info("City Map---->"+cityMap);

    }

    public static Map<Long,Hotel> getHotelsMap(){
        return hotelsMap;
    }

    public static  Map<String,List<Hotel>> getCityMap(){
        return cityMap;
    }
}
