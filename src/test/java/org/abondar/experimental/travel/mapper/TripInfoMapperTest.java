package org.abondar.experimental.travel.mapper;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TripInfoMapperTest {

    @Autowired
    private TripInfoMapper mapper;


    @Test
    public void selectTripInfoTest() {
        mapper.insertTripIds(List.of("test"));

        var res = mapper.selectRecentTripIds();

        assertTrue(res.size() > 0);
    }
}
