package io.pivotal.pal.tracker;

import java.util.*;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    private static HashMap dbMap=new HashMap<Long,TimeEntry>();
    public TimeEntry create(TimeEntry timeEntry){
        timeEntry.setId(dbMap.size());
        dbMap.put(timeEntry.getId(),timeEntry);
        return timeEntry;
    }
    public TimeEntry find(Long id){
        TimeEntry timeEntry=(dbMap.get(id)==null)? null:(TimeEntry)dbMap.get(id);
        return timeEntry;
    }

    public List<TimeEntry> list(){
        ArrayList<TimeEntry> list=new ArrayList<TimeEntry>();

        Set<Map.Entry<Long, TimeEntry>> entrySet = dbMap.entrySet();
        for (Map.Entry entry : entrySet) {
            System.out.println("------------------------------------------------");
            System.out.println("looping HashMap in Java using EntrySet and java5 for loop");
            System.out.println("key: " + entry.getKey() + " value: " + entry.getValue());
            list.add( (TimeEntry)entry.getValue());
        }
        return list;
    }



    public TimeEntry update(Long id,TimeEntry timeEntry){

        TimeEntry timeEntryFromRepo=(dbMap.get(id)==null)? null:(TimeEntry)dbMap.get(id);
        if(timeEntryFromRepo!=null){
            dbMap.put(id,timeEntry);
        }
        return timeEntryFromRepo;
    }

    public TimeEntry delete(Long id){

        TimeEntry timeEntryFromRepo=(dbMap.get(id)==null)? null:(TimeEntry)dbMap.get(id);
        if(timeEntryFromRepo!=null){
            dbMap.remove(id);
        }
        return timeEntryFromRepo;
    }
}


        /*extends
        JpaRepository<TimeEntry, Long> {
}
*/