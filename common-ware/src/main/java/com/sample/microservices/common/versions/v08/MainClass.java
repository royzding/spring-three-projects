package com.sample.microservices.common.versions.v08;

import static com.sample.microservices.common.versions.v08.CollectorsJoining.collectorsJoining;
import static com.sample.microservices.common.versions.v08.FilterList.filterNullFromList;
import static com.sample.microservices.common.versions.v08.SortList.sortedList;
import static com.sample.microservices.common.versions.v08.SortMap.sortMapByKey;
import static com.sample.microservices.common.versions.v08.StreamToCollection.streamToCollection;

public class MainClass {

    public static void main(String[] args) throws Exception {

        //listToMap();
        //listWithDuplicatedKeyToMap();
        //listSortedKeyToMap();
        //listOfListToList(ListOfListToList.getListOfList());
        //sortList();
        //filterList();

        //arrayOfArrayToList();

        //arrayOfPrimitiveArrayToList();

        //listToMapWithGroupingBy();

        //sortMapByKey();

        //collectorsJoining();

        //filterNullFromList();

        //streamToCollection();

        sortedList();

    }
}
