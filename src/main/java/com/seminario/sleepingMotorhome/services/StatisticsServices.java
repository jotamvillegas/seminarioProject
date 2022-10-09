package com.seminario.sleepingMotorhome.services;

import com.seminario.sleepingMotorhome.repositories.MotorhomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticsServices {

    @Autowired
    private MotorhomeRepository motorhomeRepository;

    public List<Integer> chartMotorhomeStayPerMonth(){
        List<Integer> totalByMonth = new ArrayList<>();
        for (int i = 1; i < 13; i++){
            String mes;
            if (String.valueOf(i).length() == 1) mes = "0" + i;
            else mes = String.valueOf(i);
            if (mes.equals("02"))
                totalByMonth.add( motorhomeRepository.motorhomeListInAMonth("2022-" + mes + "-01", "2022-" + mes + "-28") );
            else
                totalByMonth.add( motorhomeRepository.motorhomeListInAMonth("2022-" + mes + "-01", "2022-" + mes + "-30") );
        }
        return totalByMonth;
    }

    public List<Integer> chartMotorhomeStayPerMonthAndMotorhomeType(int typeId){
        List<Integer> totalByMonth = new ArrayList<>();
        for (int i = 1; i < 13; i++){
            String mes;
            if (String.valueOf(i).length() == 1) mes = "0" + i;
            else mes = String.valueOf(i);
            if (mes.equals("02"))
                totalByMonth.add( motorhomeRepository.motorhomesByMonthAndType("2022-" + mes + "-01", "2022-" + mes + "-28", typeId) );
            else
                totalByMonth.add( motorhomeRepository.motorhomesByMonthAndType("2022-" + mes + "-01", "2022-" + mes + "-30", typeId) );
        }
        return totalByMonth;
    }


}
