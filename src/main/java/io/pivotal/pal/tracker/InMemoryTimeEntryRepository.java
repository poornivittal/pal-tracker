package io.pivotal.pal.tracker;

import java.util.*;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    private HashMap dbMap=new HashMap<Long,TimeEntry>();

    @Override
    public TimeEntry create(TimeEntry timeEntry){
        TimeEntry createdTimeEntry = new TimeEntry(dbMap.size() + 1L, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());

        dbMap.put(createdTimeEntry.getId(), createdTimeEntry);
        return createdTimeEntry;
    }
    @Override
    public TimeEntry find(Long id){
        TimeEntry timeEntry=(dbMap.get(id)==null)? null:(TimeEntry)dbMap.get(id);
        return timeEntry;
    }
    @Override
    public List<TimeEntry> list(){

        return new ArrayList<>(dbMap.values());

    }



    @Override
    public TimeEntry update(Long id,TimeEntry timeEntry){

        TimeEntry updatedTimeEntry = new TimeEntry(id, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());

        dbMap.put(id,updatedTimeEntry);

        return updatedTimeEntry;
    }

    @Override
    public void delete(Long id){

        TimeEntry timeEntryFromRepo=(dbMap.get(id)==null)? null:(TimeEntry)dbMap.get(id);
        if(timeEntryFromRepo!=null){
            dbMap.remove(id);
        }

    }
}


        /*extends
        JpaRepository<TimeEntry, Long> {
}
*/